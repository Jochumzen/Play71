package com.example.play71

sealed class MyStateEvent: StateEvent {

    class InsertBabyNameEvent(
        val name: String
    ): MyStateEvent() {

        override fun errorInfo(): String {
            return "Error inserting new baby name."
        }

        override fun eventName(): String {
            return "InsertBabyNameEvent"
        }

        override fun shouldDisplayProgressBar() = true
    }
}