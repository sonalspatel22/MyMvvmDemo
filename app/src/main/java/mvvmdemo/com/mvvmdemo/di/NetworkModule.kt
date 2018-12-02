package mvvmdemo.com.mvvmdemo.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context, isDebug:Boolean):OkHttpClient{

        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cacheDir = File(context.cacheDir, "HttpCache")
        val cache = Cache(cacheDir, cacheSize.toLong())

        val builder = OkHttpClient.Builder().readTimeout(10,TimeUnit.SECONDS).connectTimeout(10,TimeUnit.SECONDS).cache(cache)

        if(isDebug){
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
            builder.addInterceptor(object :Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request().newBuilder().addHeader("headerkey","headervalue").build()
                    return chain.proceed(request)
                }
            })
        }

        return builder.build()
    }







}
