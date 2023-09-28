package com.example.kotlin_tutorial.newbie_to_pro

import kotlin.reflect.typeOf

/*
Exceptions, Lambda Functions and Generics
*/

fun main(){

    // Exception
    exceptionFunction(3)
    exceptionFunction(null)
    println()

    // Self made exception
    catchSelfMadeException(5)
    catchSelfMadeException(null)
    println()

    // Lambda functions
    lambdaFunctions()
    println()

    // Generics
    val result1 = myGenericLamdaFunction<String, Double>("algo", 10)
    println(result1)
    val result2 = myGenericLamdaFunction<Double, String>(10.0, 10)
    println(result2)
    val result3 = myGenericLamdaFunction<Int, Boolean>(4, 10)
    println(result3)
    println()

    // Generic Class
    // The types can be written explicitly, but Kotlin can deduce them from the parameters automatically
    val gc = MyGenericClass(17, true, "amigo")
    gc.printGenericAtributes()
    println()
}


fun exceptionFunction(num: Int?){
    try {
        num!!.toDouble()    // With !! we assure that the value is not null, we use that on porpoise to provoke an exception
        println("We have converted the parameter successfully $num")
    }
    catch (e : Exception){
        println("The exception is ${e.toString()}")
    }
    finally {
        println("Something that will always be printed")
    }
}

fun selfMadeExceptionFunction (num : Int?) : Int {
    if(num == null){
        throw SelfMadeException("The number passed as parameter is null")
    }
    else return num
}

fun catchSelfMadeException (param : Int?) {
    val num = try {
        selfMadeExceptionFunction(param)
    }
    catch (e : SelfMadeException){
        println("We caught our own exception")
    }

    // This block will be executed anyway if the exception has been caught
    println("The number is $num")
}

fun lambdaFunctions () {
    // If you press Ctrl+P, you will see the parameters of a function
    // If the parameter is 'predicate', it means the parameter is a function
    var myList = (1..10).toList()
    println(myList)

    // To the filter function we have to pass a function (lambda)
    // We set the parameter name to num (per default is 'it') and the lambda function is just an expression that returns a boolean
    val filteredList = myList.filter { num ->
        num % 2 == 0
    }
    println(filteredList)

    val sortedList = filteredList.sortedByDescending {
        it
    }
    println(sortedList)

    // Here I test my own function with lambda function
    var finalList = sortedList.myLambdaFunction { it >= 5 }
    println(finalList)
}

// We overload the List class, adding the 'myLambdaFunction' to the class
fun List<Int>.myLambdaFunction(paramFun : (Int) -> (Boolean)): List<Int> {
    var returnList = mutableListOf<Int>()
    // this refers to List<Int> at the beginning
    for (item in this){
        // Each item of the list passes to the given lambda function as parameter
        // If the given lambda function returns 'true' with that item,
        // then the item is added to 'returnList'
        if ( paramFun(item) ) {
            returnList.add(item)
        }
    }
    return returnList
}

/*
    Generics are the generic types we can set in functions or classes.
    We can set them with whatever capital letter we want, usually it is used the <T>
    At the beginning of the function we declare the generic type and then use it where it is needed
*/
/*
    When a function is marked as inline, the compiler inserts the function's code directly
    at the call site, avoiding the overhead of a function call.
    This can result in reduced runtime overhead,
    especially when the function is small and called frequently.
    Inlining can increase code size, and it is generally recommended for small functions
    or cases where performance improvements are significant.
*/
/*
    When you declare a generic type parameter in Kotlin, the type information for that parameter
    is erased at runtime due to a process called type erasure.
    However, when you declare a type parameter as reified in the context of an inline function,
    it allows you to access the actual type at runtime.
    Without reified, you would need to pass a Class parameter explicitly to achieve similar
    functionality. The reified keyword makes the code cleaner and more concise.
    It's important to note that reified can only be used with inline functions because
    it relies on the inlining mechanism to access type information at the call site.
*/
inline fun <T, reified R> myGenericLamdaFunction(param1 : T, param2 : Int) : Any {
    // if the types of the parameters it is the same, we return true
    // As param1 has no type at the moment, it can be null, so when we use it we have to assure it is not null
    if(param1!!::class.simpleName == param2::class.simpleName) {
        return when(R::class){
            Int::class -> 1
            Boolean::class -> true
            String::class -> "Tipos iguales"
            else -> "Tipo desconocido"
        }
    }
    else{
        return when(R::class){
            Int::class -> 0
            Boolean::class -> false
            String::class -> "Tipos diferentes"
            else -> "Tipo desconocido"
        }
    }
}
