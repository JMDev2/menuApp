package com.morarafrank.template.ui.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.morarafrank.template.R
import com.morarafrank.template.ui.screens.components.AddMenuDialog
import com.morarafrank.template.ui.screens.components.MenuItemCard
import java.io.InputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenusScreen(
    menusViewModel: MenusViewModel = hiltViewModel(),
    navigateToUpdateMenuScreen: (menuId: Int) -> Unit,
    navigateToAddMenuScreen: () -> Unit
) {

    val allMenus by menusViewModel.allMenus.collectAsState(initial = emptyList())
    var openAddDialog by remember{ mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Menus") },
            )
        },
        content = {

            AddMenuDialog(
                openAddDialog = openAddDialog,
                closeDialog = {
                    openAddDialog = false
                },
                addMenu = { menu ->
                    menusViewModel.addMenu(menu)
                }
            )

            LazyColumn(modifier  = Modifier.padding(it)){

                Log.i("MenusScreen", "allMenus: $allMenus")
                items(allMenus) { menu ->
                    MenuItemCard(
                        menu = menu,
                        onClick = {
                        navigateToUpdateMenuScreen(menu.id)
                    },
                        image = menu.image
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          openAddDialog = true
                },
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .clip(CircleShape),
                containerColor = MaterialTheme.colorScheme.primary

            ) {
                Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = null)
            }
        },

    )
}
//@Composable
//fun readImageFromFiles(imageId: Int): Bitmap? {
//    val contentResolver = LocalContentResolver.current
//    val inputStream: InputStream? = contentResolver.openInputStream(Uri.parse("content://your_authority/$imageId"))
//    return inputStream?.let { BitmapFactory.decodeStream(it) }
//}