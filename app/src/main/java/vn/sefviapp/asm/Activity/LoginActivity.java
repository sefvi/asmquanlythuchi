package vn.sefviapp.asm.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.sefviapp.asm.Database.Database;
import vn.sefviapp.asm.MainActivity;
import vn.sefviapp.asm.R;
import vn.sefviapp.asm.Utils.Utils;

public class LoginActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    Button btnLogin;
    TextView txtDangky;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        initControls();
        initEvents();
    }

    private void initEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        txtDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    public  void login(){
        if (!validate()){
            Toast.makeText(this, "Đăng nhập lỗi", Toast.LENGTH_SHORT).show();
        }else {
            statusLogin();
        }
    }
    public void statusLogin(){
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Đăng đăng nhập...");
        progressDialog.show();

        final String email = edtEmail.getText().toString();
        final String password = edtPassword.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Cursor dataUser = database.GetDate("SELECT * FROM User");
                        int kqS = 0;
                        Boolean kqD = false;
                        while (dataUser.moveToNext()){
                            String emailDb = dataUser.getString(3);
                            String passDb = dataUser.getString(2);
                            if (email.equals(emailDb) && password.equals(passDb)){
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                progressDialog.dismiss();
                                kqD = true;
                            }else {
                                kqS++;
                            }
                        }
                        if (kqS > 0 && kqD == false) {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                }, 3000);
    }
    private void initControls() {
        edtEmail = findViewById(R.id.input_email);
        edtPassword = findViewById(R.id.input_password);
        btnLogin = findViewById(R.id.btn_login);
        txtDangky = findViewById(R.id.link_signup);
        Utils.darkenStatusBar(this, R.color.colorPrimary);

        database = new Database(LoginActivity.this);
    }
    public boolean validate() {
        boolean valid = true;

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Email không đúng định dạng");
            valid = false;
        } else {
            edtEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            edtPassword.setError("Mật khẩu phải từ 4 đến 10 ký tự");
            valid = false;
        } else {
            edtPassword.setError(null);
        }

        return valid;
    }
}
