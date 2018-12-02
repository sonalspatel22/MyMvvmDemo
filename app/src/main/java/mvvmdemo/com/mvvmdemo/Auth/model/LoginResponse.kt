package mvvmdemo.com.mvvmdemo.Auth.model

import com.google.gson.annotations.SerializedName


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
        @SerializedName("rj_data")
        val rjData: RjData,
        @SerializedName("rj_detail")
        val rjDetail: RjDetail
)

data class RjData(
        @SerializedName("admin_mst_channel_id")
        val adminMstChannelId: String,
        @SerializedName("admin_mst_created_at")
        val adminMstCreatedAt: String,
        @SerializedName("admin_mst_created_by")
        val adminMstCreatedBy: String,
        @SerializedName("admin_mst_email")
        val adminMstEmail: String,
        @SerializedName("admin_mst_id")
        val adminMstId: String,
        @SerializedName("admin_mst_image")
        val adminMstImage: String,
        @SerializedName("admin_mst_last_login")
        val adminMstLastLogin: String,
        @SerializedName("admin_mst_name")
        val adminMstName: String,
        @SerializedName("admin_mst_password")
        val adminMstPassword: String,
        @SerializedName("admin_mst_role_id")
        val adminMstRoleId: String,
        @SerializedName("admin_mst_role_name")
        val adminMstRoleName: String,
        @SerializedName("admin_mst_station_id")
        val adminMstStationId: String,
        @SerializedName("admin_mst_status")
        val adminMstStatus: String
)

data class RjDetail(
        @SerializedName("rj_deails")
        val rjDeails: String,
        @SerializedName("rj_id")
        val rjId: String,
        @SerializedName("rj_rj_id")
        val rjRjId: String
)


data class LoginRequest(@SerializedName("username") var username: String,
                        @SerializedName("password") var password: String,
                        @SerializedName("fcm_token") var fcmToken: String,
                        @SerializedName("device_type") var deviceType: String,
                        @SerializedName("device_id") var deviceId: String)