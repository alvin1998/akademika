package com.AdaInt.akademika.activity.dosen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.AdaInt.akademika.R
import com.AdaInt.akademika.activity.login.ActivityLogin
import com.AdaInt.akademika.helper.Sessions
import kotlinx.android.synthetic.main.fragment_profil_ds.*
import org.jetbrains.annotations.Nullable

class ProfilFragmentDs : Fragment(){

    lateinit var session: Sessions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profil_ds, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtExitDs.setOnClickListener { keluar() }
    }

    private fun keluar(){
        session = Sessions(requireContext())
        session.createSession("is_login", "")
        session.createSession("nama_user", "")
        session.createSession("level_user", "")
        session.createSession("id_user", "")

        val intent = Intent(context, ActivityLogin::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        session = Sessions(requireContext())
        var check_login = session.getSession("is_login")
        var level_user = session.getSession("level_user")

        if(check_login != "masuk"){
            if(level_user != "Dosen"){
                val intent = Intent(context, ActivityLogin::class.java)
                startActivity(intent)
            }
        }
    }
}