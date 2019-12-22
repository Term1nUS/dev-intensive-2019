package ru.skillbranch.devintensive.extensions


fun String.truncate(strlen:Int = 16):String{

    var res:String = this.trim()

    if (res.length > strlen) {
        res = res.substring(0, strlen).trim() + "..."
    } else {
        res = res.trim()
    }

    return res
}

fun String.stripHTML():String {

    var rez:String = this
    var tagbegin = 0
    var found = false
    var exit = false
    var i = 0

    while (!exit) {
        while (!exit){
            if (i>=rez.length) exit = true else{
                if (!found && rez[i].equals('<')) {
                    tagbegin = i
                    found = true
                }
                if (found && rez[i].equals('>')) {
                    rez = rez.removeRange(tagbegin..i)
                    found = false
                    i = i - (i - tagbegin + 1)
                }
                i += 1
            }
        }
        found = false
        exit = false
        i = 0
        while (!exit){
            if (i>=rez.length) exit = true else {
                if (!found && rez[i].equals('&')) {
                    tagbegin = i
                    found = true
                }
                if (found && rez[i].equals(';')) {
                    rez = rez.removeRange(tagbegin..i)
                    found = false
                    i = i - (i - tagbegin + 1)
                }
                i += 1
            }
        }
        exit = false
        while (!exit) {
            i = rez.length
            rez = rez.replace("  ", " ")
            if (i == rez.length) exit = true
        }
    }

    return rez
}

