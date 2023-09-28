package com.example.kotlin_tutorial.newbie_to_pro

/*
    An abstract class is a class that defines a shape but we can't create
    objects of this class. Basically it is a super class which objects we can't create.
    However this class can be inherited and behave like others classes.
    Same happens for the abstract functions or abstract attributes. Functions that are
    defined as abstract can only be defined and it is the job of the subclasses to implement
    the functionality of that class (like an interface).
 */

abstract class MyAbstractClass (
    var atribute1 : Int
){
    abstract var atribute2 : Int    // Will be implemented by the subclasses
    abstract var atribute3 : Int    // Will be implemented by the subclasses
    var atribute4 : Int = 4

    init {
        println("Creation of the Abstract Class")
    }

    abstract fun operateAtributes() : Int   // Will be implemented by the subclasses

    fun printSumSuperAtributes() {
        // You can print all the atributes even if they are not initialized in this class
        // Because they will be initialized by the subclass
        println("The sum of the atributes of the super class is: ${this.atribute1+this.atribute2+this.atribute3+this.atribute4}")
    }
}

class SubclassOfAbstract (
    var subAtribute1 : Int,         // constructor with one parameter
    override var atribute2: Int
) : MyAbstractClass(1) {

    var subAtribute2 = 20
    override var atribute3: Int = 3 // mandatory it is needed to initialize the attribute of the abstract super class

    init {
        println("Creation of the Subclass of the Abstract class")
    }

    // No need to put whay type it returns because it is set in the abstract function
    override fun operateAtributes() = super.atribute1 + this.atribute2 + this.atribute3 + super.atribute4 + this.subAtribute1 + this.subAtribute2
}