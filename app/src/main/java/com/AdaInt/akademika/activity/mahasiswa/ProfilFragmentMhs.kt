package com.AdaInt.akademika.activity.mahasiswa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.AdaInt.akademika.R
import com.AdaInt.akademika.activity.login.ActivityLogin
import com.AdaInt.akademika.helper.Sessions
import com.AdaInt.akademika.model.*
import com.AdaInt.akademika.network.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_profil_mhs.*
import org.jetbrains.annotations.Nullable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilFragmentMhs : Fragment() {

    lateinit var session: Sessions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profil_mhs, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GetBiodata()

        btnSimpanEdit.setOnClickListener { editBiodata() }
        txtExitMhs.setOnClickListener { keluar() }
    }

    private fun  GetBiodata(){
        session = Sessions(requireContext())
        var id_user = session.getSession("id_user")

        var usercek = SendBiodataEdit()
        usercek.id = id_user.toString().toInt()

        val httpClient = httpClient()
        val apiRequest = apiRequest<MBiodata>(httpClient)
        val call = apiRequest.SendShowBiodata(usercek)
        call.enqueue( object  : Callback<ResponseModelBiodataItem> {
            override fun onResponse(
                call: Call<ResponseModelBiodataItem>,
                response: Response<ResponseModelBiodataItem>
            ) {
                txtNamaEdit.setText(response.body()!!.nama.toString())
                txtemailEdit.setText(response.body()!!.email.toString())
                txtnomerEdit.setText(response.body()!!.nomer.toString())
                txtalamatEdit.setText(response.body()!!.alamat.toString())
                txtUsernameEdit.setText(response.body()!!.username.toString())
            }
            override fun onFailure(call: Call<ResponseModelBiodataItem>, t: Throwable) {
                Toast.makeText(requireContext(),"gagal", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun editBiodata(){
        session = Sessions(requireContext())
        var id_user = session.getSession("id_user")

        var usercek = EditBiodata()

        usercek.nama = txtNamaEdit.text.toString()
        usercek.nomer = txtnomerEdit.text.toString()
        usercek.alamat = txtalamatEdit.text.toString()
        usercek.username = txtUsernameEdit.text.toString()
        usercek.email = txtemailEdit.text.toString()

        val httpClient = httpClient()
        val apiRequest = apiRequest<MBiodata>(httpClient)
        val call = apiRequest.SendEditBiodata(id_user.toString().toInt(), usercek)
        call.enqueue( object  : Callback<ResponseModelBiodataItem> {
            override fun onResponse(
                call: Call<ResponseModelBiodataItem>,
                response: Response<ResponseModelBiodataItem>
            ) {
                txtNamaEdit.setText(response.body()!!.nama.toString())
                txtemailEdit.setText(response.body()!!.email.toString())
                txtnomerEdit.setText(response.body()!!.nomer.toString())
                txtalamatEdit.setText(response.body()!!.alamat.toString())
                txtUsernameEdit.setText(response.body()!!.username.toString())
            }
            override fun onFailure(call: Call<ResponseModelBiodataItem>, t: Throwable) {
                Toast.makeText(requireContext(),"gagal", Toast.LENGTH_LONG).show()
            }
        })
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
            if(level_user != "Mahasiswa"){
                val intent = Intent(context, ActivityLogin::class.java)
                startActivity(intent)
            }
        }
    }
}