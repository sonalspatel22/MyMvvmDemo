package mvvmdemo.com.mvvmdemo.main.viewmodel

import androidx.lifecycle.MutableLiveData
import mvvmdemo.com.mvvmdemo.Auth.RjData
import mvvmdemo.com.mvvmdemo.AuthRepository
import mvvmdemo.com.mvvmdemo.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val authRepository: AuthRepository) : BaseViewModel() {


    val launchLoginActivity: MutableLiveData<Unit> = MutableLiveData()
    val loginSuccess: MutableLiveData<RjData> = MutableLiveData()
    val messageReceived: MutableLiveData<String> = MutableLiveData()


}