package be.simonraes.statictv.adapters.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import be.simonraes.statictv.model.event.AbstractEvent

/**
 * Created by SimonRaes on 19/03/16.
 */
abstract class BaseViewHolder<T>(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    abstract fun bindData(item : T)

}