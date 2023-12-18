fun main(args: Array<String>) {

}

//создаем Data class для хранения данных об объекте
data class Post(
    var id: Int,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val date: Int,
    val text: String,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val friendsOnly: Boolean,
    val likes: Int,
) {
}

//одно из полей class Post типа object
class Comments(
    var count: Int,
    val canPost: Boolean,
    val groupsCanPost: Boolean,
    val canClose: Boolean,
    val canOpen: Boolean
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
        posts += post.copy()
        var counter = 0 //объявляем счетчик
        post.id = ++counter//создаем уникальный id каждому посту
        return posts.last()
    }

    //обновление записи
    fun update(post: Post): Boolean {
        for ((index, item) in posts.withIndex()) { //перебераем массив posts и присваиваем индексы в index, элементы в item
            if (item.id == post.id) {//сравниваем id входного параметра post и массива постов posts
                posts[index] = post //если условие верно, присваиваем элементу posts новое значения post
                return true
            }
        }
        return false
    }
}

