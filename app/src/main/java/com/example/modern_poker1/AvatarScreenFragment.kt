package com.example.modern_poker1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class AvatarScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_avatar_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Back button to go to HomeFragment
        val continueIcon: androidx.appcompat.widget.AppCompatButton = view.findViewById(R.id.continueSelect)
        continueIcon.setOnClickListener {
            findNavController().navigate(R.id.action_avatarScreenFragment_to_mainScreenFragment)
        }


    }

}