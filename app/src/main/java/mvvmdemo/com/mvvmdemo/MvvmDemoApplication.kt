package mvvmdemo.com.mvvmdemo

import android.app.Application
import mvvmdemo.com.mvvmdemo.di.AppComponent
import timber.log.Timber

class MvvmDemoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppComponent.default(this)
        AppComponent.component.inject(this)
        Timber.plant(Timber.DebugTree())
    }
}