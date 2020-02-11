package com.alex.graphics.gl

import android.opengl.GLES30.*
import org.intellij.lang.annotations.Language
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer


class Square(val texID: Int) {

    var textureID: Int = 0

    //Geometry
    var vertexBuffer: FloatBuffer? = null
    val POSITION_COUNT = 3 // number of position coordinates
    val COLOR_COUNT = 3    // number of color coordinates
    val TEXTURE_COUNT = 2  // number of texture coordinates

    val COORDINATES_PER_VERTEX = POSITION_COUNT + TEXTURE_COUNT

    var triangleCoords =
            floatArrayOf(
                    // vertex coords             texture coords
                    // square
                    -0.5f, 0.6f, 0.0f,           0f, 0f,
                    0.5f, 0.6f, 0.0f,            1f, 0f,
                    -0.5f, -0.311004243f, 0.0f,  0f, 1f,

                    0.5f, 0.6f, 0.0f,            1f, 0f,
                    0.5f, -0.311004243f, 0.0f,   1f, 1f,
                    -0.5f, -0.311004243f, 0.0f,  0f, 1f,

                    // star
                    -0.5f, 0f, 0.3f,             0f, 1f,
                    0f, 0.6f, -0.3f,             0.5f, 0f,
                    0.5f, 0f, 0.3f,              1f, 1f  )

    @Language("GLSL")
    val vertexShaderCode =
            """
              attribute vec4 aPosition;
              attribute vec2 aTexCoord;
              uniform mat4 uMVPMatrix;
              varying vec2 vTexCoord;
              void main() {
                vTexCoord = aTexCoord;
                gl_Position = uMVPMatrix*aPosition;
              }
              """

    @Language("GLSL")
    val fragmentShaderCode =
            """precision mediump float;
               uniform sampler2D uTextureUnit;
               varying vec2 vTexCoord;
               void main() {
                 gl_FragColor = texture2D(uTextureUnit,vTexCoord);
               }
              """

    var mProgram: Int = 3


    //Draw
    var mPositionHandle: Int = 0
    val mColorHandle: Int = 0
    var mTextCoordHandle: Int = 0
    var mTextUnitHandle: Int = 0

    var mMVPMatrixHandle: Int = 0
    val vertexCount = triangleCoords.size / COORDINATES_PER_VERTEX
    val vertexStride = COORDINATES_PER_VERTEX * 4 // 4 bytes per vertex


    init {
        textureID = texID
        val bb = ByteBuffer.allocateDirect(triangleCoords.size * 4)//4 bytes for 1 float
        bb.order(ByteOrder.nativeOrder())
        vertexBuffer = bb.asFloatBuffer()
        vertexBuffer!!.put(triangleCoords)


        val vertexShader = SDKGLRenderer.loadShader(GL_VERTEX_SHADER, vertexShaderCode)
        val fragmentShader = SDKGLRenderer.loadShader(GL_FRAGMENT_SHADER, fragmentShaderCode)

        mProgram = glCreateProgram()
        glAttachShader(mProgram, vertexShader)
        glAttachShader(mProgram, fragmentShader)
        glLinkProgram(mProgram)
    }

    fun draw(mvpMatrix: FloatArray) {

        glUseProgram(mProgram)
        mPositionHandle = glGetAttribLocation(mProgram, "aPosition")
        mTextCoordHandle = glGetAttribLocation(mProgram, "aTexCoord")
        mTextUnitHandle = glGetUniformLocation(mProgram, "uTextureUnit")
        // mColorHandle = glGetAttribLocation(mProgram, "vColor");
        mMVPMatrixHandle = glGetUniformLocation(mProgram, "uMVPMatrix")



        glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0)

        vertexBuffer!!.position(0)
        glVertexAttribPointer(mPositionHandle, POSITION_COUNT,
                GL_FLOAT, false, vertexStride, vertexBuffer)
        glEnableVertexAttribArray(mPositionHandle)

        vertexBuffer!!.position(POSITION_COUNT)
        glVertexAttribPointer(mTextCoordHandle, TEXTURE_COUNT,
                GL_FLOAT, false, vertexStride, vertexBuffer)
        glEnableVertexAttribArray(mTextCoordHandle)



        glActiveTexture(GL_TEXTURE0)
        glBindTexture(GL_TEXTURE_2D, textureID)
        glUniform1i(mTextUnitHandle, 0)

        glDrawArrays(GL_TRIANGLES, 0, vertexCount)

        glBindTexture(GL_TEXTURE_2D, 0)



        glDisableVertexAttribArray(mPositionHandle)
        glDisableVertexAttribArray(mTextCoordHandle)
    }


}
