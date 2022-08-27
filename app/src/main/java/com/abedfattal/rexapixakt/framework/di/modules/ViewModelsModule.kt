package com.abedfattal.rexapixakt.framework.di.modules

import com.abedfattal.rexapixakt.ui.HomeViewModel
import com.abedfattal.rexapixakt.ui.newuser.register.RegisterViewModel
import com.abedfattal.rexapixakt.ui.newuser.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        LoginViewModel(userRepository = get())
    }
    viewModel {
        RegisterViewModel(userRepository = get())
    }
    viewModel {
        HomeViewModel(imagesRepository = get())
    }
}