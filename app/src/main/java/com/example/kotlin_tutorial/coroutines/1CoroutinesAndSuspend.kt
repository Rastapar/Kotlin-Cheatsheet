package com.example.kotlin_tutorial.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
    In Kotlin, a suspend function is a function that can be paused and resumed.
    It is typically used in conjunction with Kotlin Coroutines, which are a language feature
    that allows you to write asynchronous code in a more sequential and readable manner.
*/

suspend fun main(){

    firstCoroutines()   // As the function is of type 'suspend', the function that use it has to be 'suspend' too
    withoutSuspendCoroutine()

    delay(1000) // To allow the non-main coroutines execute
}

suspend fun firstCoroutines(){

    // this Coroutine will live as long as the program does
    GlobalScope.launch {
        println("Hello from thread ${Thread.currentThread().name}")
    }
    // This delay is necessary because the execution ends before the new thread can print its message
    // The delay fucntion only delays the coroutine where it is called
    delay(1000)
    println("Hello from normal thread ${Thread.currentThread().name}")

}

/*
    This function doesn't need the 'suspend' key because the delay is not executed in the Main Thread
*/
fun withoutSuspendCoroutine (){
    GlobalScope.launch {
        delay(500)
        println("Hello from thread without suspend ${Thread.currentThread().name}")
    }
}