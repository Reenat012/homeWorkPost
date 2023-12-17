fun main(args: Array<String>) {

}

//создаем Data class для хранения данных об объекте
data class Post(
    val owner_id: Int,
    val from_id: Int,
    val created_by: Int,
    val date: Int,
    val text: String,
    val reply_owner_id: Int,
    val reply_post_id: Int,
    val friends_only: Boolean,
    val likes: Int,
    private val id: Int,
) {
}

//одно из полей class Post типа object
object Comments(
    var count = 0,
    var can_post: Boolean,
    var groups_can_post: Boolean,
    var can_close: Boolean,
    var can_open: Boolean
) {
    fun addComment() count += 1 //функция добавления коментария со счетчиком
    fun deleteComment() count = if (count > 0) count -= 1 else count //аналогичная функция удаления комментария
}

//создаем объект, в котором будет описываться логика или синглтон
object WallService {
    private var posts = emptyArray<Post>() //создаем пустой массив для хранения постов

    fun add(post: Post): Post {
        posts += post
        id =
            UUID.randomUUID() //UUID (Универсально уникальные идентификаторы) содержат 128 бит энтропии, и обычно считается крайне маловероятным, что случайный UUID когда-либо будет создан дважды (конечно, это возможно, просто маловероятно).
        return posts.last()
    }

    //обновление записи
    fun update(post: Post): Boolean {
        for ((index, post) in posts.withIndex())
            if (post.id == id) {
                posts[index] = post.copy(
                    owner_id = UUID.randomUUID(),
                    from_id = UUID.randomUUID(),
                    created_by = UUID.randomUUID(),
                    date = "12/12/2023"
                            text : = "About me"
                            reply_owner_id = UUID . randomUUID (),
                    reply_post_id = UUID.randomUUID(),
                    friends_only = true,
                    likes = posts.likes + 1
                )
            } else false
    }
}

