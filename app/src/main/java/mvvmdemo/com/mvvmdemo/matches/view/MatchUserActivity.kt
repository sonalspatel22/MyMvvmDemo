package mvvmdemo.com.mvvmdemo.matches.view


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import mvvmdemo.com.mvvmdemo.matches.viewmodel.MatchUserViewModel

import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_match_user.*
import mvvmdemo.com.mvvmdemo.PaginationScrollListener
import mvvmdemo.com.mvvmdemo.R.*
import mvvmdemo.com.mvvmdemo.TintDrawableHelper
import mvvmdemo.com.mvvmdemo.base.BaseActivity
import mvvmdemo.com.mvvmdemo.base.rxjava.autoDispose
import mvvmdemo.com.mvvmdemo.di.AppComponent
import javax.inject.Inject

class MatchUserActivity : BaseActivity() {


    @Inject
    lateinit var matchUserViewModel: MatchUserViewModel
    private var matchUsersAdapter: MatchUsersAdapter? = null
    private var gridLayoutManager: GridLayoutManager? = null


    companion object {
        const val SHOW_ID = "SHOW_ID"
        fun getIntent(context: Context, showId: String): Intent {
            val intent = Intent(context, MatchUserActivity::class.java)
            intent.putExtra(SHOW_ID, showId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_match_user)
        AppComponent.component.inject(this)

        activityMatchUserToolbar.navigationIcon = TintDrawableHelper.getTintedResource(this, drawable.ic_arrow_back, color.colorPrimaryDark)
        activityMatchUserToolbar.setNavigationOnClickListener { v -> finish() }
        gridLayoutManager = GridLayoutManager(this, 2)
        activityMatchUserRecyclerView.layoutManager = gridLayoutManager
        loadMatchUserData()
        activityMatchUserSwipeRefreshLayout.setColorSchemeResources(color.colorPrimary)
        activityMatchUserSwipeRefreshLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                matchUserViewModel.onRefreshCall()
            }
        })

        matchUserViewModel.isLoading.observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it) {
                showProgress(getString(string.msg_loading))
            } else {
                hideProgress()
            }
        }.autoDispose(compositeDisposable)

        matchUserViewModel.matchesListObserver.observeOn(AndroidSchedulers.mainThread()).subscribe {
            activityMatchUserSwipeRefreshLayout.isRefreshing = false
            hideProgress()
            matchUsersAdapter?.setMatchedUsers(it) ?: kotlin.run {
                matchUsersAdapter = MatchUsersAdapter(this, compositeDisposable)
                matchUsersAdapter?.setMatchedUsers(it)
                activityMatchUserRecyclerView.adapter = matchUsersAdapter
            }
        }.autoDispose(compositeDisposable)

        gridLayoutManager?.let {
            activityMatchUserRecyclerView?.addOnScrollListener(object : PaginationScrollListener(it) {
                override fun loadMoreItems() {
                    loadMatchUserData()
                }
            })
        }
    }

    fun loadMatchUserData() {
        matchUserViewModel.initData(intent.getStringExtra(SHOW_ID))
    }


}
