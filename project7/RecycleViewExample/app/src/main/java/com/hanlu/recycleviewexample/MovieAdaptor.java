package com.hanlu.recycleviewexample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class MovieAdaptor extends RecyclerView.Adapter<MovieAdaptor.MovieViewHolder> {

    private ArrayList<Movie> movies;

    public MovieAdaptor() {
        movies = new ArrayList<>();
    }

    public void addMovie(String title, String genre, String year) {
        Movie movie = new Movie(title, genre, year);
        movies.add(movie);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list, parent, false);
        return new MovieViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        Movie movie = movies.get(i);
        movieViewHolder.title.setText(movie.getTitle());
        movieViewHolder.genre.setText(movie.getGenre());
        movieViewHolder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView title, genre, year;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            genre = itemView.findViewById(R.id.movie_genre);
            year = itemView.findViewById(R.id.movie_year);
        }
    }
}
