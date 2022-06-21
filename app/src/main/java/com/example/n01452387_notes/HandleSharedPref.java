package com.example.n01452387_notes;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

public class HandleSharedPref {

    public static void createUpdateSharedPref(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.prefName), context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(NotesRepository.getAllNotes());
        if(sharedPreferences.contains(context.getString(R.string.prefItem))){
            System.out.println("Note are available in Shared Preferences, REPLACING");
            sharedPreferences.edit().remove(context.getString(R.string.prefItem)).putString(context.getString(R.string.prefItem), json).apply();
        }else{
            System.out.println("Note are NOT available in Shared Preferences, ADDING");
            sharedPreferences.edit().putString(context.getString(R.string.prefItem), json).apply();
        }
    }
}
