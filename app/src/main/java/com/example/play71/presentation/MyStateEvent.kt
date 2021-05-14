package com.example.play71.presentation

import com.example.play71.StateEvent

sealed class MyStateEvent: StateEvent {

    class GetNationalityEvent(
    ): MyStateEvent() {

        override fun errorInfo(): String {
            return "Error getting a new nationality."
        }

        override fun eventName(): String {
            return "GetNationalityEvent"
        }

        override fun shouldDisplayProgressBar() = true
    }
}