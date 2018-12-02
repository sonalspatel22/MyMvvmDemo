package com.rjmingletainment.ui.login.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import mvvmdemo.com.mvvmdemo.main.view.MainActivity
import mvvmdemo.com.mvvmdemo.Auth.viewmodel.LoginViewModel


import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_login.*
import mvvmdemo.com.mvvmdemo.R
import mvvmdemo.com.mvvmdemo.base.BaseActivity
import mvvmdemo.com.mvvmdemo.base.rxjava.autoDispose
import mvvmdemo.com.mvvmdemo.base.ui.throttleClicks
import mvvmdemo.com.mvvmdemo.di.AppComponent
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: LoginViewModel

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        AppComponent.component.inject(this)
//        getToken()


        viewModel.startMainScreen.observeOn(AndroidSchedulers.mainThread()).subscribe {
            startActivity(MainActivity.getIntent(this, ""))
        }.autoDispose(compositeDisposable)

        viewModel.error.observeOn(AndroidSchedulers.mainThread()).subscribe {
            showToast(it)
        }.autoDispose(compositeDisposable)

        viewModel.isLoading.subscribe { allowToShowLoader ->
            if (allowToShowLoader) {
                showProgress(getString(R.string.msg_loading))
            } else {
                hideProgress()
            }
        }.autoDispose(compositeDisposable)
        login.throttleClicks().observeOn(AndroidSchedulers.mainThread()).subscribe {
            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()
            when {
                email.isEmpty() -> loginEmail.error = "Please enter email"
                password.isEmpty() -> loginPassword.error = "Please enter password"
                else -> viewModel.loginWithEmailPassword(resources.getString(R.string.netConnectionError), email, password, "android", "1")
            }
        }.autoDispose(compositeDisposable)
    }

//    private fun getToken() {
//        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(this, OnSuccessListener<InstanceIdResult> { instanceIdResult ->
//            val token = instanceIdResult.token
//            val pref = AppPreferencesHelper(this, AppConstants.PREF_NAME)
//            pref.fcmToken = token
//        })
//
//
//    }
}