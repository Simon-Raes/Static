package be.simonraes.statictv.model.social

import com.google.gson.annotations.SerializedName

/**
 * Created by SimonRaes on 11/03/16.
 */
class User(
        val username: String,
        val private: Boolean,
        val name: String,
        val vip: Boolean,
        @SerializedName("vip_ep")
        val vipEp: Boolean
)