import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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
    fun updateIdContains() {
        val service = WallService

        service.add(Post(id = 1))
        service.add(Post(id = 2))
        service.add(Post(id = 3))

        val update = Post(1, text = "Hello!")

        val result = service.update(update)

        assertTrue(result)
    }

    @Test
    fun updateIdNoContains() {
        val service = WallService

        service.add(Post(id = 2))
        service.add(Post(id = 3))

        val update = Post(id = 4, text = "Hello!")

        val result = service.update(update)

        assertFalse(result)
    }
}