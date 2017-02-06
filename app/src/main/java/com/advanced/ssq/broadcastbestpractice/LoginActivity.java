package com.advanced.ssq.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ssq on 2017/2/6.
 */

public class LoginActivity extends BaseActivity{

    private EditText accountEdit;

    private EditText passwordEdit;

    private Button login;

    private SharedPreferences pref;

    private SharedPreferences.Editor editor;

    private CheckBox rememberPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.Login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        rememberPassword = (CheckBox) findViewById(R.id.remember_password);
        boolean isRemember = pref.getBoolean("remember_password",false);

        if(isRemember) {
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPassword.setChecked(true);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                if("admin".equals(account) && "123456".equals(password)){
                    editor = pref.edit();
                    if (rememberPassword.isChecked()) {
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                        editor.apply();
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "account or password is invalid",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
