package mvvmdemo.com.mvvmdemo.matches.viewmodel



import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import mvvmdemo.com.mvvmdemo.AppConstants
import mvvmdemo.com.mvvmdemo.AuthRepository
import mvvmdemo.com.mvvmdemo.base.BaseViewModel
import mvvmdemo.com.mvvmdemo.base.rxjava.autoDispose
import mvvmdemo.com.mvvmdemo.matches.model.Match
import mvvmdemo.com.mvvmdemo.preference.AppPreferencesHelper


class MatchUserViewModel(private val authRepository: AuthRepository, private val appPreferencesHelper: AppPreferencesHelper) : BaseViewModel() {

    private var showId: String = ""
    private var offset: Int = 0
    private var isRunning: Boolean = false
    private var matchesList: MutableList<Match> = mutableListOf()

    private val matchesListSubject: BehaviorSubject<MutableList<Match>> = BehaviorSubject.create()
    var matchesListObserver: Observable<MutableList<Match>> = matchesListSubject.hide()

    fun initData(showID: String) {
        showId = showID
        if (offset != -1 && !isRunning)
            getMatches()
    }

    private fun getMatches() {
        isLoadingSubject.onNext(true)
        isRunning = true
        authRepository.getMatchesUsers(offset, showId, appPreferencesHelper.loginToken, appPreferencesHelper.Id).observeOn(Schedulers.io()).subscribe({ matchUserResponse ->
            if (matchUserResponse.code == AppConstants.SUCCESS) {
                offset = matchUserResponse.offset
                isLoadingSubject.onNext(false)
                matchesList.addAll(matchUserResponse.data.matches)
                matchesListSubject.onNext(matchesList)
                isRunning = false
                isLoadingSubject.onNext(false)
            }
        }, { error ->
            isRunning = false
            error.message?.let {
                isLoadingSubject.onNext(false)
                errorSubject.onNext(it)
            }
        }).autoDispose(compositeDisposable)
    }

    fun onRefreshCall() {
        offset = 0
        matchesList.clear()
        matchesListSubject.onNext(matchesList)
        getMatches()
    }


}