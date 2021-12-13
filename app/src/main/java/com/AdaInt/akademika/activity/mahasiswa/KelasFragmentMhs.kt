package com.AdaInt.akademika.activity.mahasiswa

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.AdaInt.akademika.R
import com.AdaInt.akademika.activity.dosen.TambahTugas
import com.AdaInt.akademika.activity.login.ActivityLogin
import com.AdaInt.akademika.adapter.ShowAdapterKelasMhs
import com.AdaInt.akademika.adapter.ShowAdapterUser
import com.AdaInt.akademika.adapter.UserModelItem
import com.AdaInt.akademika.helper.Sessions
import com.AdaInt.akademika.model.*
import com.AdaInt.akademika.network.CreateData
import com.AdaInt.akademika.network.GetKelasMhsMo
import com.AdaInt.akademika.network.httpClient
import com.AdaInt.akademika.network.inputData
import com.AdaInt.akademika.util.tampilToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.rv_listMyFreinds
import kotlinx.android.synthetic.main.fragment_kelas_mhs.*
import org.jetbrains.annotations.Nullable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KelasFragmentMhs : Fragment() {

    private lateinit var  session: Sessions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kelas_mhs, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GetMHS()

        fab_mhs_m.setOnClickListener {
            btnTambahKelas()
        }
    }

    private fun btnTambahKelas(){
        val inflter = LayoutInflater.from(requireContext())
        val v = inflter.inflate(R.layout.input_kelas_mhs, null)
        val addDialog = AlertDialog.Builder(requireContext())

        val idKelas = v.findViewById<EditText>(R.id.txtIdKelas)
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){

                dialog,_->
            tambahKelas(idKelas.text.toString().toInt())

        }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->
        }
        addDialog.create()
        addDialog.show()
    }

    private fun tambahKelas(idKelas : Int){

        session = Sessions(requireContext())
        var id_user = session.getSession("id_user")

        var userID = SendIdDataKelas()
        userID.id_mhs = id_user.toString().toInt()
        userID.id_kelas = idKelas

        val httpClient = httpClient()
        val apiRequest = inputData<GetKelasMhsMo>(httpClient)
        val call = apiRequest.SendKelasM(userID)
        call.enqueue( object  : Callback<List<GetKelasMhsItem>> {
            override fun onResponse(
                call: Call<List<GetKelasMhsItem>>,
                response: Response<List<GetKelasMhsItem>>
            ) {
                tampilGithubUser(response.body()!!)
            }

            override fun onFailure(call: Call<List<GetKelasMhsItem>>, t: Throwable) {

            }
        })
    }

    private fun GetMHS(){

        session = Sessions(requireContext())
        var id_user = session.getSession("id_user")

        var userID = SendIdMhs()
        userID.id = id_user.toString().toInt()

        val httpClient = httpClient()
        val apiRequest = inputData<GetKelasMhsMo>(httpClient)
        val call = apiRequest.GetKelasM(userID)
        call.enqueue( object  : Callback<List<GetKelasMhsItem>> {
            override fun onResponse(
                call: Call<List<GetKelasMhsItem>>,
                response: Response<List<GetKelasMhsItem>>
            ) {
                tampilGithubUser(response.body()!!)
            }

            override fun onFailure(call: Call<List<GetKelasMhsItem>>, t: Throwable) {

            }
        })
    }

    private fun tampilGithubUser(githubUsers: List<GetKelasMhsItem>) {
        rv_list_kelas_mhs.layoutManager = LinearLayoutManager(requireContext())
        rv_list_kelas_mhs.adapter = ShowAdapterKelasMhs(requireContext(), githubUsers){
            val item = it

            if(item.kondisi.equals("lihat")){
                val intent = Intent(this.context, Materi_mhs::class.java)
                val bundle = Bundle()
                bundle.putString("id_kelas" , item.id.toString())
                bundle.putString("id_dosen" , item.id_dosen.toString())
                intent.putExtras(bundle)
                startActivity(intent)
            }
            if(item.kondisi.equals("hapus")){
                hapusKelas(item.id_kelas_mhs.toString().toInt())
            }
        }
    }
    private fun PindahMateriMhs(id_dosen : Int, id_kelas: Int){

    }

    private fun hapusKelas(id_kelas : Int){

        session = Sessions(requireContext())
        var id_user = session.getSession("id_user")

        val httpClient = httpClient()
        val apiRequest = inputData<GetKelasMhsMo>(httpClient)
        val call = apiRequest.hpsKls(id_kelas,id_user.toString().toInt())
        call.enqueue( object  : Callback<List<GetKelasMhsItem>> {
            override fun onResponse(
                call: Call<List<GetKelasMhsItem>>,
                response: Response<List<GetKelasMhsItem>>
            ) {
                tampilGithubUser(response.body()!!)
            }

            override fun onFailure(call: Call<List<GetKelasMhsItem>>, t: Throwable) {

            }
        })
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