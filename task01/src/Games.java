
public class Games {

    private String App;
    private String Category;
    private String Rating;

    public Games(String app, String category, String rating)
    {
        this.App = app;
        this.Category = category;
        this.Rating = rating;
    }

    public String getApp() {
        return App;
    }

    public void setApp(String app) {
        App = app;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }
    
    // public void removeGame()
    // {

    // }
    
}
