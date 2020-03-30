package br.com.havebreak.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import br.com.havebreak.R
import br.com.havebreak.contactList.ContactListActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var logginButton:CardView
    private lateinit var editUsername:EditText
    private lateinit var editPassword:EditText
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        configureLoginFields()

        logginButton.setOnClickListener {
            viewModel.login(editUsername.text.toString(), editPassword.text.toString())
        }

        viewModel.contact.observe(this, Observer {
            val intent:Intent = Intent(this, ContactListActivity::class.java)
            intent.putExtra("LOGGED_CONTACT", viewModel.contact.value)
            startActivity(intent)
        })
    }

    private fun configureLoginFields() {
        logginButton = activity_login_button_login as CardView
        editUsername = activity_login_edit_username as EditText
        editPassword = activity_login_edit_password as EditText
    }
}
