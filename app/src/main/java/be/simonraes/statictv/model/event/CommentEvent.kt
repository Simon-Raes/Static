package be.simonraes.statictv.model.item

import be.simonraes.statictv.model.event.AbstractEvent
import be.simonraes.statictv.model.event.Comment

/**
 * Created by SimonRaes on 06/03/16.
 */
class CommentEvent : AbstractEvent() {

    val comment: Comment? = null

    override fun getDate() = comment?.createdAt ?: ""

}
