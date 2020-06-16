package com.local_storage_native_module;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.content.SharedPreferences; 
import android.content.Context; 
import android.widget.Toast;
import android.util.Log;

public class LocalStorageModule extends ReactContextBaseJavaModule {
  private static ReactApplicationContext reactContext;

  LocalStorageModule(ReactApplicationContext Context)
  {
    super(Context);
    reactContext = Context;
  }

  @Override
  public String getName(){
    return "LocalStorageModule";
  }

  @ReactMethod
  public String getData(String key){
    SharedPreferences localData = reactContext.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
    Log.d("the value of the key  is",key);
    String name = localData.getString(key,null);
    Log.d("the value from the storage",name);
    return name;
  };

  @ReactMethod
  public String setData(String key, String value){
    Log.d("the value and the key are",key);
    Log.d("the value and the key are",value);

    SharedPreferences localData = reactContext.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = localData.edit(); 
    editor.putString(key,value);
    editor.apply();
    Toast.makeText(getReactApplicationContext(),"Data Saved",Toast.LENGTH_SHORT).show();
    return null;

  }
}

