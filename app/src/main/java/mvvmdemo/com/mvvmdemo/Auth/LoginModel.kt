package mvvmdemo.com.mvvmdemo.Auth

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username") var username: String,
    @SerializedName("password") var password: String,
    @SerializedName("fcm_token") var fcmToken: String,
    @SerializedName("device_type") var deviceType: String,
    @SerializedName("device_id") var deviceId: String
)

data class LoginResponse(
    @SerializedName("CODE")
    val code: String,
    @SerializedName("DATA")
    val data: DATA,
    @SerializedName("MSG")
    val message: String,
    @SerializedName("TOKEN")
    val token: String

)

data class DATA(
    @SerializedName("current_show_id")
    val currentShowId: String,
    @SerializedName("data")
    val rjData: RjData,
    @SerializedName("detail")
    val rjDetail: RjDetail
)

data class RjData(
    @SerializedName("admin_channel_id")
    val adminMstChannelId: String,
    @SerializedName("admin_created_at")
    val adminMstCreatedAt: String,
    @SerializedName("admin_created_by")
    val adminMstCreatedBy: String,
    @SerializedName("admin_email")
    val adminMstEmail: String,
    @SerializedName("admin_id")
    val adminMstId: String,
    @SerializedName("admin_image")
    val adminMstImage: String,
    @SerializedName("admin_last_login")
    val adminMstLastLogin: String,
    @SerializedName("admin_name")
    val adminMstName: String,
    @SerializedName("admin_password")
    val adminMstPassword: String,
    @SerializedName("admin_role_id")
    val adminMstRoleId: String,
    @SerializedName("admin_role_name")
    val adminMstRoleName: String,
    @SerializedName("admin_station_id")
    val adminMstStationId: String,
    @SerializedName("admin_status")
    val adminMstStatus: String
)

data class RjDetail(
    @SerializedName("deails")
    val rjDeails: String,
    @SerializedName("id")
    val rjId: String,
    @SerializedName("id")
    val rjRjId: String
)