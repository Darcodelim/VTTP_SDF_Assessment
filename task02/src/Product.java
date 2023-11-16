package task02.src;

public class Product {
    private String id;
    private String ProductName;
    private int Rating;
    private int price;



    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    
    public String getiD() {
        return id;
    }

    public void setiD(String id) {
        this.id = id;
    }



}
