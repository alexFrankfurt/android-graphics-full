package com.alex.graphics

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import com.alex.graphics.activity.BallActivity
import com.alex.graphics.activity.CanvasActivity
import com.alex.graphics.activity.CustomActivity
import com.alex.graphics.activity.SDKIntersectionActivity
import com.alex.graphics.gl.native.GLJNIActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)


        // Example of a call to a native method
//        sample_text.text = stringFromJNI()
        ballAnim.setOnClickListener {
            start(BallActivity::class.java)
        }

        indexB.setOnClickListener {
            start(IndexActivity::class.java)
        }

        canvasAnim.setOnClickListener {
            start(CanvasActivity::class.java)
        }

        customB.setOnClickListener {
            start(CustomActivity::class.java)
        }

        openglB.setOnClickListener {
            start(SDKIntersectionActivity::class.java)
        }

        nativeB.setOnClickListener {
            start(GLJNIActivity::class.java)
        }
        openCVB.setOnClickListener {
            start(OpenCVActivity::class.java)
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String


    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
//            System.loadLibrary("gl2jni")
        }
    }
}
