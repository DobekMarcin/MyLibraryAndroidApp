package md.rein.mylibrary;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WantToReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_want_to_read);

        RecyclerView recyclerView = findViewById(R.id.booksRecView);

        BookRecViewAdapter adapter = new BookRecViewAdapter(this,"wantToRead");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance(this).getWantToReadBooks());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}