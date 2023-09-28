package com.example.kotlin_tutorial.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

/*
    Every time we create a coroutine, this returns a job

 */

fun main(){
    myJobCoroutine()    // This coroutine uses 'join()' function, so until it is not done, the next lines won't be executed yet

    //myCancelCoroutine()

    myTimeoutCoroutine()

    //Thread.sleep(5000)  // To allow the other coroutines to finish
}

fun myJobCoroutine (){
    runBlocking {
        val job = launch {
            repeat(3) {
                delay(1000)
                println("I am the wild coroutine in thread ${Thread.currentThread().name}")
            }
        }
        // With 'join()' function we wait until the coroutine has finished
        // So in this case we will block the main thread until this coroutine has ended
        job.join()
    }
}

/*
    We can use the function 'cancel()' to send a signal to end a coroutine.
    But if the coroutine is very busy and has no pauses to do so, it won't stop executing.
    So it has to be checked manually from the coroutine if it has received the 'cancel' signal.
*/
fun myCancelCoroutine(){
    runBlocking {
        val job = launch (Dispatchers.Default) {
            println("Starting Default Thread called: ${Thread.currentThread().name}")
            for (i in 40..45){
                if (isActive){  // Check if the cancel signal has been received
                    val fiboResult = heavyCalculationFunctionFibonacci(i)
                    println("The result of fibonacci $i is $fiboResult")
                }
            }
        }
        delay(2000)
        job.cancel()
        println("Sent Cancel Signal to the Job")
    }
}

/*
    With 'withTimeout' function we will execute the coroutine until the timeout ends
*/
fun myTimeoutCoroutine(){
    runBlocking {
        val job = launch (Dispatchers.Default) {
            println("Starting Second Default Thread called: ${Thread.currentThread().name}")
            withTimeout(2000) {
                for (i in 30..43) {
                    if (isActive) {  // Check if the cancel signal has been received
                        val fiboResult = heavyCalculationFunctionFibonacci(i)
                        println("The result of fibonacci $i is $fiboResult")
                    }
                }
            }
        }
        println("Executing the Coroutine Job with a timeout")
    }
}

fun heavyCalculationFunctionFibonacci(num : Int) : Long{
    return if(num <= 0) 0
    else if (num == 1) 1
    else heavyCalculationFunctionFibonacci(num-1) + heavyCalculationFunctionFibonacci(num-2)
}