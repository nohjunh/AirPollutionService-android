# AirPollutionService
An application that enables you to view the latest air pollution information about a selected area through the foreground.

# Setting
- Android Dolphin l 2021.3.1
- Kotlin

# Tstack
- Timber
- SplashScreen
- Coil
- Retrofit
- Lottie
- Navigation
- Coroutine
- Room
- Flow
- LiveData
- AndroidSlidingUpPanel : https://github.com/umano/AndroidSlidingUpPanel
- DataStore
- ViewPager2
- WorkManager
- MotionToast : https://github.com/Spikeysanju/MotionToast
- SwitchButton : https://github.com/kyleduo/SwitchButton

# Main src
```bash
├── .idea
├── app 
│   └── src
│        └── main.java.com.nohjunh.airpollutionservice
│                                                  ├── adapter
│                                                  ├── background
│                                                  ├── dataStore
│                                                  ├── database
│                                                  ├── foreground
│                                                  ├── model
│                                                  ├── network
│                                                  ├── repository
│                                                  ├── view
│                                                  ├── viewModel
│                                                  └── App.kt
└── gradle/wrapper

``` 

# API
-한국환경공단_에어코리아_대기오염정보 일반 인증키(Decoding)
Add the key to local.properties file on the project.
APIKEY_AirPollution = "~~~~~"


# View
![1번](https://user-images.githubusercontent.com/75293768/214249998-1a1c6328-cabe-4675-b3ae-f4a714728db1.gif)
![2번](https://user-images.githubusercontent.com/75293768/214250005-15d07872-1ac5-429d-8dfe-f4d89a7b912b.gif)
![3번](https://user-images.githubusercontent.com/75293768/214250016-41a7c26e-0ca2-48fb-9a77-234159bc8ddf.gif)

![4번](https://user-images.githubusercontent.com/75293768/214250024-7c321429-6f36-4b0a-88f7-32fe123cdb39.gif)
![5번](https://user-images.githubusercontent.com/75293768/214250030-bfc650d1-6ebf-45d6-94da-45942b5ebad1.gif)
![6번](https://user-images.githubusercontent.com/75293768/214250048-d03df135-0eae-4865-acca-301f6f602180.gif)
