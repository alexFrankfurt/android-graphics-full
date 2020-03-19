package com.alex.graphics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity


fun <T> AppCompatActivity.start(cls: Class<T>) = startActivity(Intent(this, cls))
