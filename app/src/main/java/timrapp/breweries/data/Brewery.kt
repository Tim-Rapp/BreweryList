package timrapp.breweries.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Brewery(
    @Json(name = "name") val name: String,
    @Json(name = "city") val city: String,
    @Json(name = "brewery_type") val breweryType: String
) {
}