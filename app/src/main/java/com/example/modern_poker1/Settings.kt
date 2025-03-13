package com.example.modern_poker1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class Settings : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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