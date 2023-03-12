package com.giussepr.ceiba.ui.presentation.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.giussepr.ceiba.R

@Composable
fun SearchTextField(searchTerm: MutableState<String>, onValueChange: (String) -> Unit) {

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        value = searchTerm.value,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        label = { Text(text = stringResource(R.string.search_user)) },
        onValueChange = onValueChange)
}
