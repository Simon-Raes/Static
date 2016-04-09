package be.simonraes.statictv.model.social

/**
 * Created by SimonRaes on 28/03/16.
 */
class Stats (val movies: DetailStats?,
             val shows: DetailStats?,
             val seasons: DetailStats?,
             val episodes: DetailStats?,
             val network : Network?,
             val ratings: Ratings?)

class DetailStats(val plays: Int?,
                  val watched: Int?,
                  val minutes: Int?,
                  val collected: Int?,
                  val ratings: Int?,
                  val comments: Int?)

class Network(val friends: Int?,
              val followers: Int?,
              val following: Int?)

class Ratings(val total : Int?,
              val distribution : Map<String, Int>)