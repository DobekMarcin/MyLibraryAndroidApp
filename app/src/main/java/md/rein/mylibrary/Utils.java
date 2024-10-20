package md.rein.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS = "already_red_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading-books";
    private static final String FAVOURITE_BOOKS = "favourite_books";

    private static Utils instance;

    private SharedPreferences sharedPreferences;

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> wantToReadBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> favoriteBooks;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if (getAllBooks() == null) {
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (getAlreadyReadBooks() == null) {
            editor.putString(ALREADY_READ_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getWantToReadBooks() == null) {
            editor.putString(WANT_TO_READ_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getCurrentlyReadingBooks() == null) {
            editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getFavoriteBooks() == null) {
            editor.putString(FAVOURITE_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

    }

    private void initData() {

        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(1, "1q84", "Haruki Murakami", 1250, "https://s.lubimyczytac.pl/upload/books/5128000/5128711/1184391-352x500.jpg",
                "A word od maddening brillance", "Long description Long description Long description Long description Long description Long description Long description Long description Long description " +
                "Long description Long description Long description Long description Long description Long description Long description Long description Long description A word od maddening brillance\",\"Long description Long description Long description Long description Long description Long description Long description Long description Long description \" +\n" +
                "                \"Long description Long description Long description Long description Long description Long description Long description Long description Long description", "https://onet.pl/"));

        books.add(new Book(2, "1q84", "Haruki Murakami", 1250, "https://s.lubimyczytac.pl/upload/books/5128000/5128711/1184391-352x500.jpg",
                "A word od maddening brillance", "Long description Long description Long description Long description Long description Long description Long description Long description Long description " +
                "Long description Long description Long description Long description Long description Long description Long description Long description Long description A word od maddening brillance\",\"Long description Long description Long description Long description Long description Long description Long description Long description Long description \" +\n" +
                "                \"Long description Long description Long description Long description Long description Long description Long description Long description Long description", "https://google.com/"));


        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();

    }

    public static Utils getInstance(Context context) {
        if (null != instance)
            return instance;
        else {
            instance = new Utils(context);
            return instance;
        }
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();

        ArrayList<Book> boks = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return boks;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> boks = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS, null), type);
        return boks;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> boks = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS, null), type);
        return boks;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> boks = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null), type);
        return boks;
    }

    public  ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> boks = gson.fromJson(sharedPreferences.getString(FAVOURITE_BOOKS, null), type);
        return boks;
    }

    public Book getBookById(int id) {
        ArrayList<Book> books = getAllBooks();
        if(null != books){
            for (Book b : books) {
                if (b.getId() == id) {
                    return b;
                }
            }
        }

        return null;
    }

    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if(null!=books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWantToread(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if(null!=books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavourites(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if(null!=books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVOURITE_BOOKS);
                editor.putString(FAVOURITE_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyRead(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(null!=books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromAlreadyRead(Book book) {
       ArrayList<Book> books = getAlreadyReadBooks();
       if(null != books){
               for(Book b : books){
                   if(b.getId() == book.getId()){
                       if(books.remove(b)){
                           Gson gson = new Gson();
                           SharedPreferences.Editor editor = sharedPreferences.edit();
                           editor.remove(ALREADY_READ_BOOKS);
                           editor.putString(ALREADY_READ_BOOKS,gson.toJson(books));
                           editor.commit();
                           return true;
                       }
                   }
               }

       }
       return  false;
    }

    public boolean removeFromWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if(null != books){
            for(Book b : books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }

        }
        return  false;
    }

    public boolean removeFromCurrentlyRead(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(null != books){
            for(Book b : books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }

        }
        return  false;
    }

    public boolean removeFromFavourites(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if(null != books){
            for(Book b : books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVOURITE_BOOKS);
                        editor.putString(FAVOURITE_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }

        }
        return  false;
    }

}
