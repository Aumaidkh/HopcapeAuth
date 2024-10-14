package com.hopcape.tracemanager

import java.lang.Exception

interface TraceManager {
    fun configure(
        config: TraceConfig
    )
    fun logAction(caller: String,data: String)

    fun logFailure(caller: String,exception: Exception)

    fun logFailure(caller: String,errorMessage: String)

}

interface TraceConfig {
    val tag: String
}