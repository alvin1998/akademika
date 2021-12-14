package com.AdaInt.akademika.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.AdaInt.akademika.R
import com.AdaInt.akademika.activity.dosen.MainActivityFragment
import com.AdaInt.akademika.activity.mahasiswa.MainActivityFragmenMhs
import com.AdaInt.akademika.helper.Sessions
import com.AdaInt.akademika.model.CekLoginRespon
import com.AdaInt.akademika.model.RequestCekLogin
import com.AdaInt.akademika.network.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityLogin : AppCompatActivity() {

    lateinit var session: Sessions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnRegister.setOnClickListener {Register()}
        btnLogin.setOnClickListener { CekLogin() }

    }

    private  fun Register(){
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }

    private fun CekLogin(){
        var user = txtUsername.text.toString()
        var pw   = inputPassword.text.toString()

        if(user.length > 0 && pw.length >0){

            var usercek = RequestCekLogin()
            usercek.username = user
            usercek.password = pw


            val httpClient = httpClient()
            val apiRequest = cekLogin<CekLoginD>(httpClient)
            val call = apiRequest.cekData(usercek)
            call.enqueue( object  : Callback<CekLoginRespon> {
                override fun onResponse(
                    call: Call<CekLoginRespon>,
                    response: Response<CekLoginRespon>
                ) {
//                    Log.i("coba",response.body()!!.username.toString())
//                    Log.i("coba",response.body()!!.email.toString())

                    pindah(
                        response.body()!!.username.toString(),
                        response.body()!!.id.toString(),
                        response.body()!!.level.toString(),
                        response.body()!!.id_biodata.toString().toInt()
                    )

                }
                override fun onFailure(call: Call<CekLoginRespon>, t: Throwable) {
                    Log.i("coba",t.localizedMessage.toString())
                    Snackbar.make(
                        btnLogin,
                        "Kata Sandi Atau Password anda salah",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            })

        }else{
            Snackbar.make(
                btnLogin,
                "Anda Belom Melengkapi Data Login",
                Snackbar.LENGTH_SHORT
            ).show()
        }

    }
    private fun pindah(username : String, id: String, level: String, id_biodata: Int){



        if(level.equals("Dosen")){

            if(!id_biodata.equals(1)){
                session = Sessions(this)
                session.createSession("is_login", "masuk")
                session.createSession("nama_user", username)
                session.createSession("level_user", level)
                session.createSession("id_user", id)
                val intent = Intent(this, MainActivityFragment::class.java)
                startActivity(intent)
            }else{
                session = Sessions(this)
                session.createSession("id_user", id)
                session.createSession("biodata", "tidak_ada")
                session.createSession("level_user", level)
                val intent = Intent(this, Biodata::class.java)
                startActivity(intent)
            }



        }else{
            if(!id_biodata.equals(1)){
                session = Sessions(this)
                session.createSession("is_login", "masuk")
                session.createSession("nama_user", username)
                session.createSession("level_user", level)
                session.createSession("id_user", id)
                val intent = Intent(this, MainActivityFragmenMhs::class.java)
                startActivity(intent)
            }else{
                session = Sessions(this)
                session.createSession("id_user", id)
                session.createSession("biodata", "tidak_ada")
                session.createSession("level_user", level)
                val intent = Intent(this, Biodata::class.java)
                startActivity(intent)
            }
        }


    }

    override fun onStart() {
        super.onStart()
        session = Sessions(this)
        var check_login = session.getSession("is_login")
        var level_user = session.getSession("level_user")

        if(check_login.equals("masuk")){

            if(level_user.equals("Dosen")){
                val intent = Intent(this, MainActivityFragment::class.java)
                startActivity(intent)
            }
            if(level_user.equals("Mahasiswa")){
                val intent = Intent(this, MainActivityFragmenMhs::class.java)
                startActivity(intent)
            }
        }
    }
}