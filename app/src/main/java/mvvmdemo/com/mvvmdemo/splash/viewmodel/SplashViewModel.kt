package com.rjmingletainment.ui.splash.viewmodel

import com.rjmingletainment.base.BaseViewModel
import com.rjmingletainment.util.preference.AppPreferencesHelper
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class SplashViewModel(private val appPreferencesHelper: AppPreferencesHelper) : BaseViewModel() {

    private val isLoginSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()
    var isLogin: Observable<Boolean> = isLoginSubject.hide()


    init {
        val rjId = appPreferencesHelper.rjId
        if (rjId.isEmpty() || rjId.isBlank()) {
            isLoginSubject.onNext(false)
        } else {
            isLoginSubject.onNext(true)
        }
    }

}