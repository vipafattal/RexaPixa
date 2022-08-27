package com.abedfattal.rexapixakt.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.abedfattal.rexapixakt.R
import com.abedfattal.rexapixakt.databinding.FragmentHomeBinding
import com.abedfattal.rexapixakt.models.ProcessState
import com.abedfattal.rexapixakt.ui.HomeViewModel
import com.abedfattal.rexapixakt.utils.shortToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private val imagesListAdapter = ImagesListAdapter()
    private val vm: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.imagesRecycler.adapter = imagesListAdapter
        binding.lifecycleOwner = viewLifecycleOwner
        vm.getImages().observe(viewLifecycleOwner){process->
            when(process){
                is ProcessState.Loading -> shortToast(requireContext(),R.string.loading)
                is ProcessState.Success -> {
                    shortToast(requireContext(),process.data!!.size.toString())
                    Log.d("DATA_LOADED",process.data.toString())
                    imagesListAdapter.submitList(process.data)
                }
                is ProcessState.Failed -> {
                    shortToast(requireContext(),process.error ?: "Error")
                    Log.d("DATA_LOADED",process.error ?: "")
                }
            }
        }
        return binding.root
    }


}