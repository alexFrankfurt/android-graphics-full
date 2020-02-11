package com.alex.graphics

import android.content.Intent
import android.support.v7.app.AppCompatActivity


fun <T> AppCompatActivity.start(cls: Class<T>) = startActivity(Intent(this, cls))
