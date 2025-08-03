package priority;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class Priority1_task1 {
    @Test(priority = 7)
    public void a() {
        assertTrue(true);
        System.out.println("Test a executed");
    }

    @Test(priority = 6)
    public void b() {
        assertTrue(true);
        System.out.println("Test b executed");
    }

    @Test(priority = 5)
    public void c() {
        assertTrue(true);
        System.out.println("Test c executed");
    }

    @Test(priority = 4)
    public void d() {
        assertTrue(true);
        System.out.println("Test d executed");
    }

    @Test(priority = 3)
    public void e() {
        assertTrue(true);
        System.out.println("Test e executed");
    }

    @Test(priority = 2)
    public void f() {
        assertTrue(true);
        System.out.println("Test f executed");
    }

    @Test(priority = 1)
    public void g() {
        assertTrue(true);
        System.out.println("Test g executed");
    }


}
