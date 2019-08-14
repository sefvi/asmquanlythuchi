package vn.sefviapp.asm.Activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.sefviapp.asm.Database.Database;
import vn.sefviapp.asm.MainActivity;
import vn.sefviapp.asm.Modle.User;
import vn.sefviapp.asm.R;
import vn.sefviapp.asm.Utils.Utils;

public class RegisterActivity extends AppCompatActivity {
    EditText input_email_re, input_password_re, input_RePassword_re;
    Button btn_register;
    TextView link_login;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initControls();
        initEvents();

    }

    private void initEvents() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void register(){
        if (!validate()){
            Toast.makeText(this, "Đăng ký lỗi", Toast.LENGTH_SHORT).show();
        }else {
           statusRegister();
        }

    }

    private void initControls() {
        input_email_re = findViewById(R.id.input_email_re);
        input_password_re = findViewById(R.id.input_password_re);
        input_RePassword_re = findViewById(R.id.input_RePassword_re);
        btn_register = findViewById(R.id.btn_register);
        link_login = findViewById(R.id.link_login);

        Utils.darkenStatusBar(this, R.color.colorPrimary);

        database = new Database(RegisterActivity.this);
    }

    private void statusRegister(){
        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Đăng đăng ký...");
        progressDialog.show();

        final String email = input_email_re.getText().toString();
        final String password = input_password_re.getText().toString();
        database.addUser(new User(email ,password));
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Toast.makeText(RegisterActivity.this, "" + email + password, Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 3000);
    }

    public boolean validate() {
        boolean valid = true;

        String email = input_email_re.getText().toString();
        String password = input_password_re.getText().toString();
        String rePassword = input_RePassword_re.getText().toString();


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            input_email_re.setError("Email không đúng định dạng");
            valid = false;
        } else {
            input_email_re.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            input_password_re.setError("Mật khẩu phải từ 4 đến 10 ký tự");
            valid = false;
        } else {
            input_password_re.setError(null);
        }

        if (!rePassword.equals(password)){
            input_RePassword_re.setError("Mật khẩu không trùng khớp");
            valid = false;
        }else {
            input_RePassword_re.setError(null);
        }

        return valid;
    }
}
