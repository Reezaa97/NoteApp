package com.example.noteapp.presentation.util

sealed class Screen(val route:String){
    object NoteScreen:Screen("notes_screen")
    object AddEditScreen:Screen("add_edit_screen")
}
