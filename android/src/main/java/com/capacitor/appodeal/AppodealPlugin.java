package com.capacitor.appodeal;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import com.getcapacitor.Plugin;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.PluginMethod;

@CapacitorPlugin(name = "AppodealPlugin")
public class AppodealPlugin extends Plugin {
    @PluginMethod
    public void isLoaded(PluginCall call) {
        int adType = call.getInt("adType", 0);
        int adTypeFlags = getAdType(adType);

        new Handler(Looper.getMainLooper()).post(() -> {
            boolean isLoaded = Appodeal.isLoaded(adTypeFlags);
            JSObject ret = new JSObject();
            ret.put("isLoaded", isLoaded);
            call.resolve(ret);
        });
    }
    @PluginMethod
    public void initialize(PluginCall call) {
        String appKey = call.getString("appKey");
        int adType = call.getInt("adType", 0);

        if (appKey == null) {
            call.reject("appKey is required");
            return;
        }

        int adTypeFlags = getAdType(adType);

        Activity activity = getActivity();
        new Handler(Looper.getMainLooper()).post(() -> {
            Appodeal.initialize(activity, appKey, adTypeFlags);
            call.resolve();
        });
    }
   
    @PluginMethod
    public void muteVideosIfCallsMuted(PluginCall call) {
        boolean value = call.getBoolean("value", true);
        new Handler(Looper.getMainLooper()).post(() -> {
            Appodeal.muteVideosIfCallsMuted(value);
            call.resolve();
        });
    }
    @PluginMethod
    public void setInterstitialCallbacks(PluginCall call) {
        new Handler(Looper.getMainLooper()).post(() -> {
            Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
                @Override
                public void onInterstitialLoaded(boolean isPrecache) {
                    JSObject ret = new JSObject();
                    ret.put("event", "onInterstitialLoaded");
                    ret.put("isPrecache", isPrecache);
                    notifyListeners("onInterstitialEvent", ret);
                }

                @Override
                public void onInterstitialFailedToLoad() {
                    JSObject ret = new JSObject();
                    ret.put("event", "onInterstitialFailedToLoad");
                    notifyListeners("onInterstitialEvent", ret);
                }

                @Override
                public void onInterstitialShown() {
                    JSObject ret = new JSObject();
                    ret.put("event", "onInterstitialShown");
                    notifyListeners("onInterstitialEvent", ret);
                }

                @Override
                public void onInterstitialShowFailed() {
                    JSObject ret = new JSObject();
                    ret.put("event", "onInterstitialShowFailed");
                    notifyListeners("onInterstitialEvent", ret);
                }

                @Override
                public void onInterstitialClicked() {
                    JSObject ret = new JSObject();
                    ret.put("event", "onInterstitialClicked");
                    notifyListeners("onInterstitialEvent", ret);
                }

                @Override
                public void onInterstitialClosed() {
                    JSObject ret = new JSObject();
                    ret.put("event", "onInterstitialClosed");
                    notifyListeners("onInterstitialEvent", ret);
                }

                @Override
                public void onInterstitialExpired() {
                    JSObject ret = new JSObject();
                    ret.put("event", "onInterstitialExpired");
                    notifyListeners("onInterstitialEvent", ret);
                }
            });
            call.resolve();
        });
    }
    @PluginMethod
    public void show(PluginCall call) {
        int adType = call.getInt("adType", 0);
        int adTypeFlags = getAdType(adType);
        Activity activity = getActivity();

        new Handler(Looper.getMainLooper()).post(() -> {
            boolean result = Appodeal.show(activity, adTypeFlags);
            JSObject ret = new JSObject();
            ret.put("result", result);
            call.resolve(ret);
        });
    }
    @PluginMethod
    public void cache(PluginCall call) {
        int adType = call.getInt("adType", 0);
        int adTypeFlags = getAdType(adType);
        Activity activity = getActivity();

        new Handler(Looper.getMainLooper()).post(() -> {
            Appodeal.cache(activity, adTypeFlags);
            call.resolve();
        });
    }
    private int getAdType(int adtype) {
        int type = 0;
        if ((adtype & 3) > 0) {
            type |= Appodeal.INTERSTITIAL;
        }
        if ((adtype & 4) > 0) {
            type |= Appodeal.BANNER;
        }
        if ((adtype & 8) > 0) {
            type |= Appodeal.BANNER_BOTTOM;
        }
        if ((adtype & 16) > 0) {
            type |= Appodeal.BANNER_TOP;
        }
        if ((adtype & 128) > 0) {
            type |= Appodeal.REWARDED_VIDEO;
        }
        return type;
    }

}
