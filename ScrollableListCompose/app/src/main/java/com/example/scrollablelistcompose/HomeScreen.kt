package JetpackCompose

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text

private val BgDark = Color(0xFF121212)

@Composable
fun HomeScreen(
    onNavigateToDetail: (Int) -> Unit
) {
    val context = LocalContext.current
    val fishList = FishData.getFishList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
            .padding(8.dp)
    ) {
        // Judul
        Text(
            text = "Spesies Ikan",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 12.dp)
        )

        // LazyColumn (setara RecyclerView)
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(fishList.size) { index ->
                val fish = fishList[index]
                FishCard(
                    fish = fish,
                    onWikiClick = {
                        // Intent eksplisit untuk membuka browser
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fish.wikiUrl))
                        context.startActivity(intent)
                    },
                    onDetailClick = {
                        // Navigation component untuk membuka halaman detail
                        onNavigateToDetail(index)
                    }
                )
            }
        }
    }
}
