package com.AdaInt.akademika.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.AdaInt.akademika.R
import com.AdaInt.akademika.activity.dosen.MainActivityFragment
import com.AdaInt.akademika.activity.mahasiswa.MainActivityFragmenMhs
import com.AdaInt.akademika.adapter.UserModelItem
import com.AdaInt.akademika.helper.Sessions
import com.AdaInt.akademika.model.GetBiodataItem
import com.AdaInt.akademika.model.ResponseModelBiodataItem
import com.AdaInt.akademika.model.SendBiodataItem
import com.AdaInt.akademika.network.CreateData
import com.AdaInt.akademika.network.MBiodata
import com.AdaInt.akademika.network.httpClient
import com.AdaInt.akademika.network.inputData
import com.AdaInt.akademika.util.tampilToast
import kotlinx.android.synthetic.main.activity_biodata.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Biodata : AppCompatActivity() {

    lateinit var session: Sessions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biodata)
        Log.i("coba","as")
        btnAdBiodata.setOnClickListener { sendApiBiodata() }
    }

    private  fun sendApiBiodata(){
        session = Sessions(this)
        var id_user = session.getSession("id_user")

        var bd = SendBiodataItem()
        bd.id = id_user.toString().toInt()
        bd.nama = txtNamaBiodata.text.toString()
        bd.nomer = txtNomerTlpBiodata.text.toString()
        bd.alamat = txtAlamatBiodata.text.toString()

        val httpClient = httpClient()
        val apiRequest = inputData<MBiodata>(httpClient)
        val call = apiRequest.SendBiodata(bd)
        call.enqueue( object  : Callback<List<ResponseModelBiodataItem>> {
            override fun onResponse(
                call: Call<List<ResponseModelBiodataItem>>,
                response: Response<List<ResponseModelBiodataItem>>
            ) {
                pindah(response.body()!!.get(0).username.toString(),
                    response.body()!!.get(0).level.toString(),
                    response.body()!!.get(0).id.toString())

//                Toast.makeText(this@Biodata,response.body()!!.,Toast.LENGTH_SHORT).show()
                Log.i("cobaaaaa",response.body()!!.get(0).nama.toString())
            }
            override fun onFailure(call: Call<List<ResponseModelBiodataItem>>, t: Throwable) {
                Log.i("coba",t.localizedMessage.toString())
            }
        })
    }

    private fun pindah(username : String, level : String, id: String){

        if(level.equals("Dosen")) {
            session = Sessions(this)
            session.createSession("is_login", "masuk")
            session.createSession("nama_user", username)
            session.createSession("level_user", level)
            session.createSession("id_user", id)
            val intent = Intent(this, MainActivityFragment::class.java)
            startActivity(intent)
        }
        if(level.equals("Mahasiswa")){
            session = Sessions(this)
            session.createSession("is_login", "masuk")
            session.createSession("nama_user", username)
            session.createSession("level_user", level)
            session.createSession("id_user", id)
            val intent = Intent(this, MainActivityFragmenMhs::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        session = Sessions(this)
        var check_biodata = session.getSession("biodata")

        if(!check_biodata.equals("tidak_ada")){
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
        }
    }
}