package com.example.modern_poker1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore


class SignUpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        auth      = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val usernameInput = view.findViewById<EditText>(R.id.usernameInput) // <- Fix ID too
        val emailInput = view.findViewById<EditText>(R.id.emailInput)
        val passwordInput = view.findViewById<EditText>(R.id.passwordInput)
        val registerButton = view.findViewById<AppCompatButton>(R.id.register_button)
        val signInText = view.findViewById<TextView>(R.id.signinText)



        registerButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(),
                    "Username, email & password are all required",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { createTask ->
                    if (!createTask.isSuccessful) {
                        Toast.makeText(requireContext(),
                            createTask.exception?.localizedMessage ?: "Registration failed",
                            Toast.LENGTH_SHORT).show()
                        return@addOnCompleteListener
                    }

                    // 1) set display name

                    val user = auth.currentUser
                    if (user == null) {
                        Toast.makeText(requireContext(), "User not logged in after sign up", Toast.LENGTH_SHORT).show()
                        return@addOnCompleteListener
                    }

                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(username)
                        .build()

                    user.updateProfile(profileUpdates)
                        .addOnCompleteListener { profileTask ->
                            if (!profileTask.isSuccessful) {
                                Toast.makeText(requireContext(),
                                    profileTask.exception?.localizedMessage ?: "Failed to set username",
                                    Toast.LENGTH_SHORT).show()
                                return@addOnCompleteListener
                            }

                            firestore.collection("usernames")
                                .document(username)
                                .set(mapOf("email" to email))
                                .addOnCompleteListener { mapTask ->
                                    if (!mapTask.isSuccessful) {
                                        Toast.makeText(requireContext(),
                                            "Could not save username mapping.",
                                            Toast.LENGTH_SHORT).show()
                                        return@addOnCompleteListener
                                    }

                                    // ✅ ALL TASKS SUCCESS → go to avatar screen
                                    findNavController().navigate(R.id.action_signUpFragment_to_avatarScreenFragment)
                                }
                        }
                }
        }

        signInText.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_login_page3)
        }

        return view

    }


}