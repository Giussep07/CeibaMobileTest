package com.giussepr.ceiba.ui.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giussepr.ceiba.ui.presentation.theme.CeibaTheme

@Composable
@Preview
fun PublicationCardItemPreview() {
    CeibaTheme {
        PublicationCardItem(
            title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
        )
    }
}

@Composable
fun PublicationCardItem(title: String, body: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        elevation = 1.dp,
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // title
            Text(
                text = title,
                style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.primary)
            )
            Spacer(modifier = Modifier.height(4.dp))
            // Body
            Text(
                text = body,
                style = MaterialTheme.typography.body1
            )
        }
    }
}