package uk.samdtaylor.bookkeep;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sdtay on 29/07/2016.
 */
public class parser extends Application {

    private boolean checkDataReturn(JSONObject jsonObject) {
        boolean dataReturned = false;
        try {
            int totalItems = jsonObject.getInt("totalItems");
            if (totalItems == 0){
                dataReturned = false;
            } else {
                dataReturned = true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }finally {
            Log.d("Parser","checkDataReturn, try/catch completed successfully. Value: " + dataReturned);
        }
        return dataReturned;
    }

    public String parseBookId(JSONObject jsonObject){
        String bookID = null;
        if (checkDataReturn(jsonObject) == true) {
            try {
                // Parsing json object response
                // response will be a json object
                JSONArray items = jsonObject.getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject itemsObj = items.getJSONObject(i);
                    bookID = itemsObj.getString("id");
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Error: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            } finally {
                Log.d("Parser", "parseBookId, try/catch completed successfully. Value: " + bookID);
            }
            return bookID;
        } else {
            return "Book not found.";
        }
    }

    public String parseBookKind(JSONObject jsonObject){
        String kind = null;
        try {
            // Parsing json object response
            // response will be a json object
            JSONArray items = jsonObject.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject itemsObj = items.getJSONObject(i);
                kind = itemsObj.getString("kind");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }finally {
            Log.d("Parser","parseBookKind, try/catch completed successfully.");
        }
        return kind;
    }

    public String parseBookETag(JSONObject jsonObject){
        String eTag = null;
        try {
            // Parsing json object response
            // response will be a json object
            JSONArray items = jsonObject.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject itemsObj = items.getJSONObject(i);
                eTag = itemsObj.getString("etag");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }finally {
            Log.d("Parser","parseBookETag, try/catch completed successfully.");
        }
        return eTag;
    }

    public String parseBookSelfLink(JSONObject jsonObject){
        String selfLink = null;
        try {
            // Parsing json object response
            // response will be a json object
            JSONArray items = jsonObject.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject itemsObj = items.getJSONObject(i);
                selfLink = itemsObj.getString("selfLink");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }finally {
            Log.d("Parser","parseBookSelfLink, try/catch completed successfully.");
        }
        return selfLink;
    }

    // Parsing volume info from google books API.
    public String parseBookTitle(JSONObject jsonObject){
        String title = null;
        if (checkDataReturn(jsonObject) == true) {
            try {
                // Parsing json object response
                // response will be a json object
                JSONArray items = jsonObject.getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject itemsObj = items.getJSONObject(i);
                    JSONObject volumeInfoObj = itemsObj.getJSONObject("volumeInfo");
                    title = volumeInfoObj.getString("title");
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Error: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            } finally {
                Log.d("Parser", "parseBookTitle, try/catch completed successfully.");
            }
            return title;
        } else {
            return "Book not found.";
        }
    }

    public String parseBookAuthors(JSONObject jsonObject){
        String authorMain = null;
        if (checkDataReturn(jsonObject) == true) {
            try {
                // Parsing json object response
                // response will be a json object
                JSONArray items = jsonObject.getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject itemsObj = items.getJSONObject(i);
                    JSONObject volumeInfoObj = itemsObj.getJSONObject("volumeInfo");
                    JSONArray authors = volumeInfoObj.getJSONArray("authors");

                    if (authors.length() > 1) {
                        authorMain = authors.getString(0) +
                                ", There are other authors associated with this book, please the links below for more details.";
                    } else {
                        authorMain = authors.getString(0);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Error: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            } finally {
                Log.d("Parser", "parseBookAuthors, try/catch completed successfully. Value: " + authorMain);
            }
            return authorMain;
        } else {
            return "Book not found.";
        }
    }

    public String parseBookPublishedDate(JSONObject jsonObject) {
        String pubDate = null;
        if (checkDataReturn(jsonObject) == true) {
            try {
                // Parsing json object response
                // response will be a json object
                JSONArray items = jsonObject.getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject itemsObj = items.getJSONObject(i);
                    JSONObject volumeInfoObj = itemsObj.getJSONObject("volumeInfo");
                    pubDate = volumeInfoObj.getString("publishedDate");
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Error: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            } finally {
                Log.d("Parser", "parseBookPublishedDate, try/catch completed successfully.");
            }
            return pubDate;
        } else {
            return "Book not found.";
        }
    }

    public String parseBookDescription(JSONObject jsonObject){
        String desription = null;
        if (checkDataReturn(jsonObject) == true) {
            try {
                // Parsing json object response
                // response will be a json object
                JSONArray items = jsonObject.getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject itemsObj = items.getJSONObject(i);
                    JSONObject volumeInfoObj = itemsObj.getJSONObject("volumeInfo");
                    desription = volumeInfoObj.getString("description");
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Error: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            } finally {
                Log.d("Parser", "parseBookDescription, try/catch completed successfully.");
            }
            return desription;
        } else {
            return "Book not found.";
        }
    }

    public int parseBookPages(JSONObject jsonObject){
        int pages = 0;
        if (checkDataReturn(jsonObject) == true) {
            try {
                // Parsing json object response
                // response will be a json object
                JSONArray items = jsonObject.getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject itemsObj = items.getJSONObject(i);
                    JSONObject volumeInfoObj = itemsObj.getJSONObject("volumeInfo");
                    pages = volumeInfoObj.getInt("pageCount");
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Error: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            } finally {
                Log.d("Parser", "parseBookPages, try/catch completed successfully.");
            }
            return pages;
        } else {
            return 0;
        }
    }


}
