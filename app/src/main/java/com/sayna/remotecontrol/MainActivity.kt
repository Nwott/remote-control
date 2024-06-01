package com.sayna.remotecontrol

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SettingsRemote
import androidx.compose.material.icons.outlined.AppRegistration
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SettingsRemote
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sayna.remotecontrol.feature_rc_action.presentation.add_rcaction.AddRCActionScreen
import com.sayna.remotecontrol.feature_rc_action.presentation.add_rcaction.AddRCActionViewModel
import com.sayna.remotecontrol.feature_rc_action.presentation.add_rcaction.util.AddEditRCActionIntent
import com.sayna.remotecontrol.feature_rc_action.presentation.edit_remote.EditRemoteScreen
import com.sayna.remotecontrol.feature_rc_action.presentation.remote.RemoteScreen
import com.sayna.remotecontrol.feature_rc_action.presentation.util.Screen
import com.sayna.remotecontrol.ui.theme.RemoteControlTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

data class BottomNavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var addRCActionViewModel: AddRCActionViewModel

    private val filePicker = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val selectedFileUri = data?.data
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RemoteControlTheme {

                val items = listOf(
                    BottomNavigationItem(
                        title = "Remote",
                        route = Screen.RemoteScreen.route,
                        selectedIcon = Icons.Filled.SettingsRemote,
                        unselectedIcon = Icons.Outlined.SettingsRemote,
                        hasNews = false
                    ),
                    BottomNavigationItem(
                        title = "Edit",
                        route = Screen.EditRemoteScreen.route,
                        selectedIcon = Icons.Filled.AppRegistration,
                        unselectedIcon = Icons.Outlined.AppRegistration,
                        hasNews = false
                    ),
                    BottomNavigationItem(
                        title = "Settings",
                        route = "",
                        selectedIcon = Icons.Filled.Settings,
                        unselectedIcon = Icons.Outlined.Settings,
                        hasNews = false
                    ),
                )

                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }

                Surface (
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    Scaffold (
                        bottomBar = {
                            NavigationBar {
                                items.forEachIndexed{ index, item ->
                                    NavigationBarItem(
                                        selected = selectedItemIndex == index,
                                        onClick = {
                                            selectedItemIndex = index
                                            if(item.route.isNotBlank()) {
                                                navController.navigate(item.route)
                                            }
                                        },
                                        label = {
                                            Text(text = item.title)
                                        },
                                        icon = {
                                            BadgedBox(
                                                badge = {
                                                    if(item.badgeCount != null) {
                                                        Badge {
                                                            Text(text = item.badgeCount.toString())
                                                        }
                                                    } else if(item.hasNews) {
                                                        Badge()
                                                    }
                                                }
                                            ) {
                                                Icon(
                                                    imageVector = if(index == selectedItemIndex) {
                                                                     item.selectedIcon
                                                                 } else {
                                                                    item.unselectedIcon
                                                                },
                                                    contentDescription = item.title
                                                )
                                            }
                                        })
                                }


                            }
                        }
                    ){
                        NavHost(
                            modifier = Modifier.padding(it),
                            navController = navController,
                            startDestination = Screen.RemoteScreen.route
                        ) {
                            composable(route = Screen.RemoteScreen.route)  {
                                RemoteScreen()
                            }
                            composable(route = Screen.EditRemoteScreen.route) {
                                EditRemoteScreen(navController = navController)
                            }
                            composable(
                                route = Screen.AddRCActionScreen.route + "/{editing}/{rcActionId}",
                                arguments = listOf(
                                    navArgument("editing") {
                                        type = NavType.BoolType
                                        defaultValue = false
                                        nullable = false
                                    },
                                    navArgument("rcActionId") {
                                        type = NavType.IntType
                                        defaultValue = -1
                                        nullable = false
                                    }
                                )
                            ) { entry ->
                                entry.arguments?.getBoolean("editing")?.let { it1 ->
                                    AddRCActionScreen(editing = it1,
                                        navController = navController
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }

    }
}