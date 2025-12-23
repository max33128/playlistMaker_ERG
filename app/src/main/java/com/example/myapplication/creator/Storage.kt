package com.example.myapplication.creator



import com.example.myapplication.data.dto.TrackDto

class Storage {
    private val listTracks = listOf(
        // Уникальные треки (по одному на артиста)
        TrackDto(
            trackName = "Total Eclipse of the Heart",
            artistName = "Bonnie Tyler",
            trackTimeMillis = 411000 // 6:51
        ),
        TrackDto(
            trackName = "Shout",
            artistName = "Tears For Fears",
            trackTimeMillis = 361000 // 6:01
        ),
        TrackDto(
            trackName = "Dream On",
            artistName = "Aerosmith",
            trackTimeMillis = 281000 // 4:41
        ),
        TrackDto(
            trackName = "I'll Be Missing You (feat. Faith Evans, 112)",
            artistName = "P. Diddy",
            trackTimeMillis = 308000 // 5:08
        ),
        TrackDto(
            trackName = "Hotel California (2013 Remaster)",
            artistName = "Eagles",
            trackTimeMillis = 392000 // 6:32
        ),
        TrackDto(
            trackName = "Come Undone",
            artistName = "Duran Duran",
            trackTimeMillis = 270000 // 4:30
        ),
        TrackDto(
            trackName = "Love & Hate",
            artistName = "Michael Kiwanuka",
            trackTimeMillis = 428000 // 7:08
        ),
        TrackDto(
            trackName = "Ayo Technology (Live with Orchestra)",
            artistName = "Milow",
            trackTimeMillis = 222000 // 3:42
        ),
        TrackDto(
            trackName = "Freed From Desire (Acoustic Version)",
            artistName = "Gala",
            trackTimeMillis = 194000 // 3:14
        ),
        TrackDto(
            trackName = "The Chain (Remastered)",
            artistName = "Fleetwood Mac",
            trackTimeMillis = 270000 // 4:30
        ))

    fun search(request: String): List<TrackDto> {
        val result = listTracks.filter {
            it.trackName
                .lowercase()
                .contains(request.lowercase())
        }
        return result
    }
}