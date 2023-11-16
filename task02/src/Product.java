package task02.src;

public class Product {
    private String ProductName;
    private int Rating;
    private int price;

    public Product(String productName, int rating, int price) {
        this.ProductName = productName;
        this.Rating = rating;
        this.price = price;
    }

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



}
