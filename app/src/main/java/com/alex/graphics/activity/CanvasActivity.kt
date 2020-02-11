package com.alex.graphics.activity

import android.graphics.Paint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alex.graphics.CanvasView
import com.alex.graphics.R

class CanvasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas)
        setContentView(CanvasView(this, Paint()))
    }
}
