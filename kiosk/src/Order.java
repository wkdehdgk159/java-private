import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Order {

    private static ArrayList<Product> OrderList = new ArrayList<Product>();
    public static ArrayList<Product> totalOrderList = new ArrayList<Product>();

    //주문 개수 세는 해시맵
    public static HashMap<String, Integer> cntMap = new HashMap<String, Integer>();
    private static int orderNum = 0;
    public static int totalSalePrice = 0;

    //예를 들ㅇ

    //domain, dto... 기능들 세분화해서 클래스로.
    public Order() {
    }
    public static ArrayList<Product> getOrderList() {
        return OrderList;
    }

    public static void resetOrderList() {
        cntMap.clear();
        OrderList.clear();
    }


    public static void addToCart(Product product) {
        OrderList.add(product);
    }

    public static int getOrderNum() {
        return ++orderNum;
    }
}
