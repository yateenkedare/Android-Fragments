/*
    InClass 08
    AddMoviesFragment.java
    Yateen Kedare | Rajdeep Rao
 */

package com.example.yatee.favouritemovies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class AddMovieFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public AddMovieFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_movie, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setSelection(0);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        final EditText name=(EditText) view.findViewById(R.id.nameEditText);
        final EditText description=(EditText) view.findViewById(R.id.descriptionValue);
        final EditText year=(EditText) view.findViewById(R.id.year);
        final EditText imdb=(EditText) view.findViewById(R.id.imdb);
        final Spinner genre=(Spinner) view.findViewById(R.id.spinner);
        final SeekBar rating=(SeekBar) view.findViewById(R.id.seekBar);
        final TextView rat = (TextView)view.findViewById(R.id.ratingTV);

        genre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerVal= parent.getItemAtPosition(position).toString();
                Log.d("Demo",spinnerVal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        rating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int seekbarVal=progress;
                rat.setText(Integer.toString(seekbarVal));
                Log.d("Seekbar", Integer.toString(seekbarVal));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        view.findViewById(R.id.addMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String movieName, movieDescription, movieGenre, movieIMDB,movieYear, movieRating;


                movieName=name.getText().toString();
                movieDescription=description.getText().toString();
                movieGenre=genre.getSelectedItem().toString();
                movieIMDB=imdb.getText().toString();
                movieYear=year.getText().toString();
                movieRating=Integer.toString(rating.getProgress());


                if(movieName.isEmpty() || movieDescription.isEmpty() || movieYear.isEmpty() || movieRating.equalsIgnoreCase("0") || movieGenre.equalsIgnoreCase("Select Genre"))
                {
                    Toast.makeText(getActivity(), "Invlaid Input", Toast.LENGTH_LONG).show();
                }
                else{
                    MovieClass m= new MovieClass(movieName, movieDescription, movieGenre, movieIMDB, movieYear, movieRating  );
                    mListener.onFragmentInteraction(m);
                    Toast.makeText(getActivity(),"Added "+m.getName(),Toast.LENGTH_LONG).show();
                }

            }
        });







        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(MovieClass movie);
    }
}
