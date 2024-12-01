package co.sublimetech.lideres

import android.app.Application
import co.sublimetech.lideres.di.initKoin
import org.koin.android.ext.koin.androidContext

class LideresApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@LideresApplication)
        }
    }
}