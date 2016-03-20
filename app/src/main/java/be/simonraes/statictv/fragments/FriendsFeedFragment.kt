package be.simonraes.statictv.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.simonraes.statictv.R
import be.simonraes.statictv.adapters.FriendsFeedAdapter
import be.simonraes.statictv.api.ApiManager
import be.simonraes.statictv.model.event.AbstractEvent
import kotlinx.android.synthetic.main.fragment_friends_feed.*
import java.util.*


/**
 * Created by SimonRaes on 19/03/16.
 */
class FriendsFeedFragment : Fragment() {

    var adapter: FriendsFeedAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_friends_feed, container, false);
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FriendsFeedAdapter(context)
        recyclerview_friends_feed.adapter = adapter
        recyclerview_friends_feed.layoutManager = LinearLayoutManager(context)

        ApiManager.getInstance(context)
                .friendsFeed()
                .subscribe { items -> setItems(items) }
    }

    fun setItems(items: ArrayList<AbstractEvent>) {

        adapter?.setData(items)
    }
}
