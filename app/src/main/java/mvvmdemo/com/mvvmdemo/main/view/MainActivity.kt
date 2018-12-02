package mvvmdemo.com.mvvmdemo.main.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import mvvmdemo.com.mvvmdemo.dashboard.view.DashboardFragment
import com.rjmingletainment.ui.main.view.ViewpagerAdapter
import mvvmdemo.com.mvvmdemo.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import mvvmdemo.com.mvvmdemo.R
import mvvmdemo.com.mvvmdemo.base.BaseActivity
import mvvmdemo.com.mvvmdemo.di.AppComponent
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MainViewModel


    companion object {
        const val K = "K"
        fun getIntent(context: Context, s: String): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(K, s)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppComponent.component.inject(this)
        val viewpageAdapter = ViewpagerAdapter(supportFragmentManager)
//        viewpageAdapter.add(PostFragment.newInstance())
//        viewpageAdapter.add(BlogFragment.newInstance())
        viewpageAdapter.add(DashboardFragment.newInstance())
//        viewpageAdapter.add(BirthdayFragment.newInstance())
//        viewpageAdapter.add(LivePostFragment.newInstance())
        activityMainViewPager.adapter = viewpageAdapter

        activityMainTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab) {}

            override fun onTabUnselected(p0: TabLayout.Tab) {}

            override fun onTabSelected(tab: TabLayout.Tab) {
                activityMainViewPager.currentItem = tab.position
                addtitle(tab.position)
            }
        })

        activityMainViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                activityMainTabLayout.getTabAt(position)?.select()
                addtitle(position)
            }

        })

        activityMainViewPager.setCurrentItem(2, false)
    }

    private fun addtitle(position: Int) {
        activityMainToolbarRightTextView.text = ""
        when (position) {
            0 -> activityMainToolbarTitleTextView.text = resources.getString(R.string.tabItemOne)
            1 -> activityMainToolbarTitleTextView.text = resources.getString(R.string.tabItemOne)
            2 -> {
                activityMainToolbarTitleTextView.text = resources.getString(R.string.tabItemOne)
                activityMainToolbarRightTextView.text = resources.getString(R.string.settings)
            }
            3 -> activityMainToolbarTitleTextView.text = resources.getString(R.string.tabItemOne)
            4 -> activityMainToolbarTitleTextView.text = resources.getString(R.string.tabItemOne)

        }
    }

}
