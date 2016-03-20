package be.simonraes.statictv.model.event

import be.simonraes.statictv.model.social.User
import com.google.gson.annotations.SerializedName

/**
 * Created by SimonRaes on 06/03/16.
 */
class Comment(val id: Int,
              val comment: String,
              val spoiler: Boolean,
              val review: Boolean,
              @SerializedName("parent_id")
              val parentId: Int,
              @SerializedName("created_at")
              val createdAt: String,
              val replies: Int,
              val likes: Int,
              @SerializedName("user_rating")
              val userRating: Int,
              val user: User)



/*
[
  {
    "id": 8,
    "parent_id": 0,
    "created_at": "2011-03-25T22:35:17.000Z",
    "comment": "Can't wait to watch everything on this epic list!",
    "spoiler": false,
    "review": false,
    "replies": 0,
    "user_rating": null,
    "user": {
      "username": "sean",
      "private": false,
      "name": "Sean Rudford",
      "vip": true,
      "vip_ep": false
    }
  }
]
*/

