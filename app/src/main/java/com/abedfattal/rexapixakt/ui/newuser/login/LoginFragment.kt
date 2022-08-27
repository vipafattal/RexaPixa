package com.abedfattal.rexapixakt.ui.newuser.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.abedfattal.rexapixakt.R
import com.abedfattal.rexapixakt.databinding.FragmentLoginBinding
import com.abedfattal.rexapixakt.models.ProcessState
import com.abedfattal.rexapixakt.utils.EventObserver
import com.abedfattal.rexapixakt.utils.hideKeyboard
import com.abedfattal.rexapixakt.utils.shortToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val vm: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = vm

        vm.closeKeyboardEvent.observe(viewLifecycleOwner, EventObserver {
            binding.root.hideKeyboard()
        })
        vm.submitProcess.observe(viewLifecycleOwner) { process ->
            when (process) {
                is ProcessState.Failed -> shortToast(requireContext(), process.friendlyMsg)
                is ProcessState.Loading -> shortToast(requireContext(), R.string.registering_user)
                is ProcessState.Success -> shortToast(requireContext(), R.string.register)
            }
        }

        return binding.root
    }


}