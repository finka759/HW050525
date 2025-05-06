object ChatService {
    val chats = hashMapOf<List<Int>, Chat>()

    fun clear() {
        chats.clear()
    }


    override fun toString(): String {
        var result: String = "Состояние чатов:\n"
        chats.forEach { (key, value) ->
            result += "$key $value \n"
        }
        return result
    }

    fun addMessage(userIds: List<Int>, message: Message): Chat {
        return chats.getOrPut(userIds.sorted()) {
            Chat()
        }.apply {
            messages.add(message)
        }
    }

    fun getMessagesFromChat(userIds: List<Int>): List<Message> {
        val chat = chats.filter { entry -> entry.key.containsAll(userIds.sorted()) }.values.first().messages
        chat.forEach { message -> message.isRead = true }
        return chat
    }

    fun getMessagesFromChatLimited(userIds: List<Int>, lim: Int = 1): List<Message> {
        val chat = chats.filter { entry -> entry.key.containsAll(userIds.sorted()) }.values.first().messages.take(lim)
        chat.forEach { message -> message.isRead = true }
        return chat
    }

    fun readChat(userIds: List<Int>): Boolean {
        chats.filter { entry -> entry.key.containsAll(userIds.sorted()) }.values.first().readMessages()
        return true
    }

    fun deleteChat(userIds: List<Int>): Boolean {
        return chats.remove(userIds.sorted()) != null
    }

    fun getChats(userId: Int): List<Chat> {
        return chats.filter { entry -> entry.key.contains(userId) }.values.toList()
    }

    fun getChatsLastMessages(): List<String> {
        return chats.values.map { it.messages.lastOrNull()?.text ?: "No messages" }
    }

    fun getChat(userIds: List<Int>): List<Chat> {
        return chats.filter { entry -> entry.key.containsAll(userIds.sorted()) }.values.toList()
    }

    fun getUnreadChatsCount(userId: Int): Int {
        return chats.filter { entry -> entry.key.contains(userId) }.values.filter { !it.unreadMessages() }.count()
    }
}