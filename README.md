# GridViewJson
Android GridView example. Images and data both taken from Assets. The data is retrived from JSON file in assets.

Dependencies you will need (included in the code)

        dependencies {
        
        ...
        
        compile 'com.google.code.gson:gson:2.4'
        
        compile 'com.squareup.picasso:picasso:2.5.2'
        
        }

        added Butterknife 8.0
        
        buildscript {
          repositories {
            mavenCentral()
           }
          dependencies {
            classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
          }
        }
        
        apply plugin: 'com.neenbedankt.android-apt'
        
        dependencies {
          compile 'com.jakewharton:butterknife:8.0.1'
          apt 'com.jakewharton:butterknife-compiler:8.0.1'
        }

Main View image (GridView):

![device-2016-02-10-182639](https://cloud.githubusercontent.com/assets/1615724/13015502/c11e4c7a-d1b9-11e5-841e-8b494832a3a0.png)

Pop-up View:

![device-2016-02-10-182659](https://cloud.githubusercontent.com/assets/1615724/13015525/e9e87694-d1b9-11e5-8488-a86a5c9fc2cf.png)
