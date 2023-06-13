package prototype.todolist.models

import com.google.gson.annotations.SerializedName

data class Tourisme  (
//    var id: Int,
//    @SerializedName("title")
//    var title: String,
//    var priority: Int,
//    var timestamp: Long,
//    var Image:Int

    var id: Int,
//    @SerializedName("image"       ) var image       : String? = null,
    var image: String,
    @SerializedName("city"        ) var city        : String? = null,
    @SerializedName("nom"         ) var nom         : String? = null,
    @SerializedName("conseil"     ) var conseil     : String? = null,
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("googlemaps"  ) var googlemaps  : String? = null,
    @SerializedName("like"        ) var like        : Int?    = null,
    @SerializedName("category"    ) var category    : String? = null
)

