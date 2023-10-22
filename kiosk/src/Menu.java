import java.util.ArrayList;

public class Menu {

//    Hamburger, Side dish, Drink/Coffee, Ice Shot
    //맥도날드, 버거킹 저리가라. 차원이 다른 패티로 만든 햄버거
    //햄버거와 찰떡궁합 사이드 메뉴들
    //뻑뻑할 땐 음료 한 모금
    //대뇌에 전두엽까지 시원해지는 아이스크림
    private String name;
    private String description;

    public String getName() {
        return this.name;
    }
//    public void setName(String name) {
//        this.name = name;
//    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
