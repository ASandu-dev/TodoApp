package com.example.todoapp

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapp.todo_list.TodoListScreen
import com.example.todoapp.ui.theme.ToDoAppTheme
import com.example.todoapp.ui.theme.add_edit_todo.AddEditTodoScreen
import com.example.todoapp.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController,
                    startDestination = Routes.TODO_LIST){
                        composable(Routes.TODO_LIST){
                            TodoListScreen(onNavigate = {
                                navController.navigate(it.route)
                            })
                        }
                        composable(Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                        arguments = listOf(
                            navArgument(name = "todoId"){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )){
                            AddEditTodoScreen(onPopBackStack = {
                                navController.popBackStack()
                            })
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToDoAppTheme {

    }
}