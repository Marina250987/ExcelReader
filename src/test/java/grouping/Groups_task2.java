package grouping;

import org.testng.annotations.Test;

import static junit.framework.Assert.assertTrue;

public class Groups_task2 {

    @Test(groups = "first", priority = 1)
    public void one(){
        assertTrue(true);
        System.out.println("Executing Test one from first group");
    }
    @Test(groups = "second", priority = 1)
    public void two(){
        assertTrue(true);
        System.out.println("Executing Test two from second group");
    }
    @Test(groups = "first", priority = 2)
    public void three(){
        assertTrue(true);
        System.out.println("Executing Test three from first group");
    }
    @Test(groups = "second", priority = 2)
    public void four(){
        assertTrue(true);
        System.out.println("Executing Test four from second group");
    }
    @Test(groups = "first", priority = 3)
    public void five(){
        assertTrue(true);
        System.out.println("Executing Test five from first group");
    }
    @Test(groups = "second", priority = 3)
    public void six(){
        assertTrue(true);
        System.out.println("Executing Test six from second group");
    }
    @Test(groups = "first", priority = 4)
    public void seven(){
        assertTrue(true);
        System.out.println("Executing Test seven from first group");
    }
    @Test(groups = "second", priority = 4)
    public void eight(){
        assertTrue(true);
        System.out.println("Executing Test eight from second group");
    }
}
