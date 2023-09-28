package com.example.kotlin_tutorial.newbie_to_pro

import kotlin.collections.mutableListOf as mutableListOf

// Here we go until functions inclusive

fun main(){
    /*
    println("Hello world")
    forLoop()
    listsArrays()
    whenCondition(14)
    whenCondition(18)
    noLimitParameters(13, "ok", true, null, 34.54)
    extensionFunction(5)
    */
}

fun forLoop(){
    for (i in 1..3)
        print(" $i")
    println()

    for (i in 5 downTo 3)
        print(" $i")
    println()

    for (i in 5 until 11 step 2)
        print(" $i")
    println()

    for (i in 7 downTo  3 step 2)
        print(" $i")
    println()
}

fun listsArrays(){
    val constantArray = arrayOf(1, "ok", 5, 8, null)
    val constantList = listOf(1, 2, "ok", true, 6, null)
    val constantArray2 = arrayOf<String>("ok", "no")
    val constantList2 = listOf<String>("ok", "no")

    var emptyArray = emptyArray<Any>()
    var emptyList = listOf<Any>()
    var emptyMutableList = mutableListOf<Any>() // of any type

    emptyArray += 1 // You can add values to the empty array
    emptyMutableList.also { it ->
        it.add(6); it.add("algo")
    }

    println(constantArray.contentToString())
    println(constantList)
    println(constantArray2.contentToString())
    println(constantList2)

    println(emptyArray.contentToString())
    println(emptyList)
    println(emptyMutableList)
}

fun whenCondition(num : Int = 0){

    when(num){
        in 0..5 -> println("Between 0 and 5 inclusive both")
        in 6..12 -> println("Between 6 and 12 inclusive both")
        in 13..15 -> {
            println("Between 13 and 15 inclusive both")
            println("Extra code")
        }
        18 -> println("Number is 18")
    }
}

fun noLimitParameters(vararg numbers : Any?) {
    // the parameters 'numbers' can be how many you want
    // to be able to put 'null' parameters we have to add '?' to the type
    println(numbers.contentToString())
}

fun extensionFunction(num : Int = 4){
    // we can add a function to an already existent class
    fun Int.isEven(): Boolean {
        return this % 2 == 0
    }

    println("The number $num is ${if (num.isEven()) "Even" else "Odd"}")
}

