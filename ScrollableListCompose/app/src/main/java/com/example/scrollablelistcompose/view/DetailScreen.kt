package com.example.scrollablelistcompose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scrollablelistcompose.viewmodel.FishViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: FishViewModel,
    fishIndex: Int,
    onBack: () -> Unit
) {
    // Mengambil data ikan dari ViewModel, bukan langsung dari FishData (ketentuan a)
    val fish = viewModel.getFishByIndex(fishIndex)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Image(
                painter = painterResource(id = fish.imageResId),
                contentDescription = fish.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 24.dp,
                            bottomEnd = 24.dp
                        )
                    ),
                contentScale = ContentScale.Crop
            )

            TextButton(
                onClick = onBack,
                modifier = Modifier.padding(start = 4.dp, top = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = stringResource(R.string.label_back),
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.label_back),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 14.sp
                )
            }
        }

        //Content Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset(y = (-24).dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = fish.name,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = fish.latinName,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 4.dp)
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )

                Row(modifier = Modifier.padding(bottom = 8.dp)) {
                    Text(
                        text = stringResource(R.string.label_habitat),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = fish.habitat,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 14.sp
                    )
                }

                Row(modifier = Modifier.padding(bottom = 12.dp)) {
                    Text(
                        text = stringResource(R.string.label_size),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = fish.size,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 14.sp
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.padding(bottom = 12.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )

                Text(
                    text = stringResource(R.string.label_description),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                Text(
                    text = fish.description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
