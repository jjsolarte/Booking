package co.jjsolarte.booking.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import co.jjsolarte.booking.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPerfil extends Fragment {


    public FragmentPerfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_perfil, container, false);
    }

}
