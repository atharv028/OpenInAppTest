package tare.app.test.openinapptest.ui.campaigns

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tare.app.test.openinapptest.R

class CampaignFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_campaign, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CampaignFragment()
    }
}