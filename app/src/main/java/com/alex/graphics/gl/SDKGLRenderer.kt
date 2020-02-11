package com.alex.graphics.gl

import android.content.Context
import android.graphics.BitmapFactory
import android.opengl.GLES30.*
import android.opengl.GLSurfaceView
import android.opengl.GLUtils
import android.opengl.Matrix
import com.alex.graphics.R
import javax.microedition.khronos.opengles.GL10


class SDKGLRenderer(val ctx: Context) : GLSurfaceView.Renderer {

    val star: Star? = null
    var square: Square? = null
    val mProjectionMatrix = FloatArray(16)
    var mRotationMatrix = FloatArray(16)

    var textureID = IntArray(1)

    @Volatile
    var angleX: Float = 0f
    @Volatile
    var angleY: Float = 0f


    override fun onSurfaceCreated(gl: GL10?, config: javax.microedition.khronos.egl.EGLConfig?) {

        glClearColor(0.9f, 1f, 0.9f, 1f)

        //load textures
        glGenTextures(1, textureID, 0)

        val options = BitmapFactory.Options()
        options.inScaled = false

        val bitmap = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.t1, options)

        glActiveTexture(GL_TEXTURE0)
        glBindTexture(GL_TEXTURE_2D, textureID[0])

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)

        GLUtils.texImage2D(GL_TEXTURE_2D, 0, bitmap, 0)
        bitmap.recycle()
        glBindTexture(GL_TEXTURE_2D, 0) //Unbind

        square = Square(textureID[0])
    }


    override fun onSurfaceChanged(gl10: GL10, w: Int, h: Int) {
        glViewport(0, 0, w, h)
        val ratio = if (w > h) w.toFloat() / h else h.toFloat() / w
        if (w > h) {
            Matrix.orthoM(mProjectionMatrix, 0, -ratio, ratio, -1f, 1f, -1f, 1f)
        } else {
            Matrix.orthoM(mProjectionMatrix, 0, -1f, 1f, -ratio, ratio, -1f, 1f)
        }

    }

    override fun onDrawFrame(gl10: GL10) {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

        Matrix.setIdentityM(mRotationMatrix, 0)
        Matrix.rotateM(mRotationMatrix, 0, angleX, 0f, 1f, 0f)
        Matrix.rotateM(mRotationMatrix, 0, angleY, 1f, 0f, 0f)

        val scratch = FloatArray(16)

        Matrix.multiplyMM(scratch, 0, mProjectionMatrix, 0, mRotationMatrix, 0);

        square!!.draw(scratch)
    }

    companion object {

        fun loadShader(type: Int, shaderCode: String) = {
            val shader = glCreateShader(type)
            glShaderSource(shader, shaderCode)
            glCompileShader(shader)
            shader
        }()
    }
}


