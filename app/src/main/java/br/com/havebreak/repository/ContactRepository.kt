package br.com.havebreak.repository

import br.com.havebreak.model.Contact
import java.util.*
import kotlin.collections.ArrayList

class ContactRepository {
    companion object {
        var contactList:ArrayList<Contact> = ArrayList<Contact>(Arrays.asList(
            Contact(1, "Matheus", "matheus", "123"),
            Contact(2, "Lucas", "lucas", "123"),
            Contact(3, "Rose", "rose", "123"),
            Contact(4, "Letícia", "leticia", "123"),
            Contact(5, "Ana", "ana", "123")
        ))
        var incrementedId:Int = 1
    }

    fun getContactList(): List<Contact> {
        return ArrayList<Contact>(contactList)
    }

    fun login(username: String, password: String): Contact? {
        for (contact in contactList) {
            if(contact.username.equals(username) && contact.password.equals(password)) {
                return contact
            }
        }
        return null
    }
}