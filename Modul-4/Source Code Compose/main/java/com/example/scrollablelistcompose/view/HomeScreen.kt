package com.example.scrollablelistcompose.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.scrollablelistcompose.R
import com.example.scrollablelistcompose.ui.theme.DividerDark
import com.example.scrollablelistcompose.viewmodel.FishViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: FishViewModel,
    onNavigateToDetail: (Int) -> Unit
) {
    val context = LocalContext.current

    val fishList by viewModel.fishList.collectAsStateWithLifecycle()

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
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(fishList.size) { index ->
                        val fish = fishList[index]
                        FishCard(
                            fish = fish,
                            modifier = Modifier.width(370.dp),
                            onWikiClick = {
                                viewModel.onWikiClick(index)
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fish.wikiUrl))
                                context.startActivity(intent)
                            },
                            onDetailClick = {
                                onNavigateToDetail(index)
                            }
                        )
                    }
                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = DividerDark
                )
            }

            items(fishList.size) { index ->
                val fish = fishList[index]
                FishCard(
                    fish = fish,
                    onWikiClick = {
                        viewModel.onWikiClick(index)
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
