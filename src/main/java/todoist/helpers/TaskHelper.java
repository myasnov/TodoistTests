package todoist.helpers;

import java.util.Random;

public class TaskHelper {
    public static String generateRandomString(){
            int leftLimit = 97;
            int rightLimit = 122;
            int targetStringLength = 10;

            return new Random().ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
    }
}
