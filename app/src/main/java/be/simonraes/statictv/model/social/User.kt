package be.simonraes.statictv.model.social

import com.google.gson.annotations.SerializedName

/**
 * Created by SimonRaes on 11/03/16.
 */
class User(
        @SerializedName("username")
        val username: String,
        @SerializedName("private")
        val private: Boolean,
        @SerializedName("name")
        val name: String?,
        @SerializedName("vip")
        val vip: Boolean,
        @SerializedName("vip_ep")
        val vipEp: Boolean,
        @SerializedName("images")
        val images: Images?)

class Images(@SerializedName("avatar")
             val avatar: Avatar?)

class Avatar(@SerializedName("full")
             val full: String?)
