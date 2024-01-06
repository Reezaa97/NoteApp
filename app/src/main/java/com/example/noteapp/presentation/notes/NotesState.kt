package com.example.noteapp.presentation.notes

import android.provider.ContactsContract.CommonDataKinds.Note
import com.example.noteapp.feature_note.domin.util.NoteOrder
import com.example.noteapp.feature_note.domin.util.OrderType

data class NotesState(
    val notes: List<com.example.noteapp.feature_note.domin.model.Note> = emptyList(),
    val noteOrder: NoteOrder =NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible:Boolean=false
)
