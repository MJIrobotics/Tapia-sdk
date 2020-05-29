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
 
5.  既存コードからバージョンアップする場合は、以下追加してください<br />
今回のパーションアップでは、バージョンの数字だけでなく①～⑤を追加修正する必要があります。<br />
①tapia-sample/app/build.gradle を追加・修正します
    ```
    + compileOptions {
    +     sourceCompatibility 1.8
    +      targetCompatibility 1.8
    + }
    + packagingOptions {
    +     exclude 'META-INF/DEPENDENCIES'
    +     exclude 'META-INF/INDEX.LIST'
    + }
    ```

    ```
    dependencies {
    -  compile'com.tapia.mji:tapialib:1.0.10.2'
    +    compile 'com.tapia.mji:tapialib:2.0.1'
    } 
    ``` 

    ②tapia-sample/app/src/main/java/com/tapia/mji/demo/Languages/English_US.javaを追加修正します

    ``` 
    + import com.tapia.mji.tapialib.Providers.DosmonoOnlineSTTProvider;
    ``` 
    ``` 
    public English_US(){

       -  onlineSTTProvider          = Fuetrek.class;
       +  onlineSTTProvider          = DosmonoOnlineSTTProvider.class;
    ``` 

    ③tapia-sample/app/src/main/java/com/tapia/mji/demo/Languages/Japanese.java を追加修正します
     ``` 
    + import com.tapia.mji.tapialib.Providers.DosmonoOnlineSTTProvider;
    ``` 
    ``` 
    public English_US(){

       -  onlineSTTProvider          = Fuetrek.class;
       +  onlineSTTProvider          = DosmonoOnlineSTTProvider.class;
    ``` 
    ④tapia-sample/build.gradle を追加修正します
    ``` 
    buildscript {
        repositories {
        　     jcenter()
            +  google()
        }
        dependencies {
            - 　classpath 'com.android.tools.build:gradle:2.3.3'
            +  classpath 'com.android.tools.build:gradle:3.6.3'
    ``` 
    ```
        allprojects {
            repositories {
                jcenter()
            +   google()
    ```
    ```
            +    maven { url 'https://csspeechstorage.blob.core.windows.net/maven/' }
            +    maven { url 'http://maven.aliyun.com/nexus/content/groups/public/' }
            +    maven { url 'http://maven.aliyun.com/nexus/content/repositories/jcenter' }
                 maven {
    ```
    ⑤tapia-sample/gradle/wrapper/gradle-wrapper.properties を追加修正します
    ```
    -　distributionUrl=https\://services.gradle.org/distributions/gradle-3.3-all.zip
    +　distributionUrl=https\://services.gradle.org/distributions/gradle-5.6.4-all.zip
    ```
<br/>

## Release Note

ver.2.0.1<br />
機能追加<br />
・ タピアの音声認識を変更しました。

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