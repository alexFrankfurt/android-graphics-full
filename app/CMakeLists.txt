cmake_minimum_required(VERSION 3.4.1)
# https://cmake.org/cmake/help/latest/manual/cmake-commands.7.html
# OpenCV stuff
include_directories(F:/OpenCV-android-sdk/sdk/native/jni/include)
add_library( lib_opencv SHARED IMPORTED )
 set_target_properties(lib_opencv PROPERTIES IMPORTED_LOCATION ${CMAKE_CURRENT_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libopencv_java4.so)
# now build app's shared lib
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -Wall")

add_library(gl2jni SHARED
            src/main/cpp/gl_code.cpp)

add_library(native-lib SHARED
            src/main/cpp/native-lib.cpp)

# add lib dependencies
target_link_libraries(gl2jni
                      native-lib
                      lib_opencv
                      android
                      log
                      EGL
                      GLESv2)
