package br.com.havebreak.activeChat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.com.havebreak.R
import br.com.havebreak.model.Contact
import br.com.havebreak.model.Message

class ActiveChatAdapter(var loggedContact: Contact, var contactToChat: Contact, var messageList: List<Message>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //var messageInflater:LayoutInflater = parent!!.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var message: Message = messageList.get(position)
        //var convertView = convertView
        var view:View
        if(message.sender.id == loggedContact.id) {
            view = LayoutInflater.from(parent!!.context).inflate(R.layout.my_message, parent, false)
            var txtMessage:TextView = view.findViewById(R.id.my_message_message)
            txtMessage.text = message.text
        } else {
            view = LayoutInflater.from(parent!!.context).inflate(R.layout.their_message, parent, false)
            var txtMessage:TextView = view.findViewById(R.id.their_message_message)
            txtMessage.text = message.text
        }

        /*
        if(message.sender.id == loggedContact.id) {
            convertView = messageInflater.inflate(R.layout.my_message, parent, false)
            var txtMessage:TextView = convertView.findViewById(R.id.my_message_message)
            txtMessage.text = message.text
        } else {
            convertView = messageInflater.inflate(R.layout.their_message, parent, false)
            var txtMessage:TextView = convertView.findViewById(R.id.their_message_message)
            txtMessage.text = message.text
        }*/

        return view
    }

    override fun getItem(position: Int): Any {
        return messageList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return messageList.count()
    }

}
