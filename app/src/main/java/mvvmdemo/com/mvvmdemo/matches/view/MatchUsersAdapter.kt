package mvvmdemo.com.mvvmdemo.matches.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mvvmdemo.com.mvvmdemo.matches.model.Match
import io.reactivex.disposables.CompositeDisposable
import mvvmdemo.com.mvvmdemo.R


class MatchUsersAdapter(val context: Context, private val compositeDisposable: CompositeDisposable) : RecyclerView.Adapter<MatchedUsersItemViewHolder>() {

    private var listOfMatchedUser: MutableList<Match> = mutableListOf()

    fun setMatchedUsers(listOfMatchedUsers: MutableList<Match>) {
        listOfMatchedUser = listOfMatchedUsers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchedUsersItemViewHolder {
        return MatchedUsersItemViewHolder(LayoutInflater.from(context).inflate(R.layout.items_matchusers, parent, false))
    }

    override fun getItemCount(): Int {
        return listOfMatchedUser.size
    }

    override fun onBindViewHolder(holder: MatchedUsersItemViewHolder, position: Int) {
        holder.bindData(listOfMatchedUser[position])
    }


}