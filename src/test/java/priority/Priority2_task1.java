package priority;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class Priority2_task1 {
    @Test(dependsOnMethods = "b")
    public void a() {
        assertTrue(true);
        System.out.println("Test a executed");
    }

    @Test(dependsOnMethods = "c")
    public void b() {
        assertTrue(true);
        System.out.println("Test b executed");
    }

    @Test(dependsOnMethods = "d")
    public void c() {
        assertTrue(true);
        System.out.println("Test c executed");
    }

    @Test(dependsOnMethods = "e")
    public void d() {
        assertTrue(true);
        System.out.println("Test d executed");
    }

    @Test(dependsOnMethods = "f")
    public void e() {
        assertTrue(true);
        System.out.println("Test e executed");
    }

    @Test(dependsOnMethods = "g")
    public void f() {
        assertTrue(true);
        System.out.println("Test f executed");
    }

    @Test
    public void g() {
        assertTrue(true);
        System.out.println("Test g executed");
    }

}