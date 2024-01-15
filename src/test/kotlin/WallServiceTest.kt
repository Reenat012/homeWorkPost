import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test


class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val service = WallService

        val result = service.add(Post(likes = 1))

        assertEquals(1, result.id)
    }

    @Test
    fun updateExisting() {
        val service = WallService

        service.add(Post(id = 1, likes = 1))
        service.add(Post(id = 2, likes = 1))
        service.add(Post(id = 4, likes = 1))

        val update = Post(1, text = "Hello!", likes = 1)

        val result = service.update(update)

        assertTrue(result)
    }

    @Test
    fun updateNoExisting() {
        val service = WallService

        service.add(Post(id = 2, likes = 1))
        service.add(Post(id = 3, likes = 2))

        val update = Post(id = 4, text = "Hello!", likes = 2)

        val result = service.update(update)

        assertFalse(result)
    }

    @Test
    fun allRigth() {
        val service = WallService

        service.add(Post(id = 1))
        val comment = Comments(1)

        val result = service.createComment(1, comment)

        assertEquals(comment, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        // здесь код с вызовом функции, которая должна выкинуть PostNotFoundException
        val service = WallService

        service.add(Post(id = 1))
        val comment = Comments(1)

        service.createComment(2, comment)
    }
}