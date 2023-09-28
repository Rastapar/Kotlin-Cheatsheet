package com.example.kotlin_tutorial.newbie_to_pro

/*
    It creates an instance of a class we create on the way. Basically we crete a singleton object
    in the middle of our code. This class don't have a name since it is just an object.
    This object can use inheritance and behave like the rest of classes.
    Though the atributes and functions of the object can only be accesed inside the same function.
    So we can't access to atr1 and atr2 outside the function myAnonymousObject
*/

fun myAnonymousObject() {
    val myObject = object {
        var atr1 : Int = 1  // Mandatory to be initialized
        var atr2 = 2

        init {
            println("The Anonymous Object has been created")
        }

        fun sumAtrs() : Int {
            return atr1 + atr2
        }
    }

    println("The values of my Anonymous Object are ${myObject.atr1} and ${myObject.atr2} while the sum is ${myObject.sumAtrs()}")
}
