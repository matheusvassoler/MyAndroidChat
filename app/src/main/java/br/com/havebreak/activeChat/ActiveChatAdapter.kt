package br.com.havebreak.activeChat

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.havebreak.R
import br.com.havebreak.model.Contact
import br.com.havebreak.model.Message
import kotlinx.android.synthetic.main.my_message.view.*
import kotlinx.android.synthetic.main.their_message.view.*
import java.util.*

class ActiveChatAdapter(var loggedContact: Contact, var contactToChat: Contact, var messageList: List<Message>) : RecyclerView.Adapter<AbstractViewHolder>() {
    companion object {
        val TYPE_MY_MESSAGE = 1
        val TYPE_THEIR_MESSAGE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
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

    override fun onBindViewHolder(holder: AbstractViewHolder, position: Int) {
        val message:Message = messageList[position]
        holder.bind(message)
    }

    override fun getItemViewType(position: Int): Int {
        var message: Message = messageList.get(position)
        if(message.sender.id == loggedContact.id) {
            return TYPE_MY_MESSAGE
        } else {
            return TYPE_THEIR_MESSAGE
        }
    }

    class MyMessageViewHolder(itemView: View): AbstractViewHolder(itemView) {
        private val txtMessage:TextView = itemView.my_message_message
        private val txtMessageTime:TextView = itemView.my_message_time
        val calendar = Calendar.getInstance()

        override fun bind(message: Message) {
            val timeEpoch = Date(message.dateTime * 1000L)
            calendar.time = timeEpoch

            txtMessage.text = message.text

            if(DateFormat.is24HourFormat(itemView.context)) {
                var hour = calendar.get(Calendar.HOUR_OF_DAY)
                var minute = calendar.get(Calendar.MINUTE)
                val timeFormatted = String.format("%02d:%02d", hour, minute)
                txtMessageTime.text = timeFormatted
            } else {
                var hour = calendar.get(Calendar.HOUR)
                var minute = calendar.get(Calendar.MINUTE)
                var timeFormatted = String.format("%02d:%02d", hour, minute)

                if(calendar.get(Calendar.AM_PM) == 0) {
                    timeFormatted = timeFormatted + "AM"
                } else if (calendar.get(Calendar.AM_PM) == 1) {
                    timeFormatted = timeFormatted + "PM"
                }

                txtMessageTime.text = timeFormatted
            }
        }
    }

    class TheirMessageViewHolder(itemView: View): AbstractViewHolder(itemView) {
        private val txtMessage:TextView = itemView.their_message_message

        override fun bind(message: Message) {
            txtMessage.text = message.text
        }
    }

}
