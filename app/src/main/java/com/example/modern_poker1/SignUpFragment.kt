package com.example.modern_poker1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val usernameInput = view.findViewById<TextView>(R.id.usernameIn)
        val emailInput = view.findViewById<TextView>(R.id.emailIn)
        val passwordInput = view.findViewById<TextView>(R.id.password_in)
        val registerButton = view.findViewById<AppCompatButton>(R.id.register_button)
        val signInText = view.findViewById<TextView>(R.id.signinText)



        registerButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                findNavController().navigate(R.id.action_signUpFragment_to_avatarScreenFragment)

            } else {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
        signInText.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_login_page3)
        }
        return view
    }


}