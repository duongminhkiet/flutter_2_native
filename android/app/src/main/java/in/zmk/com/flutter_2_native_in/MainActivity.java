package in.zmk.com.flutter_2_native_in;

import android.content.Intent;


import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {

  public static final String CHANNEL = "in.zmk.com.flutter_2_native_in.channel";
    public static final String KEY_NATIVE = "showNativeView";


    void startNewActivity(){
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler(
                        (call, result) -> {
                            if (call.method.equalsIgnoreCase(KEY_NATIVE)) {
                                NewActivity.TEXT_FLUTTER_2_NATIVE = call.argument("text_2_native");
                                startNewActivity();
                                result.success("This is text from Native to Flutter");
                            }
                        }
                );
    }
}
