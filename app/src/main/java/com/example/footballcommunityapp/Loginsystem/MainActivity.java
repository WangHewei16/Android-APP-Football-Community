package com.example.footballcommunityapp.Loginsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;
import android.content.SharedPreferences;
import com.example.footballcommunityapp.Database.UserService;
import com.example.footballcommunityapp.R;

public class MainActivity extends Activity {
    private Activity tem = this;
    private UserService uService = new UserService(MainActivity.this);
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    public static String accountRecord;
    public static String nameRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("output:","Information inform");
        setContentView(R.layout.activity_main);
        SQLiteStudioService.instance().start(this);
        this.getWindow().setBackgroundDrawableResource(R.drawable.loginbackground);
        TextView account=(EditText) findViewById(R.id.editTextTextPersonName);
        TextView password=(EditText) findViewById(R.id.editTextTextPassword);
        // Get object of button, textview and checkbox
        Button button=(Button) findViewById(R.id.button);
        Log.e("bottom",""+button);
        android.widget.Button registerLogin=(Button)this.findViewById(R.id.registerLogin);
        android.widget.TextView changePassword=(TextView)this.findViewById(R.id.changePassword);
        android.widget.CheckBox checkBox=(CheckBox)this.findViewById(R.id.checkBox);
        //****************************************************************************************************
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();
        boolean isRemember = pref.getBoolean("remember_password",false);
        if(isRemember) {
            String accountG = pref.getString("account","");
            String passwordG = pref.getString("password","");
            account.setText(accountG);
            password.setText(passwordG);
            checkBox.setChecked(true);
        }
        //****************************************************************************************************

        // First Listener
        View.OnClickListener clickChange = new View.OnClickListener() {
            public void onClick(View v){
                Intent in2 = new Intent(tem, ChangePasswordActivity.class);
                tem.startActivity(in2);
            }
        };
        changePassword.setOnClickListener(clickChange);


        // Second Listener
        View.OnClickListener click= new View.OnClickListener() {
            public void onClick(View v){
                Intent in = new Intent(tem, RegisterActivity.class);
                tem.startActivity(in);
            }
        };
        registerLogin.setOnClickListener(click);


        // Third Listener
        View.OnClickListener click3= new View.OnClickListener() {
            public void onClick(View v){
                String acco=account.getText().toString().trim();
                String pass=password.getText().toString().trim();
                Log.v("uService",""+uService);
                boolean flag=uService.login(acco, pass);
                if(flag){
                    Log.i("TAG","Login succeeded");
                    accountRecord = acco;
                    Toast.makeText(MainActivity.this, "Login succeeded", Toast.LENGTH_LONG).show();
                    //********************************************************************************************
                    if(checkBox.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",acco);
                        editor.putString("password",pass);
                    }else{
                        editor.clear();
                    }
                    editor.commit();
                    //********************************************************************************************
                    Intent in3 = new Intent(tem, MenusActivity.class);
                    tem.startActivity(in3);
                }else{
                    Log.i("TAG","Login failed");
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                }
            }
        };
        button.setOnClickListener(click3);
    }
}