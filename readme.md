# Open In App Test Submission

[Click here to Download](app-release.apk)

The app is developed on Kotlin following the latest MVVM architecture.

---

<h3>Features</h3>

- Splash screen with beautiful OpenInApp animation using lottie.
- Custom Bottom navigation with dummy tabs and a floating action button.
- Kotlin Coroutines for asynchronous tasks.
- Retrofit for api calling.
- Modularization for better code management.
- Dependency injection using Hilt.
- Chart using MPAndroidChart.
- Api test using JUnit.
- Exact UI matching with the given design.[Link](https://iOS.openinapp.co/UITemp)
- Use of Kotlin Extensions and Wrapper classes along with Kotlin Reflection.

---

<h3>Screen Recording</h3>

Available at demo.mp4 in the root directory of this repository.


https://github.com/atharv028/OpenInAppTest/assets/89896473/c45ac88a-57b0-4cc3-86ed-7249f7f848a6


---

<h3>Screenshots</h3>

<div class="row">
      <img src="/Screenshots/home1.png" width="250" title="Home Chart">
      <img src="/Screenshots/home2.png" width="250" title="list">     
      <img src="/Screenshots/home3.png" width="250" title="contact">
</div>

<div class="row">
      <img src="/Screenshots/splash.png" width="250" title="Splash">
      <img src="/Screenshots/dummy.png" width="250" title="Dummy">     
</div>

---

<h3>Libraries Used</h3>

- [Kotlin](https://kotlinlang.org/)
- [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [Retrofit](https://square.github.io/retrofit/)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)
- [Lottie](https://github.com/airbnb/lottie-android)
- [View Binding](https://developer.android.com/topic/libraries/view-binding)
- [Kotlin Extensions](https://kotlinlang.org/docs/tutorials/android-plugin.html)
- [Kotlin Reflection](https://kotlinlang.org/docs/reflection.html)
- [JUnit](https://junit.org/junit5/)
- [CircleImageView](https://github.com/hdodenhof/CircleImageView)
- [Pluto](https://github.com/plutolib/pluto)
- [Material Components](https://m2.material.io/)

---

<h3>Project Structure</h3>

The project is divided into 2 modules:

- **app** - Contains the main code of the application. This is the module that will be installed on the device.
- **api** - Contains the code for Api handling. This module is pure Kotlin module.

---

<h3>How to run the project?</h3>

- Clone the repository.
- Open the project in Android Studio.
- Build the project/APK in release/debug mode.
- Install the APK on your device.
- Run the app.
- Enjoy the design and code.

---

<h3>Known Issues</h3>

- The api used does not provide the user's name. So, I have used the Company name (Open In App) as the name of the user.
- The api used does not provide exact chart data as shown in the design. So, there is a slight difference in the chart design.
- The api used doesn't provide top source and top location parameters in the response. So, I have declared those fields as N/A.
- The api used doesn't provide the thumbnails for links data. So, I have used the default thumbnail for all the links.

---

<h1>Thank You</h1>
