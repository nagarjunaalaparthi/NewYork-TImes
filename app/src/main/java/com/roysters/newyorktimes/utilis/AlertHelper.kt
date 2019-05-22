package com.roysters.newyorktimes.utilis

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.databinding.BindingAdapter
import android.net.Uri
import android.widget.ImageView
import com.roysters.newyorktimes.R
import com.roysters.newyorktimes.ResultsItem
import com.squareup.picasso.Picasso
import kotlin.random.Random

fun showAlert(
    activity: Activity, message: String, positiveButton: String,
    negativeButton: String? = null, completion: ((Boolean) -> Unit?)? = null
) {

    val builder = AlertDialog.Builder(activity).apply {
        setIcon(R.mipmap.ic_launcher)
        setTitle(activity.getString(R.string.app_name))
        setMessage(message)
        setCancelable(false)
    }

    negativeButton?.let {
        builder.setNegativeButton(negativeButton) { _: DialogInterface, _: Int ->
            completion?.invoke(false)
        }
    }

    builder.setPositiveButton(positiveButton) { _: DialogInterface, _: Int ->
        completion?.invoke(true)
    }

    val dialog = builder.create()
    dialog.show()
}


@BindingAdapter("imageUrl")
fun loadAlbumImage(view: ImageView, newImageUrl: String?) {
    Picasso.get().load(newImageUrl).into(view)

    ResultsItem().media?.get(0)?.mediaMetadata?.get(2)?.url
}