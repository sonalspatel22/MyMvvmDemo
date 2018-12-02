package mvvmdemo.com.mvvmdemo.dashboard.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items_dashboard.view.*
import mvvmdemo.com.mvvmdemo.base.ui.throttleClicks


class DashboardItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    var itemClick = view.throttleClicks();

    fun bindData(text: String, image: Int) {
        view.dashboarditemTextview.text = text
        view.dashboarditemTextview.setCompoundDrawablesWithIntrinsicBounds(0, image, 0, 0)
    }
}