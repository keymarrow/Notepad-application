package com.example.notepadapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, NoteAdapter.OnClickListener {

    public static final int REQUEST_CODE_CREATE_NOTE = 1;
    public static final int REQUEST_CODE_UPDATE_NOTE = 2;

    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private List<Note> noteList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageAddNoteMain = findViewById(R.id.ImageAddNoteMain);
        imageAddNoteMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(getApplicationContext(), CreateNoteActivity.class),
                        REQUEST_CODE_CREATE_NOTE
                );
            }
        });

        ImageView imageSetting = findViewById(R.id.imageSetting);
        imageSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,settings.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.notesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);
        noteList.addAll(databaseHelper.getAllNotes());
        adapter = new NoteAdapter(noteList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onClick(int position, Note note) {
        Toast.makeText(this, "Position: " + position + " id: " + note.getId(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, updatenote.class);
        intent.putExtra("id", note.getId());
        intent.putExtra("title", note.getTitle());
        intent.putExtra("subtitle", note.getSubtitle());
        intent.putExtra("body", note.getBody());
        startActivityForResult(intent, REQUEST_CODE_UPDATE_NOTE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CREATE_NOTE && resultCode == RESULT_OK) {
            // Note created, refresh the list
            noteList.clear();
            noteList.addAll(databaseHelper.getAllNotes());
            adapter.notifyDataSetChanged();
        } else if (requestCode == REQUEST_CODE_UPDATE_NOTE && resultCode == RESULT_OK) {
            // Note updated, refresh the list
            noteList.clear();
            noteList.addAll(databaseHelper.getAllNotes());
            adapter.notifyDataSetChanged();
        }
    }
}

