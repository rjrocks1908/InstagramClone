package com.haxon.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("NfnF7NTBlzN5DPVgCbhBE6lRfQyBrbNkKavm2OOL")
                // if defined
                .clientKey("9BVArG4iYdiVqgDsJ2KGvk5I7ZX2K9mHsK8wsidd")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
