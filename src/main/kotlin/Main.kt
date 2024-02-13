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
    val ownerId: Int = 21,
    val fromId: Int = 31,
    val createdBy: Int = 41,
    val date: Int = 51,
    val text: String = "Hello World",
    val replyOwnerId: Int = 71,
    val replyPostId: Int = 81,
    val friendsOnly: Boolean = false,
    val likes: Int? = null, //создаем nullable свойство
    val comments: MutableList<Comments> = mutableListOf()
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

    private var posts = mutableMapOf<Int, Post>() //создаем пустой массив для хранения постов
    var counter = 0 //объявляем счетчик, на одном уровне с массивом, иначе он каждый раз будет создаваться заново

    private var comments = emptyArray<Comments>() //создаем пустой массив для хранения комментариев
    private var reportComments = emptyArray<Comments>() //создаем пустой массив для хранения негативных комментариев

    fun clear() {
        posts.clear()
        counter = 0
    }

    fun add(post: Post): Post {
        posts[counter++] = post
        return post
    }

    //обновление записи
    fun update(post: Post): Post {
        posts[post.ownerId] = post
        return post
    }

    fun createComment(postId: Int, comment: Comments): Comments { //создаем комментарий
        posts[postId]?.comments?.plusAssign(comment) ?: throw PostNotFoundException("Такого поста нет")
        return comments.last()
    }

    fun reportComment(commentId: Int) : Array<Comments> {
        reportComments += comments
            .filter { it.ownerId == commentId }
            .ifEmpty { throw PostNotFoundException("Такого комментария не найдено") }
        return reportComments
    }
}


class PostNotFoundException(message: String) : RuntimeException(message)

