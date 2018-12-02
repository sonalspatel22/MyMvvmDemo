package mvvmdemo.com.mvvmdemo

import mvvmdemo.com.mvvmdemo.dashboard.model.GetshowRequest
import mvvmdemo.com.mvvmdemo.dashboard.model.GetshowResponse
import io.reactivex.Single
import mvvmdemo.com.mvvmdemo.Auth.LoginRequest
import mvvmdemo.com.mvvmdemo.Auth.LoginResponse
import mvvmdemo.com.mvvmdemo.matches.model.GetMatchesUsersRequest
import mvvmdemo.com.mvvmdemo.matches.model.GetMatchesUsersResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("rjlogin/login")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>


    @POST( "rjapp/dashboard")
    fun getShow(@Body getShowRequest: GetshowRequest): Single<GetshowResponse>
    //for add parameter in url use query
    //@Query("urlparameter") offset: Int
    @POST("")
    fun getUsers(@Query("urlparameter") offset: Int, @Body  GetMatchesUsersRequest:GetMatchesUsersRequest ): Single<GetMatchesUsersResponse>


}
