package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils.toInitials
import ru.skillbranch.devintensive.utils.Utils.transliteration


fun User.toUserView(): UserView {
    val nickname = transliteration("$firstName $lastName")
    val initials = toInitials(firstName, lastName)
    val status = if(lastVisit == null) "Еще ни разу не был" else if(isOnline) "Online" else "Последний раз был ${lastVisit.humanizeDiff()}"

    return UserView(
        id,
        fullName = "$firstName $lastName",
        avatar = avatar,
        nickName = nickname,
        initials = initials,
        status = status
    )
}

