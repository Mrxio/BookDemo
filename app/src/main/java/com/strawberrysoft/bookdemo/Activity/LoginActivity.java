package com.strawberrysoft.bookdemo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.strawberrysoft.bookdemo.R;

public class LoginActivity extends AppCompatActivity {

    private EditText et_id;
    private EditText et_password;
    private Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        et_id = (EditText) findViewById(R.id.et_login_id);
        et_password = (EditText) findViewById(R.id.et_login_password);
        bt_login = (Button) findViewById(R.id.bt_login_login);
        bt_login.setEnabled(false);
        et_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(et_id.getText().toString())||TextUtils.isEmpty(et_password.getText().toString())){
                    bt_login.setEnabled(false);
                }else {
                    bt_login.setEnabled(true);
                }
            }
        });
        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(et_id.getText().toString())||TextUtils.isEmpty(et_password.getText().toString())){
                    bt_login.setEnabled(false);
                }else {
                    bt_login.setEnabled(true);
                }
            }
        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
