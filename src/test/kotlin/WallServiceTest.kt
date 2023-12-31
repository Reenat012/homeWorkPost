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

        val result = service.add(Post())

        assertEquals(1, result.id)
    }

    @Test
    fun updateExisting() {
        val service = WallService

        service.add(Post(id = 1))
        service.add(Post(id = 2))
        service.add(Post(id = 4))

        val update = Post(1, text = "Hello!")

        val result = service.update(update)

        assertTrue(result)
    }

    @Test
    fun updateNoExisting() {
        val service = WallService

        service.add(Post(id = 2))
        service.add(Post(id = 3))

        val update = Post(id = 4, text = "Hello!")

        val result = service.update(update)

        assertFalse(result)
    }
}