package tare.app.test.api.models


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("overall_url_chart")
    var overallUrlChart: OverallUrlChart?,
    @SerializedName("recent_links")
    var recentLinks: List<Link>?,
    @SerializedName("top_links")
    var topLinks: List<Link>?
)