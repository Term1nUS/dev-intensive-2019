package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils.toInitials
import ru.skillbranch.devintensive.utils.Utils.transliteration
import java.util.*
import ru.skillbranch.devintensive.utils.Utils.truncate
import ru.skillbranch.devintensive.utils.Utils.stripHTML


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user2 = User("2", "John", "Cena")
        println(user2.id)
    }

    @Test
    fun test_factory() {
        val user = User.makeUser("John Wick")
        val user2 = user.copy(id = "2", lastName = "Cena", lastVisit = Date())
        print(message = "$user\n$user2")
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("John Wick")
        fun getUserInfo() = user
        val (id, firstName, lastName) = getUserInfo()

        println("$id, $firstName, $lastName")
        println("${user.component1()}, ${user.component2()}, ${user.component3()}")

    }

    @Test
    fun test_copy() {
        val user = User.makeUser("John Cena")
        val user1 = user.copy(lastVisit = Date().add(-2, TimeUnits.SECOND))
        val user2 = user.copy(lastName = "Cena", lastVisit = Date().add(10, TimeUnits.HOUR))
        val user3 = user.copy(lastName = "Cena", lastVisit = Date())

        println(
            """
            ${user.lastVisit?.format()}
            ${user1.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
        """.trimIndent()
        )
    }

    @Test
    fun test_data_mapping() {
        val user = User.makeUser("Андрей Романовский")
        println(user)

        val userView = user.toUserView()
        userView.printMe()
    }

    @Test
    fun test_abstract_factory() {

        val user = User.makeUser("Andrew Romanowsky")

        val txtdate = Date()
        val imgdate = Date()

        txtdate.add(-12, TimeUnits.MINUTE)
        imgdate.add(-400, TimeUnits.DAY)

        println(Date().add(-50, TimeUnits.DAY).humanizeDiff())
        println(Date().format("hh:MM:ss dd.MM.yy"))
        println(toInitials(user.firstName, user.lastName))

        val txtMessage = BaseMessage.makeMessage(
            user,
            Chat("0"),
            date = txtdate,
            payload = "Any text message",
            type = "text"
        )
        val imgMessage = BaseMessage.makeMessage(
            user,
            Chat("0"),
            date = imgdate,
            payload = "Any image url",
            type = "image"
        )

        println(txtMessage.formatMessage())
        println(imgMessage.formatMessage())
    }

    @Test
    fun test_plural() {
        for (x in 0..11) println(TimeUnits.DAY.plural(x))
    }

    @Test
    fun test_truncate() {
        println("Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(8))
        println("A         ".truncate())
    }

    @Test
    fun test_transliterate() {
        println(transliteration("Анд65рей Рома20новский", "_"))
    }

    @Test
    fun test_stripHTML() {

        val html = "<p class=\"title\">&nbsp;A    A</p>"

        println(html.stripHTML())
    }

    @Test
    fun test_Builder(){
        val user = User.Builder()
                            .id("1")
                            .firstName("Andrew")
                            .lastName("Romanowsky")
                            .avatar("picture.img")
                            .lastVisit(Date().add(-10, TimeUnits.DAY))
                            .isOnline(true)
                            .respect(1000)
                            .rating(1500)
                            .build()

        println("""
            ${user.id}
            ${user.firstName}
            ${user.lastName}
            ${user.avatar}
            ${user.lastVisit}
            ${user.rating}
            ${user.respect}
            ${user.isOnline}
        """.trimIndent())
    }

}
