package com.example.noteapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.feature_note.data.data_source.NoteDao
import com.example.noteapp.feature_note.data.data_source.NoteDataBase
import com.example.noteapp.feature_note.data.repository.NoteRepositoryImp
import com.example.noteapp.feature_note.domin.repository.NoteRepository
import com.example.noteapp.feature_note.domin.use_case.AddNote
import com.example.noteapp.feature_note.domin.use_case.DeleteNote
import com.example.noteapp.feature_note.domin.use_case.GetNote
import com.example.noteapp.feature_note.domin.use_case.GetNotes
import com.example.noteapp.feature_note.domin.use_case.NoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDataBase(app: Application): NoteDataBase {
        return Room.databaseBuilder(
            app, NoteDataBase::class.java,
            NoteDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDataBase): NoteRepository {
        return NoteRepositoryImp(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(repository: NoteRepository): NoteUseCase {
        return NoteUseCase(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)

        )
    }

}