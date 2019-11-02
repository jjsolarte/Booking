package co.jjsolarte.booking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

public class Menu extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    ImageView img;
    private FirebaseAuth myAuth;
    GoogleApiClient googleApiClient;

    GoogleSignInApi googleSignInApi;

    int RC_SIGN_IN = 9001;

    //SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        img = findViewById(R.id.menuImg);
        myAuth = FirebaseAuth.getInstance();

        String fechaS = "1565586000000"; // Agosto 12 2019
        long f = Long.parseLong(fechaS);
        Date mDate = new Date(f);
        Toast.makeText(this, ""+mDate, Toast.LENGTH_SHORT).show();

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAuth.signOut();
                Intent i = new Intent(Menu.this,Login.class);
                startActivity(i);
                Toast.makeText(Menu.this, "Sesion Finalizada", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        //mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }


    public void onAcercaDe(View view){
        Intent i = new Intent(this,AcercaDe.class);
        startActivity(i);
    }

    public void onReservas(View v){
        Intent i = new Intent(this,ContainerActivity.class);
        startActivity(i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
