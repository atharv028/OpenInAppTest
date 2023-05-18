package tare.app.test.openinapptest.ui.dashboard

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import tare.app.test.api.models.OverallUrlChart
import tare.app.test.api.models.ResponseDashboard
import tare.app.test.openinapptest.R
import tare.app.test.openinapptest.adapter.LinkAdapter
import tare.app.test.openinapptest.databinding.FragmentDashboardBinding
import tare.app.test.openinapptest.ui.unavailable.NotImplementedActivity
import tare.app.test.openinapptest.utils.ApiResponse
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.reflect.full.memberProperties


@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var binding: FragmentDashboardBinding? = null
    private val viewModel: DashboardViewModel by viewModels()
    private val adapter = LinkAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentDashboardBinding.inflate(inflater, container, false).also {
            binding = it
            return it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            settingsIv.setOnClickListener {
                startActivity(Intent(requireActivity(), NotImplementedActivity::class.java))
            }
            analyticsLl.setOnClickListener {
                startActivity(Intent(requireActivity(), NotImplementedActivity::class.java))
            }
            viewLinksLl.setOnClickListener {
                startActivity(Intent(requireActivity(), NotImplementedActivity::class.java))
            }
            searchIv.setOnClickListener {
                Toast.makeText(requireContext(), "Not Implemented", Toast.LENGTH_SHORT).show()
            }
            whatsappLl.setOnClickListener {
                val contact = "+91 7000897944"
                startActivity(
                    Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse("http://api.whatsapp.com/send?phone=$contact"))
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                )
            }
            faqLl.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://listed.fans/")))
            }
            rgLink.setOnCheckedChangeListener { _, i ->
                when (i) {
                    R.id.top_links -> {
                        viewModel.selectedLink.postValue(LinkType.TOP)
                    }

                    R.id.recent_links -> {
                        viewModel.selectedLink.postValue(LinkType.RECENT)
                    }
                }
            }
            linksRv.adapter = adapter
            linksRv.isNestedScrollingEnabled = false
        }
        viewModel.dashboardData.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Success -> {
                    Toast.makeText(requireContext(), "Data Fetched", Toast.LENGTH_SHORT).show()
                    setData(it.data)
                }

                is ApiResponse.Error -> {
                    Snackbar.make(requireView(), "Failed to fetch data", Snackbar.LENGTH_SHORT)
                        .apply {
                            setAction("Retry") {
                                viewModel.getDashboardData()
                            }
                            show()
                        }
                }

                else -> {}
            }
        }
    }

    private fun setData(resp: ResponseDashboard) {
        binding?.apply {
            clickTv.text = resp.todayClicks?.toString() ?: "N/A"
            locationTv.text = if (resp.topLocation.isNullOrEmpty()) {
                "N/A"
            } else {
                resp.topLocation
            }
            sourceTv.text = if (resp.topSource.isNullOrEmpty()) {
                "N/A"
            } else {
                resp.topSource
            }

            viewModel.selectedLink.observe(viewLifecycleOwner) {
                when (it) {
                    LinkType.TOP -> {
                        adapter.updateData(resp.data?.topLinks ?: listOf())
                    }

                    LinkType.RECENT -> {
                        adapter.updateData(resp.data?.recentLinks ?: listOf())
                    }

                    else -> {}
                }
            }
            val formatter = SimpleDateFormat("dd MMM", Locale.getDefault())
            val startDate = formatter.parse("18 Apr")
            val data = mutableListOf<Entry>()
            val labels = mutableListOf<String>()
            resp.data?.overallUrlChart?.let {
                var count = 0f
                for (prop in OverallUrlChart::class.memberProperties) {
                    val value = prop.call(it)
                    println("value: $value")
                    data.add(Entry(count++, (value as Int).toFloat()))
                }
            }
            val start = Calendar.getInstance()
            start.time = startDate!!
            for (i in 0..30) {
                labels.add(formatter.format(start.time))
                start.add(Calendar.DAY_OF_MONTH, 1)
            }
            setUpChart(chart, data, labels)
        }
    }

    private fun setUpChart(chart: LineChart, data: List<Entry>, labels: List<String>) {
        val xAxis = chart.xAxis
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.textSize = 10f
        xAxis.textColor = Color.RED
        xAxis.setDrawAxisLine(true)
        xAxis.setLabelCount(4, true)
        xAxis.setDrawGridLines(false)
        xAxis.setAvoidFirstLastClipping(true)
        xAxis.valueFormatter = object : ValueFormatter() {
            override
            fun getFormattedValue(value: Float): String {
                return labels[value.toInt()]
            }
        }
        chart.axisRight.isEnabled = false
        chart.axisLeft.axisMaximum = 10f
        chart.description.isEnabled = false
        chart.legend.isEnabled = false

        val dataSet = LineDataSet(data, "Dates")
        dataSet.setDrawFilled(true)
        dataSet.setDrawCircles(false)
        dataSet.setDrawValues(false)
        dataSet.color = ContextCompat.getColor(requireContext(), R.color.primary_color)
        val fillGradient = ContextCompat.getDrawable(requireContext(), R.drawable.chart_gradient)
        dataSet.fillDrawable = fillGradient
        val chartData = LineData(dataSet)
        chart.data = chartData
        chart.invalidate()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = DashboardFragment()
    }
}