import java.lang.RuntimeException
import javax.print.attribute.standard.JobStateReason
import javax.xml.stream.events.Comment

fun main(args: Array<String>) {
}

interface Attachment {
    val type: String
}

data class Photo(val id: Int)
data class PhotoAttachment(val photo: Photo) : Attachment {
    override val type: String = "photo"
}

data class Video(val id: Int)
data class VideoAttachment(val video: Video) : Attachment {
    override val type: String = "video"
}

data class Audio(val id: Int)
data class AudioAttachment(val audio: Audio) : Attachment {
    override val type: String = "audio"
}

data class File(val id: Int)
data class FileAttachment(val file: File) : Attachment {
    override val type: String = "file"
}

data class Gift(var id: Int)
data class GiftAttachment(val gift: Gift) : Attachment {
    override val type: String = "gift"
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
    val likes: Int? = null, //создаем nullable свойство
    val comments: Comments = Comments(1, 0, false, false, false, false),
) {
    val video = Video(1)
    val videoAttachment = VideoAttachment(video)

    val audio = Audio(2)
    val audioAttachment = AudioAttachment(audio)

    val photo = Photo(3)
    val photoAttachment = PhotoAttachment(photo)

    val file = File(4)
    val fileAttachment = FileAttachment(file)

    val gift = Gift(5)
    val giftAttachment = GiftAttachment(gift)

    val arrAttachment =
        arrayOf<Attachment>(videoAttachment, audioAttachment, photoAttachment, fileAttachment, giftAttachment)
}

//одно из полей class Post типа object
open class Comments(
    var ownerId: Int,
    var count: Int = 0,
    val canPost: Boolean = false,
    val groupsCanPost: Boolean = false,
    val canClose: Boolean = false,
    val canOpen: Boolean = false
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
    val postOne = Post(likes = 1)
    val postTwo = Post()

    val arrPosts = arrayOf(postOne, postTwo)
    val likes = (if (postOne.likes == null) 0 else 1)

    private var posts = emptyArray<Post>() //создаем пустой массив для хранения постов
    var counter = 0 //объявляем счетчик, на одном уровне с массивом, иначе он каждый раз будет создаваться заново

    private var comments = emptyArray<Comments>() //создаем пустой массив для хранения комментариев
    private var reportComments = emptyArray<Comments>() //создаем пустой массив для хранения негативных комментариев

    fun clear() {
        posts = emptyArray()
        counter = 0
    }

    fun add(post: Post): Post {
        posts += post.copy(id = ++counter) //создаем копию исходного поста в массив, указываем id в параметрах

        return posts.last() //возвращаем последний добавленный пост
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

    fun createComment(postId: Int, comment: Comments): Comments { //создаем комментарий
        for ((index, item) in posts.withIndex()) { //возвращает массив элемента и сам элемент
            if (item.id == postId) {
                comments += comment //присваиваем элементу массива значения comment
                return comments.last()
            }
        }
        throw PostNotFoundException("Такого id не существует!")
    }

    fun reportComment(comment: Comments, commentId: Int) : Int {
        for ((index, item) in comments.withIndex()) {
            if (item.ownerId == commentId) { //если id комментария совпадает с id, на который пожаловались
                reportComments += comment ////присваиваем элементу массива значения comment, добавляем комментарий в нежелательные
                return 1 //после успешного выполнения возвращаем 1
            }
        }
        throw PostNotFoundException("Такого id не существует!") //выбрасываем ошибку, если не существует id
    }
}


class PostNotFoundException(message: String) : RuntimeException(message)

