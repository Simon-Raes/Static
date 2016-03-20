package be.simonraes.statictv.model.event

import com.google.gson.annotations.SerializedName

/**
 * Created by SimonRaes on 07/03/16.
 */
class RatingEvent : AbstractEvent() {

    @SerializedName("rated_at")
     val ratedAt: String = ""

     val rating: Int = 0

    override fun getDate() : String {
        return ratedAt
    }

}


/*
[
  {
    "rated_at": "2014-09-01T09:10:11.000Z",
    "rating": 10,
    "type": "movie",
    "movie": {
      "title": "TRON: Legacy",
      "year": 2010,
      "ids": {
        "trakt": 1,
        "slug": "tron-legacy-2010",
        "imdb": "tt1104001",
        "tmdb": 20526
      }
    }
  },
  {
    "rated_at": "2014-09-01T09:10:11.000Z",
    "rating": 10,
    "type": "movie",
    "movie": {
      "title": "The Dark Knight",
      "year": 2008,
      "ids": {
        "trakt": 6,
        "slug": "the-dark-knight-2008",
        "imdb": "tt0468569",
        "tmdb": 155
      }
    }
  }
]
 */