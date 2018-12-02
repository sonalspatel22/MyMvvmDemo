package mvvmdemo.com.mvvmdemo.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import mvvmdemo.com.mvvmdemo.AppConstants
import javax.inject.Singleton

@Module
class AppModule(private val app: Application,private val isDebug:Boolean) {
    @Provides
    fun provideApplication(): Application {
        return app
    }

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideIsDebug(): Boolean {
        return isDebug
    }

    @Provides
    @PreferenceInfo
    fun providePreferenceName(): String {
        return AppConstants.PREF_NAME
    }
}
