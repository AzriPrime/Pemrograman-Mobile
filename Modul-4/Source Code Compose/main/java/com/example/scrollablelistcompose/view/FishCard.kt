package com.example.scrollablelistcompose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scrollablelistcompose.data.Fish
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview
import com.example.scrollablelistcompose.R
import com.example.scrollablelistcompose.data.FishData

import com.example.scrollablelistcompose.ui.theme.*

@Composable
fun FishCard(
    fish: Fish,
    onWikiClick: () -> Unit,
    onDetailClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = fish.imageResId),
                    contentDescription = fish.name,
                    modifier = Modifier
                        .width(110.dp)
                        .height(140.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))


                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = fish.name,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = fish.latinName,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 13.sp,
                        fontStyle = FontStyle.Italic,
                    )

                    Row {
                        Text(
                            text = stringResource(R.string.label_habitat),
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = fish.habitat,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 12.sp
                        )
                    }

                    Row {
                        Text(
                            text = stringResource(R.string.label_size),
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = fish.size,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 12.sp
                        )
                    }


                    Text(
                        text = stringResource(R.string.label_description),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = fish.description,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 15.sp,

                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onWikiClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
                    border = ButtonDefaults.outlinedButtonBorder(enabled = true)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_open_in_browser),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = stringResource(R.string.label_wiki), fontSize = 12.sp)
                }

                Button(
                    onClick = onDetailClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_info),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = stringResource(R.string.label_detail), fontSize = 12.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FishCardPreview() {
    ScrollableListComposeTheme {

        val sampleFish = FishData.getFishList()[0]

        FishCard(
            fish = sampleFish,
            onWikiClick = { },
            onDetailClick = { }
        )
    }
}
