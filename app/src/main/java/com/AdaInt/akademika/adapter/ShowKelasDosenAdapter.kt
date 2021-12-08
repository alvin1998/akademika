package com.AdaInt.akademika.adapter

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.AdaInt.akademika.R
import com.AdaInt.akademika.model.GetKelasDosenItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_kelas_dosen.*


class ShowKelasDosenAdapter(private val context: Context, private val items:
List<GetKelasDosenItem> ,private val listener: (GetKelasDosenItem)-> Unit) :
    RecyclerView.Adapter<ShowKelasDosenAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(
            R.layout.item_kelas_dosen,
            parent, false))
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position) ,listener)
    }
    class ViewHolder(val context: Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: GetKelasDosenItem, listener: (GetKelasDosenItem) -> Unit) {
            txtkelasDosen.text = item.kelas


            mMenus.setOnClickListener {
                popupMenus(it,item,listener)
            }
//            containerView.setOnClickListener { listener(item) }
        }

        private fun popupMenus(v: View, item: GetKelasDosenItem, listener: (GetKelasDosenItem) -> Unit) {
            val popupMenus = PopupMenu(context,v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.ediText->{

                        item.kondisi = "edit"
                        listener(item)
                        true
                    }
                    R.id.delete->{
                        /**set delete*/
                        AlertDialog.Builder(context)
                            .setTitle("Hapus")
                            .setMessage("Kamu yakin mau hapus kelas ini")
                            .setPositiveButton("Yes"){
                                    dialog,_->
                                Log.i("coba", "1")
                                item.kondisi = "hapus"
                                listener(item)
                                dialog.dismiss()
                            }
                            .setNegativeButton("No"){
                                    dialog,_->
                                dialog.dismiss()
                            }
                            .create()
                            .show()


                        true
                    }
                    R.id.tugastxt->{
                        Toast.makeText(context,"tugas",Toast.LENGTH_SHORT).show()
                        true
                    }else-> true
                }
            }
            popupMenus.show()
        }
    }
}