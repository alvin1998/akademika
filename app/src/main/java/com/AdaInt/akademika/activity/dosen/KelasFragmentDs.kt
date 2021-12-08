package com.AdaInt.akademika.activity.dosen

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.AdaInt.akademika.R
import com.AdaInt.akademika.activity.login.ActivityLogin
import com.AdaInt.akademika.adapter.ShowKelasDosenAdapter
import com.AdaInt.akademika.adapter.UserModelItem
import com.AdaInt.akademika.helper.Sessions
import com.AdaInt.akademika.model.GetKelasDosenItem
import com.AdaInt.akademika.model.RequestGetKelasDosen
import com.AdaInt.akademika.model.RequestTambahKelasDosen
import com.AdaInt.akademika.model.editKelasDosen
import com.AdaInt.akademika.network.*
import com.AdaInt.akademika.util.tampilToast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_kelas_ds.*
import kotlinx.android.synthetic.main.item_kelas_dosen.*
import org.jetbrains.annotations.Nullable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KelasFragmentDs : Fragment(){

    private lateinit var  session: Sessions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kelas_ds, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callApiGetKelasDosen()
        fab_dosen.setOnClickListener{
            InputKelas()
        }

    }
    private fun InputKelas(){

        val inflter = LayoutInflater.from(requireContext())
        val v = inflter.inflate(R.layout.input_kelas_dosen_tost, null)
        val addDialog = AlertDialog.Builder(requireContext())

        val namekelas = v.findViewById<EditText>(R.id.txtNamaKelas)
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){

            dialog,_->
            tambahKelas(namekelas.text.toString())

        }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->
        }
        addDialog.create()
        addDialog.show()
    }
    private fun tambahKelas(nameKelas : String){

        session = Sessions(requireContext())
        var id_user = session.getSession("id_user")

        var addKelas  =  RequestTambahKelasDosen()
        addKelas.id_dosen = id_user.toString().toInt()
        addKelas.kelas = nameKelas

        val httpClient = httpClient()
        val apiRequest = inputData<GetKelasDosen>(httpClient)
        val call = apiRequest.addKelasDs(addKelas)
        call.enqueue( object  : Callback <List<GetKelasDosenItem>> {
            override fun onResponse(
                call: Call<List<GetKelasDosenItem>>,
                response: Response<List<GetKelasDosenItem>>
            ) {
                tampilGithubUser(response.body()!!)
                Snackbar.make(
                    fab_dosen,
                    "Data Kelas Tersimpan",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<List<GetKelasDosenItem>>, t: Throwable) {
                Log.i("coba","gagal")
                Log.i("coba",t.localizedMessage.toString())
            }
        })
    }

    private fun callApiGetKelasDosen() {

        session = Sessions(requireContext())
        var id_user = session.getSession("id_user")

        var getKelas  =  RequestGetKelasDosen()
        getKelas.id_dosen = id_user.toString().toInt()

        val httpClient = httpClient()
        val apiRequest = apiRequest<GetKelasDosen>(httpClient)
        val call = apiRequest.getKelasDs(getKelas)
        call.enqueue( object  : Callback<List<GetKelasDosenItem>> {
            override fun onResponse(
                call: Call<List<GetKelasDosenItem>>,
                response: Response<List<GetKelasDosenItem>>
            ) {
                tampilGithubUser(response.body()!!)

            }

            override fun onFailure(call: Call<List<GetKelasDosenItem>>, t: Throwable) {
                Log.i("coba",t.localizedMessage.toString())
            }
        })
    }

    private fun tampilGithubUser(githubUsers: List<GetKelasDosenItem>) {
        rv_list_kelas_ds.layoutManager = LinearLayoutManager(requireContext())
        rv_list_kelas_ds.adapter = ShowKelasDosenAdapter(requireContext(), githubUsers){
            val githubUser = it

            var kondisi = githubUser.kondisi
            if(kondisi == "edit"){
            editKelas(githubUser.id.toString(),githubUser.id_dosen.toString() )
            }
            if(kondisi == "hapus"){
                KelasDeleted(githubUser.id.toString().toInt())
            }
        }
    }


    private fun editKelas(id : String, id_dosen : String){
        //ini kolom
        val inflter = LayoutInflater.from(context)
        val v = inflter.inflate(R.layout.input_kelas_dosen_tost, null)
        val addDialog = AlertDialog.Builder(context)

        val namekelas = v.findViewById<EditText>(R.id.txtNamaKelas)
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
                dialog,_->
            editApiKelas(id,id_dosen,namekelas.text.toString())
        }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->

        }
        addDialog.create()
        addDialog.show()
    }

    private fun KelasDeleted(id : Int){

        val httpClient = httpClient()
        val apiRequest = apiRequest<GetKelasDosen>(httpClient)
        val call = apiRequest.deletedKelas(id)
        call.enqueue( object  : Callback <List<GetKelasDosenItem>> {
            override fun onResponse(
                call: Call<List<GetKelasDosenItem>>,
                response: Response<List<GetKelasDosenItem>>
            ) {
                tampilGithubUser(response.body()!!)

            }
            override fun onFailure(call: Call<List<GetKelasDosenItem>>, t: Throwable) {
                Log.i("coba",t.localizedMessage.toString())
            }
        })
    }


    private fun editApiKelas(id : String, id_dosen : String, nama_kelas : String){

        var kelasReg = editKelasDosen()
        kelasReg.id_dosen = id_dosen.toInt()
        kelasReg.kelas = nama_kelas


        val httpClient = httpClient()
        val apiRequest = apiRequest<GetKelasDosen>(httpClient)
        val call = apiRequest.editKelas(id.toString().toInt(),kelasReg)
        call.enqueue( object  : Callback<List<GetKelasDosenItem>> {
            override fun onResponse(
                call: Call<List<GetKelasDosenItem>>,
                response: Response<List<GetKelasDosenItem>>
            ) {
                tampilGithubUser(response.body()!!)
            }
            override fun onFailure(call: Call<List<GetKelasDosenItem>>, t: Throwable) {
                Log.i("coba",t.localizedMessage.toString())
            }
        })
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