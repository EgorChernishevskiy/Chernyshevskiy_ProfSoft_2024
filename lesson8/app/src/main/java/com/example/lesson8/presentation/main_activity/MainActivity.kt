package com.example.lesson8.presentation.main_activity

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.lesson8.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.adapter = adapter
        adapter.setItems(
            listOf(
                *listOf(JustTextItem("item 1"), JustTextItem("item 2"), JustTextItem("item 3")).toTypedArray(),
                *listOf(BlockWithTextItem("item 1"), BlockWithTextItem("item 2"), BlockWithTextItem("item 3")).toTypedArray(),
            )
        )

        fun addItem() {
            val newItem = JustTextItem("New Item")
            adapter.addItem(newItem)
        }

        val addButton = findViewById<Button>(R.id.buttonAdd)
        addButton.setOnClickListener {
            addItem()
        }

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val viewPagerAdapter = ViewPagerAdapter()
        viewPager.adapter = viewPagerAdapter
        viewPagerAdapter.setItems(listOf("item 1", "item 2", "item 3", "item 4", "item 5", "item 6"))

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.customView = layoutInflater.inflate(R.layout.tab_indicator, null)
        }.attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                for (i in 0 until tabLayout.tabCount) {
                    val tab = tabLayout.getTabAt(i)
                    val imageView = tab?.customView?.findViewById<ImageView>(R.id.tabIndicator)
                    imageView?.setImageResource(
                        if (i == position) {
                            R.drawable.tab_indicator_selected
                        } else {
                            R.drawable.tab_indicator_unselected
                        }
                    )
                }
            }
        })
    }
}