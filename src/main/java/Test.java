import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String str = "один два три";
        String[] arr = str.split("\\s");
        for (String s : arr
             ) {
            System.out.println(s);
        }
    }
}


