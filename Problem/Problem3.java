// 자릿수 구하기
public class Problem3 {
    static int getDigit(int n) {
        int result = 1;
        while (n >= 10){
            n = n / 10;
            result++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getDigit(10));
    }
}