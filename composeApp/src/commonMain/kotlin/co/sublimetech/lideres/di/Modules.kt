package co.sublimetech.lideres.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import co.sublimetech.lideres.app.MainViewModel
import co.sublimetech.lideres.authentication.data.AuthRepositoryImpl
import co.sublimetech.lideres.authentication.domain.AuthRepositoryInterface
import co.sublimetech.lideres.authentication.presentation.login.LoginViewModel
import co.sublimetech.lideres.form.data.FormRepositoryImpl
import co.sublimetech.lideres.form.data.database.DatabaseFactory
import co.sublimetech.lideres.form.data.database.FormDatabase
import co.sublimetech.lideres.form.domain.FormRepositoryInterface
import co.sublimetech.lideres.form.presentation.FormViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    singleOf(::AuthRepositoryImpl).bind<AuthRepositoryInterface>()
    singleOf(::FormRepositoryImpl).bind<FormRepositoryInterface>()

    single { get<FormDatabase>().formDao }

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    viewModelOf(::MainViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::FormViewModel)

}
