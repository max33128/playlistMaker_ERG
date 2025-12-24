package com.example.myapplication.creator



import com.example.myapplication.data.dto.TrackDto

class Storage {
    private val listTracks = listOf(


        TrackDto(
            trackName = "По МКАДу",
            artistName = "Wallem, AKVЛИЧ",
            trackTimeMillis = 185000 // 3:05
        ),
        TrackDto(
            trackName = "СВЕТЛАНА!",
            artistName = "NEXTIME",
            trackTimeMillis = 162000 // 2:42
        ),
        TrackDto(
            trackName = "plan b",
            artistName = "Rakhim, The Limba",
            trackTimeMillis = 198000 // 3:18
        ),
        TrackDto(
            trackName = "Не Беспокоюсь",
            artistName = "Мэйби Бэйби, Lida",
            trackTimeMillis = 176000 // 2:56
        ),
        TrackDto(
            trackName = "СВАГА",
            artistName = "HELLOVERCAVI, SODALUV",
            trackTimeMillis = 154000 // 2:34
        ),
        TrackDto(
            trackName = "ВИП",
            artistName = "unige, nkeeel, ARTEM SHILOVETS, Toxi$",
            trackTimeMillis = 193000 // 3:13
        ),
        TrackDto(
            trackName = "Illegal (pinkpanth)",
            artistName = "RhythmRebel, TommyMuzzle, speed up, MysticMuse",
            trackTimeMillis = 172000 // 2:52
        ),


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