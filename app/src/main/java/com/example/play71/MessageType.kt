package com.example.play71

sealed class MessageType {
    class Success: MessageType()

    class Error: MessageType()

    class Info: MessageType()

    class None: MessageType()
}