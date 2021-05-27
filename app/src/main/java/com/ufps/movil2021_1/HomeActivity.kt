package com.ufps.movil2021_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {
    lateinit var tabLayout : TabLayout
    lateinit var viewPage : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        tabLayout=findViewById(R.id.home)
        viewPage=findViewById(R.id.page)
        tabLayout.tabGravity=TabLayout.GRAVITY_FILL
        val adapter = MyAdapter(context = this, fragmentManager = supportFragmentManager, totalTabs = tabLayout.tabCount)
        viewPage.adapter =adapter
        viewPage.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPage.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}// No es necesario iplementar
            override fun onTabReselected(tab: TabLayout.Tab?) {}//No es necesario implementar
        })
    }
}