//package com.abaferastech.marvelapp.ui.comicDetails
//
//import androidx.databinding.BindingAdapter
//import androidx.viewpager2.widget.ViewPager2
//import com.google.android.material.tabs.TabLayout
//
//@BindingAdapter("app:setTabLayout")
//fun setTabLayout(view: TabLayout,viewPager: ViewPager2) {
//    view.apply {
//        addTab(view.newTab().setText("All Comics"))
//        addTab(view.newTab().setText("Details"))
//    }
//
//    view.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
//        override fun onTabSelected(tab: TabLayout.Tab?) {
//            if (tab != null) {
//                viewPager.currentItem = tab.position
//            }
//        }
//        override fun onTabUnselected(tab: TabLayout.Tab?) {
//        }
//
//        override fun onTabReselected(tab: TabLayout.Tab?) {
//        }
//    })
//
//}
//@BindingAdapter("app:setViePager2Adapter")
//fun setViePager2Adapter(view: ViewPager2,tabLayout: TabLayout) {
//    view.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
//        override fun onPageSelected(position: Int) {
//            super.onPageSelected(position)
//            tabLayout.selectTab(tabLayout.getTabAt(position))
//        }
//    })
//
//}
