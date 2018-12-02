package mvvmdemo.com.mvvmdemo.splash.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.rjmingletainment.ui.login.view.LoginActivity
import mvvmdemo.com.mvvmdemo.main.view.MainActivity
import com.rjmingletainment.ui.splash.viewmodel.SplashViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import mvvmdemo.com.mvvmdemo.R
import mvvmdemo.com.mvvmdemo.base.BaseActivity
import mvvmdemo.com.mvvmdemo.base.rxjava.autoDispose
import mvvmdemo.com.mvvmdemo.di.AppComponent
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    companion object {
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, SplashActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        AppComponent.component.inject(this)

        splashViewModel.isLogin.observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it) {
                startActivity(
                    MainActivity.getIntent(this, ""))
                finish()
            } else {
                startActivity(LoginActivity.getIntent(this))
                finish()
            }
        }.autoDispose(compositeDisposable)

    }
}