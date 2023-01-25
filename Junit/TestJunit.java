import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestJunit {
   @Test
	
   public void testAdd() {
      MyMath math = new MyMath(8, 10);
      assertEquals(17, math.practice());
      //String str = "Junit is working fine";
      //assertEquals("Junit is working fine",str);
   }
}