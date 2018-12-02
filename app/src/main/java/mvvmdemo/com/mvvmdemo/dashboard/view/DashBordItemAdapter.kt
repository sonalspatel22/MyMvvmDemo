package com.rjmingletainment.ui.dashboard.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import mvvmdemo.com.mvvmdemo.R
import mvvmdemo.com.mvvmdemo.dashboard.view.DashboardItemViewHolder


class DashBordItemAdapter(val context: Context, private val compositeDisposable: CompositeDisposable) : RecyclerView.Adapter<DashboardItemViewHolder>() {

    private val dashBoardItemTextList = arrayOf("Matches", "Rating", "Requests", "Reminds Me", "RJ Comments", "Songs", "Note", "Registered Users")
    private val dashBoardItemImageList = arrayOf(R.drawable.abc_ic_ab_back_material, R.drawable.abc_ic_ab_back_material, R.drawable.abc_ic_ab_back_material, R.drawable.abc_ic_ab_back_material,
            R.drawable.abc_ic_ab_back_material, R.drawable.abc_ic_ab_back_material, R.drawable.abc_ic_ab_back_material, R.drawable.abc_ic_ab_back_material)

    private val itemClickSubject: PublishSubject<Int> = PublishSubject.create()
    var itemClick: Observable<Int> = itemClickSubject.hide()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardItemViewHolder {
        return DashboardItemViewHolder(LayoutInflater.from(context).inflate(R.layout.items_dashboard, parent, false)).apply {
            itemClick.subscribe {
                itemClickSubject.onNext(adapterPosition)
            }.autoDispose(compositeDisposable)
        }
    }

    override fun getItemCount(): Int {
        return dashBoardItemTextList.size;
    }

    override fun onBindViewHolder(holder: DashboardItemViewHolder, position: Int) {
        holder.bindData(dashBoardItemTextList.get(position), dashBoardItemImageList.get(position))
    }
}