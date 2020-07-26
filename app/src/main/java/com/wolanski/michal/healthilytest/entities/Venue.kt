package com.wolanski.michal.healthilytest.entities

import com.squareup.moshi.Json

data class Response(
    @field:Json(name = "response") val response: Venues
)

data class Venues(
    @field:Json(name = "venues") val venues: List<Venue>
)

data class Venue(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "location") val location: Location,
    @field:Json(name = "categories") val categories: List<Category>
)

data class Location(
    @field:Json(name = "address") val address: String?
)

data class Category(
    @field:Json(name = "icon") val icon: Icon
)

data class Icon(
    @field:Json(name = "prefix") val prefix: String,
    @field:Json(name = "suffix") val suffix: String
)