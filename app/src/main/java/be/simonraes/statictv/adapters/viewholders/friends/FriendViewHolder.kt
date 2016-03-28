package be.simonraes.statictv.adapters.viewholders.friends

import android.text.TextUtils
import android.view.View
import be.simonraes.statictv.adapters.viewholders.BaseViewHolder
import be.simonraes.statictv.model.social.Friend
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.listitem_friend.view.*

/**
 * Created by SimonRaes on 28/03/16.
 */
class FriendViewHolder(view: View) : BaseViewHolder<Friend>(view) {

    override fun bindData(item: Friend) {

        Glide.with(itemView.imageview_friend_avatar.context)
                .load(item.user.images?.avatar?.full)
                .into(itemView.imageview_friend_avatar)


        // Use name if possible, else username
        if (!TextUtils.isEmpty(item.user.name)) {
            itemView.textview_friend_name.text = item.user.name
        } else {
            itemView.textview_friend_name.text = item.user.username
        }

    }
}