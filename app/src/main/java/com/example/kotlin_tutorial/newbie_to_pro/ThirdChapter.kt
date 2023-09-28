package com.example.kotlin_tutorial.newbie_to_pro

import com.example.kotlin_tutorial.newbie_to_pro.SubclassOfAbstract as SubclassOfAbstract1

/*
Here we are going to see rare types of classes
*/
fun main(){
    // Abstract class
    // We can define the type of the object as the Abstract super class to generalize
    // Or we can just define the type of the subclass
    var ac : MyAbstractClass = SubclassOfAbstract1(10, 2)
    ac.printSumSuperAtributes()
    println("The sum of the atributes of the subclass and the abstract super class are: ${ac.operateAtributes()}")
    println()

    // Singleton Object
    var mySO = MySingletonObject    // Passed by reference, mySO points to the Singleton Object
    val soNum1 = MySingletonObject.constantNum1
    var soNum2 = MySingletonObject.constantNum2
    MySingletonObject.num3 += soNum1 + soNum2
    var soNum3 = mySO.num3      // Passed by value, so soNum3 is a copy of the value of mySO.num3
    var soSum = mySO.sumConstantValues()
    println("My Singleton Object has the constants $soNum1 and $soNum2 while the sum is $soSum")
    println("And the third atribute of the Singleton Object is $soNum3")
    println()

    // Companion Object
    var coNum3 = MyCompanionObject.const1
    var myCO = MyCompanionObject.randomObject() // a function that returns an instance of the class
    var myCO2 = MyCompanionObject(2, 8)
    MyCompanionObject.printInfo()
    println("The sum of the values of the Companion Object is (${myCO.sumAtrs()}) and the atribute definded in the CO is ($coNum3)")
    println("The sum of the values of the Companion Object 2 is (${myCO2.sumAtrs()}) and the atribute definded in the CO is ($coNum3)")
    println()

    // Anonymus Object
    myAnonymousObject()
    println()
}