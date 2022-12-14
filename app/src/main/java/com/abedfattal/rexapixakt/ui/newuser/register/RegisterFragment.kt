package com.abedfattal.rexapixakt.ui.newuser.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abedfattal.rexapixakt.R
import com.abedfattal.rexapixakt.databinding.FragmentRegisterBinding
import com.abedfattal.rexapixakt.models.ProcessState
import com.abedfattal.rexapixakt.ui.MainActivity
import com.abedfattal.rexapixakt.utils.EventObserver
import com.abedfattal.rexapixakt.utils.hideKeyboard
import com.abedfattal.rexapixakt.utils.launchActivity
import com.abedfattal.rexapixakt.utils.shortToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private lateinit var binding : FragmentRegisterBinding
    private val vm: RegisterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = vm

        vm.popBackEvent.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        vm.closeKeyboardEvent.observe(viewLifecycleOwner, EventObserver {
              binding.root.hideKeyboard()
        })

        vm.submitProcess.observe(viewLifecycleOwner) { process ->
            when (process) {
                is ProcessState.Failed -> shortToast(requireContext(), process.friendlyMsg)
                is ProcessState.Loading -> shortToast(requireContext(), R.string.registering_user)
                is ProcessState.Success -> {
                    shortToast(requireContext(), getString(R.string.registered))
                    openMainActivity()
                }
            }
        }

        return binding.root
    }

    private fun openMainActivity() {
        requireActivity().apply {
            launchActivity<MainActivity>()
            finish()
        }
    }
}