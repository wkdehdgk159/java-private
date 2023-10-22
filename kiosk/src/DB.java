import java.util.ArrayList;
import java.util.HashMap;

public class DB {

    static ArrayList<Menu> menuDB = new ArrayList<Menu>();
    static ArrayList<Product> burgersDB = new ArrayList<Product>();
    static ArrayList<Product> sideDB = new ArrayList<Product>();
    static ArrayList<Product> drinkDB = new ArrayList<Product>();
    static ArrayList<Product> iceDB = new ArrayList<Product>();

    public DB() {
        menuDB.add(new Menu("Burgers", "맥도날드, 버거킹 저리가라. 차원이 다른 패티로 만든 햄버거"));
        menuDB.add(new Menu("Side Dishes", "햄버거와 찰떡궁합 사이드 메뉴들"));
        menuDB.add(new Menu("Drink/Coffee", "뻑뻑할 땐 음료 한 모금"));
        menuDB.add(new Menu("Ice Shot", "대뇌에 전두엽까지 시원해지는 아이스크림"));

        burgersDB.add(new Product("Shrimp Extream Lemon Cream", "트렌디한 새우 요리를 새우버거에 접목! 탱글한 통새우튀김과 상큼한 레몬크림마요의 조화",6900));
        burgersDB.add(new Product("Hanwoo Bulgogi", "브리오쉬번에 국내산 한우를 사용한 패티, 신선한 야채, 특제소스로 완성한 프리미엄 햄버거", 8400, new ArrayList<Option>(){{
            add(new Option("Single", 8400));
            add(new Option("Double", 12300));
        }}
                ));
        burgersDB.add(new Product("Shrimp", "새우살을 가득넣어 더 맛있어진 오리지널 새우버거", 4700));
        burgersDB.add(new Product("T-REX", "빵보다 큰 쫄깃한 통닭다리살 패티와 부드러운 크림 소스의 만남", 4700));
        burgersDB.add(new Product("Teri", "쇠고기패티에 달콤 짭짤한 데리소스를 더한 가성비 버거", 3300));

        sideDB.add(new Product("French Fries", "바로 튀겨낸 바삭바삭한 후렌치 포테이토", 1800,new ArrayList<Option>(){{
            add(new Option("Regular", 1800));
            add(new Option("Large", 2200));
        }}));
        sideDB.add(new Product("Seasoned Potatoes", "시즈닝(오니언, 치즈, 칠리)을 한가지를 선택해 뿌려먹는 포테이토", 2300, new ArrayList<Option>(){{
            add(new Option("Onion", 2300));
            add(new Option("Cheese", 2300));
            add(new Option("Chili", 2300));
        }}));
        sideDB.add(new Product("Corn Salad", "싱한 야채와 상큼한 드레싱이 어우러진 옥수수샐러드", 1900));

        drinkDB.add(new Product("Coke", "톡 쏘는 시원 상쾌한 펩시콜라", 2000));
        drinkDB.add(new Product("Cider", "톡 쏘는 시원 청량한 칠성사이다", 2000));
        drinkDB.add(new Product("Ice Americano", "공정무역 커피 원두를 사용한 ICE아메리카노 (고카페인 함유) ※어린이,임산부,카페인 민감자는 섭취에 주의 바랍니다,", 2500));
        drinkDB.add(new Product("Plumade", "자두맛의 요구르트 탄산 에이드", 2700));

        iceDB.add(new Product("Soft Cone", "부드러운 맛과 시원한 소프트 타입의 아이스콘 제품", 900));
        iceDB.add(new Product("Sunday Icecram", "본연의 맛에 충실한 부드럽고 깔끔한 소프트 아이스크림", 1900, new ArrayList<Option>(){{
            add(new Option("Plain", 1900));
            add(new Option("Strawberry", 1900));
            add(new Option("Hershey", 1900));
        }}));
        iceDB.add(new Product("Lotteria Bingsu", "빙수팥과 후르츠칵테일이 풍성하게 토핑된 전형적인 롯데리아 빙수", 5300));
    }

}