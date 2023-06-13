package prototype.todolist.models

import com.google.gson.annotations.SerializedName

data class FavoriteCityplage(
    @SerializedName("fvid"          ) var fvid         : Int?    = null,
    @SerializedName("cityplages_id" ) var cityplagesId : Int?    = null,
    @SerializedName("User_id"       ) var UserId       : Int?    = null,
    @SerializedName("image"         ) var image        : String? = null,
    @SerializedName("city"          ) var city         : String? = null,
    @SerializedName("nom"           ) var nom          : String? = null,
    @SerializedName("conseil"       ) var conseil      : String? = null,
    @SerializedName("googlemaps"    ) var googlemaps   : String? = null,
    @SerializedName("name"          ) var name         : String? = null,
    @SerializedName("id"            ) var id           : Int?    = null,
    @SerializedName("email"         ) var email        : String? = null,
    @SerializedName("password"      ) var password     : String? = null
)