package com.roysters.newyorktimes

import com.google.gson.annotations.SerializedName

data class ResultsItem(

	@field:SerializedName("nytdsection")
	val nytdsection: String? = null,

	@field:SerializedName("section")
	val section: String? = null,

	@field:SerializedName("asset_id")
	val assetId: Long? = null,

	@field:SerializedName("abstract")
	val jsonMemberAbstract: String? = null,

	@field:SerializedName("source")
	val source: String? = null,

	@field:SerializedName("media")
	val media: List<MediaItem?>? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("uri")
	val uri: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("id")
	val id: Long? = null,

	@field:SerializedName("byline")
	val byline: String? = null,

	@field:SerializedName("published_date")
	val publishedDate: String? = null,

	@field:SerializedName("updated")
	val updated: String? = null
)