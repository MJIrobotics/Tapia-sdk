# Tapia-sdk

SDKはTapiaで開発するためのものです。<br />
SDK for developing on Tapia. <br /><br />

## Installation

1.  git cloneするか、zipをダウンロードし、Android Studioでtapia-sampleプロジェクトを開いてください。<br /><br />
    Download or clone the project and then open the sample app (tapia-sample) in Android Studio. 

2.  `gradle.properties`に追加することで<br />
    Tapiaのライブラリーを使用できます。<br /><br />
    Add the following to your `gradle.properties`<br />
    This will add the Tapia library to your project. 

    ```
    sdk_username=yourUserName
    sdk_password=yourPassword
    ````
    sdk_username及びskd_passwordは TAPIA Online SDKにログイン後に取得できます。<br/>
    

3.  アカウントをまだ作成していない場合は、TAPIA　Online SDKで取得してください。<br/>
    TAPIA Online SDKのurlは下記のとおりです。<br />
    If you don't have an account yet, please create one at :<br />
    https://tos.mjirobotics.co.jp/
    
4.  アカウントを取得したら、ウェブサイトからライセンスキーを確認してください。<br />
    プロジェクトのAndroidManifest.xmlファイルに下のように追記します。<br /> <br />
    Once you have an account, please find your license key from the the website<br />
    and include it in your project's AndroidManifest.xml file as follows:

    ```
    <application
        ...
        <meta-data
                android:name="license_key"
                android:value="yourLicenseKey" />
        ...
    </application>

    ````

## Release Note

ver.1.0.10.2<br />
機能追加<br />
・ タピアの音声認識の速度を向上しました。

ver.1.0.9<br />
機能追加<br />
・ タピアの話すスピードを１～５段階で設定する事が出来るようになりました。

ver.1.0.8<br />
機能追加<br />
・ 日本語入力画面の「送信」ボタンの名称を、別の名前へ変更出来るようになりました。

ver.1.0.7<br />
機能追加<br />
・ 日本語入力画面の初期表示時に、テキスト入力ボックスに文字をセット出来るようになりました。

ver.1.0.6<br />
・ 日本語入力画面が使用出来るようになりました。

ver1.0.5<br />
・ フッターのブラックバーの不具合修正をしました。