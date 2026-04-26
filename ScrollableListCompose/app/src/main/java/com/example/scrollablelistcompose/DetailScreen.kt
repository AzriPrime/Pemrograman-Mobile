package JetpackCompose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Warna tema (sama persis dengan versi XML)
private val BgDark = Color(0xFF121212)
private val CardDark = Color(0xFF1E1E2E)
private val AccentBlue = Color(0xFF6C9BD2)
private val TextSecondary = Color(0xFFB0B0C0)
private val DividerDark = Color(0xFF3A3A4A)

@Composable
fun DetailScreen(
    fishIndex: Int,
    onBack: () -> Unit
) {
    val fish = FishData.getFishList()[fishIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
            .verticalScroll(rememberScrollState())
    ) {
        // Hero image dengan rounded bottom corners
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

            // Tombol kembali di atas gambar
            TextButton(
                onClick = onBack,
                modifier = Modifier.padding(start = 4.dp, top = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = com.example.scrollablelistxml.R.drawable.ic_arrow_back),
                    contentDescription = "Kembali",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Kembali",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }

        // Content card yang overlap dengan gambar
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset(y = (-24).dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = CardDark),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                // Nama ikan
                Text(
                    text = fish.name,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                // Nama latin
                Text(
                    text = fish.latinName,
                    color = AccentBlue,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 4.dp)
                )

                // Divider
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    thickness = 1.dp,
                    color = DividerDark
                )

                // Habitat
                Row(modifier = Modifier.padding(bottom = 8.dp)) {
                    Text(
                        text = "Habitat:",
                        color = AccentBlue,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = fish.habitat,
                        color = TextSecondary,
                        fontSize = 14.sp
                    )
                }

                // Ukuran
                Row(modifier = Modifier.padding(bottom = 12.dp)) {
                    Text(
                        text = "Ukuran:",
                        color = AccentBlue,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = fish.size,
                        color = TextSecondary,
                        fontSize = 14.sp
                    )
                }

                // Divider
                HorizontalDivider(
                    modifier = Modifier.padding(bottom = 12.dp),
                    thickness = 1.dp,
                    color = DividerDark
                )

                // Deskripsi label
                Text(
                    text = "Deskripsi:",
                    color = AccentBlue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                // Deskripsi value
                Text(
                    text = fish.description,
                    color = TextSecondary,
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
