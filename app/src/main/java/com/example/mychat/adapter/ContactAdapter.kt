package com.example.mychat.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mychat.R

// contactadapter / chat item / navigieren / chat adapter / mainviewmodel(livedata/observe)
class ContactAdapter(): RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    private var dataset = listOf<ContactAdapter>()

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val chatText = view.findViewById<TextView>(R.id.)
    }
}
