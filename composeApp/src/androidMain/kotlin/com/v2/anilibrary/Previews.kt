package com.v2.anilibrary

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.v2.anilibrary.anime.domain.AnimeReview
import com.v2.anilibrary.anime.presentation.components.AnimeReviewItem
import com.v2.anilibrary.core.presentation.theme.AppTheme
import com.v2.anilibrary.core.utils.convertUTCToLocal
import com.v2.anilibrary.core.utils.formatFullDateTime

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AnimePreview(
    modifier: Modifier = Modifier
) {
    AppTheme {
        AnimeReviewItem(
            review = AnimeReview(
                id = 1,
                date = "2022-10-30T17:42:00+00:00"
                    .convertUTCToLocal()
                    .formatFullDateTime(),
                review = "I watched the movie with a friend of mine at 2 am and we were both on the verge of tears at the end of the movie. We really enjoyed the movie and I, as a manga reader, even thought that the movie has delivered the ending better than the manga did. Mainly because the music was absolutely touching, the animation was great and every single flashback fit so well to the scene. After I read some reviews on MAL on this movie, I was surprised on how many people did not enjoy the movie, mainly because a) the \\\"character with the least development\\\" won, b)it skipped too many scenes from the manga or c) the romance in this movie is bad. So I've decided to write a review myself to give a bit of support to those people who actually liked the movie.\\n\\nIf you still don't know who the bride is going to be, I'm recommending you to stop reading this or any review on MAL due to spoilers.\\n\\nStarting with point a), I want to clarify that I like all five sisters and neither of them deserve to lose in my opinion. However, people saying that the MC choosing Yotsuba is unrealistic and weird is just nonsense to me. I don't want to explain it further because Twitter has gone through this discussion a hundred times already, but you should know that character development is just an element for the plot of a story and not a requirement for a character to be good, or at least for a character to be \\\"chosen\\\" in a harem anime. You wouldn't expect your crush to go through an entire character arc in order for you to marry him/her, or would you? It is pretty understandable that the MC chose Yotsuba if he already loves the way she is from the beginning. Besides, her character development did in fact happen while she was younger, right after meeting the MC in Kyoto to the point where she realizes she doesn't need to be better than her sisters. Nino and Miku in fact got more development throughout the story, yes, but because of that, the fact that the MC didn't choose them is even more heart-wrenching and the scene where Nino and Miku hug each other at school knowing they've lost the love-war got emotionally more powerful.\\n\\nGoing on with point b), I personally thought that they did a pretty good job on only picking the most important scenes of the manga to put in the movie. The movie got even stretched to more than 2 hours, so do people want it to be even longer when they say they left out too many scenes? I agree that it would be better storytelling-wise if this anime got a third season instead of a movie, but they must surely had their reasons why they've decided to only make a movie. The pacing for me was absolutely fine, but I do believe that non-manga readers may get confused at some point due to some scenes that got left out, but it wouldn't be a big deal for understanding the main plot or the ending anyway. I also partly agree to people saying that the movie was just a sequence of TV anime episodes packed into a movie. Nevertheless, I enjoyed watching the movie as a movie - it got its proper introduction, rising action, climax, falling action and resolution.\\n\\nFinally to point c), its subjective whether someone enjoys the romance or not. However, this movie isn't just purely about romance, it's also about family. Especially in the movie, we could see that no matter how many times the bond between the sisters gets damaged, they always manage to realize that they have to stay together, also because their late mother told them to do so. And watching the sisters getting along well at the end of the movie just makes me so happy. Plus, the development of the Nakano father was also quite nice to watch, especially when you see that he does in fact care for her daughters. I definitely liked when he asked the MC at the wedding if Yotsuba is really \\\"happy from the bottom of her heart\\\". You can clearly see a difference between the Nakano family in the 2 TV seasons and the Nakano family in the movie.\\n\\nAll in all, I absolutely enjoyed watching this piece of art, it was a rollercoaster of emotions all throughout the movie with elements of romance, comedy and drama. The music was always perfectly fitting the mood of the scene and overall well composed, the animation is as great as it was in the second season. They did a fantastic job on putting all the remaining important manga content in a movie which actually builds up towards the end. The ending itself is well-written and I love the idea of playing the quintuplets game (where the MC has to guess who is who) at the wedding, going through all five sisters and what makes them special, both for Fuutarou and Yotsuba. That was the emotional peak of the movie.\\n\\n-&gt; A must-watch for everyone who enjoyed the TV show. And please don't write bad reviews only because the girl you like didn't get chosen, even if you can list hundreds of reasons why your girl would be the much better option. That's not the point of a movie review, that's just presenting your personal waifu list. Thanks for reading, stay healthy and have a great day.",
                score = 9,
                tag = "Mixed Feelings",
                spoilerStatus = true,
                userProfile = "https://cdn.myanimelist.net/s/common/userimages/bbf9ec63-7e8c-40c2-9ab0-c4ececc648b2_42x62_i?s=a2e8f51fa470df90de13d130a6cb1aab",
                userUsername = "Daruma4"
            )
        )
    }
}