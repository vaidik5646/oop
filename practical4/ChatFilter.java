public class ChatFilter {

    public static String filterChats(String[] logs, String keyword) {

        int count = 0;
        StringBuilder report = new StringBuilder();

        for (String line : logs) {

            String[] parts = line.split(" ", 3);

            if (parts.length < 3) {
                continue;
            }

            String time = parts[0];
            String user = parts[1];
            String message = parts[2];

            if (message.toLowerCase().contains(keyword.toLowerCase())) {

                count++;

                report.append(time)
                      .append(" ")
                      .append(user)
                      .append(": ")
                      .append(message)
                      .append("\n");
            }
        }

        return "Matches: " + count + "\n" + report.toString();
    }
}

Driver.java
import java.util.Scanner;

public class Driver4 {

    public static void main(String[] args) {

        String[] logs = {
            "10:05 alice Hello there",
            "10:10 bob How are you?",
            "10:15 charlie Good morning",
            "10:20 malformed"
        };

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();

        String result = ChatFilter.filterChats(logs, keyword);

        System.out.println(result);

        sc.close();
    }
}