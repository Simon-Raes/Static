package be.simonraes.statictv.adapters.viewholders.friendsfeed

import android.text.TextUtils
import android.view.View
import be.simonraes.statictv.adapters.viewholders.BaseViewHolder
import be.simonraes.statictv.model.event.HistoryEvent
import kotlinx.android.synthetic.main.listitem_feed_history.view.*

/**
 * Created by SimonRaes on 19/03/16.
 */
class HistoryViewHolder(itemView: View?) : BaseViewHolder<HistoryEvent>(itemView) {

    override fun bindData(item: HistoryEvent) {

        var itemName: String = ""

        if (!TextUtils.isEmpty(item.show?.title)) {
            itemName = item.show?.title!!
        }

//        if (item.season?.number != null) {
//            itemName += " (Season ${item.season?.number})"
//        }

        if (!TextUtils.isEmpty(item.episode?.title)) {
            itemName += ": " + item.episode?.title!!
        } else if (!TextUtils.isEmpty(item.movie?.title)) {
            itemName = item.movie?.title!!
        }



        itemView.textview_friends_feed_history.text = "${item.user} watched ${itemName}"
    }

}
