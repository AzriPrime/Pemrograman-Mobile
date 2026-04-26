package com.example.scrollablelistcompose


import com.example.scrollablelistcompose.data.FishData
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

import com.example.scrollablelistcompose.ui.theme.ScrollableListComposeTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToDetail: (Int) -> Unit
) {
    val context = LocalContext.current
    val fishList = FishData.getFishList()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(R.string.title_species),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 12.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(fishList.size) { index ->
                val fish = fishList[index]
                FishCard(
                    fish = fish,
                    onWikiClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fish.wikiUrl))
                        context.startActivity(intent)
                    },
                    onDetailClick = {
                        onNavigateToDetail(index)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ScrollableListComposeTheme(dynamicColor = false) {
        HomeScreen(
            onNavigateToDetail = {}
        )
    }
}

