package com.morarafrank.template.ui.screens.components

import android.content.ContentResolver
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.morarafrank.template.R
import com.morarafrank.template.db.model.Menu

@Composable
fun MenuItemCard(
    menu: Menu,
    onClick: () -> Unit,
    image: String?
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .clickable { onClick() }
        .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
    ) {

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(10.dp))
//                Image(
//                    painter = painterResource(id = R.drawable.morar),
//                    contentDescription = "Menu Image",
//                    modifier = Modifier
//                        .size(60.dp)
//                        .clip(CircleShape)
//                )


                val painter = rememberImagePainter(
                    image ?: R.drawable.placeholder_image,
                )
                Image(
                    painter = painter,
                    contentDescription = null, // Provide a description if needed
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.FillBounds,

                )

//                val imageBitmap: ImageBitmap? = loadImageBitmap(imagePath).value
//
//                imageBitmap?.let { bitmap ->
//                    Image(
//                        painterResource(id = ),
//                        contentDescription = "Image",
//                        modifier = Modifier
//                            .size(60.dp)
//                            .clip(CircleShape)
//                    )
//                }



                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ){
                    Text(text = "Name: ${menu.name}", fontSize = 20.sp)
                    Text(text = "Price: KES ${menu.total}", fontSize = 16.sp)
                }
        }
    }
}