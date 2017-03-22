/*
    InClass 08
    MainActivity.java
    Yateen Kedare | Rajdeep Rao
 */
package com.example.yatee.favouritemovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyFavMoviesFragment.OnFragmentInteractionListener, AddMovieFragment.OnFragmentInteractionListener,EditMovieFragment.OnFragmentInteractionListener, RatingFragment.OnFragmentInteractionListener, YearFragment.OnFragmentInteractionListener{
    ArrayList<MovieClass> movieClassArrayList;
    private  int edit_position = 0;
    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0){
            this.setTitle("My Favourite Movies");
            getSupportFragmentManager().popBackStack();
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieClassArrayList= new ArrayList<>();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new MyFavMoviesFragment(), "MYFAVMOV_TAG")
                .commit();

    }

    @Override
    public void onFragmentInteraction(String s) {
        switch (s) {
            case "ADD":
                this.setTitle("Add Movie");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new AddMovieFragment(), "ADDMOVIE_TAG")
                        .addToBackStack(null)
                        .commit();
                break;
            case "EDIT":
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                final String[] nameArray = new String[movieClassArrayList.size()];
                for (int i = 0; i < movieClassArrayList.size(); i++) {
                    nameArray[i] = movieClassArrayList.get(i).getName();
                    Log.d("Edit", nameArray[i]);
                }
                builder.setTitle("Pick a Movie")
                        .setItems(nameArray, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.this.setTitle("Edit Movie");
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.container, new EditMovieFragment(), "EDITMOVIE_TAG")
                                        .addToBackStack(null)
                                        .commit();
                                edit_position = which;
                            }
                        });

                final AlertDialog alert = builder.create();
                alert.show();


                break;
            case "DELETE":
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);

                final String[] nameArray1 = new String[movieClassArrayList.size()];
                for (int i = 0; i < movieClassArrayList.size(); i++) {
                    nameArray1[i] = movieClassArrayList.get(i).getName();
                    Log.d("Edit", nameArray1[i]);
                }
                builder1.setTitle("Pick a Movie")
                        .setItems(nameArray1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"Deleated "+ movieClassArrayList.get(which).getName(),Toast.LENGTH_SHORT).show();
                                movieClassArrayList.remove(which);

                            }
                        });

                final AlertDialog alert1 = builder1.create();
                alert1.show();
                break;
            case "YEAR":
                if(!movieClassArrayList.isEmpty()){

                    MainActivity.this.setTitle("Movies By Year");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new YearFragment(), "YEAR_TAG")
                            .addToBackStack(null)
                            .commit();
                }
                else {
                    Toast.makeText(MainActivity.this, "Movie List Empty", Toast.LENGTH_SHORT).show();
                }
                break;
            case "RATING":
            if(!movieClassArrayList.isEmpty()){
                MainActivity.this.setTitle("Movies By Rating");

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new RatingFragment(), "RATING_TAG")
                        .addToBackStack(null)
                        .commit();
            }
            else {
                Toast.makeText(MainActivity.this, "Movie List Empty", Toast.LENGTH_SHORT).show();
            }
                break;
        }
    }

    @Override
    public void onFragmentInteraction(MovieClass movie) {
        movieClassArrayList.add(movie);
        onBackPressed();
        Log.d("Movie ADDed", movieClassArrayList.toString());
    }

    @Override
    public void onFragmentInteractionEdit(MovieClass m, int pos) {
        movieClassArrayList.set(pos,m);
        onBackPressed();
    }

    @Override
    public void onEditFraagmentCreated() {
        EditMovieFragment editMovieFragment = (EditMovieFragment) getSupportFragmentManager().findFragmentByTag("EDITMOVIE_TAG");
        if(editMovieFragment != null) {
            editMovieFragment.initMovieData(movieClassArrayList.get(edit_position), edit_position);
        }
        else
            Log.d("EDit Fragmet","NULL");
    }

    @Override
    public void onRatingFragmentCreated() {
        RatingFragment f = (RatingFragment) getSupportFragmentManager().findFragmentByTag("RATING_TAG");
        if(f != null) {
            f.initMovieData(movieClassArrayList);
        }
        else
            Log.d("EDit Fragmet","NULL");

    }

    @Override
    public void onRatingCompleted() {
        onBackPressed();
    }

    @Override
    public void onYearFragmentCreated() {
        YearFragment f = (YearFragment ) getSupportFragmentManager().findFragmentByTag("YEAR_TAG");
        if(f != null) {
            f.initMovieData(movieClassArrayList);
        }
        else
            Log.d("EDit Fragmet","NULL");

    }

    @Override
    public void onYearCompleted() {
        onBackPressed();
    }
}
