import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test
import sun.java2d.Disposer.PollDisposable


class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val service = WallService

        val result = service.add(Post())

        assertEquals(Post(), result)
    }

    @Test
    fun update() {
        val service = WallService

        service.add(Post())
        service.add(Post())

        val result = service.update(1 ,Post(likes = 1))

        assertEquals(Post(likes = 1), result)
    }


    @Test
    fun сreateCommentTest() {
        val service = WallService

        service.add(Post())

        val result = service.createComment(0, Comments(1))

        assertTrue(result)
    }


    @Test
    fun reportComment() {
        val service = WallService

        service.add(Post())
        service.add(Post())
        service.createComment(1, Comments(text = "Hello"))
        service.createComment(1, Comments(text = "HelloBob"))

        val result = service.reportComment(1, 1)

        assertEquals(Comments(ownerId = 1, count = 1, text = "Hello"), result)
    }
}