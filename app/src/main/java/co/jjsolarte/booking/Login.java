package co.jjsolarte.booking;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText edtCorreo, edtContraseña;
    Button btnLogin, btnRegistro;

    private FirebaseAuth myAuth;
    private FirebaseAuth.AuthStateListener myAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myAuth = FirebaseAuth.getInstance();
        inicializer();

        myAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = myAuth.getCurrentUser();
                if (currentUser!=null){
                    Toast.makeText(Login.this, "Usuario Logeado", Toast.LENGTH_SHORT).show();
                    goContainerMain();
                }else {
                    Toast.makeText(Login.this, "Usuario no Logeado", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void goContainerMain() {
        Intent i = new Intent(Login.this,Menu.class);
        startActivity(i);
    }

    private void inicializer() {
        edtCorreo = findViewById(R.id.loginCorreo);
        edtContraseña = findViewById(R.id.loginContraseña);
        btnLogin = findViewById(R.id.loginBtnLogin);
        btnRegistro = findViewById(R.id.loginBtnRegistrar);
    }

    public void onLogin(View v){

    }

    public void onRegister(View v){

        String correo = edtCorreo.getText().toString();
        String contraseña = edtContraseña.getText().toString();

        myAuth.createUserWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this, "Usuario Creado Exitosamente", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = myAuth.getCurrentUser();
                            goContainerMain();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        myAuth.addAuthStateListener(myAuthListener);
    }
}
