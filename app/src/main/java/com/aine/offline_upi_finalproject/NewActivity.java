package com.aine.offline_upi_finalproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashSet;

public class NewActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> contactList;
    private LinearLayout mainFeatureLayout;
    private LinearLayout recentTransactionLayout;
    private LinearLayout bankBalanceLayout;
    private LinearLayout bodyImageLayout;

    // Define a constant for the permission request
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        // Initialize views
        listView = findViewById(R.id.listView);
        SearchView searchView = findViewById(R.id.searchView);
        mainFeatureLayout = findViewById(R.id.mainfeature);
        recentTransactionLayout = findViewById(R.id.recentTransactionLayout);
        bankBalanceLayout = findViewById(R.id.bankBalanceLayout);
        bodyImageLayout = findViewById(R.id.bodyImageLayout);

        // Initialize contact list and adapter
        contactList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactList);
        listView.setAdapter(adapter);

        // Check and request permission
        checkPermission();

        // Set up search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    showMainLayouts();
                } else {
                    hideMainLayouts();
                }
                searchContacts(newText);
                return true;
            }
        });
    }




    // Method to show main layouts
    private void showMainLayouts() {
        mainFeatureLayout.setVisibility(View.VISIBLE);
        recentTransactionLayout.setVisibility(View.VISIBLE);
        bankBalanceLayout.setVisibility(View.VISIBLE);
        bodyImageLayout.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    // Method to hide main layouts
    private void hideMainLayouts() {
        mainFeatureLayout.setVisibility(View.GONE);
        recentTransactionLayout.setVisibility(View.GONE);
        bankBalanceLayout.setVisibility(View.GONE);
        bodyImageLayout.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }

    // Method to search contacts based on keyword
    private void searchContacts(String keyword) {
        contactList.clear();
        HashSet<String> uniqueContacts = new HashSet<>();
        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " LIKE ? OR " +
                        ContactsContract.CommonDataKinds.Phone.NUMBER + " LIKE ?",
                new String[]{"%" + keyword + "%", "%" + keyword + "%"},
                null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                if (!uniqueContacts.contains(contactName)) {
                    uniqueContacts.add(contactName);
                    // Add both name and number to the list
                    contactList.add(contactName + " - " + contactNumber);
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        adapter.notifyDataSetChanged();
    }



    // Method to check and request permission
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it from the user
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            // Permission is already granted, perform your operation
            // For example, proceed with contact list retrieval
        }
    }

    // Handle permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, perform your operation
                // For example, proceed with contact list retrieval
            } else {
                // Permission denied, handle accordingly
            }
        }
    }
}
