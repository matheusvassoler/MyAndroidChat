package br.com.havebreak.repository

import br.com.havebreak.model.Contact
import br.com.havebreak.model.Message
import br.com.havebreak.util.DateUtil
import org.hamcrest.CoreMatchers.hasItems
import org.hamcrest.Matchers.*
import org.hamcrest.CoreMatchers.`is` as Is
import org.junit.Test

import org.junit.Assert.*
import java.util.*
import kotlin.collections.ArrayList

class MessageRepositoryTest {

    @Test
    fun must_ReturnMessageText_WhenMessageIsCreated() {
        val epochTime = System.currentTimeMillis() / 1000
        var message = Message("Oi. Tudo bem?", Contact(1, "Matheus", "matheus", "123"),
            Contact(2, "Lucas", "lucas", "123"), epochTime)
        var messageTextReturned = message.text

        assertThat(messageTextReturned, Is(equalTo("Oi. Tudo bem?")))
    }

    @Test
    fun must_ReturnTheNameOfTheSenderOfTheMessage_WhenMessageIsCreated() {
        val epochTime = System.currentTimeMillis() / 1000
        var message = Message("Oi. Tudo bem?", Contact(1, "Matheus", "matheus", "123"),
            Contact(2, "Lucas", "lucas", "123"), epochTime)
        var nameOfTheSenderOfTheMessageReturned = message.sender.name

        assertThat(nameOfTheSenderOfTheMessageReturned, Is(equalTo("Matheus")))
    }

    @Test
    fun must_ReturnTheNameOfTheRecipientOfTheMessage_WhenMessageIsCreated() {
        val epochTime = System.currentTimeMillis() / 1000
        var message = Message("Oi. Tudo bem?", Contact(1, "Matheus", "matheus", "123"),
            Contact(2, "Lucas", "lucas", "123"), epochTime)
        var nameOfTheRecipientOfTheMessageReturned = message.recipient.name

        assertThat(nameOfTheRecipientOfTheMessageReturned, Is(equalTo("Lucas")))
    }

    @Test
    fun must_ReturnTheTimeEpochOfTheMessage_WhenMessageIsCreated() {
        val epochTime = 1586182489L
        var message = Message("Oi. Tudo bem?", Contact(1, "Matheus", "matheus", "123"),
            Contact(2, "Lucas", "lucas", "123"), epochTime)
        var timeEpochOfTheMessageReturned = message.dateTime

        assertThat(timeEpochOfTheMessageReturned, Is(equalTo(1586182489L)))
    }

    @Test
    fun must_ReturnOneMessage_WhenChatHasExactOneMessageBySender() {
        val epochTime = System.currentTimeMillis() / 1000
        var message = Message("Oi. Tudo bem?", Contact(1, "Matheus", "matheus", "123"),
            Contact(2, "Lucas", "lucas", "123"), epochTime)

        val messageRepository = MessageRepository()
        MessageRepository.messageList = ArrayList<Message>()
        messageRepository.createMessage(message)

        val listOfSentMessages = messageRepository.getMessageListBetweenTwoContacts(Contact(1, "Matheus",
            "matheus", "123"), Contact(2, "Lucas", "lucas", "123"))

        assertThat(listOfSentMessages, hasSize(1))
        assertThat(listOfSentMessages, hasItem(Message("Oi. Tudo bem?", Contact(1, "Matheus", "matheus", "123"),
            Contact(2, "Lucas", "lucas", "123"), epochTime)))
    }

    @Test
    fun must_ReturnOneMessage_WhenChatHasExactOneMessageByRecipient() {
        val epochTime = System.currentTimeMillis() / 1000
        var message = Message("Oi. Tudo bem?", Contact(2, "Lucas", "lucas", "123"),
            Contact(1, "Matheus", "Lucas", "123"), epochTime)

        val messageRepository = MessageRepository()
        MessageRepository.messageList = ArrayList<Message>()
        messageRepository.createMessage(message)

        var listOfSentMessages = messageRepository.getMessageListBetweenTwoContacts(Contact(1, "Matheus", "Lucas", "123"), Contact(2, "Lucas", "lucas", "123"))

        assertThat(listOfSentMessages, hasSize(1))
        assertThat(listOfSentMessages, contains(Message("Oi. Tudo bem?", Contact(2, "Lucas", "lucas", "123"),
            Contact(1, "Matheus", "Lucas", "123"), epochTime))
        )
    }

    @Test
    fun must_ReturnTwoMessages_WhenChatHasExactTwoMessagesBySender() {
        val epochTime = System.currentTimeMillis() / 1000
        var messageOne = Message("Oi. Tudo bem?", Contact(1, "Matheus", "matheus", "123"),
            Contact(2, "Lucas", "lucas", "123"), epochTime)
        var messageTwo = Message("É o Matheus", Contact(1, "Matheus", "matheus", "123"),
            Contact(2, "Lucas", "lucas", "123"), epochTime)

        val messageRepository = MessageRepository()
        MessageRepository.messageList = ArrayList<Message>()
        messageRepository.createMessage(messageOne)
        messageRepository.createMessage(messageTwo)

        var listOfSentMessages = messageRepository.getMessageListBetweenTwoContacts(Contact(1, "Matheus", "Lucas", "123"), Contact(2, "Lucas", "lucas", "123"))

        assertThat(listOfSentMessages, hasSize(2))
        assertThat(listOfSentMessages, contains(messageOne, messageTwo))

    }

    @Test
    fun must_ReturnTwoMessages_WhenChatHasExactTwoMessagesByRecipient() {
        val epochTime = System.currentTimeMillis() / 1000
        var messageOne = Message("Oi. Tudo bem?", Contact(2, "Lucas", "lucas", "123"),
            Contact(1, "Matheus", "matheus", "123"), epochTime)
        var messageTwo = Message("É o Lucas", Contact(2, "Lucas", "lucas", "123"),
            Contact(1, "Matheus", "matheus", "123"), epochTime)

        val messageRepository = MessageRepository()
        MessageRepository.messageList = ArrayList<Message>()
        messageRepository.createMessage(messageOne)
        messageRepository.createMessage(messageTwo)

        var listOfSentMessages = messageRepository.getMessageListBetweenTwoContacts(Contact(1, "Matheus", "Lucas", "123"), Contact(2, "Lucas", "lucas", "123"))

        assertThat(listOfSentMessages, hasSize(2))
        assertThat(listOfSentMessages, contains(messageOne, messageTwo))
    }

    @Test
    fun must_ReturnTwoMessages_WhenChatHasOneMessageByRecipientAndAnotherBySender() {
        val epochTime = System.currentTimeMillis() / 1000
        var messageByRecipient = Message("Oi Matheus! Tudo bem?", Contact(2, "Lucas", "lucas", "123"),
            Contact(1, "Matheus", "matheus", "123"), epochTime)
        var messageBySender = Message("Oi Lucas! Estou bem e você?", Contact(1, "Matheus", "matheus", "123"),
            Contact(2, "Lucas", "lucas", "123"), epochTime)

        val messageRepository = MessageRepository()
        MessageRepository.messageList = ArrayList<Message>()
        messageRepository.createMessage(messageByRecipient)
        messageRepository.createMessage(messageBySender)

        var listOfSentMessages = messageRepository.getMessageListBetweenTwoContacts(Contact(1, "Matheus", "Lucas", "123"), Contact(2, "Lucas", "lucas", "123"))

        assertThat(listOfSentMessages, hasSize(2))
        assertThat(listOfSentMessages, contains(messageByRecipient, messageBySender))
    }

    @Test
    fun must_ReturnTwoMessages_WhenChatHasOneMessageBySenderAndAnotherByRecipient() {
        val epochTime = System.currentTimeMillis() / 1000
        var messageBySender = Message("Oi Lucas! Estou bem e você?", Contact(1, "Matheus", "matheus", "123"),
            Contact(2, "Lucas", "lucas", "123"), epochTime)
        var messageByRecipient = Message("Oi Matheus! Tudo bem?", Contact(2, "Lucas", "lucas", "123"),
            Contact(1, "Matheus", "matheus", "123"), epochTime)

        val messageRepository = MessageRepository()
        MessageRepository.messageList = ArrayList<Message>()
        messageRepository.createMessage(messageBySender)
        messageRepository.createMessage(messageByRecipient)

        var listOfSentMessages = messageRepository.getMessageListBetweenTwoContacts(Contact(1, "Matheus", "Lucas", "123"), Contact(2, "Lucas", "lucas", "123"))

        assertThat(listOfSentMessages, hasSize(2))
        assertThat(listOfSentMessages, contains(messageBySender, messageByRecipient))
    }

    @Test
    fun must_ReturnMessageTimeEpoch_WhenChatHasOneMessage() {
        val epochTime = System.currentTimeMillis() / 1000
        var message = Message("Oi. Tudo bem?", Contact(1, "Matheus", "matheus", "123"),
            Contact(2, "Lucas", "lucas", "123"), epochTime)

        val messageRepository = MessageRepository()
        MessageRepository.messageList = ArrayList<Message>()
        messageRepository.createMessage(message)

        val listOfSentMessages = messageRepository.getMessageListBetweenTwoContacts(Contact(1, "Matheus",
            "matheus", "123"), Contact(2, "Lucas", "lucas", "123"))

        val timeEpochReturned = listOfSentMessages.get(0).dateTime
        assertThat(timeEpochReturned, Is(equalTo(epochTime)))
    }

    @Test
    fun must_ReturnMessageTimePmIn24HourFormat_WhenSmartphoneHas24HourFormat() {
        //1586198649L = 15:44 or 03:44PM
        val epochTimestampSeconds = 1586198649L
        val calendar = Calendar.getInstance()
        val date = DateUtil.convertEpochTimestampSecondsToDate(epochTimestampSeconds)
        calendar.time = date

        var smartphoneIsIn24HourFormat = true
        var formattedHour: String

        formattedHour = DateUtil.formatHour(calendar, smartphoneIsIn24HourFormat)

        assertThat(smartphoneIsIn24HourFormat, Is(equalTo(true)))
        assertThat(formattedHour, Is(equalTo("15:44")))
    }

    @Test
    fun must_ReturnMessageTimeAmIn24HourFormat_WhenSmartphoneHas24HourFormat() {
        //1586144649L = 00:44 or 12:44AM
        val epochTimestampSeconds = 1586144649L
        val calendar = Calendar.getInstance()
        val date = DateUtil.convertEpochTimestampSecondsToDate(epochTimestampSeconds)
        calendar.time = date

        val smartphoneIsIn24HourFormat = true
        var formattedHour: String

        formattedHour = DateUtil.formatHour(calendar, smartphoneIsIn24HourFormat)

        assertThat(smartphoneIsIn24HourFormat, Is(equalTo(true)))
        assertThat(formattedHour, Is(equalTo("00:44")))
    }

    @Test
    fun must_ReturnMessageTimeAt12h00PmIn24HourFormat_WhenSmartphoneHas24HourFormatAndTimeIs00h00Pm() {
        //1586144649L = 00:44 or 12:44AM
        val epochTimestampSeconds = 1586185209L
        val calendar = Calendar.getInstance()
        val date = DateUtil.convertEpochTimestampSecondsToDate(epochTimestampSeconds)
        calendar.time = date

        val smartphoneIsIn24HourFormat = true
        var formattedHour: String

        formattedHour = DateUtil.formatHour(calendar, smartphoneIsIn24HourFormat)

        assertThat(smartphoneIsIn24HourFormat, Is(equalTo(true)))
        assertThat(formattedHour, Is(equalTo("12:00")))
    }

    @Test
    fun must_ReturnMessageTimePmIn12HourFormat_WhenSmartphoneHas12HourFormat() {
        //1586198649L = 15:44 or 03:44PM
        val epochTimestampSeconds = 1586198649L
        val calendar = Calendar.getInstance()
        val date = DateUtil.convertEpochTimestampSecondsToDate(epochTimestampSeconds)
        calendar.time = date

        val smartphoneIsIn24HourFormat = false
        var formattedHour: String

        formattedHour = DateUtil.formatHour(calendar, smartphoneIsIn24HourFormat)

        assertThat(smartphoneIsIn24HourFormat, Is(equalTo(false)))
        assertThat(formattedHour, Is(equalTo("03:44PM")))
    }

    @Test
    fun must_ReturnMessageTimeAmIn12HourFormat_WhenSmartphoneHas12HourFormat() {
        //1586144649L = 00:44 or 12:44AM
        val epochTimestampSeconds = 1586144649L
        val calendar = Calendar.getInstance()
        val date = DateUtil.convertEpochTimestampSecondsToDate(epochTimestampSeconds)
        calendar.time = date

        val smartphoneIsIn24HourFormat = false
        var formattedHour: String

        formattedHour = DateUtil.formatHour(calendar, smartphoneIsIn24HourFormat)

        assertThat(smartphoneIsIn24HourFormat, Is(equalTo(false)))
        assertThat(formattedHour, Is(equalTo("12:44AM")))
    }


    @Test
    fun must_ReturnMessageTimeAt12h00PmIn12HourFormat_WhenSmartphoneHas12HourFormatAndTimeIs00h00Pm() {
        //1586144649L = 00:44 or 12:44AM
        val epochTimestampSeconds = 1586185209L
        val calendar = Calendar.getInstance()
        val date = DateUtil.convertEpochTimestampSecondsToDate(epochTimestampSeconds)
        calendar.time = date

        val smartphoneIsIn24HourFormat = false
        var formattedHour: String

        formattedHour = DateUtil.formatHour(calendar, smartphoneIsIn24HourFormat)

        assertThat(smartphoneIsIn24HourFormat, Is(equalTo(false)))
        assertThat(formattedHour, Is(equalTo("12:00PM")))
    }
}