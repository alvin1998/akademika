package com.AdaInt.akademika.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.AdaInt.akademika.R
import com.AdaInt.akademika.model.GetKelasMateriMhsItem
import com.AdaInt.akademika.model.GetKelasMhsItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_kelas_materi_mhs.*
import kotlinx.android.synthetic.main.item_kelas_mhs.*

class ShowAdapterKelasMateriMhs(private val context: Context, private val items:
List<GetKelasMateriMhsItem>, private val listener: (GetKelasMateriMhsItem)-> Unit) :
    RecyclerView.Adapter<ShowAdapterKelasMateriMhs.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(
            R.layout.item_kelas_materi_mhs,
            parent, false))
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }
    class ViewHolder(val context: Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: GetKelasMateriMhsItem, listener: (GetKelasMateriMhsItem) -> Unit) {
            txtJudulMateriMhs.text = item.materi
            txtTanggalMateriMhs.text = " Tanggal : " +item.tanggal
            txtJamMateriMhs.text = " Jam : " +item.jam + " WIB "
            txtRuanganMateriMhs.text = " Tempat : " +item.ruang

//            containerView.setOnClickListener { listener(item) }
        }

     }
}