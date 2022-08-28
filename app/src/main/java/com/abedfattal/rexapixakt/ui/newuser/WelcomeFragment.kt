package com.abedfattal.rexapixakt.ui.newuser

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.abedfattal.rexapixakt.R
import com.abedfattal.rexapixakt.databinding.FragmentWelcomeBinding
import com.abedfattal.rexapixakt.utils.colorOf


class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as Activity).window.statusBarColor = colorOf(R.color.dark_background_color)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as Activity).window.statusBarColor = colorOf(R.color.status_bar_color)
    }

}