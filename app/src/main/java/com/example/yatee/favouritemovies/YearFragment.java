/*
    InClass 08
    YearFragment.java
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class YearFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    ArrayList<MovieClass> movieList;
    int pos = 0;

    public YearFragment() {
    }

    public void initMovieData(ArrayList<MovieClass> movieClasses){
        this.movieList = movieClasses;
        View view = getView();
        pos = 0;
        Collections.sort(movieList, new Comparator<MovieClass>() {
            @Override
            public int compare(MovieClass o1, MovieClass o2) {
                if(Integer.parseInt(o1.getYear())<Integer.parseInt(o2.getYear()))
                    return -1;
                else if(Integer.parseInt(o1.getYear())>Integer.parseInt(o2.getYear()))
                    return 1;
                else
                    return 0;
            }
        });

        if(movieList.isEmpty()){
            Toast.makeText(getActivity(), "Empty List",Toast.LENGTH_LONG).show();
            mListener.onYearCompleted();
        }
        else {
            Log.d("Rating", movieList.toString());

            final TextView title = (TextView) view.findViewById(R.id.titleValue1);
            title.setText(movieList.get(pos).getName());

            final TextView description = (TextView) view.findViewById(R.id.descriptionValue1);
            description.setText(movieList.get(pos).getDescription());

            final TextView genre = (TextView) view.findViewById(R.id.genreValue1);
            genre.setText(movieList.get(pos).getGenre());

            final TextView rating = (TextView) view.findViewById(R.id.ratingValue1);
            rating.setText(movieList.get(pos).getRating() + "/5");

            final TextView year = (TextView) view.findViewById(R.id.yearValue1);
            year.setText(movieList.get(pos).getYear());

            final TextView imdb = (TextView) view.findViewById(R.id.imdbVal1);
            imdb.setText(movieList.get(pos).getImdb());


            view.findViewById(R.id.next1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pos < movieList.size() - 1) {
                        pos++;
                        title.setText(movieList.get(pos).getName());
                        description.setText(movieList.get(pos).getDescription());
                        year.setText(movieList.get(pos).getYear());
                        imdb.setText(movieList.get(pos).getImdb());
                        rating.setText(movieList.get(pos).getRating() + "/5");
                        genre.setText(movieList.get(pos).getGenre());
                    } else {
                        pos = movieList.size() - 1;
                        Toast.makeText(getActivity(), "End of your movies", Toast.LENGTH_LONG).show();
                    }


                }
            });

            view.findViewById(R.id.back1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pos > 0) {
                        pos--;
                        title.setText(movieList.get(pos).getName());
                        description.setText(movieList.get(pos).getDescription());
                        year.setText(movieList.get(pos).getYear());
                        imdb.setText(movieList.get(pos).getImdb());
                        rating.setText(movieList.get(pos).getRating() + "/5");
                        genre.setText(movieList.get(pos).getGenre());
                    } else {
                        pos = 0;
                        Toast.makeText(getActivity(), "End of your movies", Toast.LENGTH_LONG).show();
                    }


                }

            });
            view.findViewById(R.id.start1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pos = 0;
                    title.setText(movieList.get(pos).getName());
                    description.setText(movieList.get(pos).getDescription());
                    year.setText(movieList.get(pos).getYear());
                    imdb.setText(movieList.get(pos).getImdb());
                    rating.setText(movieList.get(pos).getRating() + "/5");
                    genre.setText(movieList.get(pos).getGenre());
                }
            });

            view.findViewById(R.id.last1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pos = movieList.size() - 1;
                    title.setText(movieList.get(pos).getName());
                    description.setText(movieList.get(pos).getDescription());
                    year.setText(movieList.get(pos).getYear());
                    imdb.setText(movieList.get(pos).getImdb());
                    rating.setText(movieList.get(pos).getRating() + "/5");
                    genre.setText(movieList.get(pos).getGenre());
                }
            });

            view.findViewById(R.id.finish1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onYearCompleted();
                }
            });
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_year, container, false);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        mListener.onYearFragmentCreated();
        super.onActivityCreated(savedInstanceState);
    }

    public interface OnFragmentInteractionListener {
        void onYearFragmentCreated();
        void onYearCompleted();
    }
}
