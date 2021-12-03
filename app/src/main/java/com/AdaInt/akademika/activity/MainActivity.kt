package com.AdaInt.akademika.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.AdaInt.akademika.R
import com.AdaInt.akademika.adapter.ShowAdapterUser
import com.AdaInt.akademika.adapter.UserModelItem
import com.AdaInt.akademika.model.RequestUser
import com.AdaInt.akademika.network.*
import com.AdaInt.akademika.util.tampilToast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callApiGetGithubUser()
        initAction()
    }

    private fun initAction(){
        btnSimpan.setOnClickListener {
            postUserApi()
        }
        btnUpdate.setOnClickListener {
            editUserApi()
        }
        btnHapus.setOnClickListener {
            deletedUserApi()
        }
    }


    private fun deletedUserApi(){
        val httpClient = httpClient()
        val apiRequest = apiRequest<CreateData>(httpClient)
        val call = apiRequest.deletedUser(txtid.text.toString().toInt())
        call.enqueue( object  : Callback <List<UserModelItem>> {
            override fun onResponse(
                call: Call<List<UserModelItem>>,
                response: Response<List<UserModelItem>>
            ) {
                tampilGithubUser(response.body()!!)
            }

            override fun onFailure(call: Call<List<UserModelItem>>, t: Throwable) {
                Log.i("coba",t.localizedMessage.toString())
            }
        })
    }

    private fun editUserApi(){
        var userReg = RequestUser()
        userReg.username = txtName.text.toString()
        userReg.email = txtEmail.text.toString()
        userReg.password = txtPassword.text.toString()
        userReg.level = txtlevel.text.toString()

        val httpClient = httpClient()
        val apiRequest = inputData<CreateData>(httpClient)
        val call = apiRequest.editUser(txtid.text.toString().toInt(),userReg)
        call.enqueue( object  : Callback <List<UserModelItem>> {
            override fun onResponse(
                call: Call<List<UserModelItem>>,
                response: Response<List<UserModelItem>>
            ) {
                Log.i("coba","berhasil ")
                tampilToast(this@MainActivity, "Data TerUpdate")
                tampilGithubUser(response.body()!!)
            }

            override fun onFailure(call: Call<List<UserModelItem>>, t: Throwable) {
                Log.i("coba","gagal")
                Log.i("coba",t.localizedMessage.toString())
            }
        })
    }

    private fun postUserApi(){

        var userReg = RequestUser()
        userReg.username = txtName.text.toString()
        userReg.email = txtEmail.text.toString()
        userReg.password = txtPassword.text.toString()
        userReg.level = txtlevel.text.toString()

        val httpClient = httpClient()
        val apiRequest = inputData<CreateData>(httpClient)
        val call = apiRequest.createData(userReg)
        call.enqueue( object  : Callback <List<UserModelItem>> {
            override fun onResponse(
                call: Call<List<UserModelItem>>,
                response: Response<List<UserModelItem>>
            ) {
                Log.i("coba","berhasil")
                tampilToast(this@MainActivity, "Data Tersimpan")
                tampilGithubUser(response.body()!!)
            }

            override fun onFailure(call: Call<List<UserModelItem>>, t: Throwable) {
                Log.i("coba","gagal")
                Log.i("coba",t.localizedMessage.toString())
            }
        })
    }

    private fun callApiGetGithubUser() {
        val httpClient = httpClient()
        val apiRequest = apiRequest<GetUserA>(httpClient)
        val call = apiRequest.getUsers()
        call.enqueue( object  : Callback <List<UserModelItem>> {
            override fun onResponse(
                call: Call<List<UserModelItem>>,
                response: Response<List<UserModelItem>>
            ) {
                tampilGithubUser(response.body()!!)
            }

            override fun onFailure(call: Call<List<UserModelItem>>, t: Throwable) {
               Log.i("coba",t.localizedMessage.toString())
            }
        })

    }
    private fun tampilGithubUser(githubUsers: List<UserModelItem>) {
        rv_listMyFreinds.layoutManager = LinearLayoutManager(this)
        rv_listMyFreinds.adapter = ShowAdapterUser(this!!, githubUsers){
            val githubUser = it
            tampilToast(this, githubUser.username.toString())
        }
    }



}


