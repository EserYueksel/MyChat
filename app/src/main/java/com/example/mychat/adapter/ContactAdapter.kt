package com.example.mychat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mychat.data.model.Contact
import com.example.mychat.ui.HomeFragmentDirections
import com.example.mychat.R



/**
 * Diese Klasse organisiert mithilfe der ViewHolder Klasse das Recycling
 */
class ContactAdapter() : RecyclerView.Adapter<ContactAdapter.ItemViewHolder>() {

    private var dataset: List<Contact> = listOf()

    fun submitList(list: List<Contact>) {
        dataset = list
        notifyDataSetChanged()
    }

    /**
     * der ViewHolder umfasst die View uns stellt einen Listeneintrag dar
     */
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPicture: ImageView = itemView.findViewById(R.id.ivContactPicture)
        val tvName: TextView = itemView.findViewById(R.id.tvContactName)
        val tvLastMessage: TextView = itemView.findViewById(R.id.tvContactLastMessage)
        val clContact: ConstraintLayout = itemView.findViewById(R.id.clContact)
    }

    /**
     * hier werden neue ViewHolder erstellt
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_contact, parent, false)

        return ItemViewHolder(itemLayout)
    }

    /**
     * hier findet der Recyclingprozess statt
     * die vom ViewHolder bereitgestellten Parameter erhalten die Information des Listeneintrags
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // Der aktuelle Kontakt wird aus dem Dataset geholt
        val contact = dataset[position]

        // Das Profilbild und der Profilname werden gesetzt
        holder.ivPicture.setImageResource(contact.imageResId)
        holder.tvName.text = contact.name

        // Falls Nachrichten in der Liste des Kontakts existieren wird die neuste Nachricht angezeigt
        if (contact.chatHistory.size > 0) {
            holder.tvLastMessage.text = contact.chatHistory[0].message
        }

        // Das komplette ConstraintLayout bekommt einen Click Listener, in dem zum ChatFragment navigiert wird
        holder.clContact.setOnClickListener {
            holder.itemView.findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToChatFragment(contact.id)
            )
        }
    }

    /**
     * damit der LayoutManager weiß, wie lang die Liste ist
     */
    override fun getItemCount(): Int {
        return dataset.size
    }
}
