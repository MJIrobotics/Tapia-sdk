# Tapia-sdk

SDKはTapiaで開発するためのものです。<br /><br />
SDK for developing on Tapia. <br />

# Installation

`gradle.properties`に追加することで<br />
Tapiaのライブラリーを使える。<br /><br />
Add the following to your `gradle.properties`<br />
This will add the Tapia library to your project. 

```
sdk_username=yourUserName
sdk_password=yourPassword
```
声のデーターベースファイルも必要である。<br />
あなたのTapiaかAndroidデバイスに次のように追加する。<br />
そうするとTapiaのText-To-Speechを使える。<br /><br />
You will also need to add the voice database file<br />
to the following folder on your Tapia or android device<br />
to enable Tapia's Text to Speech. 　　
```
/USB storage/micro_h16/tts_single_db_XXXXX.vtdb
```
アカウントをまだ作成していない場合は、以下のurlで作成する。<br /><br />
If you don't have an account yet, please create one at :<br />
https://tos.mjirobotics.co.jp/
