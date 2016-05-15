# GridViewJson
Android GridView example. Images and data both taken from Assets. The data is retrived from JSON file in assets.

Dependencies you will need (included in the code)

        apply plugin: 'com.android.application'
        apply plugin: 'com.neenbedankt.android-apt'
        
        android {
            compileSdkVersion 23
            buildToolsVersion "23.0.2"
        
            defaultConfig {
                applicationId "com.corebaseit.gridviewjson"
                minSdkVersion 15
                targetSdkVersion 23
                versionCode 1
                versionName "1.0"
            }
            buildTypes {
                release {
                    minifyEnabled false
                    proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
                }
            }
        }
        
        dependencies {
            compile fileTree(dir: 'libs', include: ['*.jar'])
            testCompile 'junit:junit:4.12'
            compile 'com.android.support:appcompat-v7:23.3.0'
            compile 'com.android.support:design:23.3.0'
            compile 'com.google.code.gson:gson:2.4'
            compile 'com.jakewharton:butterknife:8.0.1'
            apt 'com.jakewharton:butterknife-compiler:8.0.1'
            compile 'com.squareup.picasso:picasso:2.5.2'
            compile 'com.android.support:recyclerview-v7:23.+'
            compile 'com.android.support:cardview-v7:23.+'
            compile 'com.android.support:support-v4:23.3.0'
        }
        
        and in project:
        
        buildscript {
        repositories {
        jcenter()
    }
         dependencies {
                classpath 'com.android.tools.build:gradle:2.1.0'
                classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
         }
        }
        
        

Main View image (GridView):

![device-2016-02-10-182639](https://cloud.githubusercontent.com/assets/1615724/13015502/c11e4c7a-d1b9-11e5-841e-8b494832a3a0.png)

Pop-up View:

![device-2016-02-10-182659](https://cloud.githubusercontent.com/assets/1615724/13015525/e9e87694-d1b9-11e5-8488-a86a5c9fc2cf.png)
