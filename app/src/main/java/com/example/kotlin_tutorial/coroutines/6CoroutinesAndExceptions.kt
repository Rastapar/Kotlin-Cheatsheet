package com.example.kotlin_tutorial.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

/*
    The Exceptions and Try Catch block works differently with Coroutines.
    When an Exception is thrown, it will escalate to the parent coroutine, ignoring the try catch blocks.
    For that reason we have to use the 'async' key. Because we will expect an answer
    that in this case is an exception.
*/

fun main (){
    // deferredException()  // Bad practice
    // coroutineExceptionHandler() // It is okay
    // childCoroutinesException()
    // handlingManyCoroutines()
    mistakeHandlingCoroutine()  // Just to show an error on handling an Exception

    Thread.sleep(5000)  // To let the coroutines finish
}

/*
    Even if this example works, this is a bad practice.
*/
fun deferredException(){
    val deferred = GlobalScope.async {
        val answer = async {
            delay(500)
            throw Exception("Some Random Exception")
            "This is my result to async"
        }
    }
    GlobalScope.launch {
        try {
            deferred.await()    // The exception will be thrown from 'await()', basically the answer
        }
        catch (e : Exception){
            println("We caught the Exception: ")
            println(e.printStackTrace())
        }
    }

    println("And we keep executing the function")
}

/*
    Another way to handle exceptions in Coroutines, is to create a Coroutine with an
    Exception Handler.
*/
fun coroutineExceptionHandler(){
    val myHandler = CoroutineExceptionHandler { _, throwable ->
        println("Caught exception: ($throwable)")
    }
    GlobalScope.launch (myHandler) {
        throw Exception("Some random Exception")
    }
}

/*
    In the case that a Coroutine Scope includes the Exception Handler, and one of its
    child (nested) coroutines throws an exception, all child coroutines of the Coroutine Scope
    will stop executing.
*/
fun childCoroutinesException(){
    val myHandler = CoroutineExceptionHandler { _, throwable ->
        println("Caught exception: ($throwable)")
    }
    CoroutineScope(Dispatchers.IO + myHandler).launch {
        // child coroutine 1
        launch {
            delay(300)
            throw Exception("Random child coroutine exception")
        }
        // child coroutine 2
        launch {
            delay(400)
            println("Some random text that should not be printed")
        }
    }
}


/*
    With 'supervisorScope' block if one coroutine blocks, the other will continue executing
    With 'coroutineScope' block if one fails, everyone fails
*/
fun handlingManyCoroutines(){
    val myHandler = CoroutineExceptionHandler { _, throwable ->
        println("Caught exception: ($throwable)")
    }
    CoroutineScope(Dispatchers.IO + myHandler).launch {
        supervisorScope {
            // child coroutine 1
            launch {
                delay(300)
                throw Exception("Random child coroutine exception")
            }
            // child coroutine 2
            launch {
                delay(400)
                println("Some random text that should be printed from coroutine child 2")
            }
        }
    }
}

/*
    When a it is sent a 'cancel' signal to a job, this coroutine throws an exception of cancellation.
    That means that if the Exception is not handled correctly, the coroutine won't stop.
    So we have to handle specific exceptions we expect, not a general Exception.
    Or we can catch the 'CancellationException' and rethrow it, leaving the other exceptions for
    another 'catch' block.
*/
fun mistakeHandlingCoroutine(){
    CoroutineScope(Dispatchers.Default).launch{
        val job = launch {
            try {
                delay(500)
            }
            catch (e : Exception){
                println("The caught exception is: (${e.printStackTrace()})")
            }
            println("I should not be printed")
        }
        delay(300)
        job.cancel()
    }
}
