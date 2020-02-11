package com.alex.graphics.activity

import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.BounceInterpolator
import com.alex.graphics.R
import kotlinx.android.synthetic.main.activity_ball.*

class BallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ball)
        imageView2
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        val ih = imageView2.height
        val th = textView.height

        val animator = ValueAnimator.ofInt(0, th - ih)
        with(animator) {
            interpolator = BounceInterpolator()
            duration = 3000L
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener { vAnimator ->
                val y = vAnimator.animatedValue as Int
                imageView2.y = y.toFloat()
                imageView2.invalidate()
            }
            start()
        }

    }
}
