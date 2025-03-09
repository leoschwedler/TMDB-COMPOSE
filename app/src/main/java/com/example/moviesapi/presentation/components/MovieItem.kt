package com.example.moviesapi.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviesapi.data.remote.dto.ResultDTO

@Composable
fun MovieItem(
    movie: ResultDTO,
    onclick: (ResultDTO) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .width(IntrinsicSize.Min)
        .clickable {
            onclick(movie)
        }) {
        AsyncImage(
            model = movie.fullPatch,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(end = 4.dp)
                .width(120.dp)
                .height(150.dp),
            contentDescription = null
        )
        Spacer(Modifier.height(5.dp))
        Text(text = movie.title, maxLines = 1, fontWeight = FontWeight.SemiBold)
        Text(
            text = movie.overview,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold
        )
    }
}
