package br.com.havebreak.activeChat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.havebreak.model.Contact
import br.com.havebreak.model.Message
import br.com.havebreak.repository.MessageRepository

class ActiveViewModel: ViewModel() {
    val messageRepository: MessageRepository = MessageRepository()
    val messageList:MutableLiveData<List<Message>> = MutableLiveData()

    fun getMessageList(contactLogged:Contact, contactToChat:Contact) {
        val messageList:List<Message> = messageRepository.getMessageListBetweenTwoContacts(contactLogged, contactToChat)
        this.messageList.value = messageList
    }

    fun createMessage(messageText:String, loggedContact:Contact, contactToChat: Contact) {
        var message:Message = Message(messageText, loggedContact, contactToChat)
        messageRepository.createMessage(message)
    }
}