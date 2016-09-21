package uk.samdtaylor.bookkeep;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;

import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.integration.android.IntentIntegrator;

import org.json.JSONObject;

import com.android.volley.*;

import android.view.View.OnClickListener;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.integration.android.IntentIntegrator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.*;

import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class scanActivity extends AppCompatActivity implements OnClickListener{

    private Button scanBtn;
    private TextView formatTxt, contentTxt, bookTitleView, bookAuthorView, bookPublishedView, bookDescriptionView, bookPageCountView;
    private ProgressDialog pDialog;
    private static String TAG = scanActivity.class.getSimpleName();
    // temporary string to show the parsed response
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        formatTxt = (TextView)findViewById(R.id.scan_format2);
        contentTxt = (TextView)findViewById(R.id.scan_content2);
        //bookTitleView = (TextView)findViewById(R.id.scan_TitleView);
        //bookAuthorView = (TextView)findViewById(R.id.scan_AuthorView);
        //bookPublishedView = (TextView)findViewById(R.id.scan_PublishedView);
        //bookDescriptionView = (TextView)findViewById(R.id.scan_DescriptionView);
        //bookPageCountView = (TextView)findViewById(R.id.scan_PageCountView);
        scanBtn = (Button)findViewById(R.id.scanButton);
        scanBtn.setOnClickListener(this);
    }

    public void onClick(View v){
        //respond to clicks
        if(v.getId()==R.id.scanButton){
            //scan
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //setContentView(R.layout.content_scan_post);

        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            //set TextViews
            formatTxt.setText("Scan Format: " + scanFormat);
            contentTxt.setText("Scan Content: " + scanContent);

            //urlControl isbnurl = new urlControl();
            //isbnurl.setUrl(scanContent);
            //String url = isbnurl.getUrl();

            //makeJsonObjectRequestScanned(url);

        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /*private void makeJsonObjectRequestScanned(String url) {
        showpDialog();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                parser parser = new parser();
                String bookID = parser.parseBookId(response);
                String title  = parser.parseBookTitle(response);
                String author = parser.parseBookAuthors(response);
                String published = parser.parseBookPublishedDate(response);
                String description = parser.parseBookDescription(response);
                int pageCount = parser.parseBookPages(response);

                bookTitleView.setText("Title: " + title);
                bookAuthorView.setText("Author: " + author);
                bookPublishedView.setText("Published: " + published);
                bookDescriptionView.setText("Description: " + description);
                bookPageCountView.setText("Page Count: " + pageCount);

                hidepDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }*/

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }



}
