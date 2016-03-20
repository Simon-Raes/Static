package be.simonraes.statictv.adapters.viewholders.friendsfeed

import android.text.TextUtils
import android.view.View
import be.simonraes.statictv.adapters.viewholders.BaseViewHolder
import be.simonraes.statictv.model.event.RatingEvent
import kotlinx.android.synthetic.main.listitem_feed_history.view.*
import kotlinx.android.synthetic.main.listitem_feed_rating.view.*

/**
 * Created by SimonRaes on 19/03/16.
 */
class RatingViewHolder(itemView: View?) : BaseViewHolder<RatingEvent>(itemView) {

    override fun bindData(item: RatingEvent) {

        var itemName: String = ""

        if (!TextUtils.isEmpty(item.episode?.title)) {
            itemName = item.episode?.title!!
        } else if (!TextUtils.isEmpty(item.movie?.title)) {
            itemName = item.movie?.title!!
        } else if (!TextUtils.isEmpty(item.show?.title)) {
            itemName = item.show?.title!!
        }

        if (item.season?.number != null) {
            itemName += "(Season ${item.season?.number})"
        }


        itemView.textview_friends_feed_rating.text = "${item.user} rated ${itemName} a ${item.rating}"
    }

}
