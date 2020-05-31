# KristinaChanderMobile

1. Open and launch Android Studio
2. Create and launch a virtual device Pixel 3a API 28 - emulator-5554 - (get name via $adb devices)
3. Start appium server
4. To run native test via maven console:mvn clean test -Pnative
5. To run web test via maven console:mvn clean test


to run Android web test: mvn clean test -PcloudAndroidWeb

to run Android native test: mvn clean test -PcloudAndroidNative

to run iOS web test: mvn clean test -PcloudIOSWeb

to run iOS native test: mvn clean test -PcloudAndroidNative
