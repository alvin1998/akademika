package com.AdaInt.akademika.activity.mahasiswa

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapterMhs (fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity){
    private val JUMLAH_MENU = 3

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> {return DasboardFragmentMhs()
            }
            1 -> {return KelasFragmentMhs()
            }
            2 -> {return ProfilFragmentMhs()
            }
            else -> {return DasboardFragmentMhs()
            }
        }
    }

    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }
}