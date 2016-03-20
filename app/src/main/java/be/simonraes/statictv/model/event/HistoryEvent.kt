package be.simonraes.statictv.model.event

import com.google.gson.annotations.SerializedName

/**
 * Created by SimonRaes on 07/03/16.
 */
class HistoryEvent : AbstractEvent() {

    val id: Int? = null

    @SerializedName("watched_at")
    val watchedAt: String = ""

    val action: String? = null

    override fun getDate() : String {
        return watchedAt
    }

}


/*
[
  {
    "id": 1982346,
    "watched_at": "2014-03-31T09:28:53.000Z",
    "action": "scrobble",
    "type": "movie",
    "movie": {
      "title": "The Dark Knight",
      "year": 2008,
      "ids": {
        "trakt": 4,
        "slug": "the-dark-knight-2008",
        "imdb": "tt0468569",
        "tmdb": 155
      }
    }
  },
  ...

 */