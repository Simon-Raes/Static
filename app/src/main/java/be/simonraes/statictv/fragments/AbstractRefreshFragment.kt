package be.simonraes.statictv.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.simonraes.statictv.R
import kotlinx.android.synthetic.main.fragment_refresh.*

/**
 * Created by SimonRaes on 28/03/16.
 */
abstract class AbstractRefreshFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_refresh, container, false);

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshlayout_refresh.setOnRefreshListener(this)

    }


}