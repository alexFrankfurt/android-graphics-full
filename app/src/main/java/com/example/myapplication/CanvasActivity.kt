package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.nativeCanvas
import com.example.myapplication.ui.theme.MyApplicationTheme


class CanvasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CanvasActivityUI()
                }
            }
        }
    }
}


@Composable
fun CanvasActivityUI() {
    Box(modifier = Modifier.fillMaxSize()) {
        CustomCanvas()
    }
}

@Composable
fun CustomCanvas(modifier: Modifier = Modifier) {
    val paint = Paint().apply {
        color = androidx.compose.ui.graphics.Color.Red
        strokeWidth = 10f
        style = PaintingStyle.Fill
    }

    val path = remember {
        Path().apply {
            reset()
            moveTo(40f, 420f)
            lineTo(240f, 300f)
            lineTo(380f, 420f)
        }
    }

    val arc = Rect(400f, 400f, 750f, 600f)
    val rectF = Rect(100f, 400f, 300f, 600f)

    Canvas(modifier = modifier.fillMaxSize()) {
        // Background
        drawRect(
            color = Color(0xFFD2FFD2),
            size = size
        )

        // Main triangle path
        drawPath(
            path = path,
            color = Color.Red, // Or any Compose color
            style = Fill
        )

        // Rounded rectangle
        drawRoundRect(
            topLeft = Offset(rectF.left, rectF.top),
            size = Size(rectF.width, rectF.height),
            cornerRadius = CornerRadius(10f, 10f),
            color = paint.color
        )

        // Path for text (simplified)
        val textPath = Path().apply {
            moveTo(94f, 56f)
            quadraticBezierTo(100f, 39f, 119f, 32f)
            moveTo(119f, 32f)
            quadraticBezierTo(112f, 49f, 94f, 56f)
        }

        // Text on path is not yet directly supported in Compose
        // Alternative: just draw text near the path
        drawContext.canvas.nativeCanvas.drawText(
            "Hello world from view lorem ipsum ipsum ipsum image text something",
            94f,
            56f,
            android.graphics.Paint().apply {
                color = android.graphics.Color.RED
                textSize = 32f
            }
        )

        // Circle
        drawCircle(
            color = paint.color,
            radius = 50f,
            center = Offset(540f, 320f)
        )

        // Arc
        drawArc(
            color = paint.color,
            startAngle = 0f,
            sweepAngle = 120f,
            useCenter = true,
            topLeft = Offset(arc.left, arc.top),
            size = Size(arc.width, arc.height)
        )
    }
}