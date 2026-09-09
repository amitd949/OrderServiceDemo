import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo1234 {
    public static void main(String[] args) {
        Map<String, Integer> map= new HashMap<>();


        map.put("A",15);
        map.put("B",5);
        map.put("K",524);


        //
        LinkedHashMap<String, Integer> collect = map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));




        System.out.println("  after soring by  value  collect "+ collect);


    }
}
