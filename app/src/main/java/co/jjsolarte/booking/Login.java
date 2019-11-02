package co.jjsolarte.booking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    EditText edtCorreo, edtContraseña;
    Button btnLogin, btnRegistro;

    private FirebaseAuth myAuth;
    private FirebaseAuth.AuthStateListener myAuthListener;

    GoogleApiClient googleApiClient;

    GoogleSignInApi googleSignInApi;

    int RC_SIGN_IN = 9001;

    SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myAuth = FirebaseAuth.getInstance();
        signInButton = findViewById(R.id.sign_in_button);

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

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(this, "Error al iniciar Sesion", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        myAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this, "Inicio Exitoso", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = myAuth.getCurrentUser();
                            //cambiarEstado(user);
                            goContainerMain();
                            finish();
                        } else {
                            Toast.makeText(Login.this, "Error al Iniciar con Google", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void goContainerMain() {
        Intent i = new Intent(Login.this,Menu.class);
        startActivity(i);
        finish();
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
