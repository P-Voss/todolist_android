package com.example.todolist

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.*
import com.example.todolist.ui.layout.AppLayout
import com.example.todolist.ui.layout.LoginLayout
import com.example.todolist.ui.layout.components.BottomBarLayout
import com.example.todolist.ui.layout.components.TopBarLayout
import com.example.todolist.ui.layout.components.TopBarLogin
import com.example.todolist.ui.viewModel.CreateTaskViewModel
import com.example.todolist.ui.viewModel.TasklistViewModel
import com.example.todolist.ui.viewModel.UserViewModel

private const val TAG = "TodolistScreen"


enum class TodolistScreen(@StringRes val title: Int) {
    Login(title = R.string.view_login),
    SignUp(title = R.string.view_signup),
    Main(title = R.string.view_main),
    List(title = R.string.view_list),
    Friendlist(title = R.string.view_friendlist),
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

    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = if (!userViewModel.isLoggedIn()) {
        if ((backStackEntry?.destination?.route ?: TodolistScreen.Login.name) == TodolistScreen.Login.name) {
            TodolistScreen.valueOf(TodolistScreen.Login.name)
        } else {
            TodolistScreen.valueOf(TodolistScreen.SignUp.name)
        }
    } else {
        if (backStackEntry?.destination?.route == TodolistScreen.Login.name || backStackEntry?.destination?.route == TodolistScreen.SignUp.name) {
            // Get current back stack entry
            TodolistScreen.valueOf(TodolistScreen.Main.name)
        } else {
            TodolistScreen.valueOf(backStackEntry?.destination?.route ?: TodolistScreen.Main.name)
        }
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
                        onLogin = {id: Int ->
                            tasklistViewModel.refreshTasklist(id)
                        },
                        onToSignInClick = {navController.navigate(TodolistScreen.SignUp.name)}
                    )
                },
                navBar = { BottomBarLayout() }
            )
        }
        composable(route = TodolistScreen.SignUp.name) {
            LoginLayout(
                topBar = { TopBarLogin() },
                content = {
                    SignupScreen(
                        userViewModel = userViewModel,
                        onSignup = {id: Int -> tasklistViewModel.refreshTasklist(id)},
                        onToLoginClick = {navController.navigate(TodolistScreen.Login.name)}
                    )
                },
                navBar = { BottomBarLayout() }
            )
        }
        composable(route = TodolistScreen.Main.name) {
            AppLayout(
                topBar = {
                    TopBarLayout(
                        onLogout = {
                            userViewModel.logout { navController.navigate(TodolistScreen.Login.name) }
                        },
                        onToFriendlist = { navController.navigate(TodolistScreen.Friendlist.name) }
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
                        onLogout = {
                            userViewModel.logout { navController.navigate(TodolistScreen.Login.name) }
                        },
                        onToFriendlist = { navController.navigate(TodolistScreen.Friendlist.name) }
                    )
                },
                content = {
                    TasklistScreen(
                        userViewModel = userViewModel,
                        tasklistViewModel = tasklistViewModel
                    )
                },
                navBar = { BottomBarLayout() },
                navController = navController,
                buttonLabel = R.string.navigation_label_main,
                navigationTarget = TodolistScreen.Main
            )
        }
        composable(route = TodolistScreen.Friendlist.name) {
            AppLayout(
                topBar = {
                    TopBarLayout(
                        onLogout = {
                            userViewModel.logout { navController.navigate(TodolistScreen.Login.name) }
                        },
                        onToFriendlist = { navController.navigate(TodolistScreen.Friendlist.name) }
                    )
                },
                content = {
                    FriendlistScreen(
                        userViewModel = userViewModel
                    )
                },
                navBar = { BottomBarLayout() },
                navController = navController,
                buttonLabel = R.string.navigation_label_main,
                navigationTarget = TodolistScreen.Main
            )
        }
    }
}
