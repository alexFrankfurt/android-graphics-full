# Graphics examples in android


1. Property animation.
2. Canvas.
3. Custom view.
3. OpenGL ES figure with touch controls.
4. Native OpenGL ES 2d figure.
5. OpenCV camera filter. (?got broken?)


# SetUp

1. Built on Android Studio 3.5.3 + OpenCV 4.2.0. Currently targets api 29 (android 10).
2. To sync project run `File > Sync Project with Gradle Files`
3. install opencv https://opencv.org/releases/ for android
4. Create symbolic link ./app/src/main/jniLibs which points to <OpenCV Installation folder>/sdk/native/libs
5. To add openCV java sdk (from installed opencv):
    1. Android Studio: File > New > Import Module. Choose <OpenCV Installation folder>/sdk/java
    2. In imported module in build.gradle change apply plugin: 'com.android.application' to apply plugin: 'com.android.library' and remove applicationId
    3. In app/build.gradle change dependency :openCVSDK to new module name
