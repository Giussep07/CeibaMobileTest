package com.giussepr.ceiba.ui.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giussepr.ceiba.R

@Composable
@Preview(showSystemUi = false)
fun UserCardItemPreview() {
    MaterialTheme {
        UserCardItem()
    }
}

@Composable
fun UserCardItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        elevation = 4.dp,
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // User name
            Text(
                text = "Alvaro Bernal",
                style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.primary)
            )
            Spacer(modifier = Modifier.height(4.dp))
            // User phone
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.Phone,
                    tint = MaterialTheme.colors.primary,
                    contentDescription = stringResource(id = R.string.phone_icon)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "300 123 4567", style = MaterialTheme.typography.body1)
            }
            Spacer(modifier = Modifier.height(4.dp))
            // User email
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.Email,
                    tint = MaterialTheme.colors.primary,
                    contentDescription = stringResource(id = R.string.phone_icon)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "giussepr@gmail.com", style = MaterialTheme.typography.body1)
            }
            // Publication button
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 16.dp)
            ) {
                Text(text = stringResource(R.string.see_publications).uppercase())
            }
        }
    }
}