package com.hopcape.tracemanager

import android.util.Log
import java.lang.Exception
import javax.inject.Inject

internal class AndroidLogger @Inject constructor(): TraceManager {

    private var config: TraceConfig? = null
    override fun configure(config: TraceConfig) {
        this.config = config
    }

    override fun logAction(
        caller: String,
        data: String
    ) {
        config?.let {
            Log.d(
                it.tag,
                "$caller -> $data"
            )
        }?: throw IllegalStateException("Trace configuration not set")
    }

    override fun logFailure(
        caller: String,
        exception: Exception
    ) {
        logFailure(caller, exception.message.toString())
    }

    override fun logFailure(
        caller: String,
        errorMessage: String
    ) {
        config?.let {
            Log.e(
                it.tag,
                "$caller -> $errorMessage",

                )
        }?: throw IllegalStateException("Trace configuration not set")
    }
}