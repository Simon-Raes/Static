package be.simonraes.statictv.model.item

import com.google.gson.annotations.SerializedName

/**
 * Created by SimonRaes on 06/03/16.
 */
class Episode(
        val season: Int,
        @SerializedName("number")
        val number: Int,
        val title: String,
        val ids: Ids?)
