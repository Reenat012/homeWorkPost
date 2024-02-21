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
    val ownerId: Int = 0,
    val likes: Int = 0,
    val comments: MutableList<Comments> = mutableListOf(),
    var reportComments: MutableList<Comments> = mutableListOf()
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
data class Comments(
    var ownerId: Int = 0,
    var count: Int = 0,
    var text: String = ""
)


//создаем объект, в котором будет описываться логика или синглтон
object WallService {
    val postOne = Post(likes = 1)
    val postTwo = Post()

    val arrPosts = arrayOf(postOne, postTwo)
    val likes = (if (postOne.likes == null) 0 else 1)

    private var posts = mutableMapOf<Int, Post>() //создаем пустой массив для хранения постов
    var counter = 0 //объявляем счетчик, на одном уровне с массивом, иначе он каждый раз будет создаваться заново
    var count = 0//счетчик для id comments

    private var comments = emptyArray<Comments>() //создаем пустой массив для хранения комментариев

    fun getPost(): MutableMap<Int, Post> {
        return posts
    }

    fun clear() {
        posts.clear()
        counter = 0
    }

    fun add(post: Post): Post {
        posts[counter++] = post
        return post
    }

    //обновление записи
    fun update(id: Int, post: Post): Post? {
        for (item in posts) {
            if (item.key == id) {
                posts[item.key] = post.copy()
                return posts[item.key]
            }
        }
        throw PostNotFoundException("Такого поста нет")
    }


    fun createComment(postId: Int, comment: Comments): Boolean{ //создаем комментарий
        posts[postId]
            ?.comments
            ?.plusAssign(comment.copy(ownerId = count++, count = +1)) ?: throw PostNotFoundException("Такого поста нет")
        return true



    }

    fun reportComment(postId: Int, commentId: Int): Comments {
        posts[postId]?.reportComments = posts[postId]?.comments
            ?.toMutableList()!!
            .filter { it.ownerId == commentId }
            .toMutableList()

        posts[postId]?.comments?.removeIf { it.ownerId == commentId } ?: throw PostNotFoundException("Post not found")
        return posts[postId]?.reportComments?.last() ?: throw PostNotFoundException("Post not found")
        //removeIf - удаляет элемент, который соответствует предикате
}

class PostNotFoundException(message: String) : RuntimeException(message)
}
