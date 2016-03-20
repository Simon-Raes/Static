package be.simonraes.statictv.model.item

import com.google.gson.annotations.SerializedName

/**
 * Created by SimonRaes on 06/03/16.
 */
class List(
        val name: String,
        val description: String,
        val privacy: String,
        @SerializedName("display_numbers")
        val displayNumbers: Boolean,
        @SerializedName("allow_comments")
        val allowComments: Boolean) {

    // TODO: 06/03/16 add the rest of the values

}
