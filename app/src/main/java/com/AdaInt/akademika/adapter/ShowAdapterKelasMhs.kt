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
import com.AdaInt.akademika.model.GetKelasMhsItem
import com.AdaInt.akademika.model.GetMateriDosenItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_kelas_mhs.*
import kotlinx.android.synthetic.main.item_materi_dosen.*

class ShowAdapterKelasMhs(private val context: Context, private val items:
List<GetKelasMhsItem>, private val listener: (GetKelasMhsItem)-> Unit) :
    RecyclerView.Adapter<ShowAdapterKelasMhs.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(
            R.layout.item_kelas_mhs,
            parent, false))
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }
    class ViewHolder(val context: Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: GetKelasMhsItem, listener: (GetKelasMhsItem) -> Unit) {

            txtkelasMhs.text = item.kelas.toString()
            txtNamaDosenMhs.text = "Nama Dosen : " + item.nama

            mMenusMhs.setOnClickListener {
                popupMenus(it,item,listener)
            }
//            containerView.setOnClickListener { listener(item) }
        }


        private fun popupMenus(v: View, item: GetKelasMhsItem, listener: (GetKelasMhsItem) -> Unit) {
            val popupMenus = PopupMenu(context,v)
            popupMenus.inflate(R.menu.show_menu_kelas_mhs)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.LihatTextMhs->{

                        item.kondisi = "lihat"
                        listener(item)
                        true
                    }
                    R.id.deleteMhs->{
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
                    }else-> true
                }
            }
            popupMenus.show()
        }
    }
}