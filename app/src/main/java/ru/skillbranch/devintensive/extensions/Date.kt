package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Utils.makeTextDays
import ru.skillbranch.devintensive.utils.Utils.makeTextHours
import ru.skillbranch.devintensive.utils.Utils.makeTextMinutes
import ru.skillbranch.devintensive.utils.Utils.makeTextSeconds

import java.lang.Exception
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR




fun Date.format(pattern:String = "HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.humanizeDiff():String {

    val diff:Long = Date().time - this.time
    val humanized:String
    val diffSeconds = (abs(diff/SECOND)).toString()
    val diffMinutes:String = (abs(diff/MINUTE)).toString()
    val diffHours:String = (abs(diff/HOUR)).toString()
    val diffDays:String = (abs(diff/DAY)).toString()

    humanized = when {
        (diff <= SECOND)&&(diff >= 0) -> "только что"
        (diff > SECOND)&&(diff < 45*SECOND) -> "несколько секунд назад"
        (diff >= 45*SECOND)&&(diff < 75*SECOND) -> "минуту назад"
        (diff >= 75*SECOND)&&(diff < 45*MINUTE) -> "$diffMinutes ${makeTextMinutes(diff/MINUTE)} назад"
        (diff >= 45*MINUTE)&&(diff < 75*MINUTE) -> "час назад"
        (diff >= 75*MINUTE)&&(diff < 22*HOUR) -> "$diffHours ${makeTextHours(diff/HOUR)} назад"
        (diff >= 22*HOUR)&&(diff < 26*HOUR) -> "день назад"
        (diff >= 26*HOUR)&&(diff <= 360*DAY) -> "$diffDays ${makeTextDays(diff/DAY)} назад"
        (-diff >= DAY)&&(-diff <= 30*DAY) -> "через $diffDays ${makeTextDays(-diff/DAY)}"
        (-diff < DAY)&&(-diff > HOUR) -> "через $diffHours ${makeTextHours(-diff/HOUR)}"
        (-diff <= HOUR)&&(-diff >= MINUTE) -> "через $diffMinutes ${makeTextMinutes(-diff/MINUTE)}"
        (-diff > 0)&&(-diff < MINUTE) -> "через несколько секунд"
        (-diff > 30*DAY) -> "через $diffDays ${makeTextDays(-diff/DAY)}"
        diff > 360*HOUR -> "более года назад"
        else -> throw Exception("Wrong time interval!")
    }

    return humanized
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND):Date{
    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time
    return this
}

enum class TimeUnits {
    SECOND {
        override fun plural(value: Int): String {
            return "$value ${makeTextSeconds(value.toLong())}"
        }
    },
    MINUTE{
        override fun plural(value: Int): String {
            return "$value ${makeTextMinutes(value.toLong())}"
        }
    },
    HOUR{
        override fun plural(value: Int): String {
            return "$value ${makeTextHours(value.toLong())}"
        }
    },
    DAY{
        override fun plural(value: Int): String {
            return "$value ${makeTextDays(value.toLong())}"
        }
    };

    abstract fun plural(value: Int): String
}
