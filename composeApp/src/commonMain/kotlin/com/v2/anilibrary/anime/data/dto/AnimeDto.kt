package com.v2.anilibrary.anime.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeDto(
    @SerialName("mal_id") val id: Int,
    @SerialName("images") val images: AnimeCoverType?,
    @SerialName("titles") val titles: List<AnimeTitleVariant>,
    @SerialName("type") val type: String?,
    @SerialName("source") val source: String?,
    @SerialName("episodes") val episodeCount: Int?,
    @SerialName("status") val status: String,
    @SerialName("aired") val airing: AnimeAiringInfo?,
    @SerialName("duration") val duration: String?,
    @SerialName("rating") val ageRating: String?,
    @SerialName("score") val rating: Double?,
    @SerialName("scored_by") val ratingUserCount: Int?,
    @SerialName("rank") val rank: Int?,
    @SerialName("synopsis") val synopsis: String?,
    @SerialName("season") val season: String?,
    @SerialName("year") val year: Int?,
    @SerialName("broadcast") val broadcast: AnimeBroadcastInfo?,
    @SerialName("studios") val animeStudios: List<AnimeItemInfo>?,
    @SerialName("producers") val animeProducers: List<AnimeItemInfo>?,
    @SerialName("genres") val animeGenres: List<AnimeItemInfo>?,
    @SerialName("themes") val animeThemes: List<AnimeItemInfo>?,
    @SerialName("demographics") val animeDemographics: List<AnimeItemInfo>?,
    @SerialName("relations") val animeRelations: List<AnimeRelation>? = null,
    @SerialName("theme") val songTheme: AnimeTheme? = null,
    @SerialName("external") val externalLinks: List<AnimeItemInfo>? = null,
    @SerialName("streaming") val streamingPlatform: List<AnimeItemInfo>? = null,
) {
    val imageUrl: String?
        get() = images?.jpg?.high

    val title: String
        get() = titles.find { it.type == "Default" }?.title ?: "Unknown title"

    val jpTitle: String?
        get() = titles.find { it.type == "Japanese" }?.title

    val enTitle: String?
        get() = titles.find { it.type == "English" }?.title

    val airingDate: String?
        get() = airing?.string

    val airingSeason: String?
        get() = if (season != null && year != null)
            "${season.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }} $year"
        else null

    val broadcastTime: String?
        get() = broadcast?.string

    val studios: List<String>?
        get() {
            return if (animeStudios != null) {
                val list = mutableListOf<String>()

                animeStudios.map { studio ->
                    list.add(studio.name)
                }

                list.toList()
            } else {
                null
            }
        }

    val producers: List<String>?
        get() {
            return if (animeProducers != null) {
                val list = mutableListOf<String>()

                animeProducers.map { producer ->
                    list.add(producer.name)
                }

                list.toList()
            } else {
                null
            }
        }

    val genres: List<String>?
        get() {
            return if (animeGenres != null) {
                val list = mutableListOf<String>()

                animeGenres.map { genre ->
                    list.add(genre.name)
                }

                list.toList()
            } else {
                null
            }
        }

    val themes: List<String>?
        get() {
            return if (animeThemes != null) {
                val list = mutableListOf<String>()

                animeThemes.map { theme ->
                    list.add(theme.name)
                }

                list.toList()
            } else {
                null
            }
        }

    val demographics: List<String>?
        get() {
            return if (animeDemographics != null) {
                val list = mutableListOf<String>()

                animeDemographics.map { demographic ->
                    list.add(demographic.name)
                }

                list.toList()
            } else {
                null
            }
        }
}

@Serializable
data class AnimeCoverType(
    val jpg: AnimeCoverQuality,
    val webp: AnimeCoverQuality,
)

@Serializable
data class AnimeCoverQuality(
    @SerialName("image_url") val normal: String,
    @SerialName("small_image_url") val medium: String,
    @SerialName("large_image_url") val high: String,
)

@Serializable
data class AnimeTitleVariant(
    val type: String,
    val title: String,
)

@Serializable
data class AnimeAiringInfo(
    val from: String?,
    val to: String?,
    val string: String?
)

@Serializable
data class AnimeBroadcastInfo(
    val day: String?,
    val time: String?,
    val timezone: String?,
    val string: String?
)

@Serializable
data class AnimeItemInfo(
    @SerialName("mal_id") val id: Int? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
)

@Serializable
data class AnimeRelation(
    @SerialName("relation") val relationType: String,
    val entry: List<AnimeItemInfo>?,
)

@Serializable
data class AnimeTheme(
    val openings: List<String>?,
    val endings: List<String>?,
)