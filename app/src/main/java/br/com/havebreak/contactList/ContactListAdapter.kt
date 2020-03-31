package br.com.havebreak.contactList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.com.havebreak.R
import br.com.havebreak.model.Contact

class ContactListAdapter(var contactList: List<Contact>?, var loggedUserId: Long) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var contact:Contact = contactList!!.get(position)
        var view:View = LayoutInflater.from(parent!!.context).inflate(R.layout.contact_list, parent, false)

        var name:TextView = view.findViewById(R.id.contact_list_contact_name)
        if(contact.id != loggedUserId) {
            name.text = contact.name
        } else {
            name.text = contact.name + " (Você)"
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return contactList!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return contactList!!.get(position).id
    }

    override fun getCount(): Int {
        return contactList!!.count()
    }

}
