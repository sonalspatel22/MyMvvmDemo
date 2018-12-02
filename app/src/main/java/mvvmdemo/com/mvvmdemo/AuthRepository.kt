package mvvmdemo.com.mvvmdemo

import mvvmdemo.com.mvvmdemo.dashboard.model.GetshowRequest
import mvvmdemo.com.mvvmdemo.dashboard.model.GetshowResponse
import io.reactivex.Single
import mvvmdemo.com.mvvmdemo.Auth.LoginRequest
import mvvmdemo.com.mvvmdemo.Auth.LoginResponse
import mvvmdemo.com.mvvmdemo.matches.model.GetMatchesUsersRequest
import mvvmdemo.com.mvvmdemo.matches.model.GetMatchesUsersResponse

class AuthRepository(private val apiService: ApiService) {

    fun login(email: String, password: String, fcmToken: String, deviceType: String, deviceId: String): Single<LoginResponse> {
        return apiService.login(LoginRequest(email, password, fcmToken, deviceType, deviceId))
    }

    fun getMatchesUsers(offset: Int, showId: String, token: String, rjId: String): Single<GetMatchesUsersResponse> {
        return apiService.getUsers(offset, GetMatchesUsersRequest(rjId, token, showId))
    }

    fun getShow(rjId: String, date: String, token: String): Single<GetshowResponse> {
        return apiService.getShow(GetshowRequest(rjId, date, token))
    }
}