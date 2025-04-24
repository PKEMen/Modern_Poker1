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
import com.google.firebase.firestore.FirebaseFirestore


class Login_page : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login_page, container, false)

        val usernameInput = view.findViewById<EditText>(R.id.usernameInput)
        val passwordInput = view.findViewById<EditText>(R.id.passwordInput)
        val loginButton = view.findViewById<AppCompatButton>(R.id.login_button)
        val signUpText = view.findViewById<TextView>(R.id.signupText1)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(),
                    "Please enter username and password",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 1) fetch the email for this username
            firestore.collection("usernames")
                .document(username)
                .get()
                .addOnSuccessListener { doc ->
                    if (!doc.exists()) {
                        Toast.makeText(requireContext(),
                            "Username not found",
                            Toast.LENGTH_SHORT).show()
                        return@addOnSuccessListener
                    }
                    val email = doc.getString("email")!!

                    // 2) now sign in with email/password
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                findNavController()
                                    .navigate(R.id.action_login_page3_to_mainScreenFragment)
                            } else {
                                Toast.makeText(requireContext(),
                                    task.exception?.localizedMessage ?: "Login failed",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(),
                        "Error looking up username",
                        Toast.LENGTH_SHORT).show()
                }
        }

        signUpText.setOnClickListener {
            findNavController().navigate(R.id.action_login_page3_to_signUpFragment)
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        // skip if already signed in
        auth.currentUser?.let {
            findNavController().navigate(R.id.action_login_page3_to_mainScreenFragment)
        }
    }
}