package br.com.havebreak.repository

import br.com.havebreak.model.Contact
import br.com.havebreak.model.Message

class MessageRepository {
    companion object {
        var messageList:MutableList<Message> = ArrayList<Message>()
    }

    fun createMessage(message:Message) {
        messageList.add(message)
    }

    fun getMessageListBetweenTwoContacts(sender: Contact, recipient: Contact): MutableList<Message> {
        var listFiltered:MutableList<Message> = ArrayList<Message>()
        for (message in messageList) {
            if((message.sender.id == sender.id && message.recipient.id == recipient.id) ||
                (message.recipient.id == sender.id && message.sender.id == recipient.id)) {
                listFiltered.add(message)
            }
        }
        return listFiltered
    }
}
