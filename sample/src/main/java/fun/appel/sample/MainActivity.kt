package `fun`.appel.sample

import `fun`.appel.imagepopup.ImagePopup
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var popup: ImagePopup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        popup = ImagePopup(this)
        popup.initiatePicassoPopup(R.mipmap.francis_taylor_33214_unsplash)

        sampleImage.setOnClickListener {
            popup.show()
        }
    }

    override fun onPause() {
        super.onPause()
        popup.close()
    }

    override fun onStop() {
        super.onStop()
        popup.close()
    }
}
