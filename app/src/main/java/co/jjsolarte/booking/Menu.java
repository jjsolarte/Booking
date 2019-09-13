package co.jjsolarte.booking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String fechaS = "1565586000000"; // Agosto 12 2019
        long f = Long.parseLong(fechaS);
        Date mDate = new Date(f);
        Toast.makeText(this, ""+mDate, Toast.LENGTH_SHORT).show();
    }

    public void onAcercaDe(View view){
        Intent i = new Intent(this,AcercaDe.class);
        startActivity(i);
    }

    public void onReservas(View v){
        Intent i = new Intent(this,ContainerActivity.class);
        startActivity(i);
    }
}
