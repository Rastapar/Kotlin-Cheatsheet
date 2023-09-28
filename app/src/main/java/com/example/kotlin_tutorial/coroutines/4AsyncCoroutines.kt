package com.example.kotlin_tutorial.coroutines

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

/*
    Use 'launch' if the coroutine does not return anything
    Use 'async' if the coroutine return something
*/

fun main(){

    myAsyncCoroutine()

    Thread.sleep(5000) // To let enough time for the coroutines
}

/*
    This is a bad approach
    Do not do it like this!
*/
fun commonCoroutine(){
    GlobalScope.launch {
        val timeExecution = measureTimeMillis {
            var answer1 : String? = null
            var answer2 : String? = null
            val job1 = launch { answer1 = doNetworkStuff1()}  // Execute parallel coroutine
            val job2 = launch { answer2 = doNetworkStuff2()}  // Execute parallel coroutine
            job1.join()     // Wait until the coroutine ends
            job2.join()     // Wait until the coroutine ends
            println("The answer 1 is ($answer1)")       // Print the result
            println("The answer 2 is ($answer2)")       // Print the result
        }
    }
}

/*
    Anytime we want to execute something asynchronously and get a result out of it, we should use
    async function
 */
fun myAsyncCoroutine(){
    GlobalScope.launch {
        val timeExecution = measureTimeMillis {
            var answer1 : Deferred<String> = async { doNetworkStuff1() }
            var answer2 : Deferred<String> = async { doNetworkStuff2() }
            println("The answer 1 is (${answer1.await()})")       // Print the result WHEN the 'answer1' is available
            println("The answer 2 is (${answer2.await()})")       // Print the result WHEN the 'answer2' is available
        }
        println("The time execution of the Network Calls: $timeExecution")
    }
}

private suspend fun doNetworkStuff1() : String{
    delay(2000)
    return "Network 1 Stuff Done!"
}
private suspend fun doNetworkStuff2() : String{
    delay(2000)
    return "Network 2 Stuff Done!"
}