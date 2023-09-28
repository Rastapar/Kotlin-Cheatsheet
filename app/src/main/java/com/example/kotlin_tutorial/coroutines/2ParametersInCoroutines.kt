package com.example.kotlin_tutorial.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/*
    The Coroutines can accept parameters through the Dispather class
    - Main dispather is executed in the main thread. It is good to update UI elements because
        you can only change the UI from the main thread.
    - IO dispatcher it is useful for data operations. Input and Output of: Network, Databases or Files
    - Default dispatcher is useful for long and complex calculations that may block the main thread.
    - Unconfined dispatcher has not an established function. It will stay in the thread in
        which the suspend function resumed.
    Or we can just create a new thread with newSingleThreadContext()
*/

suspend fun main(){

    coroutineWithParameter()

    myCoroutineRunBlocking()

    delay(15000) // To allow the others coroutines to finish
}

fun coroutineWithParameter(){
    GlobalScope.launch (newSingleThreadContext("MyOwnThread")) {
        println("I am in my thread: ${Thread.currentThread().name}")

        // We change of thread, to the 'data' thread
        withContext(Dispatchers.IO) {
            val netAnswer = doNetworkStuff()    // Where it has to be done
            println("We have done the Network stuff in the thread ${Thread.currentThread().name}")

            // In Android it would change of thread, but in normal execution the Dispatcher works differently
            withContext(Dispatchers.Default){
                println("The Default thread received the message: ($netAnswer). In the thread ${Thread.currentThread().name}")
            }

            // We change of thread again, to the main thread in case we want to update the UI
            // This throws an error because it is not Android environment (not an app)
            /*withContext(Dispatchers.Main){
                println("The main thread received the message: ($netAnswer) in the thread ${Thread.currentThread().name}")
            }*/
        }
    }
}

private suspend fun doNetworkStuff() : String {
    delay(3000)
    return "Network stuff finished"
}

/*
    The 'runBlocking' will create a coroutine inside the Main Thread blocking it while the coroutine is executed
    The advantage of it is that inside we can execute 'suspend' functions. (If not we would have to make the parent function suspend too)
    Moreover we can launch other coroutines inside, that will be executed asynchronously
    If we don't specify another thread for the launch coroutine, it will use the thread of 'runBlocking' scope
*/
fun myCoroutineRunBlocking() {
    runBlocking {
        launch (Dispatchers.IO){
            delay(1000)
            println("Random coroutine 1 in thread ${Thread.currentThread().name}")
        }
        launch (Dispatchers.IO){
            delay(1000)
            println("Random coroutine 2 in thread ${Thread.currentThread().name}")
        }
        delay(2000)
        println("Run Blocking in the thread ${Thread.currentThread().name}")
    }
}