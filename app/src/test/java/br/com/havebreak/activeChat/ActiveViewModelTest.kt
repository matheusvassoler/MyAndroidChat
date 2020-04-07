package br.com.havebreak.activeChat

import br.com.havebreak.model.Contact
import br.com.havebreak.model.Message
import br.com.havebreak.repository.MessageRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.Matchers
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.hasSize
import org.junit.Assert.*
import org.junit.Test
import java.lang.RuntimeException
import org.hamcrest.CoreMatchers.`is` as Is

class ActiveViewModelTest {

    @Test
    fun mustNot_SendMessage_WhenFieldMessageIsEmpty() {
        var activeViewModel = ActiveViewModel()

        var contactLogged = Contact(1, "Matheus", "matheus", "123")
        var contactToChat = Contact(2, "Lucas", "lucas", "123")
        var messageText = ""

        try {
            activeViewModel.createMessage(messageText, contactLogged, contactToChat, 1586226450L)
            fail("Era esperada uma RunTimeException")
        } catch (exception: RuntimeException) {
            assertThat(exception.message, Is(equalTo("Mensagem não pode ser vazia")))
        }
    }

    @Test
    fun must_ReturnOneMessage_WhenMessageIsValidAndChatHasExactOneMessageBySender() {
        var activeViewModel = ActiveViewModel()
        val messageRepository = MessageRepository()

        var contactLogged = Contact(1, "Matheus", "matheus", "123")
        var contactToChat = Contact(2, "Lucas", "lucas", "123")
        var messageText = "Mensagem enviada"

        activeViewModel.createMessage(messageText, contactLogged, contactToChat, 1586226450L)
        val listOfSentMessages = messageRepository.getMessageListBetweenTwoContacts(contactLogged, contactToChat)

        assertThat(listOfSentMessages, hasSize(1))
        assertThat(listOfSentMessages, hasItem(Message("Mensagem enviada", contactLogged, contactToChat, 1586226450L)))
    }
}