package mvvmdemo.com.mvvmdemo.di

import android.app.Application
import android.content.Context
import dagger.Component
import mvvmdemo.com.mvvmdemo.FrameworkApplication
import javax.inject.Singleton

@Component(modules = [AppModule::class,NetworkModule::class])
@Singleton
interface AppComponent : BaseAppComponent {

    companion object {
        lateinit var component: AppComponent
        fun default(app:Application){
            component = DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .networkModule(NetworkModule())
                .build()
        }
    }

    fun inject(frameworkApplication: FrameworkApplication)


}


interface BaseAppComponent {

    fun application(): Application

    fun context(): Context
}