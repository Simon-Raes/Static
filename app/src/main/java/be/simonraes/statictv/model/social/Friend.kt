package be.simonraes.statictv.model.social

import com.google.gson.annotations.SerializedName

/**
 * Created by SimonRaes on 11/03/16.
 */
class Friend(
        @SerializedName("friends_at")
        val friendsAt: String,
        val user: User) {}
