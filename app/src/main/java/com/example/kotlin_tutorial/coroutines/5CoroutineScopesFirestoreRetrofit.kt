package com.example.kotlin_tutorial.coroutines

/*
    The 'GlobalScope' will stop working when the Main Thread has ended (App runs on it).
    This means that if you change of Activity, the coroutines inside that Global Scope will
    keep running, with the chance of creating a memory leak.
    For that reason we have to use other Scopes.

    'lifeCycleScope' will execute the coroutines inside until the Activity has ended or has been set aside.
    In case some operation inside 'lifeCycleScope' is complex and even after changing of Activity
    that operation keeps running, then is has to be checked regularly with 'isActive'.
    The 'lifeCycleScope' also will work for the life cycle of the Fragments.

    GlobalScope -> While the Main Thread is alive
    lifeCycleScope -> While the Activity or Fragment is alive
    viewModelScope -> While the ViewModel is alive (TextView or something like that)
*/

/*
    For the next chapter it is needed to have set Firebase database Firestore

    For the Chapter after that it is needed to use Retrofit.
    Retrofit turns your HTTP API into a Java interface.
*/

fun main(){

}