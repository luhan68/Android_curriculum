package com.hanlu.recycleviewexample;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.concurrent.Callable;

public class MovieFragment extends DialogFragment {
    private EditText title, year;
    private ConstraintLayout genreLayout;
    private Button save, cancel;
    private CommunicateWithActivity communicateWithActivity;

    public interface CommunicateWithActivity {
        void createMovie(String title, String year, String genre);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_movie, container, false);
        title = view.findViewById(R.id.movie_title_edit);
        year = view.findViewById(R.id.movie_year_edit);
        save = view.findViewById(R.id.add_movie_btn);
        genreLayout = view.findViewById(R.id.movie_genre_layout);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMovie();
            }
        });
        cancel = view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }

    public void addMovie() {
        String title = this.title.getText().toString();
        String year = this.year.getText().toString();
        StringBuilder genre = new StringBuilder();
        for (int i = 0; i < genreLayout.getChildCount(); i++) {
            if (genreLayout.getChildAt(i) instanceof CheckBox) {
                CheckBox checkBox = ((CheckBox) genreLayout.getChildAt(i));
                if (checkBox.isChecked()) {
                    genre.append(checkBox.getText().toString());
                    genre.append(" & ");
                }
                // check whether its the last.
            }
        }

        if (genre.length() >= 2 && genre.charAt(genre.length() - 2) == '&') {
            genre.delete(genre.length() - 2, genre.length());
        }


        if (title.isEmpty() || year.isEmpty() || genre.toString().isEmpty() || year.length() != 4) {
            alertView("Error", "Please fill in all fields", "Try Again!", getContext());
        } else {
            communicateWithActivity = (CommunicateWithActivity) getActivity();
            communicateWithActivity.createMovie(title, year, genre.toString());
            dismiss();
        }
    }

    private void alertView(String title, String message, String buttonName, Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(buttonName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
    }
}
