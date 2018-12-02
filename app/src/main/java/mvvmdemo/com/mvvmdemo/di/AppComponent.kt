package mvvmdemo.com.mvvmdemo.di

import android.app.Application
import android.content.Context
import com.rjmingletainment.ui.login.view.LoginActivity

import mvvmdemo.com.mvvmdemo.base.BaseActivity
import dagger.Component
import mvvmdemo.com.mvvmdemo.ApiService
import mvvmdemo.com.mvvmdemo.MvvmDemoApplication
import mvvmdemo.com.mvvmdemo.dashboard.view.DashboardFragment
import mvvmdemo.com.mvvmdemo.main.view.MainActivity
import mvvmdemo.com.mvvmdemo.matches.view.MatchUserActivity
import javax.inject.Singleton

@Component(modules = [AppModule::class,NetworkModule::class])
@Singleton
interface AppComponent : BaseAppComponent {

    companion object {
        lateinit var component: AppComponent
        fun default(app:Application){
            component = DaggerAppComponent.builder()
                .appModule(AppModule(app,true))
                .networkModule(NetworkModule())
                .build()
        }
    }

    fun inject(mvvmDemoApplication: MvvmDemoApplication)

    fun inject(baseActivity: BaseActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(dashboardFragment: DashboardFragment)

    fun inject(matchActivity: MatchUserActivity)

    fun inject(loginActivity: LoginActivity)

}


interface BaseAppComponent {

    fun application(): Application

    fun context(): Context

    fun apiservice(): ApiService
}