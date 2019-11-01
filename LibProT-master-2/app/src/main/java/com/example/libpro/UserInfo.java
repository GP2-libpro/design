package com.example.libpro;

import android.content.Context;
import android.content.SharedPreferences;

public class UserInfo {
    private static SharedPreferences refrnce;

    public UserInfo(Context context){
        refrnce = context.getSharedPreferences("LibraryRef", Context.MODE_PRIVATE);

    }

    public void saveUserInfo(String username, String email,String password) {
        SharedPreferences.Editor editor = refrnce.edit();
        editor.putString("Username", username);
        editor.putString("UserEmail", email);
        editor.putString("UserPassword", password);
        editor.apply();
    }

    public String getUserusername() {
        return refrnce.getString("Userusername", "");
    }
    public String getUserEmail() {
        return refrnce.getString("UserEmail", "");
    }
    public String getUserPassword() {
        return refrnce.getString("UserPassword", "");
    }

}
