package com.alex.graphics.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.EaseInOutBounce
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme

class BallActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OldLayoutEquivalent()
                }
            }
        }
    }
}

@Composable
fun OldLayoutEquivalent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BouncingBall()
        Text(
            text = "Your text here",
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun BouncingBall() {
    val infiniteTransition = rememberInfiniteTransition()

    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 600f, // Change to th - ih as needed
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = EaseInOutBounce),
            repeatMode = RepeatMode.Restart
        )
    )

    Image(
        painter = painterResource(id = R.drawable.ball),
        contentDescription = "Ball",
        modifier = Modifier
            .offset(y = offsetY.dp)
            .size(100.dp)
    )
}

//
//
//class BallActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_ball)
//        imageView2
//    }
//
//    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)
//
//        val ih = imageView2.height
//        val th = textView.height
//
//        val animator = ValueAnimator.ofInt(0, th - ih)
//        with(animator) {
//            interpolator = BounceInterpolator()
//            duration = 3000L
//            repeatMode = ValueAnimator.RESTART
//            repeatCount = ValueAnimator.INFINITE
//            addUpdateListener { vAnimator ->
//                val y = vAnimator.animatedValue as Int
//                imageView2.y = y.toFloat()
//                imageView2.invalidate()
//            }
//            start()
//        }
//
//    }
//}
