package com.example.footballcommunityapp.Community;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.TextView;

import com.example.footballcommunityapp.R;

public class CircleItem extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_item);
        TextView tv = (TextView) findViewById(R.id.circlecontent);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        tv.setHorizontallyScrolling(false);
        tv.setSingleLine(false);
    }
}
