package lesson_15;

import org.openqa.selenium.WebElement;

public class ElementComparator_task3 {
    public static void compareElements(WebElement element1, WebElement element2) {
        int element1X = element1.getLocation().getX();
        int element1Y = element1.getLocation().getY();
        int element2X = element2.getLocation().getX();
        int element2Y = element2.getLocation().getY();

        int element1Width = element1.getSize().width;
        int element1Height = element1.getSize().height;
        int element2Width = element2.getSize().width;
        int element2Height = element2.getSize().height;

        if (element1Y < element2Y) {
            System.out.println("Element 1 is higher on the page than Element 2");
        } else if (element1Y > element2Y) {
            System.out.println("Element 2 is higher on the page than Element 1");
        } else {
            System.out.println("Element 1 and Element 2 are located at the same height");
        }


        if (element1X < element2X) {
            System.out.println("Element 1 is located further left on the page than Element 2");
        } else if (element1X > element2X) {
            System.out.println("Element 2 is located further left on the page than Element 1");
        } else {
            System.out.println("Element 1 and Element 2 are located on the same horizontal level");
        }

        int element1Area = element1Width * element1Height;
        int element2Area = element2Width * element2Height;

        if (element1Area > element2Area) {
            System.out.println("Element 1 takes up more space than Element 2");
        } else if (element1Area < element2Area) {
            System.out.println("Element 2 takes up more space than Element 1");
        } else {
            System.out.println("Element 1 and Element 2 occupy the same space");
        }
    }
}
