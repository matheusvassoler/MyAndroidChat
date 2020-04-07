package br.com.havebreak.activeChat

import android.graphics.drawable.Drawable
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.havebreak.R
import br.com.havebreak.model.Contact
import br.com.havebreak.model.Message
import br.com.havebreak.util.DateUtil
import br.com.havebreak.util.DrawableUtil
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
        private val txtMessageCheck:ImageView = itemView.my_message_check
        val calendar = Calendar.getInstance()

        override fun bind(message: Message) {
            val epochTimestampSeconds = DateUtil.convertEpochTimestampSecondsToDate(message.dateTime)
            calendar.time = epochTimestampSeconds

            val smartphoneIsIn24HourFormat = DateFormat.is24HourFormat(itemView.context)
            val formattedHour = DateUtil.formatHour(calendar, smartphoneIsIn24HourFormat)

            val messageWasRead = message.messageWasRead
            val imageDrawable: Drawable? = DrawableUtil.getDrawableMessageCheck(itemView.context, messageWasRead)

            txtMessage.text = message.text
            txtMessageTime.text = formattedHour
            txtMessageCheck.setImageDrawable(imageDrawable)
        }
    }

    class TheirMessageViewHolder(itemView: View): AbstractViewHolder(itemView) {
        private val txtMessage:TextView = itemView.their_message_message
        private val txtMessageTime:TextView = itemView.their_message_time
        val calendar = Calendar.getInstance()

        override fun bind(message: Message) {
            val epochTimestampSeconds = DateUtil.convertEpochTimestampSecondsToDate(message.dateTime)
            calendar.time = epochTimestampSeconds

            val smartphoneIsIn24HourFormat = DateFormat.is24HourFormat(itemView.context)
            val formattedHour = DateUtil.formatHour(calendar, smartphoneIsIn24HourFormat)

            txtMessage.text = message.text
            txtMessageTime.text = formattedHour
        }
    }

}
