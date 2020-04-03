package br.com.havebreak.activeChat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.havebreak.R
import br.com.havebreak.model.Contact
import br.com.havebreak.model.Message
import kotlinx.android.synthetic.main.my_message.view.*
import kotlinx.android.synthetic.main.their_message.view.*
import org.w3c.dom.Text

class ActiveChatAdapter(var loggedContact: Contact, var contactToChat: Contact, var messageList: List<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        val TYPE_MY_MESSAGE = 1
        val TYPE_THEIR_MESSAGE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view:View

        if(viewType == TYPE_MY_MESSAGE) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.my_message, parent, false)
            return MyMessageViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.their_message, parent, false)
            return TheirMessageViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return messageList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message:Message = messageList.get(position)

        if(getItemViewType(position) == TYPE_MY_MESSAGE) {
            (holder as MyMessageViewHolder).bindValues(message)
        } else {
            (holder as TheirMessageViewHolder).bindValues(message)
        }
    }

    override fun getItemViewType(position: Int): Int {
        var message: Message = messageList.get(position)
        if(message.sender.id == loggedContact.id) {
            return TYPE_MY_MESSAGE
        } else {
            return TYPE_THEIR_MESSAGE
        }
    }

    class MyMessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val txtMessage:TextView = itemView.my_message_message

        fun bindValues(message: Message) {
            txtMessage.text = message.text
        }
    }

    class TheirMessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val txtMessage:TextView = itemView.their_message_message

        fun bindValues(message: Message) {
            txtMessage.text = message.text
        }
    }

}
