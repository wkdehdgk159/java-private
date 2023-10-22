import java.util.ArrayList;
import java.util.HashMap;

public class Product extends Menu{

    private String name;
    private String description;
    private int price;
    public int n = 1;
    ArrayList<Option> options;

    public Product(String name, String description, int price) {
//        this.name = name;
//        this.description = description;
        //이렇게 하면 에러.
        super(name, description);
        this.price = price;
    }
    public Product(String name, String description, int price, ArrayList<Option> options) {
        super(name, description);
        this.price = price;
        this.options = options;
    }

    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public ArrayList<Option> getOptions() {
        return options;
    }

}
