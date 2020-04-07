package br.com.havebreak.model

import java.io.Serializable

data class Contact(var id:Long, var name:String, var username:String, var password:String): Serializable,
    Comparable<Contact> {
    override fun compareTo(other: Contact): Int {
        if(name < other.name) {
            return -1;
        }
        if(name > other.name) {
            return 1;
        }
        return 0;
    }
}
