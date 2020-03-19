package com.alex.graphics

import android.Manifest
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.SurfaceView
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_open_cv.*
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.CameraBridgeViewBase
import org.opencv.android.LoaderCallbackInterface
import org.opencv.core.Mat
import android.widget.Toast
import android.content.pm.PackageManager
import org.opencv.android.OpenCVLoader




class OpenCVActivity : AppCompatActivity() , CameraBridgeViewBase.CvCameraViewListener2 {

    val TAG = "OCVSample::Activity"
    var _cameraBridgeViewBase: CameraBridgeViewBase? = null

    val _baseLoaderCallback = object : BaseLoaderCallback(this) {
        override fun onManagerConnected(status: Int) {
            when (status) {
                LoaderCallbackInterface.SUCCESS -> {
                    Log.i(TAG, "OpenCV loaded successfully")
                    // Load ndk built module, as specified in moduleName in build.gradle
                    // after opencv initialization
                    System.loadLibrary("native-lib")
                    _cameraBridgeViewBase!!.enableView()
                }
                else -> {
                    super.onManagerConnected(status)
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.activity_open_cv)

        // Permissions for Android 6+
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                1)

        _cameraBridgeViewBase = main_surface as CameraBridgeViewBase
        _cameraBridgeViewBase!!.visibility = SurfaceView.VISIBLE
        _cameraBridgeViewBase!!.setCvCameraViewListener(this)
    }

    override fun onPause() {
        super.onPause()
        disableCamera()
    }

    public override fun onResume() {
        super.onResume()
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization")
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, _baseLoaderCallback)
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!")
            _baseLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }// other 'case' lines to check for other
        // permissions this app might request
    }

    public override fun onDestroy() {
        super.onDestroy()
        disableCamera()
    }

    fun disableCamera() {
        if (_cameraBridgeViewBase != null)
            _cameraBridgeViewBase!!.disableView()
    }

    override fun onCameraViewStarted(width: Int, height: Int) {}

    override fun onCameraViewStopped() {}

    override fun onCameraFrame(inputFrame: CameraBridgeViewBase.CvCameraViewFrame): Mat {
        val matGray = inputFrame.gray()
        salt(matGray.nativeObjAddr, 2000)
        return matGray
    }

    external fun salt(matAddrGray: Long, nbrElem: Int)

}
