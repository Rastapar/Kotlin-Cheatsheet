package com.example.kotlin_tutorial.newbie_to_pro

/*
    This is used to use properties or functions of a class without creating an instance of it.
    Kinda like a library inside a class.
*/

class MyCompanionObject (
    var atr1 : Int,
    var atr2 : Int
) {
    companion object {
        val const1 = 3
        fun printInfo() = println("I am the companion object hehe")
        fun randomObject() : MyCompanionObject{
            return MyCompanionObject(6, 9)
        }
    }

    fun sumAtrs() = atr1 + atr2
}