package com.example.modern_poker1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController



/**
 * A simple [Fragment] subclass.
 * Use the [MainScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainScreenFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val avatarIcon: ImageView = view.findViewById(R.id.settingIcon)
        avatarIcon.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreenFragment_to_settings)
        }

        val playButton = view.findViewById<AppCompatButton>(R.id.PLAY2_button)

        playButton.setOnClickListener{
            findNavController().navigate(R.id.action_mainScreenFragment_to_poker_UI)
        }

    }
}