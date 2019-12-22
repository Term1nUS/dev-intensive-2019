package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.*
import java.util.*

data class User(
    val id:String = "",
    var firstName:String? = "",
    var lastName:String? = "",
    var avatar:String? = "",
    var rating:Int = 0,
    var respect:Int = 0,
    val lastVisit:Date? = null,
    val isOnline:Boolean = false) {


    companion object Factory{
        private var lastID:Int = -1
        fun makeUser(fullName:String?) : User {
            lastID++

//            val parts : List<String>? = fullName?.split(" ")

            val (firstName, lastName) = parseFullName(fullName)

            return User(
                id = "$lastID",
                firstName = firstName,
                lastName = lastName
            )
        }
    }


    class Builder {
        private var id: String = ""
        private var firstName: String? = ""
        private var lastName: String? = ""
        private var avatar: String? = ""
        private var rating: Int = 0
        private var respect: Int = 0
        private var lastVisit: Date? = null
        private var isOnline: Boolean = false

        fun id(value: String) = apply { this.id = value }
        fun firstName(value: String?) = apply { this.firstName = value }
        fun lastName(value: String?) = apply { this.lastName = value }
        fun avatar(value: String?) = apply { this.avatar = value }
        fun rating(value: Int) = apply { this.rating = value }
        fun respect(value: Int) = apply { this.respect = value }
        fun lastVisit(value: Date?) = apply { this.lastVisit = value }
        fun isOnline(value: Boolean) = apply { this.isOnline = value }

        fun build() = User (
                id = id,
                firstName = firstName,
                lastName = lastName,
                avatar = avatar,
                rating = rating,
                respect = respect,
                lastVisit = lastVisit,
                isOnline = isOnline )
    }

}