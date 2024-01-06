package com.example.noteapp.feature_note.domin.use_case

import com.example.noteapp.feature_note.domin.model.Note
import com.example.noteapp.feature_note.domin.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}