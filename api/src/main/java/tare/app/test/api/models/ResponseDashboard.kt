package tare.app.test.api.models


import com.google.gson.annotations.SerializedName

data class ResponseDashboard(
    @SerializedName("applied_campaign")
    var appliedCampaign: Int?,
    @SerializedName("data")
    var `data`: Data?,
    @SerializedName("extra_income")
    var extraIncome: Double?,
    @SerializedName("links_created_today")
    var linksCreatedToday: Int?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("startTime")
    var startTime: String?,
    @SerializedName("status")
    var status: Boolean?,
    @SerializedName("statusCode")
    var statusCode: Int?,
    @SerializedName("support_whatsapp_number")
    var supportWhatsappNumber: String?,
    @SerializedName("today_clicks")
    var todayClicks: Int?,
    @SerializedName("top_location")
    var topLocation: String?,
    @SerializedName("top_source")
    var topSource: String?,
    @SerializedName("total_clicks")
    var totalClicks: Int?,
    @SerializedName("total_links")
    var totalLinks: Int?
)