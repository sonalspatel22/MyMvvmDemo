package mvvmdemo.com.mvvmdemo.dashboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.rjmingletainment.ui.dashboard.view.DashBordItemAdapter
import com.rjmingletainment.ui.login.view.LoginActivity
import mvvmdemo.com.mvvmdemo.dashboard.viewmodel.DashboardViewModel
import mvvmdemo.com.mvvmdemo.matches.view.MatchUserActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_dashboard.*
import mvvmdemo.com.mvvmdemo.R
import mvvmdemo.com.mvvmdemo.base.BaseFragment
import mvvmdemo.com.mvvmdemo.base.rxjava.autoDispose
import mvvmdemo.com.mvvmdemo.di.AppComponent
import javax.inject.Inject


class DashboardFragment : BaseFragment() {

    @Inject
    lateinit var dashboardViewModel: DashboardViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppComponent.component.inject(this)
        fragmentDashboardRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        val dashBoardItemAdapter = DashBordItemAdapter(activity, compositeDisposable).apply {
            itemClick.subscribe {
                dashboardViewModel.onDashBoardItemClick(it)
            }.autoDispose(compositeDisposable)
        }
        fragmentDashboardRecyclerView.adapter = dashBoardItemAdapter

        dashboardViewModel.displayDateList.observeOn(AndroidSchedulers.mainThread()).subscribe {
            val dataAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, it)
            dataAdapter.setDropDownViewResource(R.layout.spinneritem)
            fragmentDashboardDate.adapter = dataAdapter
        }.autoDispose(compositeDisposable)

        dashboardViewModel.displayShowList.observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.size > 0) {
                fragmentDashboardShow.isClickable = true
                val dataAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, it)
                dataAdapter.setDropDownViewResource(R.layout.spinneritem)
                fragmentDashboardShow.adapter = dataAdapter
            } else {
                fragmentDashboardShow.isClickable = false
                fragmentDashboardShow.adapter = null
            }
        }.autoDispose(compositeDisposable)

        fragmentDashboardDate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                dashboardViewModel.displayShowByDate(parent?.getItemAtPosition(position).toString())
            }
        }

        fragmentDashboardShow.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                dashboardViewModel.selectShow(position)
            }
        }


        dashboardViewModel.isLoading.observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it)
                showProgress(getString(R.string.msg_loading))
            else
                hideProgress()
        }.autoDispose(compositeDisposable)

        dashboardViewModel.error.observeOn(AndroidSchedulers.mainThread()).subscribe {
            showToast(it)
        }.autoDispose(compositeDisposable)

        dashboardViewModel.startLoginActivity.observeOn(AndroidSchedulers.mainThread()).subscribe {
            startActivity(LoginActivity.getIntent(activity))
        }.autoDispose(compositeDisposable)

        dashboardViewModel.startMatchesUserActivity.observeOn(AndroidSchedulers.mainThread()).subscribe {
            startActivity(MatchUserActivity.getIntent(activity, it))
        }.autoDispose(compositeDisposable)


    }

    companion object {
        @JvmStatic
        fun newInstance() = DashboardFragment().apply {}
    }
}
