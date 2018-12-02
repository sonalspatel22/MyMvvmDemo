package mvvmdemo.com.mvvmdemo.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Provides
    fun provideApplication(): Application {
        return app
    }

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

}
