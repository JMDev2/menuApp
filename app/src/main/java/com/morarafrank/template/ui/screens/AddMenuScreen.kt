package com.morarafrank.template.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.morarafrank.template.db.model.Menu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMenuScreen(
    viewModel: MenusViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Menus") },)
        }
    ) {

        var name by remember{ mutableStateOf("") }
        var total by remember{ mutableStateOf("") }
        val context = LocalContext.current

        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .padding(8.dp)) {
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {Text("What are you saving for?")},
                placeholder = {Text("Laptop")},
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
                label = {Text("Goal Amount ?")},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20)
            )

            OutlinedButton(
                onClick = {
                          if (name.isNotEmpty() && total.isNotEmpty()){
                              val menu = Menu(0, name, total, null)
                              viewModel.addMenu(menu)
                              navigateBack()
                          } else{
                              Toast.makeText(context, "Please fill out all fields!", Toast.LENGTH_LONG).show()
                          }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Add Menu")
            }
        }
        
    } 
}