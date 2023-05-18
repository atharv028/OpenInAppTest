package tare.app.test.api.models


import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("app")
    var app: String?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("domain_id")
    var domainId: String?,
    @SerializedName("original_image")
    var originalImage: String?,
    @SerializedName("smart_link")
    var smartLink: String?,
    @SerializedName("thumbnail")
    var thumbnail: Any?,
    @SerializedName("times_ago")
    var timesAgo: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("total_clicks")
    var totalClicks: Int?,
    @SerializedName("url_id")
    var urlId: Int?,
    @SerializedName("url_prefix")
    var urlPrefix: Any?,
    @SerializedName("url_suffix")
    var urlSuffix: String?,
    @SerializedName("web_link")
    var webLink: String?
)