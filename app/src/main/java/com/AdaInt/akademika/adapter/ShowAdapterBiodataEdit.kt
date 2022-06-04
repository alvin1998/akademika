package com.AdaInt.akademika.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.AdaInt.akademika.R
import com.AdaInt.akademika.model.ResponseModelBiodataItem
import com.AdaInt.akademika.model.ResponseShowBiodataEditItem
import kotlinx.android.extensions.LayoutContainer


class ShowAdapterBiodataEdit(private val context: Context, private val items:
List<ResponseModelBiodataItem>, private val listener: (ResponseModelBiodataItem)-> Unit) :
    RecyclerView.Adapter<ShowAdapterBiodataEdit.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(
            R.layout.user_item,
            parent, false))
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }
    class ViewHolder(val context: Context, override val containerView : View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(item: ResponseModelBiodataItem, listener: (ResponseModelBiodataItem) -> Unit) {

        }
    }
}