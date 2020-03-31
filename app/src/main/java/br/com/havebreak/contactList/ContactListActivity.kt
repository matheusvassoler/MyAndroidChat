package br.com.havebreak.contactList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import br.com.havebreak.R
import br.com.havebreak.model.Contact
import kotlinx.android.synthetic.main.activity_contact_list.*

class ContactListActivity : AppCompatActivity() {

    private lateinit var viewModel: ContactListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

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
    }
}
