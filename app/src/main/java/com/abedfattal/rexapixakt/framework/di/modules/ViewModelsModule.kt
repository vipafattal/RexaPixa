package com.abedfattal.rexapixakt.framework.di.modules

import com.abedfattal.rexapixakt.ui.home.HomeViewModel
import com.abedfattal.rexapixakt.ui.imagedetail.ImageDetailsViewModel
import com.abedfattal.rexapixakt.ui.myaccount.MyAccountViewModel
import com.abedfattal.rexapixakt.ui.newuser.login.LoginViewModel
import com.abedfattal.rexapixakt.ui.newuser.register.RegisterViewModel
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
    viewModel {
        ImageDetailsViewModel()
    }
    viewModel {
        MyAccountViewModel()
    }
}