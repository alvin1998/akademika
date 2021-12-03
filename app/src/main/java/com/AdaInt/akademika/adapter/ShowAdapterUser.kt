package com.AdaInt.akademika.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.AdaInt.akademika.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_item.*



class ShowAdapterUser(private val context: Context, private val items:
List<UserModelItem>, private val listener: (UserModelItem)-> Unit) :
    RecyclerView.Adapter<ShowAdapterUser.ViewHolder>() {
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
        fun bindItem(item: UserModelItem, listener: (UserModelItem) -> Unit) {
            txtFriendName.text = item.username
            txtFriendEmail.text = item.email

            containerView.setOnClickListener { listener(item) }
        }
    }
}