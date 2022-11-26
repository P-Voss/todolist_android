package com.example.todolist

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.ListScreen
import com.example.todolist.ui.LoginScreen
import com.example.todolist.ui.MainScreen
import com.example.todolist.ui.layout.AppLayout
import com.example.todolist.ui.layout.LoginLayout
import com.example.todolist.ui.layout.components.BottomBarLayout
import com.example.todolist.ui.layout.components.TopBarLayout
import com.example.todolist.ui.layout.components.TopBarLogin
import com.example.todolist.ui.theme.TodolistTheme
import com.example.todolist.ui.viewModel.CreateTaskViewModel
import com.example.todolist.ui.viewModel.TasklistViewModel
import com.example.todolist.ui.viewModel.UserViewModel

private const val TAG = "TodolistScreen"


enum class TodolistScreen(@StringRes val title: Int) {
    Login(title = R.string.view_login),
    Main(title = R.string.view_main),
    List(title = R.string.view_list),
    Detail(title = R.string.view_detail),
}


@Composable
fun TodolistApp(
    userViewModel: UserViewModel,
    createTaskViewModel: CreateTaskViewModel,
    tasklistViewModel: TasklistViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
)
{
    val userState by userViewModel.user.collectAsState()

    Log.d(TAG, "isLoggedIn: " + userState.loginState.toString())

    // Get the name of the current screen
    val currentScreen = if (!userViewModel.isLoggedIn()) {
         TodolistScreen.valueOf(TodolistScreen.Login.name)
    } else {
        // Get current back stack entry
        val backStackEntry by navController.currentBackStackEntryAsState()
        TodolistScreen.valueOf(
            backStackEntry?.destination?.route ?: TodolistScreen.Main.name
        )
    }

    NavHost(
        navController = navController,
        startDestination = currentScreen.name,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = TodolistScreen.Login.name) {
            LoginLayout(
                topBar = { TopBarLogin() },
                content = {
                    LoginScreen(
                        userViewModel = userViewModel,
                        onLogin = {id: Int -> tasklistViewModel.refreshTasklist(id)}
                    )
                },
                navBar = { BottomBarLayout() }
            )
        }
        composable(route = TodolistScreen.Main.name) {
            AppLayout(
                topBar = {
                    TopBarLayout(
                        userViewModel = userViewModel,
                        currentScreen = currentScreen,
                        canNavigateBack = false,
                        navigateToMain = { navController.navigate(TodolistScreen.Main.name) }
                    )
                 },
                content = {
                    MainScreen(
                        userViewModel = userViewModel,
                        createTaskViewModel = createTaskViewModel,
                        tasklistViewModel = tasklistViewModel
                    )
                },
                navBar = { BottomBarLayout() },
                navController = navController,
                buttonLabel = R.string.navigation_label_list,
                navigationTarget = TodolistScreen.List
            )
        }
        composable(route = TodolistScreen.List.name) {
            AppLayout(
                topBar = {
                    TopBarLayout(
                        userViewModel = userViewModel,
                        currentScreen = currentScreen,
                        canNavigateBack = false,
                        navigateToMain = { navController.navigate(TodolistScreen.Main.name) }
                    )
                },
                content = { innerPadding: PaddingValues -> ListScreen(
                    userViewModel = userViewModel,
                    tasklistViewModel = tasklistViewModel
                ) },
                navBar = { BottomBarLayout() },
                navController = navController,
                buttonLabel = R.string.navigation_label_main,
                navigationTarget = TodolistScreen.Main
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodolistTheme {
        TodolistApp(
            userViewModel = UserViewModel(),
            createTaskViewModel = CreateTaskViewModel(),
            tasklistViewModel = TasklistViewModel()
        )
    }
}