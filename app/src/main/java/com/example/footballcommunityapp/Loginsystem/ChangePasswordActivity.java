package com.example.footballcommunityapp.Loginsystem;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.footballcommunityapp.Database.UserService;
import com.example.footballcommunityapp.R;

public class ChangePasswordActivity extends Activity {
    private Activity tem = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        this.getWindow().setBackgroundDrawableResource(R.drawable.changepasswordbackground);
        android.widget.TextView account=(EditText) findViewById(R.id.editTextTextPersonName2);
        android.widget.TextView password=(EditText) findViewById(R.id.editTextTextPassword2);
        android.widget.TextView newpassword=(EditText) findViewById(R.id.editTextTextPassword5);
        android.widget.TextView passwordagain=(EditText) findViewById(R.id.editTextTextPassword6);
        android.widget.Button changebut=(Button) findViewById(R.id.button2);
        View.OnClickListener click= new View.OnClickListener() {
            public void onClick(View v) {
                String acco=account.getText().toString().trim();
                String pass=password.getText().toString().trim();
                String newpass=newpassword.getText().toString().trim();
                String passagain=passwordagain.getText().toString().trim();
                UserService uService=new UserService(ChangePasswordActivity.this);
                boolean flag=uService.login(acco, pass);
                if(!newpass.equals(passagain)){
                    Toast.makeText(ChangePasswordActivity.this, "The two passwords are inconsistent", Toast.LENGTH_LONG).show();
                }else if(!flag){
                    Toast.makeText(ChangePasswordActivity.this, "Wrong account or password", Toast.LENGTH_LONG).show();
                }else if(flag){
                    uService.resetpwd(acco,newpass);
                    Toast.makeText(ChangePasswordActivity.this, "Modified successfully", Toast.LENGTH_LONG).show();
                    //*************此处改写修改操作***************
                    Intent in2 = new Intent(tem,MainActivity.class);
                    tem.startActivity(in2);
                }else{
                    Toast.makeText(ChangePasswordActivity.this, "Modification failed", Toast.LENGTH_LONG).show();
                }
            }
        };
        changebut.setOnClickListener(click);
    }
}