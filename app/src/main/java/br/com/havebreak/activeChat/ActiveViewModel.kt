package br.com.havebreak.activeChat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.havebreak.model.Contact
import br.com.havebreak.model.Message
import br.com.havebreak.repository.MessageRepository
import br.com.havebreak.util.DateUtil
import java.lang.Exception
import java.lang.RuntimeException

class ActiveViewModel: ViewModel() {
    val messageRepository: MessageRepository = MessageRepository()
    val messageList:MutableLiveData<List<Message>> = MutableLiveData()

    fun getMessageList(contactLogged:Contact, contactToChat:Contact) {
        val messageList:List<Message> = messageRepository.getMessageListBetweenTwoContacts(contactLogged, contactToChat)
        for (message in messageList) {
            if(message.sender.id != contactLogged.id) {
                message.messageWasRead = true
            }
        }
        this.messageList.value = messageList
    }

    fun createMessage(messageText:String, loggedContact:Contact, contactToChat: Contact, epochTimestampSeconds: Long) {
        var messageText = messageText
        messageText = messageText.trim()

        if(messageText.equals("")) {
            throw RuntimeException("Mensagem não pode ser vazia")
        }
        var message:Message = Message(messageText, loggedContact, contactToChat, epochTimestampSeconds)
        messageRepository.createMessage(message)
    }
}