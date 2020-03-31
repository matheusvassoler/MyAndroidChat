package br.com.havebreak.contactList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.havebreak.R
import br.com.havebreak.activeChat.ActiveChatActivity
import br.com.havebreak.login.LoginActivity
import br.com.havebreak.model.Contact
import kotlinx.android.synthetic.main.activity_contact_list.*

class ContactListActivity : AppCompatActivity() {

    private lateinit var viewModel: ContactListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        title = "Contatos"

        var intent: Intent = intent as Intent
        var contactLogged:Contact = intent.getSerializableExtra("LOGGED_CONTACT") as Contact

        var contactListView:ListView = activity_contact_listView as ListView

        viewModel = ViewModelProviders.of(this).get(ContactListViewModel::class.java)
        viewModel.contactList.observe(this, Observer {
            contactListView.apply {
                adapter = ContactListAdapter(it, contactLogged.id)
            }
        })

        viewModel.getContactList()

        contactListView.setOnItemClickListener { parent, view, position, id ->
            val contact:Contact = parent.getItemAtPosition(position) as Contact
            var intent: Intent = Intent(ContactListActivity@this, ActiveChatActivity::class.java) as Intent
            intent.putExtra("LOGGED_CONTACT", contactLogged)
            intent.putExtra("CONTACT_TO_CHAT", contact)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_contact_list_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId: Int = item.itemId
        if(itemId == R.id.activity_contact_list_menu_exit) {
            val intent:Intent = Intent(this, LoginActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
