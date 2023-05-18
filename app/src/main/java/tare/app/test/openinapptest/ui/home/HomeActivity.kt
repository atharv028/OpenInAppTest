package tare.app.test.openinapptest.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import tare.app.test.openinapptest.R
import tare.app.test.openinapptest.databinding.ActivityHomeBinding
import tare.app.test.openinapptest.ui.campaigns.CampaignFragment
import tare.app.test.openinapptest.ui.courses.CourseFragment
import tare.app.test.openinapptest.ui.dashboard.DashboardFragment
import tare.app.test.openinapptest.ui.profile.ProfileFragment
import tare.app.test.openinapptest.ui.unavailable.NotImplementedActivity

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityHomeBinding.inflate(layoutInflater).apply {
            setContentView(root)
            fab.setOnClickListener {
                startActivity(Intent(this@HomeActivity,NotImplementedActivity::class.java))
            }
            customBottomBar.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.dashboard -> {
                        setCurrentFragment(
                            DashboardFragment.newInstance()
                        )
                        true
                    }

                    R.id.campaigns -> {
                        setCurrentFragment(CampaignFragment.newInstance())
                        true
                    }

                    R.id.profile -> {
                        setCurrentFragment(
                            ProfileFragment.newInstance()
                        )
                        true
                    }

                    R.id.courses -> {
                        setCurrentFragment(CourseFragment.newInstance())
                        true
                    }

                    else -> false
                }
            }
            customBottomBar.selectedItemId = R.id.dashboard
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, fragment)
            addToBackStack(null)
            commit()
        }
    }
}