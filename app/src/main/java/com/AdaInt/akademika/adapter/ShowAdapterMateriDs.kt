package com.AdaInt.akademika.adapter

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.AdaInt.akademika.R
import com.AdaInt.akademika.model.GetKelasDosenItem
import com.AdaInt.akademika.model.GetMateriDosenItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_materi_dosen.*

class ShowAdapterMateriDs(private val context: Context, private val items:
List<GetMateriDosenItem>, private val listener: (GetMateriDosenItem)-> Unit) :
    RecyclerView.Adapter<ShowAdapterMateriDs.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(
            R.layout.item_materi_dosen,
            parent, false))
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }
    class ViewHolder(val context: Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: GetMateriDosenItem, listener: (GetMateriDosenItem) -> Unit) {
            txtJudulMateri.text =  item.materi
            txtTanggalMateri.text = " Tanggal : " +item.tanggal
            txtJamMateri.text = " Jam : " +item.jam + " WIB "
            txtRuanganMateri.text = " Tempat : " +item.ruang
            mMenusMateri.setOnClickListener {
                popupMenus(it,item,listener)
            }
//            containerView.setOnClickListener { listener(item) }
        }


    private fun popupMenus(v: View, item: GetMateriDosenItem, listener: (GetMateriDosenItem) -> Unit) {
        val popupMenus = PopupMenu(context,v)
        popupMenus.inflate(R.menu.show_menu_materi)
        popupMenus.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.ediTextM->{

                    item.kondisi = "edit"
                    listener(item)
                    true
                }
                R.id.deleteM->{
                    /**set delete*/
                    AlertDialog.Builder(context)
                        .setTitle("Hapus")
                        .setMessage("Kamu yakin mau hapus materi ini")
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
                }else-> true
            }
        }
        popupMenus.show()
    }
}
}