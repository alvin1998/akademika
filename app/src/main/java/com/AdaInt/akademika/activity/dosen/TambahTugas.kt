package com.AdaInt.akademika.activity.dosen

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.set
import androidx.recyclerview.widget.LinearLayoutManager
import com.AdaInt.akademika.R
import com.AdaInt.akademika.activity.login.ActivityLogin
import com.AdaInt.akademika.adapter.ShowAdapterMateriDs
import com.AdaInt.akademika.helper.Sessions
import com.AdaInt.akademika.model.GetMateriDosenItem
import com.AdaInt.akademika.model.GetMateriDsId
import com.AdaInt.akademika.model.SendMateriApi
import com.AdaInt.akademika.model.SendMateriDosenEdit
import com.AdaInt.akademika.network.MateriDs
import com.AdaInt.akademika.network.apiRequest
import com.AdaInt.akademika.network.httpClient
import kotlinx.android.synthetic.main.activity_tambah_tugas.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahTugas : AppCompatActivity() {

    private lateinit var  session: Sessions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_tugas)
        GetApiMateri()

        fab_materi_dosen.setOnClickListener { TambahMateri() }
    }

    private fun TambahMateri(){
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.input_materi_dosen, null)
        val addDialog = AlertDialog.Builder(this)

        var namaMa = v.findViewById<EditText>(R.id.txtNamaMaterisd)
        var tgl = v.findViewById<EditText>(R.id.txtTanggalds)
        var jam = v.findViewById<EditText>(R.id.txtJamds)
        var ruang = v.findViewById<EditText>(R.id.txtRuangands)

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){

                dialog,_->
            TambahMateriApi(namaMa.text.toString(), tgl.text.toString(), jam.text.toString(), ruang.text.toString())

        }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->
        }
        addDialog.create()
        addDialog.show()
    }


    private fun TambahMateriApi(namaMa: String,tgl: String,jam: String,ruang: String){

        val bundle = intent.extras
        session = Sessions(this)

        var id_dosen = session.getSession("id_user")
        var id_kelas = bundle?.getString("id_kelas")

        var Ma = SendMateriApi()

        Ma.id_dosen = id_dosen.toString().toInt()
        Ma.id_kelas = id_kelas.toString().toInt()
        Ma.materi = namaMa
        Ma.tanggal = tgl
        Ma.jam = jam
        Ma.ruang = ruang

        val httpClient = httpClient()
        val apiRequest = apiRequest<MateriDs>(httpClient)
        val call = apiRequest.SendMateri(Ma)
        call.enqueue( object  : Callback<List<GetMateriDosenItem>> {
            override fun onResponse(
                call: Call<List<GetMateriDosenItem>>,
                response: Response<List<GetMateriDosenItem>>
            ) {
                tampilGithubUser(response.body()!!)
            }

            override fun onFailure(call: Call<List<GetMateriDosenItem>>, t: Throwable) {
                Log.i("coba",t.localizedMessage.toString())
            }
        })

    }

    private fun GetApiMateri(){
        val bundle = intent.extras
        session = Sessions(this)

        var id_dosen = session.getSession("id_user")
        var id_kelas = bundle?.getString("id_kelas")

        var Ma = GetMateriDsId()

        Ma.id_dosen = id_dosen.toString().toInt()
        Ma.id_kelas = id_kelas.toString().toInt()

        val httpClient = httpClient()
        val apiRequest = apiRequest<MateriDs>(httpClient)
        val call = apiRequest.GetMateri(Ma)
        call.enqueue( object  : Callback<List<GetMateriDosenItem>> {
            override fun onResponse(
                call: Call<List<GetMateriDosenItem>>,
                response: Response<List<GetMateriDosenItem>>
            ) {
                tampilGithubUser(response.body()!!)
            }

            override fun onFailure(call: Call<List<GetMateriDosenItem>>, t: Throwable) {
                Log.i("coba",t.localizedMessage.toString())
            }
        })
    }
    private fun tampilGithubUser(githubUsers: List<GetMateriDosenItem>) {
        rv_list_materi_ds.layoutManager = LinearLayoutManager(this)
        rv_list_materi_ds.adapter = ShowAdapterMateriDs(this!!, githubUsers){
            val item = it

            if(item.kondisi == "edit"){
                EditMateri(
                    item.id.toString().toInt(),
                    item.id_dosen.toString().toInt(),
                    item.idKelas.toString().toInt(),
                    item.materi.toString(),
                    item.tanggal.toString(),
                    item.jam.toString(),
                    item.ruang.toString()
                    )

            }
            if(item.kondisi == "hapus"){
                ApiHapus(item.id.toString().toInt(),item.id_dosen.toString().toInt(),item.idKelas.toString().toInt())
            }

        }
    }

    private fun EditMateri(id:Int,
                           id_dosen:Int,
                           id_kelas:Int,
                           materi:String,
                           tanggal:String,
                           jam1:String,
                           ruang1:String,
    ){
        //ini kolom
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.input_materi_dosen, null)
        val addDialog = AlertDialog.Builder(this)

        var namaMa = v.findViewById<EditText>(R.id.txtNamaMaterisd)
        var tgl = v.findViewById<EditText>(R.id.txtTanggalds)
        var jam = v.findViewById<EditText>(R.id.txtJamds)
        var ruang = v.findViewById<EditText>(R.id.txtRuangands)

        namaMa?.setText(materi)
        tgl?.setText(tanggal)
        jam?.setText(jam1)
        ruang?.setText(ruang1)

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
                dialog,_->
            editApiMateri(id,id_dosen,id_kelas,
                namaMa.text.toString(), tgl.text.toString(),
                jam.text.toString(), ruang.text.toString())
        }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->

        }
        addDialog.create()
        addDialog.show()
    }

    private fun editApiMateri(id:Int,id_dosen:Int,id_kelas:Int,namaMa: String,tgl: String,jam: String,ruang: String){

        val Ma = SendMateriDosenEdit()
        Ma.id_dosen = id_dosen
        Ma.id_kelas = id_kelas
        Ma.materi = namaMa
        Ma.tanggal = tgl
        Ma.jam = jam
        Ma.ruang = ruang

        val httpClient = httpClient()
        val apiRequest = apiRequest<MateriDs>(httpClient)
        val call = apiRequest.editKelas(id,Ma)
        call.enqueue( object  : Callback<List<GetMateriDosenItem>> {
            override fun onResponse(
                call: Call<List<GetMateriDosenItem>>,
                response: Response<List<GetMateriDosenItem>>
            ) {
                tampilGithubUser(response.body()!!)
            }

            override fun onFailure(call: Call<List<GetMateriDosenItem>>, t: Throwable) {
                Log.i("coba",t.localizedMessage.toString())
            }
        })
    }

    private fun ApiHapus(id: Int,id2: Int,id3: Int){

        val httpClient = httpClient()
        val apiRequest = apiRequest<MateriDs>(httpClient)
        val call = apiRequest.deletedMateri(id,id2,id3)
        call.enqueue( object  : Callback <List<GetMateriDosenItem>> {
            override fun onResponse(
                call: Call<List<GetMateriDosenItem>>,
                response: Response<List<GetMateriDosenItem>>
            ) {
                tampilGithubUser(response.body()!!)
            }

            override fun onFailure(call: Call<List<GetMateriDosenItem>>, t: Throwable) {
                Log.i("coba",t.localizedMessage.toString())
            }
        })
    }

    override fun onStart() {
        super.onStart()
        session = Sessions(this)
        var check_login = session.getSession("is_login")
        var level_user = session.getSession("level_user")

        if(check_login != "masuk"){
            if(level_user != "Dosen"){
                val intent = Intent(this, ActivityLogin::class.java)
                startActivity(intent)
            }
        }
    }
}