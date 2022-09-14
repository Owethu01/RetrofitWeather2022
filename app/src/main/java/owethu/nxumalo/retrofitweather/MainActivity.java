package owethu.nxumalo.retrofitweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

   // @Override
   // protected void onCreate(Bundle savedInstanceState) {
       // super.onCreate(savedInstanceState);

       // setContentView(R.layout.activity_main);
        EditText Et_Username;
        EditText Et_Password;
        Button Btn_Login;
        Button Btn_Signup;
        private FirebaseAuth mAuth;
        private FirebaseUser currentUser;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mAuth = FirebaseAuth.getInstance();
            // check if person is not logged in, send them to login.
            currentUser = mAuth.getCurrentUser();
            Et_Username = findViewById(R.id.et_username);
            Et_Password = findViewById(R.id.et_password);
            Btn_Login = findViewById(R.id.btn_login);
            Btn_Signup = findViewById(R.id.btn_signup);
            Btn_Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = Et_Username.getText().toString();
                    String password = Et_Password.getText().toString();
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(MainActivity.this, "Welcome " + email, Toast.LENGTH_SHORT).show();
                                        currentUser = mAuth.getCurrentUser();
//                                    Intent next = new Intent(MainActivity.this, UserInfo.class);
//                                    startActivity(next);
//                                    finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                        task.getException();
                                    }

                                    // ...
                                }
                            });
                }
            });
            Btn_Signup.setOnClickListener(new View.OnClickListener() { // create new user
                @Override
                public void onClick(View view) {

                    String email = Et_Username.getText().toString();
                    String password = Et_Password.getText().toString();
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(MainActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                        currentUser = mAuth.getCurrentUser();
                                        // go to next page if successful
//                                    Intent next = new Intent(MainActivity.this, UserInfo.class);
//                                    startActivity(next);
//                                    finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                        task.getException();
                                    }
                                    // ...
                                }
                            });
                }
            });
        }
    }

