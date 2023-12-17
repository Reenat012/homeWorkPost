import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class WallServiceTest {

    @Test
    fun add() {
        val post = Post(1,
            2,
            3,
            4,
            5,
            "About me",
            6,
            7,
            true,
            9)

        val result = WallService.add(post)

        assertEquals(1, result.id)
    }

    @Test
    fun updateIdContains() {
        val post = Post(1,
            2,
            3,
            4,
            5,
            "About me",
            6,
            7,
            true,
            9)

        val result = WallService.update(post)

        assertEquals(true, result)
    }

    @Test
    fun updateIdNoContains() {
        val post = Post(0,
            2,
            3,
            4,
            5,
            "About me",
            6,
            7,
            true,
            9)

        val result = WallService.update(post)

        assertEquals(false, result)
    }
}