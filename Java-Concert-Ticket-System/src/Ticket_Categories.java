import java.util.*;

public class Ticket_Categories {
    public static void main(String[] args) throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        String preferences = scanner.nextLine();

        String[] preferences_arr = preferences.split(",");

        if(preferences_arr.length != 10) {
            scanner.close();
            throw new IllegalArgumentException("Input must contain exactly 10 preferences.");
        }

        for(int i=0;i<preferences_arr.length;++i) {
            preferences_arr[i] = preferences_arr[i].trim();
        }

        List<String> preference_list = new ArrayList<>(Arrays.asList(preferences_arr));
        Set<String> preference_set = new HashSet<>(preference_list);
        Map<String,Integer> preference_map = new HashMap<>();

        for(String str : preference_list ) {
            preference_map.put(str,preference_map.getOrDefault(str,0)+1);
        }

        System.out.print("ArrayList: ");
        System.out.println(preference_list);
        System.out.println("Hashset: ");
        System.out.println(preference_set);
        System.out.println("HashMap: ");
        System.out.println(preference_map);

        scanner.close();
    }
}
