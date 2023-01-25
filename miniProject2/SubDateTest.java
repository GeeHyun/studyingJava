import junit.framework.*;

public class SubDateTest extends TestCase {
    public static void main(String[] args) {
        junit.textui.TestRunner.run(SubDateTest.class);
    }
    public void testGetYearDay() {    // test로 시작하는 메서드는 자동으로 실행됨
        // 최초 일자를 1년 1월 1일이라고 설정
        // 1년까지의 총 일 수는 0일
        // 2년까지의 총 일 수는 365일
        assertEquals(0, SubDate.getYearDay(1));
        assertEquals(365, SubDate.getYearDay(2));
    }
}