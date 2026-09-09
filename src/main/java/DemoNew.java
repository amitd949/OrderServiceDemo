import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class DemoNew {

    public static void main(String[] args) {

        List<Integer>  dataList1= Arrays.asList(6, 58, 54, 32, 12);
        List<Integer>  dataList2= Arrays.asList(14,55,45, 124,32, 12);
        Set<Integer>commonElements= new TreeSet<>(dataList2);

        dataList1.stream().filter(commonElements::contains)
                .distinct().collect(Collectors.toList());

        List<Integer> common = dataList2.stream()
                .filter(dataList1::contains)
                .collect(Collectors.toList());

        System.out.println(common);










    }

}
