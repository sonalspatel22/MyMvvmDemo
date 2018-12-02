package mvvmdemo.com.mvvmdemo

import android.app.Application
import mvvmdemo.com.mvvmdemo.di.AppComponent

class FrameworkApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppComponent.default(this)

    }
}