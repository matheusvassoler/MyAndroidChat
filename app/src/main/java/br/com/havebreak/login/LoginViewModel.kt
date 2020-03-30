package br.com.havebreak.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.havebreak.model.Contact
import br.com.havebreak.repository.ContactRepository

class LoginViewModel: ViewModel() {
    var contactRepository:ContactRepository = ContactRepository()
    val contact: MutableLiveData<Contact> = MutableLiveData()

    fun login(username: String, password: String) {
        var contact: Contact? = contactRepository.login(username, password)

        if(contact != null) {
            this.contact.value = contact
        }
    }

}