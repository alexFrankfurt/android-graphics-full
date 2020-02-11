package com.alex.graphics.gl.native

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class GLJNIActivity : Activity() {


    private var mView: GLJNIView? = null

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        mView = GLJNIView(application)
        setContentView(mView)
    }

    override fun onPause() {
        super.onPause()
        mView?.onPause()
    }

    override fun onResume() {
        super.onResume()
        mView?.onResume()
    }


}
