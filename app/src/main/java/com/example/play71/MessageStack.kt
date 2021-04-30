package com.example.play71

class MessageStack: ArrayList<StateMessage>() {

    fun isStackEmpty(): Boolean{
        return size == 0
    }
}