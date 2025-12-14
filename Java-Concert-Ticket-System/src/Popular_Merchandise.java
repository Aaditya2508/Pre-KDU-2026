import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Popular_Merchandise {
    public static void main(String[] args) {
      List<String> merchandise = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\HP\\Desktop\\Pre-KDU-2026\\Java-Concert-Ticket-System\\src\\merchandise.csv"));

            String line;

            while((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                for (String value : values) {
                    merchandise.add(value.trim());   // remove spaces
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HashMap<String,Integer> map = new HashMap<>();

        for(String str: merchandise) {
            map.put(str,1 + map.getOrDefault(str,0));
        }

        System.out.println(map);

        TreeMap<Integer, List<String>> treeMap = new TreeMap<>((a,b)-> b-a);

        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            String str = entry.getKey();

            treeMap.computeIfAbsent(freq,k->new ArrayList<>()).add(str);
        }

        System.out.println("TreeMap :"+ treeMap);

        List<String> top3 = new ArrayList<>();
        List<Integer> top3_count = new ArrayList<>();

        for(Map.Entry<Integer,List<String>> entry : treeMap.entrySet()) {
            for(String s : entry.getValue()) {
                top3.add(s);
                top3_count.add(entry.getKey()); 
                if(top3.size() == 3) break;
            }
            if(top3.size() == 3) break;
        }
        for(int i=0;i<top3.size();++i) {
            System.out.println("Merchanise :" + top3.get(i) + " Frequency: " + top3_count.get(i) );
        }
    }
}