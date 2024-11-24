package com.example.latihanretrofit.data.response

import com.google.gson.annotations.SerializedName

data class PhotoResponse(

	@field:SerializedName("PhotoResponse")
	val photoResponse: List<PhotoResponseItem>
)

data class PhotoResponseItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("url")
	val url: String,

)
