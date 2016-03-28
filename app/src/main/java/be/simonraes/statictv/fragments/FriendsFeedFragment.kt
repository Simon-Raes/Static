package be.simonraes.statictv.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import be.simonraes.statictv.adapters.FriendsFeedAdapter
import be.simonraes.statictv.api.ApiManager
import kotlinx.android.synthetic.main.fragment_refresh.*


/**
 * Created by SimonRaes on 19/03/16.
 */
class FriendsFeedFragment : AbstractRefreshFragment() {

    var adapter: FriendsFeedAdapter? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FriendsFeedAdapter(context)
        recyclerview_refresh.layoutManager = LinearLayoutManager(context)
        recyclerview_refresh.adapter = adapter

        onRefresh()
    }

    override fun onRefresh() {
        ApiManager.getInstance()
                .friendsFeed()
                .subscribe(
                        { items -> adapter?.setData(items) },
                        { error -> println(error) })
    }


}
