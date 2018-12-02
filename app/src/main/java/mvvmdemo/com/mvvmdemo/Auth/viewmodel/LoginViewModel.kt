package mvvmdemo.com.mvvmdemo.Auth.viewmodel


import mvvmdemo.com.mvvmdemo.preference.AppPreferencesHelper
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import mvvmdemo.com.mvvmdemo.AppConstants
import mvvmdemo.com.mvvmdemo.AuthRepository
import mvvmdemo.com.mvvmdemo.base.BaseViewModel
import mvvmdemo.com.mvvmdemo.base.rxjava.autoDispose


class LoginViewModel(private val authRepository: AuthRepository, private val appPreferencesHelper: AppPreferencesHelper) : BaseViewModel() {

    private val startMainScreenSubject: PublishSubject<Unit> = PublishSubject.create()
    var startMainScreen: Observable<Unit> = startMainScreenSubject.hide()

    fun loginWithEmailPassword(networkError: String, email: String, password: String, deviceType: String, deviceId: String) {
        val fcmToken = appPreferencesHelper.fcmToken
        if (fcmToken.isEmpty()) {
            errorSubject.onNext(networkError)
            return
        }
        isLoadingSubject.onNext(true)
        authRepository.login(email, password, fcmToken, deviceType, deviceId).observeOn(Schedulers.io()).subscribe({ loginResult ->
            isLoadingSubject.onNext(false)
            if (loginResult.code == AppConstants.SUCCESS) {
                appPreferencesHelper.loginToken = loginResult.token
                appPreferencesHelper.Id = loginResult.data.rjData.adminMstId
                errorSubject.onNext(loginResult.message)
                startMainScreenSubject.onNext(Unit)
            } else {
                errorSubject.onNext(loginResult.message)
            }
        }, { error ->
            isLoadingSubject.onNext(false)
            error.message?.let {
                errorSubject.onNext(it)
            }
        }).autoDispose(compositeDisposable)
    }

}