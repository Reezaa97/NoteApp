package com.example.noteapp.feature_note.domin.use_case

import com.example.noteapp.feature_note.domin.model.InvalidNoteException
import com.example.noteapp.feature_note.domin.repository.NoteRepository
import kotlin.jvm.Throws

class AddNote(private val repository: NoteRepository) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: com.example.noteapp.feature_note.domin.model.Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty")

        }
        if (note.content.isBlank()){
            throw InvalidNoteException("The content of the note can't be empty")
        }
        repository.insertNote(note)

    }
}