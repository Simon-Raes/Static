package be.simonraes.statictv.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.simonraes.statictv.R

/**
 * Created by SimonRaes on 27/03/16.
 */
class PlaceholderFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_placeholder, container, false);
    }
}