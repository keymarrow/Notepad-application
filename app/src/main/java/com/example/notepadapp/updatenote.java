package com.example.notepadapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class updatenote extends AppCompatActivity {

    public static final String EXTRA_NOTE_TITLE = "extra_note_title";
    public static final String EXTRA_NOTE_SUBTITLE = "extra_note_subtitle";
    public static final String EXTRA_NOTE_CONTENT = "extra_note_content"; // Add content key

    private EditText titleEditText;
    private EditText subtitleEditText;
    private EditText inputNote; // Add inputNote field
    private int noteId;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatenote);

        ImageView imageBack = findViewById(R.id.back);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        inputNote = findViewById(R.id.inputNote); // Initialize inputNote field

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

        noteId = getIntent().getIntExtra("id", 0);
        String title = getIntent().getStringExtra("title");
        String subtitle = getIntent().getStringExtra("subtitle");
        content = getIntent().getStringExtra("content"); // Retrieve content

        titleEditText.setText(title);
        subtitleEditText.setText(subtitle);
        inputNote.setText(content); // Set content in inputNote field

        ImageView updateBtn = findViewById(R.id.imageUpdateNote);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEditText.getText().toString();
                String subtitle = subtitleEditText.getText().toString();
                String content = inputNote.getText().toString(); // Retrieve updated content

                if (!subtitle.isEmpty() && !title.isEmpty()) {
                    DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                    db.updateNote(new Note(noteId, title, subtitle)); // Update note with new content

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        ImageView deleteBtn = findViewById(R.id.imageDelete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String subtitle = subtitleEditText.getText().toString();

                DatabaseHelper db = new DatabaseHelper(getApplicationContext());

                db.deleteNote(new Note(noteId,title,content));


                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
