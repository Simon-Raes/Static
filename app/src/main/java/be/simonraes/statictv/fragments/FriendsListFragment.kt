package be.simonraes.statictv.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import be.simonraes.statictv.adapters.FriendsListAdapter
import be.simonraes.statictv.api.ApiManager
import kotlinx.android.synthetic.main.fragment_refresh.*

/**
 * Created by SimonRaes on 27/03/16.
 */
class FriendsListFragment : AbstractRefreshFragment() {

    var adapter: FriendsListAdapter? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FriendsListAdapter(context)
        recyclerview_refresh.layoutManager = LinearLayoutManager(context)
        recyclerview_refresh.adapter = adapter

        onRefresh()
    }

    override fun onRefresh() {
        ApiManager.getInstance()
                .friends("me")
                .subscribe(
                        {
                            adapter?.setData(it)
                            refreshlayout_refresh.isRefreshing = false
                        },
                        {
                            println(it)
                            refreshlayout_refresh.isRefreshing = false
                        }
                )
    }


}