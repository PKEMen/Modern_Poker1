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


class Login_page : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login_page, container, false)

        val usernameInput = view.findViewById<TextView>(R.id.usernameIn)
        val passwordInput = view.findViewById<TextView>(R.id.password_in)
        val loginButton = view.findViewById<AppCompatButton>(R.id.login_button)
        val signUpText = view.findViewById<TextView>(R.id.signupText1)



        loginButton.setOnClickListener {
            val email = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
               findNavController().navigate(R.id.action_login_page3_to_mainScreenFragment)

            } else {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
        signUpText.setOnClickListener {
            findNavController().navigate(R.id.action_login_page3_to_signUpFragment)
        }
        return view
    }


}