package com.ufps.movil2021_1

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
@Suppress("DEPRECATION")
class MyAdapter(
        var context:Context,
        fragmentManager: FragmentManager,
        var totalTabs:Int) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MedicineFragment()
            }
            1 -> {
                FavoritesFragment()
            }
            else -> ProfileFragment()
        }
    }
}