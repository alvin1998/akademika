package com.AdaInt.akademika.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import com.AdaInt.akademika.R
import com.AdaInt.akademika.adapter.UserModelItem
import com.AdaInt.akademika.model.RequestUser
import com.AdaInt.akademika.network.CreateData
import com.AdaInt.akademika.network.httpClient
import com.AdaInt.akademika.network.inputData
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register : AppCompatActivity() {

    private lateinit var radioButton: RadioButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnDaftar.setOnClickListener{Daftar()}
    }

    private fun Daftar(){


        val SelectOption: Int = rgLevel!!.checkedRadioButtonId

        if(SelectOption == -1){
            Snackbar.make(
                btnDaftar,
                "Anda Belom Memilih Level",
                Snackbar.LENGTH_SHORT
            ).show()
        }else{
            radioButton = findViewById(SelectOption)
            var user = DaftarUsername.text.toString()
            var email = DaftarEmail.text.toString()
            var pws = DaftarPassword.text.toString()
            var lvl = radioButton.text.toString()

            if(user.length > 0 && email.length > 0 && pws.length > 0){


//ini untuk simpan ke api
                var userReg = RequestUser()
                userReg.username = user
                userReg.email = email
                userReg.password = pws
                userReg.level = lvl

                val httpClient = httpClient()
                val apiRequest = inputData<CreateData>(httpClient)
                val call = apiRequest.createData(userReg)
                call.enqueue( object  : Callback<List<UserModelItem>> {
                    override fun onResponse(
                        call: Call<List<UserModelItem>>,
                        response: Response<List<UserModelItem>>
                    ) {
                        Snackbar.make(
                            btnDaftar,
                            "Data Tersimpan",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        pindah()
                    }
                    override fun onFailure(call: Call<List<UserModelItem>>, t: Throwable) {
                        Log.i("coba",t.localizedMessage.toString())
                        Snackbar.make(
                            btnDaftar,
                            t.localizedMessage.toString(),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                })


            }else{
                Snackbar.make(
                    btnDaftar,
                    "Anda Belom Mengisi Semua Form",
                    Snackbar.LENGTH_SHORT
                ).show()

            }
        }

    }

    private fun pindah(){

        var user = DaftarUsername.text.toString()
        var pws = DaftarPassword.text.toString()

        val intent = Intent(this, ActivityLogin::class.java)
        val bundle = Bundle()
        bundle.putString("user" , user)
        bundle.putString("Pws" , pws)
        startActivity(intent)
    }
}