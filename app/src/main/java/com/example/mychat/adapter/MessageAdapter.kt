package com.example.mychat.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mychat.R
import com.example.mychat.data.model.Messages



class MessageAdapter() : RecyclerView.Adapter<MessageAdapter.ItemViewHolder>() {

    private var dataset = listOf<Messages>()

    fun submitList(list: List<Messages>) {
        Log.d("MessageAdapter", "Adapter BANG")
        dataset = list
        notifyItemInserted(0)
    }

    /**
     * der ViewHolder umfasst die View uns stellt einen Listeneintrag dar
     */
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvText: TextView = itemView.findViewById(R.id.tvMessageText)
        val cardText: CardView = itemView.findViewById(R.id.cvMessage)

        val tvTextAnswer: TextView = itemView.findViewById(R.id.tvMessageText_answer)
        val cardTextAnswer: CardView = itemView.findViewById(R.id.cvMessage_answer)
    }

    /**
     * hier werden neue ViewHolder erstellt
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_message, parent, false)

        return ItemViewHolder(itemLayout)
    }

    /**
     * hier findet der Recyclingprozess statt
     * die vom ViewHolder bereitgestellten Parameter erhalten die Information des Listeneintrags
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // Die aktuelle Message wird aus dem Dataset geholt
        val message = dataset[position]

        if (message.isAnswer) {
            holder.cardTextAnswer.visibility = View.VISIBLE
            holder.cardText.visibility = View.GONE

            holder.tvTextAnswer.text = message.message
        } else {
            holder.cardTextAnswer.visibility = View.GONE
            holder.cardText.visibility = View.VISIBLE

            holder.tvText.text = message.message
        }
    }

    /**
     * damit der LayoutManager weiß, wie lang die Liste ist
     */
    override fun getItemCount(): Int {
        return dataset.size
    }
}
