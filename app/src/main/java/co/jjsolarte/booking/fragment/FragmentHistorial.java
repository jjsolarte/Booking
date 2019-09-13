package co.jjsolarte.booking.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import co.jjsolarte.booking.R;
import co.jjsolarte.booking.TomarFoto;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHistorial extends Fragment {


    ImageView imageView;

    public FragmentHistorial() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_historial, container, false);

        imageView = view.findViewById(R.id.fg_historia_img);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), TomarFoto.class);
                startActivity(i);
            }
        });

        return view;
    }

}
