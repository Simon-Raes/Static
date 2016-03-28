package be.simonraes.statictv.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import be.simonraes.statictv.R
import be.simonraes.statictv.adapters.viewholders.friends.FriendViewHolder
import be.simonraes.statictv.model.social.Friend

/**
 * Created by SimonRaes on 28/03/16.
 */
class FriendsListAdapter(val context: Context) : RecyclerView.Adapter<FriendViewHolder>() {

    val layoutInflater: LayoutInflater
    var friends: Array<Friend>? = null

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    fun setData(friends: Array<Friend>) {
        this.friends = friends
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FriendViewHolder {
        val view = layoutInflater.inflate(R.layout.listitem_friend, null, false)
        return FriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bindData(friends?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return friends?.size ?: 0
    }
}