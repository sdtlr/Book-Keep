package uk.samdtaylor.bookkeep;

/**
 * Created by sdtay on 29/07/2016.
 */
public class urlControl {
    private String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:";
    private String apikey = "AIzaSyBLQOTwCkIFiSnC08HmEkoLw-rf5aG_mrY";

    public void setUrl(String isbn){
        this.url  = url + isbn;
    }

    public String getUrl(){
        return url;
    }
}
