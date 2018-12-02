package mvvmdemo.com.mvvmdemo.matches.model

import com.google.gson.annotations.SerializedName


class GetMatchesUsersRequest(
        @SerializedName("rj_id") var rjId: String,
        @SerializedName("login_token") var token: String,
        @SerializedName("show_id") var showId: String
)

data class GetMatchesUsersResponse(
        @SerializedName("CODE")
        val code: String,
        @SerializedName("DATA")
        val data: DATA,
        @SerializedName("MSG")
        val message: String,
        @SerializedName("OFFSET")
        val offset: Int
)

data class DATA(
        @SerializedName("matches")
        val matches: MutableList<Match>
)

data class Match(
        @SerializedName("matched_user_created_at")
        val matchedUserCreatedAt: String,
        @SerializedName("matched_user_id")
        val matchedUserId: String,
        @SerializedName("matched_user_is_block")
        val matchedUserIsBlock: String,
        @SerializedName("matched_user_song_like_id")
        val matchedUserSongLikeId: String,
        @SerializedName("matched_user_type")
        val matchedUserType: String,
        @SerializedName("matched_user_user_id1")
        val matchedUserUserId1: String,
        @SerializedName("matched_user_user_id2")
        val matchedUserUserId2: String,
        @SerializedName("song_mst_channel_id")
        val songMstChannelId: String,
        @SerializedName("song_mst_created_at")
        val songMstCreatedAt: String,
        @SerializedName("song_mst_created_by")
        val songMstCreatedBy: String,
        @SerializedName("song_mst_disc")
        val songMstDisc: String,
        @SerializedName("song_mst_file")
        val songMstFile: String,
        @SerializedName("song_mst_ganers_id")
        val songMstGanersId: String,
        @SerializedName("song_mst_id")
        val songMstId: String,
        @SerializedName("song_mst_image")
        val songMstImage: String,
        @SerializedName("song_mst_modified_at")
        val songMstModifiedAt: String,
        @SerializedName("song_mst_modified_by")
        val songMstModifiedBy: String,
        @SerializedName("song_mst_movie_album_name")
        val songMstMovieAlbumName: String,
        @SerializedName("song_mst_name")
        val songMstName: String,
        @SerializedName("song_mst_singer")
        val songMstSinger: String,
        @SerializedName("song_mst_status")
        val songMstStatus: String,
        @SerializedName("song_mst_year")
        val songMstYear: String,
        @SerializedName("songs_like_created_at")
        val songsLikeCreatedAt: String,
        @SerializedName("songs_like_id")
        val songsLikeId: String,
        @SerializedName("songs_like_schedule_id")
        val songsLikeScheduleId: String,
        @SerializedName("songs_like_show_id")
        val songsLikeShowId: String,
        @SerializedName("songs_like_song_id")
        val songsLikeSongId: String,
        @SerializedName("songs_like_user_id")
        val songsLikeUserId: String,
        @SerializedName("user1_img")
        val user1Img: String,
        @SerializedName("user1_name")
        val user1Name: String,
        @SerializedName("user2_img")
        val user2Img: String,
        @SerializedName("user2_name")
        val user2Name: String
)