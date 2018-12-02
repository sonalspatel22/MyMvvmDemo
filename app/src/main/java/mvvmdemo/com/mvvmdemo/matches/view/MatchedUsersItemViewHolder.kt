package mvvmdemo.com.mvvmdemo.matches.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mvvmdemo.com.mvvmdemo.matches.model.Match

import kotlinx.android.synthetic.main.items_matchusers.view.*
import mvvmdemo.com.mvvmdemo.AppConstants


class MatchedUsersItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bindData(match: Match) {
        view.itemMatchUserTextviewSongName.text = match.songMstName
        Glide.with(view).load(match.user1Img).into(view.itemMatchUserCircleImageViewUser1)
        view.itemMatchUserTextViewUser1.text = match.user1Name
        Glide.with(view).load(match.user2Img).into(view.itemMatchUserCircleImageViewUser2)
        view.itemMatchUserTextViewUser2.text = match.user2Name

    }
}