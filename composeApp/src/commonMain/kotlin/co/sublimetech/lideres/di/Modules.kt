package co.sublimetech.lideres.di

import co.sublimetech.lideres.app.MainViewModel
import co.sublimetech.lideres.authentication.data.AuthRepositoryImpl
import co.sublimetech.lideres.authentication.domain.AuthRepositoryInterface
import co.sublimetech.lideres.authentication.presentation.login.LoginViewModel
import co.sublimetech.lideres.authentication.presentation.registration.RegisterViewModel
import co.sublimetech.lideres.form.presentation.FormViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    singleOf(::AuthRepositoryImpl).bind<AuthRepositoryInterface>()


    viewModelOf(::MainViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::FormViewModel)

}
