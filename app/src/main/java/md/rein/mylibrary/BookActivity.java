package md.rein.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private TextView txtBookName, txtAuthor, txtPages, txtDescription,webSite;
    private Button btnAddToWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorites;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initView();
        int bookIdd = getIntent().getIntExtra(BOOK_ID_KEY, -1);
        Book incomingBookk = Utils.getInstance(this).getBookById(bookIdd);

        webSite.setOnClickListener(view->{


            Intent intent = new Intent(this,WebsiteActivity.class);
            intent.putExtra("url",incomingBookk.getWebsite());
            startActivity(intent);

        });


        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if (incomingBook != null) {
                    setData(incomingBook);


                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                }
            }
        }
    }

    private void handleFavoriteBooks(final Book book) {

        ArrayList<Book> addToFavourite = Utils.getInstance(this).getFavoriteBooks();

        boolean existInFavouriteBooks = false;

        for (Book b : addToFavourite) {
            if (b.getId() == book.getId()) {
                existInFavouriteBooks = true;
            }
        }

        if(existInFavouriteBooks==true){
            btnAddToFavorites.setEnabled(false);
        }else{
            btnAddToFavorites.setOnClickListener(view -> {
                if(Utils.getInstance(BookActivity.this).addToFavourites(book)){
                    Intent intent = new Intent(BookActivity.this,FavouriteBooksActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(BookActivity.this,"Something worng!!",Toast.LENGTH_SHORT).show();
                    //TODO: navigate the user
                }
            });
        }

    }

    private void handleCurrentlyReadingBooks(Book book) {
        ArrayList<Book> addToCurrentlyRead = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean existInCurrentlyReadBooks = false;

        for (Book b : addToCurrentlyRead) {
            if (b.getId() == book.getId()) {
                existInCurrentlyReadBooks = true;
            }
        }

        if(existInCurrentlyReadBooks==true){
            btnAddToCurrentlyReading.setEnabled(false);
        }else{
            btnAddToCurrentlyReading.setOnClickListener(view -> {
                if(Utils.getInstance(BookActivity.this).addToCurrentlyRead(book)){
                    Intent intent = new Intent(BookActivity.this,CurrentlyReadActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(BookActivity.this,"Something worng!!",Toast.LENGTH_SHORT).show();
                    //TODO: navigate the user
                }
            });
        }
    }

    private void handleWantToReadBooks(Book book) {

        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();

        boolean existInWantToReadBooks = false;

        for (Book b : wantToReadBooks) {
            if (b.getId() == book.getId()) {
                existInWantToReadBooks = true;
            }
        }

        if(existInWantToReadBooks==true){
            btnAddToWantToRead.setEnabled(false);
        }else{
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToWantToread(book)){
                        Intent intent = new Intent(BookActivity.this,WantToReadActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this,"Something worng!!",Toast.LENGTH_SHORT).show();
                        //TODO: navigate the user
                    }
                }
            });
        }
    }

    private void handleAlreadyRead(Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (Book b : alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                existInAlreadyReadBooks = true;
            }
        }

        if(existInAlreadyReadBooks==true){
            btnAddToAlreadyRead.setEnabled(false);
        }else{
            btnAddToAlreadyRead.setOnClickListener(view -> {
                if(Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                    Intent intent = new Intent(BookActivity.this,AlreadyReadBookActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(BookActivity.this,"Something worng!!",Toast.LENGTH_SHORT).show();
            //TODO: navigate the user
                }
            });
        }

    }

    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this).asBitmap().load(book.getImageUrl())
                .into(bookImage);
        webSite.setText(String.valueOf(book.getWebsite()));

    }

    private void initView() {
        txtBookName = findViewById(R.id.txtBookNameEdit);
        txtAuthor = findViewById(R.id.txtAuthorEdit);
        txtPages = findViewById(R.id.txtpagesEdit);
        txtDescription = findViewById(R.id.txtLongDescriptionEdit);

        btnAddToAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnAddToCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnAddToFavorites = findViewById(R.id.btnAddToFavorites);
        btnAddToWantToRead = findViewById(R.id.btnWantToRead);

        bookImage = findViewById(R.id.imgBook);

        webSite = findViewById(R.id.webSite);
    }
}