package data

import com.example.scrollablelistxml.R

object FishData {

    fun getFishList(): List<Fish> {
        return listOf(
            Fish(
                name = "Ikan Cupang",
                latinName = "Betta splendens",
                habitat = "Air Tawar",
                size = "5 – 7 cm",
                description = "Ikan cupang adalah ikan hias air tawar yang terkenal dengan siripnya yang indah dan warnanya yang cerah. Ikan ini berasal dari Asia Tenggara dan dikenal agresif terhadap sesama jantan. Cupang mampu bernapas langsung dari udara berkat organ labirin yang dimilikinya.",
                imageResId = R.drawable.cupang,
                wikiUrl = "https://id.wikipedia.org/wiki/Ikan_cupang"
            ),
            Fish(
                name = "Ikan Lele",
                latinName = "Clarias batrachus",
                habitat = "Air Tawar",
                size = "20 – 45 cm",
                description = "Ikan lele merupakan ikan air tawar yang banyak dibudidayakan di Indonesia. Ciri khasnya adalah kumis panjang di sekitar mulutnya yang berfungsi sebagai alat peraba. Ikan ini mampu bertahan di lingkungan air dengan kadar oksigen rendah dan merupakan sumber protein yang populer.",
                imageResId = R.drawable.lele,
                wikiUrl = "https://id.wikipedia.org/wiki/Lele"
            ),
            Fish(
                name = "Ikan Nila",
                latinName = "Oreochromis niloticus",
                habitat = "Air Tawar",
                size = "15 – 30 cm",
                description = "Ikan nila adalah ikan konsumsi yang berasal dari perairan Afrika dan kini banyak dibudidayakan di seluruh dunia. Ikan ini memiliki daging yang tebal dan lezat dengan kandungan protein yang tinggi. Nila juga dikenal mudah beradaptasi di berbagai lingkungan perairan.",
                imageResId = R.drawable.nila,
                wikiUrl = "https://id.wikipedia.org/wiki/Nila_(ikan)"
            ),
            Fish(
                name = "Ikan Hiu",
                latinName = "Selachimorpha",
                habitat = "Air Laut",
                size = "1 – 12 meter",
                description = "Hiu adalah predator laut yang telah ada sejak jutaan tahun lalu. Mereka memiliki rangka yang terbuat dari tulang rawan dan deretan gigi tajam yang terus berganti. Hiu berperan penting dalam ekosistem laut sebagai pemangsa puncak yang menjaga keseimbangan rantai makanan.",
                imageResId = R.drawable.hiu,
                wikiUrl = "https://id.wikipedia.org/wiki/Hiu"
            ),
            Fish(
                name = "Ikan Tuna",
                latinName = "Thunnus",
                habitat = "Air Laut",
                size = "40 cm – 4,6 meter",
                description = "Ikan tuna merupakan salah satu ikan laut yang paling bernilai ekonomis tinggi di dunia. Tuna dikenal sebagai perenang cepat yang mampu melintasi samudra. Dagingnya kaya akan omega-3 dan protein, menjadikannya bahan utama dalam sushi dan sashimi kelas premium.",
                imageResId = R.drawable.tuna,
                wikiUrl = "https://id.wikipedia.org/wiki/Tuna"
            )
        )
    }
}