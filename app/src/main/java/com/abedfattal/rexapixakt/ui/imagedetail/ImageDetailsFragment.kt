package com.abedfattal.rexapixakt.ui.imagedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.abedfattal.rexapixakt.databinding.FragmentImageDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ImageDetailsFragment : Fragment() {

    private lateinit var binding: FragmentImageDetailsBinding
    private val args: ImageDetailsFragmentArgs by navArgs()

    private val vm: ImageDetailsViewModel by viewModel()
    private val image by lazy { args.image }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
        binding.vm = vm
        binding.image = image.withFormattedTags()
        binding.lifecycleOwner = viewLifecycleOwner

        vm.popBackEvent.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        return binding.root
    }


}