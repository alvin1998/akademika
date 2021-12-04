package com.AdaInt.akademika.activity.dosen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.AdaInt.akademika.activity.dosen.DasboardFragmentDs

class  ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity){
    private val JUMLAH_MENU = 3

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> {return DasboardFragmentDs()}
            1 -> {return KelasFragmentDs()}
            2 -> {return ProfilFragmentDs()}
            else -> {return DasboardFragmentDs()}
        }
    }

    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }
}
