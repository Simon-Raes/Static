package be.simonraes.statictv.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.simonraes.statictv.R
import be.simonraes.statictv.api.ApiManager

/**
 * Created by SimonRaes on 09/04/16.
 * Shows a grid of the current month with all episodes of followed shows.
 */
class CalendarGridFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_calendar_grid, container, false);

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        ApiManager.getInstance()
//                .calendarShows()
//                .subscribe { println(it) }
    }

}