package com.example.kotlin_tutorial.newbie_to_pro

/*
    We can leave the generic types like '<A, B, C>'
    But if we add a little more specific type like '<A : Any?, B : Any?, C : Any?>' we are giving
    the chance to accept any type with nullable safety
    'Number' is the superclass of 'Int', 'Double', 'Float' and so on
*/

class MyGenericClass <A : Number?, B : Any?, C : Any?> (
    val atr1 : A,
    val atr2 : B,
    val atr3 : C,
){
    fun printGenericAtributes() {
        println("The atributes of the Generic Class are: ($atr1), ($atr2), ($atr3)")

        if (atr1 != null && atr2 != null && atr3 != null) {
            println("The classes of the atributes of the Generic Class are: (${atr1!!::class.simpleName}), (${atr2!!::class.simpleName}), (${atr3!!::class.simpleName})")
        }
    }
}