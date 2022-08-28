package com.abedfattal.rexapixakt.ui.myaccount

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.abedfattal.rexapixakt.databinding.DialogMyAccountBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by ${User} on ${Date}
 */
class MyAccountDialog : DialogFragment() {

    private lateinit var binding: DialogMyAccountBinding
    private val vm: MyAccountViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogMyAccountBinding.inflate(inflater, container, false)

        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()

        applyDialogTransparency()

        return binding.root
    }

    private fun applyDialogTransparency() {
        dialog?.run {
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

}