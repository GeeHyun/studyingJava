public class Item {
    private String name;
    private int length;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String raw() {
    StringBuffer padded = new StringBuffer(this.value);
    // StringBuffer 자료형: 문자열을 추가하거나 변경할 때 주로 사용 (mutable)
    // append('추가문자') 메서드를 통해 문자열 추가
    // toString() 메서드를 이용하여 String 자료형으로 변경
    while (padded.toString().getBytes().length < this.length) {
    // 한글은 한 글자에 3바이트
    padded.append(' ');
    }
    return padded.toString();
    }

    public static Item create(String name, int length, String value) {  // 팩토리 메소드: 객체를 생성하는 static 메소드
        Item item = new Item();
        item.setName(name);
        item.setLength(length);
        item.setValue(value);
        return item;
    }
    
    public static void main(String[] args) {
        Item item = new Item();
        item.setName("이름");
        item.setLength(20);
        item.setValue("이지현");
        System.out.println("[" + item.raw() + "]");
    }
}