package be.simonraes.statictv.adapters.viewholders.friendsfeed

import android.view.View
import be.simonraes.statictv.adapters.viewholders.BaseViewHolder
import be.simonraes.statictv.model.item.CommentEvent
import kotlinx.android.synthetic.main.listitem_feed_comment.view.*


/**
 * Created by SimonRaes on 19/03/16.
 */
class CommentViewHolder(itemView: View?) : BaseViewHolder<CommentEvent>(itemView) {

    override fun bindData(item: CommentEvent) {
        itemView.textview_friends_feed_comment.text =  "${item.user} said: ${item.comment?.comment}"
    }

}
