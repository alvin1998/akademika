package com.AdaInt.akademika.activity.mahasiswa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.AdaInt.akademika.R
import com.AdaInt.akademika.adapter.ShowAdapterKelasMateriMhs
import com.AdaInt.akademika.adapter.ShowAdapterMateriDs
import com.AdaInt.akademika.model.GetKelasMateri
import com.AdaInt.akademika.model.GetKelasMateriMhsItem
import com.AdaInt.akademika.model.GetMateriDosenItem
import com.AdaInt.akademika.model.GetMateriDsId
import com.AdaInt.akademika.network.GetKelasMhsMo
import com.AdaInt.akademika.network.MateriDs
import com.AdaInt.akademika.network.apiRequest
import com.AdaInt.akademika.network.httpClient
import kotlinx.android.synthetic.main.activity_materi_mhs.*
import kotlinx.android.synthetic.main.activity_tambah_tugas.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Materi_mhs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materi_mhs)
        getMateriMh()
    }

    private fun getMateriMh(){
        val bundle = intent.extras

        var id_dosen = bundle?.getString("id_dosen")
        var id_kelas = bundle?.getString("id_kelas")

        var Ma = GetKelasMateri()

        Ma.id_dosen = id_dosen.toString().toInt()
        Ma.id_kelas = id_kelas.toString().toInt()

        val httpClient = httpClient()
        val apiRequest = apiRequest<GetKelasMhsMo>(httpClient)
        val call = apiRequest.GetMateri(Ma)
        call.enqueue( object  : Callback<List<GetKelasMateriMhsItem>> {
            override fun onResponse(
                call: Call<List<GetKelasMateriMhsItem>>,
                response: Response<List<GetKelasMateriMhsItem>>
            ) {
                tampilGithubUser(response.body()!!)
            }

            override fun onFailure(call: Call<List<GetKelasMateriMhsItem>>, t: Throwable) {
                Log.i("coba",t.localizedMessage.toString())
            }
        })
    }
    private fun tampilGithubUser(githubUsers: List<GetKelasMateriMhsItem>) {
        rv_list_kelas_materi_mhs.layoutManager = LinearLayoutManager(this)
        rv_list_kelas_materi_mhs.adapter = ShowAdapterKelasMateriMhs(this!!, githubUsers){
            val item = it


        }
    }
}