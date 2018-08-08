package donald.com.godlife.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.valdesekamdem.library.mdtoast.MDToast;

import donald.com.godlife.R;

public class AdminLogin extends AppCompatActivity {

    private EditText uEmail;
    private EditText uPassword;
    private Button login;

    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        uEmail = findViewById(R.id.etLoginEmail);
        uPassword = findViewById(R.id.etLoginPassword);
        login = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        if (mAuth.getCurrentUser() != null) {
            startActivity(new android.content.Intent(AdminLogin.this, LiveUpdates.class));
            finish();
        }        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = uEmail.getText().toString().trim();
                String password = uPassword.getText().toString().trim();

                if (email.isEmpty()){
                    uEmail.setError("email empty");
                    uEmail.setFocusable(true);
                } else if (password.isEmpty()){
                    uPassword.setError("password empty");
                    uPassword.setFocusable(true);
                } else {
                    mDialog.setMessage("Signing admin in....");
                    mDialog.show();

                    mAuth.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(AdminLogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                MDToast.makeText(getApplicationContext(),"Login Successful",
                                        MDToast.LENGTH_LONG,MDToast.TYPE_SUCCESS).show();
                                Intent updates = new Intent(AdminLogin.this, LiveUpdates.class);
                                startActivity(updates);
                                finish();
                            } else{
                                MDToast.makeText(getApplicationContext(),"Incorrect email/password",
                                        MDToast.LENGTH_LONG,MDToast.TYPE_ERROR).show();

                            }

                        }
                    });

                }
            }
        });


    }
}
