package com.example.kotlin_tutorial.newbie_to_pro

/*
    This is a single instance of an object.
    Used in the case when a class has many default atributes or constants so we don't want
    to create every time an instance of that class with those defalut values. Instead we will
    create only a single instance of that class to save those variables.
    We can import this object wherever we want and use its properties.
*/

object MySingletonObject {
    // Constant created in compile time.
    // They must be top-level or member of an object declaration
    // and they cannot be declared inside functions or local scopes.
    // They can only hold primitive types
    // (or types that can be represented as primitives) and strings.
    const val constantNum1 = 1000

    // Constant created in run time.
    // Can hold any type, including complex objects.
    const val constantNum2 = 2000

    var num3 = 3000

    fun sumConstantValues() = constantNum1 + constantNum2
}