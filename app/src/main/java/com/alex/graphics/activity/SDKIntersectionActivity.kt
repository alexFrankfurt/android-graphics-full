package com.alex.graphics.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MotionEvent
import com.alex.graphics.gl.SDKGLRenderer
import com.alex.graphics.gl.SDKGLSurfaceView
import android.opengl.ETC1.getWidth
import android.opengl.ETC1.getHeight
import android.util.DisplayMetrics


class SDKIntersectionActivity : AppCompatActivity() {


    val displayMetrics = DisplayMetrics();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        val sw = SDKGLSurfaceView(this, SDKGLRenderer(this), displayMetrics.density)
        setContentView(sw)
    }

}
