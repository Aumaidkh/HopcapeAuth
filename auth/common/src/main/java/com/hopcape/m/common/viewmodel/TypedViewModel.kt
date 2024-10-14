package com.hopcape.m.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hopcape.m.common.Error
import com.hopcape.m.common.wrappers.UseCaseResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


abstract class TypedViewModel<S: ScreenState,E:ScreenEvent,A: UserAction>: ViewModel(){

    private val _eventChannel = Channel<E>()
    val event get() = _eventChannel.receiveAsFlow()

    protected fun <D,E: Error> Flow<UseCaseResult<D, E>>.handleUseCaseResult(
        onLoading: (Boolean) -> Unit,
        onSuccess: (D) -> Unit,
        onError: (E) -> Unit
    ): Flow<UseCaseResult<D, E>> {
        return this.onEach {
            when(it){
                is UseCaseResult.Success -> { onSuccess(it.data); onLoading(false); }
                is UseCaseResult.Error -> { onError(it.error); onLoading(false) }
                is UseCaseResult.Loading -> onLoading(true)
            }
        }
    }

    protected fun <D,E: Error> Flow<UseCaseResult<D,out E>>.handleUseCase(
        onLoading: (Boolean) -> Unit,
        onSuccess: (D) -> Unit,
        onError: (E) -> Unit
    ): Flow<UseCaseResult<D,out E>> {
        return this.onEach {
            when(it){
                is UseCaseResult.Success -> { onLoading(false); onSuccess(it.data); }
                is UseCaseResult.Error -> {  onLoading(false); onError(it.error) }
                is UseCaseResult.Loading -> onLoading(true)
            }
        }
    }

    abstract fun onAction(action: A)

    protected fun pushEvent(event: E){
        viewModelScope.launch {
            _eventChannel.send(event)
        }
    }


}