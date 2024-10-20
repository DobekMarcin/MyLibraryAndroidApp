package md.rein.mylibrary;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnAllBooks, btnAlreadyRead, btnWantToRead,btnCurrentlyReading, btnFavorite, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnAllBooks.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
            startActivity(intent);
        });

        btnAlreadyRead.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,AlreadyReadBookActivity.class);
            startActivity(intent);
        });

        btnWantToRead.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,WantToReadActivity.class);
            startActivity(intent);
        });

        btnFavorite.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,FavouriteBooksActivity.class);
            startActivity(intent);
        });

        btnCurrentlyReading.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,CurrentlyReadActivity.class);
            startActivity(intent);
        });

btnAbout.setOnClickListener(view->{
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(R.string.app_name);
    builder.setMessage("Designed and developed with love by Marcin\n"+
            "Check my website or more awesome application:");
    builder.setNegativeButton("Dismiss", (dialogInterface, i) -> {

    });
    builder.setPositiveButton("Visit",(dialogInterface,i)->{
    Intent intent = new Intent(MainActivity.this,WebsiteActivity.class);
    intent.putExtra("url","https://google.com/");
    startActivity(intent);

    });
    builder.create().show();
});


        Utils.getInstance(this);

    }

    private void initViews() {
    btnAllBooks = findViewById(R.id.btnAllBooks);
    btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
    btnWantToRead = findViewById(R.id.btnWantToRead);
    btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
    btnFavorite = findViewById(R.id.btnFavorite);
    btnAbout = findViewById(R.id.btnAbout);

    }
}