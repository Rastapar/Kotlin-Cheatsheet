package com.example.kotlin_tutorial.newbie_to_pro

class OverloadClass (
    var atr1 : Int,     // constructor por defecto
) {

    // Un constructor que también admite un parámetro pero de otro tipo
    constructor(num1 : Double) : this( num1.toInt() )

    // Un constructor que admite 2 parametros Int y se crea a partir del constructor por defecto
    constructor(num1 : Int, num2 : Int) : this(num1) {
        var atr2 = num2
    }

    // Un constructor que admite 3 parametros Int y se crea a partir del constructor por defecto
    constructor(num1 : Int, num2 : Int, num3 : Int) : this(num1) {
        var atr2 = num2
        var atr3 = num3
    }

    // Un constructor que admite 4 parametros Int y se crea a partir del constructor con 3 parametros Int
    constructor(num1 : Int, num2 : Int, num3 : Int, num4 : Int) : this(num1, num2, num3) {
        var atr4 = num4
    }

    // Overloading functions, same principle that with constructors
    fun sumNums(num1 : Int, num2 : Int) : Int {
        return num1 + num2
    }

    fun sumNums(num1 : Int, num2 : Int, num3 : Int) : Int {
        return num1 + num2 + num3
    }

    fun sumNums(num1 : Double, num2 : Double, num3 : Double) : Double {
        return sumNums(num1.toInt(), num2.toInt(), num3.toInt()).toDouble()
    }
}