package com.alex.graphics.gl

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.Log
import android.view.MotionEvent


class SDKGLSurfaceView(ctx: Context,val renderer: SDKGLRenderer, val mDensity: Float) : GLSurfaceView(ctx) {
    init {
        setEGLContextClientVersion(3)
        setRenderer(renderer)
        setRenderMode(RENDERMODE_WHEN_DIRTY)
    }

    var mPreviousX: Float = 0f
    var mPreviousY: Float = 0f
    val TOUCH_SCALE_FACTOR = 180.0f / 320


    override fun onTouchEvent(e: MotionEvent?): Boolean = {

        val x = e!!.getX()
        val y = e.getY()

        when (e.getAction()) {
            MotionEvent.ACTION_MOVE -> {

                var dx = x - mPreviousX
                var dy = y - mPreviousY

//                // reverse direction of rotation above the mid-line
//                if (y > getHeight() / 2) {
//                    dx = dx * -1 ;
//                }
//
//                // reverse direction of rotation to left of the mid-line
//                if (x < getWidth() / 2) {
//                    dy = dy * -1 ;
//                }

                Log.i("some move","$dx")

                renderer.angleX = renderer.angleX + dx * TOUCH_SCALE_FACTOR
                renderer.angleY = renderer.angleY + dy * TOUCH_SCALE_FACTOR
                requestRender()
            }
        }

        mPreviousX = x
        mPreviousY = y
        true
    }()
}
