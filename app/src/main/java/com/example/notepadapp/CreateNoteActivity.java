package com.example.notepadapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateNoteActivity extends AppCompatActivity {

    public static final String EXTRA_NOTE_TITLE = "extra_note_title";
    public static final String EXTRA_NOTE_SUBTITLE = "extra_note_subtitle";

    private EditText titleEditText;
    private EditText subtitleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        ImageView imageBack = findViewById(R.id.back);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        EditText inputNote = findViewById(R.id.inputNote);
        ImageView imageShare = findViewById(R.id.imageShareNote);
        imageShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputNote.getText().toString();

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });

        titleEditText = findViewById(R.id.inputNoteTittle);
        subtitleEditText = findViewById(R.id.inputNoteSubTittle);

        ImageView imageSave = findViewById(R.id.ImageAddNoteMain);
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String subtitle = subtitleEditText.getText().toString();

                Note newNote = new Note(title, subtitle);
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                databaseHelper.addNote(newNote);
                Intent resultIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(resultIntent);
            }
        });
    }
}
