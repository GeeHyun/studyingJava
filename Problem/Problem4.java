// 공백을 제외한 글자수 세기
public class Problem4 {
    static int getCount(String s) {
        int result = s.length();
        for (int i=0; i < s.length(); i++) {
            if (s.charAt(i) == ' '){
                result -= 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getCount("점프 투 자바"));
    }
}