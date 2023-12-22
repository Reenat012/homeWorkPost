fun main(args: Array<String>) {

}

//создаем Data class для хранения данных об объекте
data class Post(
    var id: Int = 1,
    val ownerId: Int = 21,
    val fromId: Int = 31,
    val createdBy: Int = 41,
    val date: Int = 51,
    val text: String = "Hello World",
    val replyOwnerId: Int = 71,
    val replyPostId: Int = 81,
    val friendsOnly: Boolean = false,
    val likes: Int = 0,
    val comments: Comments = Comments(0, false, false, false, false)
) {
}

//одно из полей class Post типа object
class Comments(
    var count: Int, 1
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
    var counter = 0 //объявляем счетчик, на одном уровне с массивом, иначе он каждый раз будет создаваться заново

    fun clear() {
        posts = emptyArray()
        counter = 0
    }

    fun add(post: Post): Post {
        posts += post.copy(id = ++counter) //создаем копию исходного поста в массив, указываем id в параметрах

        return posts.last()
    }

    //обновление записи
    fun update(post: Post): Boolean {
        for ((index, item) in posts.withIndex()) { //перебераем массив posts и присваиваем индексы в index, элементы в item
            if (item.id == post.id) {//сравниваем id входного параметра post и массива постов posts
                posts[index] = post.copy() //если условие верно, присваиваем элементу posts новое значения post
                return true
            }
        }
        return false
    }
}

