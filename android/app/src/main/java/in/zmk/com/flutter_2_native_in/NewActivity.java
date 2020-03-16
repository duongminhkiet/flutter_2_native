package in.zmk.com.flutter_2_native_in;

import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class NewActivity extends FlutterActivity implements View.OnClickListener {
    private TextView text_view;
    public static String TEXT_FLUTTER_2_NATIVE = "";
    @java.lang.Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);

        text_view = (TextView)findViewById(R.id.text_view);
        text_view.setText(TEXT_FLUTTER_2_NATIVE);

        text_view.setOnClickListener(this);
        //MethodChannel channel = new  MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), MainActivity.CHANNEL);
    }
    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);
        MethodChannel channel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), MainActivity.CHANNEL);
        channel.invokeMethod("message", "Hello from native host");


    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.text_view:
                text_view.setText("XXXXXXX");
                break;
        }
    }
}
