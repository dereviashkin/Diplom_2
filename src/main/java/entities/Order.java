package entities;

public class Order {
    private String _id;
    private String name;
    private String type;
    private String proteins;
    private String fat;
    private String carbohydrates;
    private String calories;
    private String price;
    private String image;
    private String image_mobile;
    private String image_large;
    private String __v;

    public Order(String _id, String name, String type, String proteins, String fat, String carbohydrates,
                 String calories, String price, String image, String image_mobile, String image_large, String __v) {
        this._id = _id;
        this.name = name;
        this.type = type;
        this.proteins = proteins;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
        this.price = price;
        this.image = image;
        this.image_mobile = image_mobile;
        this.image_large = image_large;
        this.__v = __v;
    }
}
