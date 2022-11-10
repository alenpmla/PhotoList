package com.example.library.presentation.photos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.library.R
import com.example.library.domain.entities.Photo
import com.example.library.ui.theme.CardBg
import com.example.library.ui.theme.MainFontColor
import com.example.library.ui.theme.PrimaryColor


/*
Single list item in Photo
 */
@Composable
fun PhotoListItem(
    photo: Photo,
    onItemClicked: (Photo) -> Unit
) {
    Card(modifier = Modifier) {

        Box(
            modifier = Modifier
                .background(color = CardBg)
                .fillMaxWidth()
                .clickable {
                    onItemClicked(photo)
                }

        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp, start = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier, elevation = 2.dp,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .aspectRatio(16f / 9f)
                    ) {
                        AsyncImage(
                            model = photo.thumbnailUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = photo.title, color = MainFontColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = stringResource(id = R.string.text_open),
                        color = PrimaryColor,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.End,
                    )
                }
            }

        }
    }

}