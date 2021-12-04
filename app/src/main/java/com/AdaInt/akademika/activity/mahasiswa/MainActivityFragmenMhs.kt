package com.AdaInt.akademika.activity.mahasiswa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.AdaInt.akademika.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main_fragmen_mhs.*

class MainActivityFragmenMhs : AppCompatActivity() {
    val menuTeks = arrayOf("Dasboard","Kelas","Profil")
    val menuIcon = arrayOf(R.drawable.ic_home_icon_icons_com_73532,R.drawable.ic__486485588_add_create_new_math_sign_cross_plus_81186, R.drawable.ic_account_avatar_face_man_people_profile_user_icon_123197)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_fragmen_mhs)

        val adapter = ViewPagerAdapterMhs(this)
        view_pager.setAdapter(adapter);
        TabLayoutMediator(tab_layout, view_pager,
            TabLayoutMediator.TabConfigurationStrategy{
                    tab , position ->
                tab.text=menuTeks[position]
                tab.icon= ResourcesCompat.getDrawable(resources, menuIcon[position], null)
            }).attach()
    }
}