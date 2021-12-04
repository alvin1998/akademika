package com.AdaInt.akademika.activity.mahasiswa

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.AdaInt.akademika.R
import com.AdaInt.akademika.activity.login.ActivityLogin
import com.AdaInt.akademika.helper.Sessions
import org.jetbrains.annotations.Nullable

class DasboardFragmentMhs : Fragment() {

    private lateinit var  session: Sessions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dasboard_mhs, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        session = Sessions(requireContext())
        var check_login = session.getSession("is_login")
        var level_user = session.getSession("level_user")

        if(check_login != "masuk"){
            if(level_user != "Mahasiswa"){
                val intent = Intent(context, ActivityLogin::class.java)
                startActivity(intent)
            }
        }
    }
}