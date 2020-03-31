package br.com.havebreak.active_chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ListView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.havebreak.R
import br.com.havebreak.model.Contact
import kotlinx.android.synthetic.main.activity_active_chat.*
import kotlin.math.log

class ActiveChatActivity : AppCompatActivity() {
    private lateinit var viewModel: ActiveViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_chat)

        val intent: Intent = intent as Intent
        val loggedContact: Contact = intent.getSerializableExtra("LOGGED_CONTACT") as Contact
        val contactToChat: Contact = intent.getSerializableExtra("CONTACT_TO_CHAT") as Contact

        setTitle(contactToChat.name)

        val messageListView:ListView = activity_active_chat_listView as ListView

        viewModel = ViewModelProviders.of(this).get(ActiveViewModel::class.java)
        viewModel.messageList.observe(this, Observer {
            messageListView.apply {
                adapter = ActiveChatAdapter(loggedContact, contactToChat, it)
            }
        })

        viewModel.getMessageList(loggedContact, contactToChat)

        val buttonSendMessage:CardView = activity_button_send_message as CardView
        buttonSendMessage.setOnClickListener {
            val txtMessage:EditText = activity_active_chat_input_new_message as EditText
            viewModel.createMessage(txtMessage.text.toString(), loggedContact, contactToChat)
            viewModel.getMessageList(loggedContact, contactToChat)
            txtMessage.setText("")
        }
    }
}
