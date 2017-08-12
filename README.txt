# BasicChat

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile 'com.android.support:appcompat-v7:25.3.1'
    compile 'com.android.support:design:25.3.1'
    compile 'com.android.support.constraint:constraint-layout:1.0.2'
    compile 'com.google.firebase:firebase-database:11.0.2'
    compile 'com.google.firebase:firebase-core:11.0.2'
    compile 'com.android.support:cardview-v7:25.1.1'
    compile 'com.android.support:recyclerview-v7:25.1.1'
    testCompile 'junit:junit:4.12'
}


color value 
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="colorPrimary">#00BCD4</color>
    <color name="colorPrimaryDark">#0097A7</color>
    <color name="colorAccent">#009688</color>
    <color name="primary">#00BCD4</color>
    <color name="primary_dark">#0097A7</color>
    <color name="primary_light">#B2EBF2</color>
    <color name="accent">#009688</color>
    <color name="primary_text">#212121</color>
    <color name="secondary_text">#757575</color>
    <color name="icons">#f0f0f0</color>
    <color name="divider">#BDBDBD</color>
</resources>

manifest file
<application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity"></activity>
        <activity android:name=".ChatRoom" />
        <activity android:name=".Login">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name=".ChangePass"></activity>
    </application>
