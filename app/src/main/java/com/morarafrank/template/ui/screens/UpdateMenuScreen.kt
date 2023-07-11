package com.morarafrank.template.ui.screens

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.morarafrank.template.R
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

//val LocalContentResolver = compositionLocalOf<ContentResolver> {
//    error("No ContentResolver provided")
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateMenu(
    navigateBack: () -> Unit,
    menuId: Int,
    viewModel: MenusViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getMenu(menuId)
    }

    var name by remember{ mutableStateOf(viewModel.menu.name) }
    var total by remember{ mutableStateOf(viewModel.menu.total) }
    val context = LocalContext.current
    var selectedImageUri by remember{ mutableStateOf<Uri?>(null) }

    var visibleImageView by remember{ mutableStateOf(true) }

//    val contentResolver = LocalContentResolver.current
    Scaffold(
        topBar = {
                 TopAppBar(
                     title = {
                         Row(
                             modifier = Modifier.fillMaxWidth(),
                             horizontalArrangement = Arrangement.Center,
                             verticalAlignment = Alignment.CenterVertically
                         ) {
                             Text("Add Image", color = MaterialTheme.colorScheme.primary)
                         }
                     },
                     navigationIcon = {
                         IconButton(onClick = { navigateBack() }) {
                             Icon(
                                 painter = painterResource(id = R.drawable.ic_arrow_back),
                                 contentDescription = null)
                         }
                     }
                 )
        },
        
        content = {
            Column(modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent(),
                    onResult = { uri: Uri? ->
                        uri?.let {
                            selectedImageUri = it
                        }
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Add Image", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(20.dp))


                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.BottomEnd
                ) {
                    selectedImageUri?.let { uri ->
                        Image(
                            painter = rememberImagePainter(uri),
                            contentDescription = "Selected Image",
                            modifier = Modifier
                                .size(130.dp)
                                .clip(CircleShape)
                        )
                    }

                    IconButton(
                        onClick = {
                            launcher.launch("image/*")

                        },
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color.Green)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = null
                        )
                    }
                }

//                val inputStream: InputStream? = selectedImageUri?.let { it1 ->
//                    contentResolver.openInputStream(
//                        it1
//                    )
//                }
//                val imageBitmap = BitmapFactory.decodeStream(inputStream)
                OutlinedButton(
                    onClick = {
//                        saveImageToFile(
//                            imageBitmap, "Image", menuId
//                        )
                        viewModel.updateMenuPath(selectedImageUri.toString())
                        navigateBack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "Add")
                }
            }
        }
    )

}

val imageMap: MutableMap<Int, Uri> = mutableMapOf()

fun saveImageToFile(imageBitmap: Bitmap, fileName: String, entityId: Int): Uri? {
    val storageDir = Environment.getExternalStorageDirectory()
    val imageFile = File(storageDir, fileName)

    return try {
        val fos = FileOutputStream(imageFile)
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        fos.close()

        imageMap[entityId] = Uri.fromFile(imageFile)

        Uri.fromFile(imageFile)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}


