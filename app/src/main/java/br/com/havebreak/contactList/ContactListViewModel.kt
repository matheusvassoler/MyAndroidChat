package br.com.havebreak.contactList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.havebreak.model.Contact
import br.com.havebreak.repository.ContactRepository

class ContactListViewModel: ViewModel() {
    var contactRepository:ContactRepository = ContactRepository()
    var contactList:MutableLiveData<List<Contact>> = MutableLiveData()

    fun getContactList() {
        var contactList: List<Contact>  = contactRepository.getContactList()
        this.contactList.value = contactList
    }

}