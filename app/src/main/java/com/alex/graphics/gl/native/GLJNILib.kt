package com.alex.graphics.gl.native


class GLJNILib {

    /**
     * @param width the current view width
     * @param height the current view height
     */

    external fun init(width: Int, height: Int)

    external fun step()

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("gl2jni")
        }


    }
}
