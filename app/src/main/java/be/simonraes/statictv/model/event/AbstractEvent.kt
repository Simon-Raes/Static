package be.simonraes.statictv.model.event

import be.simonraes.statictv.extensions.toLongTimeStamp
import be.simonraes.statictv.model.item.Episode
import be.simonraes.statictv.model.item.Item
import be.simonraes.statictv.model.item.Season

/**
 * Created by SimonRaes on 07/03/16.
 */
abstract class AbstractEvent : Comparable<AbstractEvent> {

    val type: String = ""

    var user: String = ""

    val movie: Item? = null

    val show: Item? = null

    val season: Season? = null

    val episode: Episode? = null

    abstract fun getDate(): String

    override fun compareTo(other: AbstractEvent) = getDate().toLongTimeStamp().compareTo(other.getDate().toLongTimeStamp())


}
