package com.example.noteapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteapp.presentation.add_edit_note.AddEditNoteScreen
import com.example.noteapp.presentation.notes.NotesScreen
import com.example.noteapp.presentation.util.Screen
import com.example.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                Surface(color = androidx.compose.material.MaterialTheme.colors.background) {
                    val navController= rememberNavController()
                    NavHost(navController = navController,
                        startDestination = Screen.NoteScreen.route
                    ){
                        composable(route = Screen.NoteScreen.route){
                            NotesScreen(navController = navController)
                        }
                        composable(route=Screen.AddEditScreen.route +
                                "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                )
                        {
                                    type= NavType.IntType
                            defaultValue=-1

                        },

                                    navArgument(
                                        name = "noteColor"
                                    )
                                    {
                                        type= NavType.IntType
                                        defaultValue=-1
                                    },

                        )
                        ){
                            val color=it.arguments?.getInt("noteColor")?:-1
                            AddEditNoteScreen(navController = navController,
                                noteColor = color)
                        }

                    }
                }

            }
        }
    }
}


