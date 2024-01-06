package com.example.noteapp.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.feature_note.domin.model.Note
import com.example.noteapp.feature_note.domin.use_case.NoteUseCase
import com.example.noteapp.feature_note.domin.util.NoteOrder
import com.example.noteapp.feature_note.domin.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state
    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    init {
        getnotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder == event.noteOrder.orderType
                ) {
                    return
                }
                getnotes(event.noteOrder)


            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCase.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }

            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCase.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }

            }

            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )

            }


        }
    }

    private fun getnotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCase.getNotes(noteOrder).onEach { notes ->
            _state.value = state.value.copy(
                notes = notes, noteOrder = noteOrder
            )
        }
            .launchIn(viewModelScope)
    }
}