package com.roysters.newyorktimes

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

	@field:SerializedName("copyright")
	val copyright: String? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null,

	@field:SerializedName("num_results")
	val numResults: Int? = null,

	@field:SerializedName("status")
	val status: String? = null,

	var errorMessage: String? = null
)