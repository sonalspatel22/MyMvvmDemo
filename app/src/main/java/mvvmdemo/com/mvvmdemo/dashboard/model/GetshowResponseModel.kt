package mvvmdemo.com.mvvmdemo.dashboard.model

import com.google.gson.annotations.SerializedName

data class GetshowRequest(
        @SerializedName("rj_id") var rjid: String,
        @SerializedName("date") var date: String,
        @SerializedName("login_token") var token: String
)

data class GetshowResponse(
        @SerializedName("CODE") val code: String,
        @SerializedName("DATA") val data: DATA,
        @SerializedName("MSG") val msg: String
)

data class DATA(
        @SerializedName("all_shows") val allShows: MutableList<AllShow>,
        @SerializedName("current_show_id") val currentShowId: String,
        @SerializedName("rj_data") val rjData: RjData,
        @SerializedName("rj_detail") val rjDetail: RjDetail
)

data class AllShow(
//        @SerializedName("added_by") val addedBy: String,
//        @SerializedName("date") val date: String,
//        @SerializedName("desc") val desc: String,
//        @SerializedName("display_song_id") val displaySongId: String,
//        @SerializedName("end_time") val endTime: String,
        @SerializedName("id") val id: String,
//        @SerializedName("is_music") val isMusic: String,
//        @SerializedName("note") val note: String,
//        @SerializedName("show_mst_channel_id") val showMstChannelId: String,
//        @SerializedName("show_mst_created_at") val showMstCreatedAt: String,
//        @SerializedName("show_mst_created_by") val showMstCreatedBy: String,
//        @SerializedName("show_mst_desc") val showMstDesc: String,
//        @SerializedName("show_mst_id") val showMstId: String,
//        @SerializedName("show_mst_modified_at") val showMstModifiedAt: String,
//        @SerializedName("show_mst_modified_by") val showMstModifiedBy: String,
        @SerializedName("show_mst_name") val showMstName: String
//        @SerializedName("show_mst_rj_id1") val showMstRjId1: String,
//        @SerializedName("show_mst_rj_id2") val showMstRjId2: String,
//        @SerializedName("show_mst_rj_id3") val showMstRjId3: String,
//        @SerializedName("show_mst_station_id") val showMstStationId: String,
//        @SerializedName("show_mst_status") val showMstStatus: String,
//        @SerializedName("show_type") val showType: String,
//        @SerializedName("show_type_id") val showTypeId: String,
//        @SerializedName("song_id") val songId: String,
//        @SerializedName("start_time") val startTime: String,
//        @SerializedName("title") val title: String
) {
    override fun toString(): String {
        return showMstName
    }
}

data class RjData(
//        @SerializedName("admin_mst_channel_id") val adminMstChannelId: String,
//        @SerializedName("admin_mst_created_at") val adminMstCreatedAt: String,
//        @SerializedName("admin_mst_created_by") val adminMstCreatedBy: String,
//        @SerializedName("admin_mst_email") val adminMstEmail: String,
        @SerializedName("admin_mst_id") val adminMstId: String
//        @SerializedName("admin_mst_image") val adminMstImage: String,
//        @SerializedName("admin_mst_last_login") val adminMstLastLogin: String,
//        @SerializedName("admin_mst_name") val adminMstName: String,
//        @SerializedName("admin_mst_password") val adminMstPassword: String,
//        @SerializedName("admin_mst_role_id") val adminMstRoleId: String,
//        @SerializedName("admin_mst_role_name") val adminMstRoleName: String,
//        @SerializedName("admin_mst_station_id") val adminMstStationId: String,
//        @SerializedName("admin_mst_status") val adminMstStatus: String
)

data class RjDetail(
        @SerializedName("rj_deails") val rjDeails: String,
        @SerializedName("rj_id") val rjId: String,
        @SerializedName("rj_rj_id") val rjRjId: String
)