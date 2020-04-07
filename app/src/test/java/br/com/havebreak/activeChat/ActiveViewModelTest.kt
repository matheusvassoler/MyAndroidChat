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
            fail("Era esperada uma RuntimeException")
        } catch (exception: RuntimeException) {
            assertThat(exception.message, Is(equalTo("Mensagem não pode ser vazia")))
        }
    }

    @Test
    fun must_ReturnOneMessage_WhenMessageIsValidAndChatHasExactOneMessageBySender() {
        var activeViewModel = ActiveViewModel()
        val messageRepository = MessageRepository()
        MessageRepository.messageList = ArrayList<Message>()

        var contactLogged = Contact(1, "Matheus", "matheus", "123")
        var contactToChat = Contact(2, "Lucas", "lucas", "123")
        var messageText = "Mensagem enviada"

        activeViewModel.createMessage(messageText, contactLogged, contactToChat, 1586226450L)
        val listOfSentMessages = messageRepository.getMessageListBetweenTwoContacts(contactLogged, contactToChat)

        assertThat(listOfSentMessages, hasSize(1))
        assertThat(listOfSentMessages, hasItem(Message("Mensagem enviada", contactLogged, contactToChat, 1586226450L)))
    }

    @Test
    fun mustNot_SendMessage_WhenMessageFieldHasOnlyBlankSpace() {
        var activeViewModel = ActiveViewModel()
        MessageRepository.messageList = ArrayList<Message>()

        var contactLogged = Contact(1, "Matheus", "matheus", "123")
        var contactToChat = Contact(2, "Lucas", "lucas", "123")
        var messageText = "           "

        try {
            activeViewModel.createMessage(messageText, contactLogged, contactToChat, 1586226450L)
            fail("Era esperada RuntimeException")
        } catch (exception: RuntimeException) {
            assertThat(exception.message, Is(equalTo("Mensagem não pode ser vazia")))
        }
    }

    @Test
    fun must_RemoveBlankSpaceOnLeftOfTheMessage_WhenMessageHasBlankSpaceOnLeft() {
        var activeViewModel = ActiveViewModel()
        val messageRepository = MessageRepository()
        MessageRepository.messageList = ArrayList<Message>()

        var contactLogged = Contact(1, "Matheus", "matheus", "123")
        var contactToChat = Contact(2, "Lucas", "lucas", "123")
        var messageText = "           Olá! Tudo bem?"

        activeViewModel.createMessage(messageText, contactLogged, contactToChat, 1586226450L)
        val listOfSentMessages = messageRepository.getMessageListBetweenTwoContacts(contactLogged, contactToChat)

        assertThat(listOfSentMessages, hasSize(1))
        assertThat(listOfSentMessages[0].text, Is(equalTo("Olá! Tudo bem?")))
    }

    @Test
    fun must_RemoveBlankSpaceOnRightOfTheMessage_WhenMessageHasBlankSpaceOnRight() {
        var activeViewModel = ActiveViewModel()
        val messageRepository = MessageRepository()
        MessageRepository.messageList = ArrayList<Message>()

        var contactLogged = Contact(1, "Matheus", "matheus", "123")
        var contactToChat = Contact(2, "Lucas", "lucas", "123")
        var messageText = "Olá! Tudo bem?                   "

        activeViewModel.createMessage(messageText, contactLogged, contactToChat, 1586226450L)
        val listOfSentMessages = messageRepository.getMessageListBetweenTwoContacts(contactLogged, contactToChat)

        assertThat(listOfSentMessages, hasSize(1))
        assertThat(listOfSentMessages[0].text, Is(equalTo("Olá! Tudo bem?")))
    }

    @Test
    fun must_RemoveBlankSpaceOnLeftAndRightOfTheMessage_WhenMessageHasBlankSpaceOnRightAndLeftAtTheSameTime() {
        var activeViewModel = ActiveViewModel()
        val messageRepository = MessageRepository()
        MessageRepository.messageList = ArrayList<Message>()

        var contactLogged = Contact(1, "Matheus", "matheus", "123")
        var contactToChat = Contact(2, "Lucas", "lucas", "123")
        var messageText = "                    Olá! Tudo bem?                   "

        activeViewModel.createMessage(messageText, contactLogged, contactToChat, 1586226450L)
        val listOfSentMessages = messageRepository.getMessageListBetweenTwoContacts(contactLogged, contactToChat)

        assertThat(listOfSentMessages, hasSize(1))
        assertThat(listOfSentMessages[0].text, Is(equalTo("Olá! Tudo bem?")))
    }

    @Test
    fun mustNot_RemoveAdditionalBlankSpaceBetweenStringOfTheMessage_WhenMessageHasAdditionalBlankSpaceBetweenString() {
        var activeViewModel = ActiveViewModel()
        val messageRepository = MessageRepository()
        MessageRepository.messageList = ArrayList<Message>()

        var contactLogged = Contact(1, "Matheus", "matheus", "123")
        var contactToChat = Contact(2, "Lucas", "lucas", "123")
        var messageText = "Olá! Tudo      bem?      Quem está falando é o    Matheus"

        activeViewModel.createMessage(messageText, contactLogged, contactToChat, 1586226450L)
        val listOfSentMessages = messageRepository.getMessageListBetweenTwoContacts(contactLogged, contactToChat)

        assertThat(listOfSentMessages, hasSize(1))
        assertThat(listOfSentMessages[0].text, Is(equalTo("Olá! Tudo      bem?      Quem está falando é o    Matheus")))
    }

    @Test
    fun mustNot_AllowUserSeeMessagesBetweenTwoOtherUsers() {
        var activeViewModel = ActiveViewModel()
        val messageRepository = MessageRepository()
        MessageRepository.messageList = ArrayList<Message>()

        var contactOne = Contact(1, "Matheus", "matheus", "123")
        var contactTwo = Contact(2, "Lucas", "lucas", "123")
        var messageText = "Olá! Tudo      bem?      Quem está falando é o    Matheus"
        var contactThree = Contact(3, "Rose", "rose", "123")

        activeViewModel.createMessage(messageText, contactOne, contactTwo, 1586226450L)
        val listOfSentMessagesBetweenUserOneAndThree = messageRepository.getMessageListBetweenTwoContacts(contactThree,
            contactOne)
        val listOfSentMessagesBetweenUserTwoAndThree = messageRepository.getMessageListBetweenTwoContacts(contactThree,
            contactTwo)

        assertThat(listOfSentMessagesBetweenUserOneAndThree, hasSize(0))
        assertThat(listOfSentMessagesBetweenUserTwoAndThree, hasSize(0))
    }
}