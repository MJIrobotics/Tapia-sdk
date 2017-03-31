# Tapia-sdk

SDKはTapiaで開発するためのものです。　　
SDK for developing on Tapia. 

`gradle.properties`に追加することで　　
Tapiaのライブラリーを使える。　　
Add the following to your `gradle.properties`　　
This will add the Tapia library to your project. 

```
sdk_username=yourUserName
sdk_password=yourPassword
```
声のデーターベースファイルも必要である。　　
あなたのTapiaかAndroidデバイスに次のように追加する。　　
そうするとTapiaのText-To-Speechを使える。　　
You will also need to add the voice database file　　
to the following folder on your Tapia or android device　　
to enable Tapia's Text to Speech. 　　
```
/USB storage/micro_h16/tts_single_db_XXXXX.vtdb
```
アカウントをまだ作成していない場合は、以下のurlで作成する。　　
If you don't have an account yet, please create one at :　　
https://tos.mjirobotics.co.jp/
