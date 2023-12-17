import java.util.*

fun main(args: Array<String>) {

}

//создаем Data class для хранения данных об объекте
data class Post(
    var id: Int,
    val owner_id: Int,
    val from_id: Int,
    val created_by: Int,
    val date: Int,
    val text: String,
    val reply_owner_id: Int,
    val reply_post_id: Int,
    val friends_only: Boolean,
    val likes: Int,
) {
}

//одно из полей class Post типа object
class Comments(
    var count: Int,
    val can_post: Boolean,
    val groups_can_post: Boolean,
    val can_close: Boolean,
    val can_open: Boolean
) {
    //функция добавления комментария со счетчиком
    fun addComment() {
        count += 1
    }

    //аналогичная функция удаления комментария
    fun deleteComment() {
        if (count > 0) {
            count -= 1
        } else count
    }
}

//создаем объект, в котором будет описываться логика или синглтон
object WallService {
    private var posts = emptyArray<Post>() //создаем пустой массив для хранения постов

    fun add(post: Post): Post {
        posts += post
        post.id = (0..10).random() * posts.size//создаем уникальный id каждому посту
        return posts.last()
    }

    //обновление записи
    fun update(post: Post): Boolean {
        for ((index, post) in posts.withIndex())
            return if (post.id == posts[index].id) {
                posts[index] = post.copy(
                    date = 12,
                    text = "About me",
                    friends_only = true,
                    likes = post.likes + 1
                )
                true
            } else false
        return false
    }
}

