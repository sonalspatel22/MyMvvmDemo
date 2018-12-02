package com.rjmingletainment.ui.main.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class ViewpagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {


    private val fragments: ArrayList<Fragment>

    init {
        fragments = ArrayList()
    }

    fun add(fragment: Fragment) {
        fragments.add(fragment)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

}