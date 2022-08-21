package com.dreamsoftware.coins4all.data.local.room

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Types
import com.dreamsoftware.coins4all.data.remote.response.Team
import com.dreamsoftware.coins4all.data.utils.JsonParser
import com.dreamsoftware.coins4all.domain.model.CoinCurrency

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromTagsJson(json: String): List<String> {
        return jsonParser.fromJson(
            json,
            Types.newParameterizedType(
                List::class.java,
                String::class.java
            )
        ) ?: emptyList()
    }

    @TypeConverter
    fun toTagsJson(tags: List<String>): String {
        return jsonParser.toJson(
            tags,
            Types.newParameterizedType(
                List::class.java,
                String::class.java
            )
        ) ?: "[]"
    }

    @TypeConverter
    fun fromTeamsJson(json: String): List<Team> {
        return jsonParser.fromJson(
            json,
            Types.newParameterizedType(
                List::class.java,
                Team::class.java
            )
        ) ?: emptyList()
    }

    @TypeConverter
    fun toTeamsJson(teams: List<Team>): String {
        return jsonParser.toJson(
            teams,
            Types.newParameterizedType(
                List::class.java,
                Team::class.java
            )
        ) ?: "[]"
    }

    @TypeConverter
    fun fromCurrencyJson(json: String): List<CoinCurrency> {
        return jsonParser.fromJson(
            json,
            Types.newParameterizedType(
                List::class.java,
                CoinCurrency::class.java
            )
        ) ?: emptyList()
    }

    @TypeConverter
    fun toCurrencyJson(teams: List<CoinCurrency>): String {
        return jsonParser.toJson(
            teams,
            Types.newParameterizedType(
                List::class.java,
                CoinCurrency::class.java
            )
        ) ?: "[]"
    }

}