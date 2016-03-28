package be.simonraes.statictv.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.simonraes.statictv.R
import be.simonraes.statictv.adapters.FriendsFeedAdapter
import be.simonraes.statictv.adapters.FriendsListAdapter
import be.simonraes.statictv.api.ApiManager
import kotlinx.android.synthetic.main.fragment_refresh.*

/**
 * Created by SimonRaes on 27/03/16.
 */
class FriendsListFragment : AbstractRefreshFragment() {

    var adapter : FriendsListAdapter? = null


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FriendsListAdapter(context)
        recyclerview_refresh.layoutManager = LinearLayoutManager(context)
        recyclerview_refresh.adapter = adapter

        onRefresh()
    }

    override fun onRefresh() {
        ApiManager.getInstance()
                .friends("voshond")
                .subscribe(
                        { items -> adapter?.setData(items)  },
                        { error -> println(error) })
    }


}