import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ChatServiceTest {

    @Before
    fun clearBeforeTest() {
        ChatService.clear()
        ChatService.addMessage(listOf(456, 123), Message(text = "message1"))
        ChatService.addMessage(listOf(456, 123), Message(text = "message4"))
        ChatService.addMessage(listOf(123, 111), Message(text = "message2"))
        ChatService.addMessage(listOf(456, 111), Message(text = "message5"))
        ChatService.addMessage(listOf(456, 111), Message(text = "message6"))
    }

    @Test
    fun getChats() {
        val result = ChatService.getChats(123).size
        assertEquals(2, result)
    }

    @Test
    fun addMessage() {
        val result = ChatService.chats.size
        assertEquals(3, result)
    }

    @Test
    fun delMessage(){
        ChatService.delMessage(listOf(456, 123), 1)
        val result = ChatService.getMessagesFromChat(listOf(456, 123)).size
        assertEquals(1, result)
    }

    @Test
    fun getMessagesFromChat() {
        val result = ChatService.getMessagesFromChat(listOf(456, 123)).size
        assertEquals(2, result)
    }

    @Test
    fun readChat() {
        val res = ChatService.getUnreadChatsCount(111)
        ChatService.readChat(listOf(123, 111))
        val result = ChatService.getUnreadChatsCount(111)
        assertEquals(res-1, result)
    }


    @Test
    fun testGetChats() {
        val result = ChatService.getChats(111).size
        assertEquals(result, 2)
    }

    @Test
    fun getChat() {
        val result = ChatService.getChat(listOf(456, 123)).get(0).messages.size
        assertEquals(result, 2)
    }

    @Test
    fun getUnreadChatsCount() {
        val result = ChatService.getUnreadChatsCount(111)
        assertEquals(result, 2)
    }

    @Test
    fun deleteChat() {
        ChatService.deleteChat(listOf(456, 123))
        val result = ChatService.getChats(456).size
        assertEquals(1, result)
    }

    @Test
    fun getChatsLastMessages() {
        val result = ChatService.getChatsLastMessages()
        assertEquals(listOf("message6", "message2", "message4"), result)
    }

    @Test
    fun getMessagesFromChatLimited() {
        ChatService.addMessage(listOf(456, 123), Message(text = "message 3"))
        ChatService.addMessage(listOf(456, 123), Message(text = "message 4"))
        ChatService.addMessage(listOf(456, 123), Message(text = "message 5"))
        val result = ChatService.getMessagesFromChatLimited(listOf(456, 123), 4).size
        assertEquals(4, result)

    }





}