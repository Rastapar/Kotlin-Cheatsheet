package com.example.kotlin_tutorial.newbie_to_pro

class SelfMadeException (
    private val reason : String
) : Exception ("This is a self made exception message thrown because ($reason)") {}