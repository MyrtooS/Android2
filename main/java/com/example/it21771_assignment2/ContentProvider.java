package com.example.it21771_assignment2;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



public class ContentProvider extends android.content.ContentProvider {

    private LocationHelper helper;
    static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    @Override
    public boolean onCreate() {
        helper = new LocationHelper(getContext());
        uriMatcher.addURI("com.example.it21771.assignment2.ContentProvider","locations",1);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
       SQLiteDatabase db = helper.getReadableDatabase();

       int uriType = uriMatcher.match(uri);
       switch (uriType){
           case 1:
               selection = null;
               selectionArgs = null;
               break;
       }
        Cursor cursor = db.query(LocationHelper.TABLE_NAME,null,selection,selectionArgs,null,null,null,null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        int uriType = uriMatcher.match(uri);

        SQLiteDatabase db = helper.getWritableDatabase();

        long id = 0;
        switch (uriType) {
            case 1:
                id = db.insert(LocationHelper.TABLE_NAME,
                        null, values);
                Toast.makeText(getContext(),"Insert",Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: "
                        + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        uri = Uri.parse("content://com.example.it21771.assignment2.ContentProvider/locations/"+id);
        return uri;

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
