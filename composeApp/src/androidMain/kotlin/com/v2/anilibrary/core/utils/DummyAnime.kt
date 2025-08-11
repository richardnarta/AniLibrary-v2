package com.v2.anilibrary.core.utils

import androidx.compose.runtime.Composable
import com.v2.anilibrary.R
import com.v2.anilibrary.anime.domain.Anime
import androidx.compose.ui.res.stringResource
import com.v2.anilibrary.anime.domain.AnimeCharacter
import com.v2.anilibrary.anime.domain.AnimeItemInfo
import com.v2.anilibrary.anime.domain.AnimeRelation
import com.v2.anilibrary.anime.domain.AnimeReview
import com.v2.anilibrary.anime.domain.AnimeTheme
import com.v2.anilibrary.anime.domain.AnimeThemeSong
import com.v2.anilibrary.anime.domain.AnimeTrailer

object DummyAnime {
    @Composable
    fun getAnime(): Anime = Anime(
        id = 0,
        images = stringResource(R.string.dummy_anime_images),
        title = stringResource(R.string.dummy_anime_title),
        jpTitle = stringResource(R.string.dummy_anime_jp_title),
        enTitle = stringResource(R.string.dummy_anime_en_title),
        type = stringResource(R.string.dummy_anime_type),
        source = stringResource(R.string.dummy_anime_source),
        episodeCount = stringResource(R.string.dummy_anime_episodes).toInt(),
        status = stringResource(R.string.dummy_anime_status),
        airingDate = stringResource(R.string.dummy_anime_airing_date),
        duration = stringResource(R.string.dummy_anime_duration),
        ageRating = stringResource(R.string.dummy_anime_age_rating),
        rating = stringResource(R.string.dummy_anime_rating).toDouble(),
        ratingUserCount = stringResource(R.string.dummy_anime_rating_user).toInt(),
        rank = stringResource(R.string.dummy_anime_rank).toInt(),
        synopsis = stringResource(R.string.dummy_anime_synopsis),
        airingSeason = stringResource(R.string.dummy_anime_season),
        broadcastTime = stringResource(R.string.dummy_anime_broadcast),
        studios = listOf(
            stringResource(R.string.dummy_anime_studio_1),
        ),
        producers = listOf(
            stringResource(R.string.dummy_anime_producer_1),
            stringResource(R.string.dummy_anime_producer_2),
            stringResource(R.string.dummy_anime_producer_3),
            stringResource(R.string.dummy_anime_producer_4),
            stringResource(R.string.dummy_anime_producer_5),
            stringResource(R.string.dummy_anime_producer_6),
            stringResource(R.string.dummy_anime_producer_7),
            stringResource(R.string.dummy_anime_producer_8),
        ),
        genres = listOf(
            stringResource(R.string.dummy_anime_genre_1),
            stringResource(R.string.dummy_anime_genre_2),
        ),
        themes = listOf(
            stringResource(R.string.dummy_anime_theme_1),
            stringResource(R.string.dummy_anime_theme_2),
        ),
        demographics = listOf(
            stringResource(R.string.dummy_anime_demographic),
        ),
        animeRelations = listOf(
            AnimeRelation(
                relationType = stringResource(R.string.prequel),
                entry = listOf(
                    AnimeItemInfo(
                        id = 0,
                        type = "x",
                        name = stringResource(R.string.dummy_anime_prequel),
                        url = "x"
                    )
                )
            ),
            AnimeRelation(
                relationType = stringResource(R.string.sequel),
                entry = listOf(
                    AnimeItemInfo(
                        id = 0,
                        type = "x",
                        name = stringResource(R.string.dummy_anime_sequel),
                        url = "x"
                    )
                )
            ),
            AnimeRelation(
                relationType = stringResource(R.string.adaptation),
                entry = listOf(
                    AnimeItemInfo(
                        id = 0,
                        type = "x",
                        name = stringResource(R.string.dummy_anime_adaptation),
                        url = "x"
                    )
                )
            )
        ),
        songTheme = AnimeTheme(
            openings = listOf(
                AnimeThemeSong(
                    song = stringResource(R.string.dummy_anime_opening_song),
                    singer = stringResource(R.string.dummy_anime_singer)
                ),
            ),
            endings = listOf(
                AnimeThemeSong(
                    song = stringResource(R.string.dummy_anime_ending_1_song),
                    singer = stringResource(R.string.dummy_anime_singer)
                ),
                AnimeThemeSong(
                    song = stringResource(R.string.dummy_anime_ending_2_song),
                    singer = stringResource(R.string.dummy_anime_singer)
                )
            )
        ),
        externalLinks = arrayListOf(
            AnimeItemInfo(
                id = 0,
                type = "",
                name = "Official Site",
                url = stringResource(R.string.dummy_anime_official_site)
            ),
            AnimeItemInfo(
                id = 0,
                type = "",
                name = "AniDB",
                url = "https://anidb.net/perl-bin/animedb.pl?show=anime&aid=16165"
            ),
            AnimeItemInfo(
                id = 0,
                type = "",
                name = "ANN",
                url = "https://www.animenewsnetwork.com/encyclopedia/anime.php?id=24314"
            ),
            AnimeItemInfo(
                id = 0,
                type = "",
                name = "Wikipedia",
                url = "https://en.wikipedia.org/wiki/The_Quintessential_Quintuplets#Anime"
            ),
        ),
        streamingPlatform = listOf(
            AnimeItemInfo(
                id = 0,
                type = "",
                name = stringResource(R.string.dummy_anime_streaming_platform_1),
                url = "http://www.crunchyroll.com/series-277381"
            )
        ),
    )
    
    @Composable
    fun getAnimeCharacter(): List<AnimeCharacter> {
        return listOf(
            AnimeCharacter(
                id = 161471,
                images = "https://cdn.myanimelist.net/images/characters/5/437010.jpg?s=78ea744f81e05986a8c5d1ae0ed56257",
                name = "Nakano, Itsuki",
                role = "Main"
            ),
            AnimeCharacter(
                id = 161472,
                images = "https://cdn.myanimelist.net/images/characters/7/437011.jpg?s=a50b82662ce8bd2cef7ee56673ab0bac",
                name = "Nakano, Nino",
                role = "Main"
            ),
            AnimeCharacter(
                id = 161470,
                images = "https://cdn.myanimelist.net/images/characters/12/436646.jpg?s=e5f1f9b3873caf65584e84d2a09217bd",
                name = "Nakano, Ichika",
                role = "Main"
            ),
            AnimeCharacter(
                id = 161469,
                images = "https://cdn.myanimelist.net/images/characters/8/437009.jpg?s=9f92210bf0c47a198dd25bffb003b1c1",
                name = "Nakano, Yotsuba",
                role = "Main"
            ),
            AnimeCharacter(
                id = 160603,
                images = "https://cdn.myanimelist.net/images/characters/15/507743.jpg?s=ecc7de646b4ccef8b1074546c3e09010",
                name = "Nakano, Miku",
                role = "Main"
            ),
            AnimeCharacter(
                id = 159332,
                images = "https://cdn.myanimelist.net/images/characters/3/554695.jpg?s=39d5b60a1689ce44f7ff7bb61568016e",
                name = "Uesugi, Fuutarou",
                role = "Main"
            ),
        )
    }
    
    @Composable
    fun getAnimeTrailer(): List<AnimeTrailer> {
        return arrayListOf(
            AnimeTrailer(
                title = "Trailer 3",
                url = "https://www.youtube.com/watch?v=GTjfXPANIXY",
                imageCover = "https://img.youtube.com/vi/GTjfXPANIXY/hqdefault.jpg"
            ),
            AnimeTrailer(
                title = "Trailer 2",
                url = "https://www.youtube.com/watch?v=8cQH4CELCSw",
                imageCover = "https://img.youtube.com/vi/8cQH4CELCSw/hqdefault.jpg"
            ),
            AnimeTrailer(
                title = "Trailer 1",
                url = "https://www.youtube.com/watch?v=kB5dLElIVI4",
                imageCover = "https://img.youtube.com/vi/kB5dLElIVI4/hqdefault.jpg"
            ),
            AnimeTrailer(
                title = "Teaser",
                url = "https://www.youtube.com/watch?v=TvEk_gJLSVA",
                imageCover = "https://img.youtube.com/vi/TvEk_gJLSVA/hqdefault.jpg"
            ),
            AnimeTrailer(
                title = "Announcement",
                url = "https://www.youtube.com/watch?v=RvCrvrzBRnw",
                imageCover = "https://img.youtube.com/vi/RvCrvrzBRnw/hqdefault.jpg"
            )
        )
    }

    @Composable
    fun getAnimeReview(): List<AnimeReview> {
        return arrayListOf(
            AnimeReview(
                id = 462410,
                date = "2022-10-30T17:42:00+00:00".convertUTCToLocal()
                    .formatFullDateTime(),
                review = "I watched the movie with a friend of mine at 2 am and we were both on the verge of tears at the end of the movie. We really enjoyed the movie and I, as a manga reader, even thought that the movie has delivered the ending better than the manga did. Mainly because the music was absolutely touching, the animation was great and every single flashback fit so well to the scene. After I read some reviews on MAL on this movie, I was surprised on how many people did not enjoy the movie, mainly because a) the \"character with the least development\" won, b)it skipped too many scenes from the manga or c) the romance in this movie is bad. So I've decided to write a review myself to give a bit of support to those people who actually liked the movie.\n\nIf you still don't know who the bride is going to be, I'm recommending you to stop reading this or any review on MAL due to spoilers.\n\nStarting with point a), I want to clarify that I like all five sisters and neither of them deserve to lose in my opinion. However, people saying that the MC choosing Yotsuba is unrealistic and weird is just nonsense to me. I don't want to explain it further because Twitter has gone through this discussion a hundred times already, but you should know that character development is just an element for the plot of a story and not a requirement for a character to be good, or at least for a character to be \"chosen\" in a harem anime. You wouldn't expect your crush to go through an entire character arc in order for you to marry him/her, or would you? It is pretty understandable that the MC chose Yotsuba if he already loves the way she is from the beginning. Besides, her character development did in fact happen while she was younger, right after meeting the MC in Kyoto to the point where she realizes she doesn't need to be better than her sisters. Nino and Miku in fact got more development throughout the story, yes, but because of that, the fact that the MC didn't choose them is even more heart-wrenching and the scene where Nino and Miku hug each other at school knowing they've lost the love-war got emotionally more powerful.\n\nGoing on with point b), I personally thought that they did a pretty good job on only picking the most important scenes of the manga to put in the movie. The movie got even stretched to more than 2 hours, so do people want it to be even longer when they say they left out too many scenes? I agree that it would be better storytelling-wise if this anime got a third season instead of a movie, but they must surely had their reasons why they've decided to only make a movie. The pacing for me was absolutely fine, but I do believe that non-manga readers may get confused at some point due to some scenes that got left out, but it wouldn't be a big deal for understanding the main plot or the ending anyway. I also partly agree to people saying that the movie was just a sequence of TV anime episodes packed into a movie. Nevertheless, I enjoyed watching the movie as a movie - it got its proper introduction, rising action, climax, falling action and resolution.\n\nFinally to point c), its subjective whether someone enjoys the romance or not. However, this movie isn't just purely about romance, it's also about family. Especially in the movie, we could see that no matter how many times the bond between the sisters gets damaged, they always manage to realize that they have to stay together, also because their late mother told them to do so. And watching the sisters getting along well at the end of the movie just makes me so happy. Plus, the development of the Nakano father was also quite nice to watch, especially when you see that he does in fact care for her daughters. I definitely liked when he asked the MC at the wedding if Yotsuba is really \"happy from the bottom of her heart\". You can clearly see a difference between the Nakano family in the 2 TV seasons and the Nakano family in the movie.\n\nAll in all, I absolutely enjoyed watching this piece of art, it was a rollercoaster of emotions all throughout the movie with elements of romance, comedy and drama. The music was always perfectly fitting the mood of the scene and overall well composed, the animation is as great as it was in the second season. They did a fantastic job on putting all the remaining important manga content in a movie which actually builds up towards the end. The ending itself is well-written and I love the idea of playing the quintuplets game (where the MC has to guess who is who) at the wedding, going through all five sisters and what makes them special, both for Fuutarou and Yotsuba. That was the emotional peak of the movie.\n\n-&gt; A must-watch for everyone who enjoyed the TV show. And please don't write bad reviews only because the girl you like didn't get chosen, even if you can list hundreds of reasons why your girl would be the much better option. That's not the point of a movie review, that's just presenting your personal waifu list. Thanks for reading, stay healthy and have a great day.",
                score = 9,
                tag = "Recommended",
                spoilerStatus = false,
                userProfile = "https://cdn.myanimelist.net/s/common/userimages/bbf9ec63-7e8c-40c2-9ab0-c4ececc648b2_42x62_i?s=a2e8f51fa470df90de13d130a6cb1aab",
                userUsername = "Daruma4"
            ),
            AnimeReview(
                id = 565709,
                date = "2025-06-18T00:20:00+00:00".convertUTCToLocal()
                    .formatFullDateTime(),
                review = "This movie isn't perfect but it's still incredible. The music, animation, and character development definitely lived up to the expectations set by the first 2 seasons and summer specials. The main issue with this movie is how it condenses so much of the story into only 2 hours. If you haven't read/don't plan on reading the manga before watching this movie, know that an extremely valuable and worthwhile character arc (which ends up having a lot of plot significance) didn't make the cut for the adaptation. Still, the movie offers an emotional and unforgettable conclusion to the story. By the end of the movie, you'llbe able to accept the victor (and look forward to the honeymoon special). While I wish the writing and plot condensing could have better represented the complete story in this movie, I assure you it's a worthwhile watch. If you've already watched the rest of the anime, the manga is objectively a better choice as this is probably the worst adaptation of the series. While it doesn't perfectly capture the beauty of the source material, you will enjoy every second of this film. If you're truly invested in the plot, go read the manga. This movie is still a great substitute, but the source material is even better. Even if your favorite girl doesn't win, the ending is something you can still enjoy. After all, how would the author create an ending everyone is satisfied with if not everyone can win? The answer's simple: the value in the story isn't just finding out who wins, the beauty is in how and why they win. I know I said the manga was better, but this film is more than enough. Overall, this series was a wonderful rollercoaster of emotions and is an invaluable experience for everyone.\n\nAlso, I will die on the hill that Itsuki is the best girl.",
                score = 10,
                tag = "Recommended",
                spoilerStatus = false,
                userProfile = "https://cdn.myanimelist.net/s/common/userimages/4d869944-f748-4209-867f-eb03fe6aaf11_225w?s=9543f33a0a4fd275b4d3633b71805801",
                userUsername = "INK_MasTerMinD"
            ),
            AnimeReview(
                id = 482199,
                date = "2023-04-29T16:58:00+00:00".convertUTCToLocal()
                    .formatFullDateTime(),
                review = "It appears that there is a fairly even line drawn down the middle as to whether this is good or bad. There's obviously way more good considering its rating as of the time of this review, but I find it hard to agree with. There is nothing special about this. It's kind of ho-hum and the characters are flat and really less special than what they were in the show. I still cannot tolerate Futaro. I think he's flat and unimpressive and completely clueless through all of the show and the movie together. I think they chose the wrong voice actor and presentation. He has noexpression of anything but one deep droning voice. He is marginally better in the movie, but definitely not enough to redeem my image of him.\n\nEach one of the girls were given their own dedicated time. While I'm somewhat surprised at the ending and I honestly would have made a different choice, it makes sense if you think back on it. I think it was an awful stretch to make the connection on who I thought was the least developed sister. I had things in there that I had to remember from the show, which has been quite a while.\n\nI'll drop a mixed feelings I miss because someone out there likes it while I don't recommend it. I think it's only fair to be subjective for everybody to see.",
                score = 6,
                tag = "Mixed Feelings",
                spoilerStatus = true,
                userProfile = "https://cdn.myanimelist.net/s/common/userimages/42c070f6-bd7f-4e68-b2ba-f1e6314625f4_42x62_i?s=5e8711829b4d1c877f4d4736c0c07424",
                userUsername = "MyDogTige"
            )
        )
    }
}