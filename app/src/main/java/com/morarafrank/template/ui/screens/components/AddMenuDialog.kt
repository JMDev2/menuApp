package com.morarafrank.template.ui.screens.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.morarafrank.template.db.model.Menu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMenuDialog(
    openAddDialog: Boolean,
    closeDialog: () -> Unit,
    addMenu: (menu: Menu) -> Unit
) {


    if (openAddDialog){
        val context = LocalContext.current
        var menuName by remember{ mutableStateOf("") }
        var total by remember{ mutableStateOf("") }

        AlertDialog(

            onDismissRequest = closeDialog,
            title = {
                Text("Add Menu")
            },
            text = {
                Column(modifier = Modifier
                    .padding(8.dp)) {
                    OutlinedTextField(
                        value = menuName,
                        onValueChange = {
                            menuName = it
                        },
                        label = { Text("Menu Name") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20)
                    )

                    OutlinedTextField(
                        value = total,
                        onValueChange = {
                            total = it
                        },
                        label = { Text("Total Price") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20)
                    )

                }


            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (menuName.isNotEmpty() && total.isNotEmpty()){
                            val menu = Menu(0, menuName, total, null)
                            addMenu(menu)
                            closeDialog()
                        } else{
                            Toast.makeText(context, "Please fill out all fields!", Toast.LENGTH_LONG).show()
                        }
                    }
                ) {
                    Text("Add Menu")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    closeDialog()
                    Toast.makeText(context, "You have cancelled!", Toast.LENGTH_SHORT).show()

                }) {
                    Text("Dismiss")
                }
            }
        )

    }

}