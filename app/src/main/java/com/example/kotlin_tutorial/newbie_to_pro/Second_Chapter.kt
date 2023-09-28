package com.example.kotlin_tutorial.newbie_to_pro

/*
    Classes, inheritance, constructors and visibility (public, protected, private)
*/

fun main(){
    /*
    val nm = NormalClass(5)
    nm.addAttributes()
    nm.doubleFirstParameter()
     */
    var mySubObject = Subclass("myAtribute1_Text", 20).also {
        it.printSubclass()
    }
}

class NormalClass (
    val atr1 : Int
) {
    val atr2 : String = "second attribute"

    init {
        println("We have created an object of a Normal Class")
    }

    fun addAttributes() { println("My attribute 1 is $atr1 and my attribute 2 is $atr2") }

    fun doubleFirstParameter() = println("The x2 of the first parameter is ${atr1*2}")
}

// Open class means it can be inherited, so this class can be a super class now
// We set this class as protected, by default everything is public
open class SuperClass protected constructor(
    var superAtribute1 : Int,   // parameter that becomes in an atribute
    justAparameter : Int    // some parameter that won't be saved as atribute in the object
){
    var superAtribute2 = ""     // default atribute, can also be initialized in init or other constructors

    init {
        superAtribute2 = justAparameter.toString()  // we can use the parameters only in init
        println("Executed Super Class with atributes (${this.superAtribute1}) (${this.superAtribute2})")
    }

    // Second constructor
    // This constructor creates an object from the init constructor
    constructor(num : Int, someText : String, someMoreText : String) : this(num, 5) {
        this.superAtribute2 = superAtribute2 + someText + someMoreText
    }

    fun changeSuperParameter(num : Int){
        superAtribute1 = num
    }
}

class Subclass (
    var subclassAtribute1 : String,
    subclassParameter : Int
) : SuperClass(3, "someText", "someMoreText")   // Here we use the second constructor of the super class
{
    var subclassAtribute2 = "I am the subclass"

    init {
        subclassAtribute2 += " and I have executed the init of subclass"
        println(subclassAtribute2)
        println("My super class attributes are (${this.superAtribute1}) and (${super.superAtribute2}) ")

        this.changeSuperParameter(90)
        // we can use the parameters only in init. And for super atributes we can use super. instead of this.
        this.superAtribute1 += subclassParameter + super.superAtribute1
    }

    fun printSubclass(){
        println("And now my super class attributes are (${super.superAtribute1}) and (${super.superAtribute2}) ")
        println("Moreover my subclass attributes are (${this.subclassAtribute1}) and (${this.subclassAtribute2})")
    }
}