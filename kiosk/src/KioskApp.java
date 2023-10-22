import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KioskApp {

    Scanner sc = new Scanner(System.in);

    static ArrayList<Menu> menuList = new ArrayList<Menu>();
    static ArrayList<Product> burgersList = new ArrayList<Product>();
    static ArrayList<Product> sideList = new ArrayList<Product>();
    static ArrayList<Product> drinkList = new ArrayList<Product>();
    static ArrayList<Product> iceList = new ArrayList<Product>();

    public KioskApp() {
        DB db = new DB();
        dbLoad();
    }

    void dbLoad() {
        menuList = DB.menuDB;
        burgersList = DB.burgersDB;
        sideList = DB.sideDB;
        drinkList = DB.drinkDB;
        iceList = DB.iceDB;
        //메뉴는 메뉴 클래스에 리스트 만들고. 앱에서 불러오기?
    }
    void start() {
        this.mainMenu();
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    void printIntro() {
        System.out.println(ANSI_YELLOW + "\"롯데리아에 오신 걸 환영합니다.\"" + ANSI_RESET);
    }
    //메인 메뉴판 화면
    void hiddenPage() {
        System.out.println("[ 총 판매금액 현황 ]");
        System.out.printf("현재까지 총 판매된 금액은 [ W %.1f ] 입니다.\n\n",(double)Order.totalSalePrice/1000);

        System.out.println("[ 총 판매상품 목록 현황 ]");
        System.out.println("현재까지 총 판매된 상품 목록은 아래와 같습니다.\n");

        for(Product p: Order.totalOrderList) {
            System.out.printf("- %-30s | W %-4.1f\n", p.getName(), (double)p.getPrice()/1000);
        }

        System.out.println("1. 돌아가기");
        int option = sc.nextInt();
        if(option == 1) mainMenu();
    }
    void mainMenu() {
        printBoundary();
        printIntro();

        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n");

        System.out.println("[ 롯데리아 MENU ]");
        for(int i = 0; i < menuList.size(); i++) {
            Menu tmpMenu = menuList.get(i);
            System.out.printf("%d. %-13s | %s\n", i+1, tmpMenu.getName(), tmpMenu.getDescription());
        }
        System.out.println();
        System.out.println("[ ORDER MENU ]");
        System.out.printf("5. %-10s | 장바구니를 확인 후 주문합니다.\n", "Order");
        System.out.printf("6. %-10s | 진행중인 주문을 취소합니다.\n", "Cancel");
        printBoundary();

        int option = sc.nextInt();
        switch (option) {
            case 0:
                hiddenPage();
            case 1: case 2: case 3: case 4:
                //show productMenu
                productMenu(option);
                break;
            case 5:
                //show Order
                orderPage();
                break;
            case 6:
                //show cancel
                cancelPage();
                break;
            default:
                return;
        }
    }

    //숨겨진 페이지 총 판매금액 + 총 판매상품
    //상품 메뉴판 화면
    void productMenu(int option) {
        printBoundary();
        printIntro();
        System.out.println("아래 상품 메뉴판을 보시고 상품을 골라 입력해주세요.");
        ArrayList<Product> PL = new ArrayList<Product>();
        switch (option) {
            case 1:
                PL = burgersList;
                break;
            case 2:
                PL = sideList;
                break;
            case 3:
                PL = drinkList;
                break;
            case 4:
                PL = iceList;
                break;
        }
        printProductMenu(PL);
        printBoundary();
        orderProduct(PL);
    }
    void printProductMenu(ArrayList<Product> PL) {
        printBoundary();
        for(int i = 0; i < PL.size(); i++) {
            Product p = PL.get(i);
            System.out.printf("%d. %-30s | W %-3.1f | %s\n", i+1, p.getName(), (double)p.getPrice()/1000,p.getDescription());
        }
    }
    void orderProduct(ArrayList<Product> PL) {
        int option = sc.nextInt();
        Product p = PL.get(option - 1);
        //옵션이 있는 경우에는 객체의 이름을 + (Large) 이런 식으로 변경해줄 예정이므로 deep copy로 새로운 객체 생성
        if(p.options != null) p = new Product(p.getName(), p.getDescription(), p.getPrice(), p.getOptions());
        putCart(p);
    }

    //구매 화면
    void putCart(Product p) {
        printBoundary();
        String name = p.getName();
        int price = p.getPrice();
        ArrayList<Option> options = p.getOptions();

        //옵션이 있는 메뉴라면
        if(options != null) {
            System.out.printf(ANSI_YELLOW +"\"%-20s | W %-3.1f | %s\"\n" + ANSI_RESET, name, (double)price / 1000 ,p.getDescription());
            System.out.println("위 메뉴의 어떤 옵션으로 추가하시겠습니까?");

            //옵션 개수만큼 돌면서 선택지 제공
            for(int i = 1; i <= options.size(); i++) {
                Option option = options.get(i-1);
                System.out.printf("%d. %s(W %.1f)      ", i, option.name, (double) option.price / 1000);
            }
            System.out.println();

            //선택한 메뉴로 가격, 이름 변경
            int choice = sc.nextInt();
            String newName = name.concat("(").concat(options.get(choice-1).name).concat(")");
            p.setName(newName);
            p.setPrice(options.get(choice-1).price);
            printBoundary();
        }


        System.out.printf(ANSI_YELLOW +"\"%-20s | W %-3.1f | %s\"\n" + ANSI_RESET, p.getName(), (double)p.getPrice()/1000,p.getDescription());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인            2. 취소");
        int option = sc.nextInt();
        if(option == 1) {
            Order.addToCart(p);
            System.out.println(p.getName() + "이/가 장바구니에 추가되었습니다.");
        }
        System.out.println();
        mainMenu();
    }
    //모든 로직의 시작점만 메인에 두고 다 분리.


    //주문 화면
    void orderPage() {
        printBoundary();
        ArrayList<Product> orderList = Order.getOrderList();

        System.out.println("아래와 같이 주문하시겠습니까?");
        System.out.println("[ Order ]");
        int totalPrice = 0;
        for(Product p: orderList) {
            totalPrice += p.getPrice();
        }
        for(Product p: orderList) {
            //개수 세는 로직 넣기
            System.out.printf("%-20s | W %-3.1f | %s\n", p.getName(), (double)p.getPrice()/1000, p.getDescription());
        }
        System.out.println();
        System.out.println("[ Total ]");
        System.out.println("W " + (double)totalPrice/1000);
        System.out.println("1. 주문       2. 메뉴");
        int option = sc.nextInt();
        if(option == 1) {
            Order.totalSalePrice += totalPrice;
            Order.totalOrderList.addAll(Order.getOrderList());
            orderComplete();
        } else if(option == 2) {
            mainMenu();
        }
    }

    //주문 완료 화면
    void orderComplete() {
        printBoundary();
        System.out.println("주문이 완료되었습니다!");
        Order.resetOrderList();
        System.out.printf("주문번호는 [ %d ] 번입니다.\n", Order.getOrderNum());
        System.out.println("3초 후 메뉴판으로 돌아갑니다");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mainMenu();
    }

    //주문 취소 화면
    void cancelPage() {
        printBoundary();
        System.out.println("진행하던 주문을 취소하시겠습니까?");
        System.out.println("1. 확인       2. 취소");
        int option = sc.nextInt();
        if(option == 1) {
            System.out.println("진행하던 주문이 취소되었습니다.");
            Order.resetOrderList();
        }
        mainMenu();
    }

    void printBoundary() {
        System.out.println("---------------------------------------------------------------------");
    }
}
