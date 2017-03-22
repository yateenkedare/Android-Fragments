/*
    InClass 08
    EditMovieFragment.java
    Yateen Kedare | Rajdeep Rao
 */
package com.example.yatee.favouritemovies;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class EditMovieFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private MovieClass movie;
    private int position;


    public EditMovieFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        mListener.onEditFraagmentCreated();
        super.onActivityCreated(savedInstanceState);
    }

    public void initMovieData(MovieClass movie, int pos){
        this.movie = movie;
        this.position = pos;
        Log.d("EDIT Movie",movie.toString());

        final View view = getView();

        final TextView rat = (TextView)view.findViewById(R.id.ratingTV2);
        final EditText nameEditText=(EditText)view.findViewById(R.id.nameEditTextUpdate);
        final EditText descriptionEditText=(EditText)view.findViewById(R.id.descriptionValueUpdate);
        final Spinner genreSpinner= (Spinner) view.findViewById(R.id.spinnerUpdate);
        final EditText yearEditText=(EditText)view.findViewById(R.id.yearUpdate);
        final EditText imdbEditText=(EditText)view.findViewById(R.id.imdbUpdate);
        final SeekBar ratingValue= (SeekBar) view.findViewById(R.id.seekBarUpdate);
        nameEditText.setText(movie.getName());


        descriptionEditText.setText(movie.getDescription());



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(adapter);

        adapter.getPosition(movie.getGenre());
//TODO
        genreSpinner.setSelection(adapter.getPosition(movie.getGenre()));


        yearEditText.setText(movie.getYear());


        imdbEditText.setText(movie.getImdb());


        ratingValue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rat.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ratingValue.setProgress(Integer.parseInt(movie.getRating()));


        view.findViewById(R.id.saveChanges).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameEditText.getText().toString().isEmpty() || descriptionEditText.getText().toString().isEmpty() || yearEditText.getText().toString().isEmpty() || Integer.toString(ratingValue.getProgress()).equalsIgnoreCase("0") || genreSpinner.getSelectedItem().toString().equalsIgnoreCase("Select Genre"))
                {
                    Toast.makeText(getActivity(), "Invlaid Input", Toast.LENGTH_LONG).show();
                }
                else {
                    MovieClass m = new MovieClass(nameEditText.getText().toString(), descriptionEditText.getText().toString(), genreSpinner.getSelectedItem().toString(), imdbEditText.getText().toString(), yearEditText.getText().toString(), Integer.toString(ratingValue.getProgress()));
                    mListener.onFragmentInteractionEdit(m,position);
                }
            }
        });


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_edit_movie, container, false);

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
        void onFragmentInteractionEdit(MovieClass m, int pos);
        void onEditFraagmentCreated();
    }


}
