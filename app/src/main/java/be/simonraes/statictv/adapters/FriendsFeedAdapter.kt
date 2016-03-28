package be.simonraes.statictv.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.simonraes.statictv.R
import be.simonraes.statictv.adapters.viewholders.BaseViewHolder
import be.simonraes.statictv.adapters.viewholders.friendsfeed.CommentViewHolder
import be.simonraes.statictv.adapters.viewholders.friendsfeed.HistoryViewHolder
import be.simonraes.statictv.adapters.viewholders.friendsfeed.RatingViewHolder
import be.simonraes.statictv.model.event.AbstractEvent
import be.simonraes.statictv.model.event.HistoryEvent
import be.simonraes.statictv.model.event.RatingEvent
import be.simonraes.statictv.model.item.CommentEvent
import java.util.*

/**
 * Created by SimonRaes on 19/03/16.
 */
class FriendsFeedAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val VIEWTYPE_RATING = 1
    val VIEWTYPE_COMMENT = 2
    val VIEWTYPE_HISTORY = 3

    val layoutInflater: LayoutInflater
    var items: ArrayList<AbstractEvent>? = null

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    fun setData(items: ArrayList<AbstractEvent>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {

        when (items?.get(position)) {
            is RatingEvent -> return VIEWTYPE_RATING
            is CommentEvent -> return VIEWTYPE_COMMENT
            is HistoryEvent -> return VIEWTYPE_HISTORY
            else -> return -1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {

        var view: View?
        var viewHolder: BaseViewHolder<*>? = null

        when (viewType) {
            VIEWTYPE_RATING -> {
                view = layoutInflater.inflate(R.layout.listitem_feed_rating, null)
                viewHolder = RatingViewHolder(view)
            }
            VIEWTYPE_COMMENT -> {
                view = layoutInflater.inflate(R.layout.listitem_feed_comment, null)
                viewHolder = CommentViewHolder(view)
            }
            VIEWTYPE_HISTORY -> {
                view = layoutInflater.inflate(R.layout.listitem_feed_history, null)
                viewHolder = HistoryViewHolder(view)
            }

        }
        return viewHolder
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        // todo try to get rid of the !!
        (holder as BaseViewHolder<AbstractEvent>).bindData(items?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }
}
