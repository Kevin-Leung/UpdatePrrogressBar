# UpdatePrrogressBar
this is a progress bar lib of android platform . especially the ble devices update on the phone.
based on the project:
```XML
https://github.com/hongyangAndroid/Android-ProgressBarWidthNumber
```
## effect of ImageUpdateProgressBar
![](https://github.com/Kevin-Leung/UpdatePrrogressBar/blob/master/gif/GIF.gif)
## effect of TextUpdateProgressBar
![](https://github.com/Kevin-Leung/UpdatePrrogressBar/blob/master/gif/gif2.gif)
## usage
add lib in the build.gradle of app,
add below code.
``` XML
compile 'com.kevin:progressbarlib:1.0.4'
```
the in the xml files
``` XML
 <com.kevin.progressbarlib.progressbarlib.ImageUpdateProgressBar
        android:id="@+id/progressbar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/rom_update_desc"
        android:layout_centerHorizontal="true"
        android:padding="5dp"
        android:progress="0"
        app:central_image="@drawable/update"
        app:progress_text_color="#ffffff"
        app:layout_marginTopPercent="10%h"
        app:progress_unreached_bar_height="2dp"
        app:update_radius="100dp" />
``` 
``` XML
    <com.kevin.progressbarlib.progressbarlib.TextUpdateProgressBar
        android:id="@+id/progressbar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:padding="5dp"
        android:progress="0"
        app:progress_reached_bar_height="2dp"
        app:progress_text_color="#ffffff"
        app:central_text = "assessing"
        app:text_update_radius="100dp" />
``` 
