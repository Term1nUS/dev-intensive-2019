package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        var firstName: String? = parts?.getOrNull(0)
        var lastName: String? = parts?.getOrNull(1)

        if (firstName.isNullOrBlank()) {
            firstName = null
        }

        if (lastName.isNullOrBlank()) {
            lastName = null
        }

        if (firstName.isNullOrBlank()) {
            if (lastName.isNullOrBlank()) {
                lastName = null
            }
            firstName = lastName
            lastName = null
        }

        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {

        val fN: Char? = if (!firstName.isNullOrBlank()) firstName[0].toUpperCase() else Char.MIN_VALUE
        val lN: Char? = if (!lastName.isNullOrBlank()) lastName[0].toUpperCase() else Char.MIN_VALUE

        return if ((fN == Char.MIN_VALUE) && (lN == Char.MIN_VALUE)) null else "$fN$lN"
    }

    fun makeTextDays(days: Long): String {
        val ones: Char = days.toString()[days.toString().length - 1]
        return when {
            (days > 5) && (days < 21) -> "дней"
            ones == '1' -> "день"
            (ones == '2') || (ones == '3') || (ones == '4') -> "дня"
            (ones == '5') || (ones == '6') || (ones == '7') || (ones == '8') || (ones == '9') -> "дней"
            else -> "дней"
        }
    }

    fun makeTextHours(hours: Long): String {
        val ones: Char = hours.toString()[hours.toString().length - 1]
        return when {
            (hours > 5) && (hours < 21) -> "часов"
            ones == '1' -> "час"
            (ones == '2') || (ones == '3') || (ones == '4') -> "часа"
            (ones == '5') || (ones == '6') || (ones == '7') || (ones == '8') || (ones == '9') -> "часов"
            else -> "часов"
        }
    }

    fun makeTextMinutes(minutes: Long): String {
        val ones: Char = minutes.toString()[minutes.toString().length - 1]
        return when {
            (minutes > 5) && (minutes < 21) -> "минут"
            ones == '1' -> "минута"
            (ones == '2') || (ones == '3') || (ones == '4') -> "минуты"
            (ones == '5') || (ones == '6') || (ones == '7') || (ones == '8') || (ones == '9') -> "минут"
            else -> "минут"
        }
    }

    fun makeTextSeconds(seconds: Long): String {
        val ones: Char = seconds.toString()[seconds.toString().length - 1]
        return when {
            (seconds > 5) && (seconds < 21) -> "секунд"
            ones.equals('1') -> "секунда"
            (ones.equals('2')) || (ones.equals('3')) || (ones.equals('4')) -> "секунды"
            (ones.equals('5')) || (ones.equals('6')) || (ones.equals('7')) || (ones.equals('8')) || (ones.equals(
                '9'
            )) -> "секунд"
            else -> "секунд"
        }
    }

    fun transliteration(payload: String, divider: String = " "): String {

        var rez = ""
        val transmap = mapOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya",
            " " to divider
        )

        for (c in payload) {
            if (!c.isDigit()) {
                if (c.isUpperCase()) {
                    rez = rez + transmap.get(c.toLowerCase().toString())?.toUpperCase().toString()
                } else {
                    rez = rez + transmap.get(c.toString()).toString()
                }
            } else {
                rez = rez + c
            }
        }

        return rez
    }

}