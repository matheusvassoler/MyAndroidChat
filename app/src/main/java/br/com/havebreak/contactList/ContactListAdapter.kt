package br.com.havebreak.contactList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.havebreak.R
import br.com.havebreak.model.Contact
import kotlinx.android.synthetic.main.contact_list.view.*

class ContactListAdapter(var contacts: List<Contact>, var contactLoggedId: Long, val onItemClickListener: (contact:Contact) -> Unit): RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.contact_list, parent, false);
        return ViewHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return contacts.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var contact:Contact = contacts.get(position)
        holder.bindValues(contact, contactLoggedId)
    }

    class ViewHolder(itemView: View, private val onItemClickListener: (contact: Contact) -> Unit): RecyclerView.ViewHolder(itemView) {
        private val contactNameTxt:TextView = itemView.contact_list_contact_name

        fun bindValues(contact: Contact, contactLoggedId: Long) {
            if(contact.id == contactLoggedId) {
                contactNameTxt.text = contact.name + " (Você)"
            } else {
                contactNameTxt.text = contact.name
            }

            itemView.setOnClickListener {
                onItemClickListener(contact)
            }
        }
    }

}