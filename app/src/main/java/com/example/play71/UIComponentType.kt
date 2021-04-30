package com.example.play71

sealed class UIComponentType {
    class Toast: UIComponentType()

    class Dialog: UIComponentType()

    class None: UIComponentType()
}