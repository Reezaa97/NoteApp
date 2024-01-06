package com.example.noteapp.presentation.notes

import com.example.noteapp.feature_note.domin.model.Note
import com.example.noteapp.feature_note.domin.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSection : NotesEvent()
}
