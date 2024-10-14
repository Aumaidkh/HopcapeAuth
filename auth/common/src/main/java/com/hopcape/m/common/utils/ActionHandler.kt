package com.hopcape.m.common.utils

class ActionHandler<A : Any> {
    private val actionMap = mutableMapOf<A, () -> Unit>()

    fun register(action: A, handler: () -> Unit) {
        actionMap[action] = handler
    }

    fun handleAction(action: A) {
        actionMap[action]?.invoke()
    }
}
