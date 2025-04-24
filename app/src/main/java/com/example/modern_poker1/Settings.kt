package com.example.modern_poker1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class Settings : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        auth      = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameView = view.findViewById<TextView>(R.id.settings_username)
        val emailView = view.findViewById<TextView>(R.id.settings_email)

        val user = auth.currentUser
        if (user != null) {
            val email = user.email
            emailView.text = email ?: "No email found"

            // Look for matching username in Firestore
            firestore.collection("usernames")
                .get()
                .addOnSuccessListener { snapshot ->
                    for (doc in snapshot) {
                        if (doc.getString("email") == email) {
                            usernameView.text = doc.id
                            break
                        }
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Failed to fetch username", Toast.LENGTH_SHORT)
                        .show()
                }
        }

        // Back button to go to HomeFragment
        val backIcon: ImageView = view.findViewById(R.id.back_icon)
        backIcon.setOnClickListener {
            findNavController().navigateUp() // Navigates back to HomeFragment
        }

        val retToMainIcon: ImageView = view.findViewById(R.id.ret_main_menu)
        retToMainIcon.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_mainScreenFragment)
        }


        // Logout button to go to LoginFragment
        val logoutButton: ImageView = view.findViewById(R.id.logout)
        logoutButton.setOnClickListener {
            auth.signOut()
            findNavController().navigate(R.id.action_settings_to_login_page3)
        }

        val exitButton: ImageView = view.findViewById(R.id.close_app)
        exitButton.setOnClickListener {

        }

        val editavatarButton: androidx.appcompat.widget.AppCompatButton = view.findViewById(R.id.edit_avatar_button)
        editavatarButton.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_avatarScreenFragment)
        }
    }

}