package mvvmdemo.com.mvvmdemo.dashboard.viewmodel

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import mvvmdemo.com.mvvmdemo.AppConstants.Companion.SUCCESS
import mvvmdemo.com.mvvmdemo.AuthRepository
import mvvmdemo.com.mvvmdemo.base.BaseViewModel
import mvvmdemo.com.mvvmdemo.base.rxjava.autoDispose
import mvvmdemo.com.mvvmdemo.dashboard.model.AllShow
import mvvmdemo.com.mvvmdemo.preference.AppPreferencesHelper
import java.text.SimpleDateFormat
import java.util.*

class DashboardViewModel(private val authRepository: AuthRepository, private val appPreferencesHelper: AppPreferencesHelper) : BaseViewModel() {

    private val displayShowListSubject: PublishSubject<Array<AllShow>> = PublishSubject.create()
    var displayShowList: Observable<Array<AllShow>> = displayShowListSubject.hide()

    private val displayDateListSubject: BehaviorSubject<Array<String?>> = BehaviorSubject.create()
    var displayDateList: Observable<Array<String?>> = displayDateListSubject.hide()

    private val startMatchesUserActivitySubject: PublishSubject<String> = PublishSubject.create()
    var startMatchesUserActivity: Observable<String> = startMatchesUserActivitySubject.hide()


    private val startLoginActivitySubject: BehaviorSubject<Unit> = BehaviorSubject.create()
    var startLoginActivity: Observable<Unit> = startLoginActivitySubject.hide()

    private var showList: MutableList<AllShow>? = null
    private var selectedShowId = ""
    private var selectedDate = ""

    init {
        getNextSevenDaysDate()
    }

    fun displayShowByDate(date: String) {
        isLoadingSubject.onNext(true)
        selectedDate = date;
        selectedShowId = ""
        authRepository.getShow(appPreferencesHelper.Id, date, appPreferencesHelper.loginToken).observeOn(Schedulers.io()).subscribe(
                { showResponse ->
                    if (showResponse.code == SUCCESS) {
                        showList = showResponse.data.allShows
                        displayShowListSubject.onNext(toArray(showList!!))
                    }
                    isLoadingSubject.onNext(false)
                }, { error ->
            error.message?.let {
                isLoadingSubject.onNext(false)
                errorSubject.onNext(it)
            }
        }
        ).autoDispose(compositeDisposable)
    }


    private fun getNextSevenDaysDate() {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateList = mutableListOf<String>()
        for (i in 0..6) {
            val calendar = GregorianCalendar()
            calendar.add(Calendar.DATE, i)
            val day = sdf.format(calendar.getTime())
            dateList.add(day)
        }
        displayDateListSubject.onNext(dateList.toTypedArray())
    }


    private inline fun <reified T> toArray(list: List<T>): Array<T> {
        return list.toTypedArray()
    }

    fun onDashBoardItemClick(it: Int) {
        if (selectedShowId.isNotEmpty() && selectedShowId.isNotBlank()) {
            when (it) {
                0 -> startMatchesUserActivitySubject.onNext(selectedShowId)

            }
        } else {
            errorSubject.onNext("Please Select Show")
        }
    }

    fun selectShow(position: Int) {
        showList?.let {
            if (it.isNotEmpty() && it.size > 0) {
                selectedShowId = it.get(position).id
            } else {
                selectedShowId = ""
            }
        }
    }

}