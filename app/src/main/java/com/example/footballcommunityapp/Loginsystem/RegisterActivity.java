package com.example.footballcommunityapp.Loginsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballcommunityapp.Database.User;
import com.example.footballcommunityapp.Database.UserService;
import com.example.footballcommunityapp.R;

public class RegisterActivity extends Activity {
    private Activity tem = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.getWindow().setBackgroundDrawableResource(R.drawable.registerbackground);
        android.widget.TextView account=(EditText) findViewById(R.id.editTextTextPersonName5);
        android.widget.TextView username=(EditText) findViewById(R.id.editTextTextPersonName4);
        android.widget.TextView password=(EditText) findViewById(R.id.editTextTextPassword4);
        android.widget.TextView passwordagain=(EditText) findViewById(R.id.editTextTextPassword3);
        android.widget.Spinner position = (Spinner) findViewById(R.id.spinner);
        android.widget.Button registerbut=(Button) findViewById(R.id.button3);
        android.widget.TextView allborder= (TextView) findViewById(R.id.textView6);
        allborder.getBackground().setAlpha(50);
        View.OnClickListener click= new View.OnClickListener() {
            public void onClick(View v) {
                String acco=account.getText().toString().trim();
                String usern=username.getText().toString().trim();
                String pass=password.getText().toString().trim();
                String passagain=passwordagain.getText().toString().trim();
                String posi=position.getSelectedItem().toString();
                UserService uService=new UserService(RegisterActivity.this);
                // Check if the user name exists
                if(uService.checkExist(acco)){
                    Toast.makeText(RegisterActivity.this, "Account already exists", Toast.LENGTH_LONG).show();
                }else if(uService.checkExistUsername(usern)){
                    Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_LONG).show();
                }else if(!pass.equals(passagain)){
                    Toast.makeText(RegisterActivity.this, "The two passwords are inconsistent", Toast.LENGTH_LONG).show();
                }
                // Check whether the registration information is not empty and whether the two passwords are consistent.
                // If they are consistent, it will succeed.
                else if(!acco.equals("") && !usern.equals("") && !pass.equals("") && !passagain.equals("")
                        && !posi.equals("Choose position")){
                    User user=new User();
                    user.setAccount(acco);
                    user.setUsername(usern);
                    user.setPassword(pass);
                    user.setPosition(posi);
                    user.setHaveTeam("No");
                    user.setIsCaptain("No");
                    uService.register(user);
                    Toast.makeText(RegisterActivity.this, "Register succeeded", Toast.LENGTH_LONG).show();
                    Intent in2 = new Intent(tem, MainActivity.class);
                    tem.startActivity(in2);
                }else{
                    Toast.makeText(RegisterActivity.this, "Cannot have empty information, registration failed", Toast.LENGTH_LONG).show();
                }
            }
        };
        registerbut.setOnClickListener(click);
    }
}