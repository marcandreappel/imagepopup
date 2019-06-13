package `fun`.appel.library

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.annotation.DrawableRes
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_popup.view.*
import java.io.File

class ImagePopup : ImageView {

    private var popupWindow: PopupWindow? = null
    private var imageView: ImageView? = null
    private var layout: View? = null

    var windowHeight = 0
    var windowWidth = 0
    var isWithBackgroundColor: Int = Color.WHITE
    var isWithCloseIcon: Boolean = true
    var isImageOnClickClose: Boolean = false
    var isFullScreen: Boolean = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun initiatePopup(drawable: Drawable) {
        try {
            initiate()
            imageView!!.setImageDrawable(drawable)

        } catch (exception: Exception) {
            exception.printStackTrace()
            Log.e("ImagePopup ", exception.message)
        }
    }

    @JvmName("initiatePicassoPopupString")
    fun initiatePicassoPopup(string: String) {
        try {
            initiate()

            Picasso.get()
                .load(string)
                .into(imageView)

        } catch (exception: Exception) {
            exception.printStackTrace()
            Log.e("ImagePopup ", exception.message)
        }
    }

    @JvmName("initiatePicassoPopupUri")
    fun initiatePicassoPopup(uri: Uri) {
        try {
            initiate()

            Picasso.get()
                .load(uri)
                .into(imageView)

        } catch (exception: Exception) {
            exception.printStackTrace()
            Log.e("ImagePopup ", exception.message)

        }
    }

    @JvmName("initiatePicassoPopupFile")
    fun initiatePicassoPopup(file: File) {
        try {
            initiate()

            Picasso.get()
                .load(file)
                .into(imageView)

        } catch (exception: Exception) {
            exception.printStackTrace()
            Log.e("ImagePopup ", exception.message)
        }
    }

    @JvmName("initiatePicassoPopupDrawable")
    fun initiatePicassoPopup(@DrawableRes drawable: Int) {
        try {
            initiate()

            Picasso.get()
                .load(drawable)
                .into(imageView)

        } catch (exception: Exception) {
            exception.printStackTrace()
            Log.e("ImagePopup ", exception.message)
        }
    }

    fun show() {
        val metrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(metrics)

        var width = metrics.widthPixels
        var height = metrics.heightPixels

        if (windowHeight != 0 || windowWidth != 0) {
            width = windowWidth
            height = windowHeight
            popupWindow = PopupWindow(layout, width, height, true)
        } else {
            isFullScreen = true
//            popupWindow = PopupWindow(layout, (width * .8).toInt(), (height * .6).toInt(), true)
        }

        if (isFullScreen) {
            layout?.setBackgroundColor(isWithBackgroundColor)
            popupWindow = PopupWindow(layout, width, height, true)

        }
        popupWindow!!.showAtLocation(layout, Gravity.CENTER, 0, 0)

        val popupClose = layout?.imagePopupClose

        if (!isWithCloseIcon) {
            popupClose?.visibility = View.GONE
        }
        popupClose?.setOnClickListener {
            popupWindow?.dismiss()
        }

        if (isImageOnClickClose) {
            imageView?.setOnClickListener {
                popupWindow!!.dismiss()
            }
        }
    }

    fun close() {
        popupWindow?.dismiss()
    }

    private fun initiate() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layout = inflater.inflate(R.layout.image_popup, this.findViewById(R.id.imagePopup))

        imageView = layout?.findViewById(R.id.imagePopupView)
    }
}
