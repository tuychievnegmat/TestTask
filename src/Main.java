import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Map.*;
import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

public class Main {
    public static void main(String[] args) {
        String s = "сапог сарай арбуз болт бокс биржа";
       Map<Character, List<String>> map = getGroupStr(s);


        for (Map.Entry<Character, List<String>> strings : map.entrySet()) {
            if(strings.getValue().size() > 1){
                System.out.println(strings);
            }
        }


    }
    public static Map<Character, List<String>> getGroupStr(String str){

        List<String> strings = Arrays.asList(str.split(" "));


        Collections.sort(strings);
        Function<String, Character> firstChar = s -> Character.toLowerCase(s.charAt(0));


        Map<Character, List<String>> collect = strings.stream()
                .collect(Collectors.groupingBy(firstChar));

        //оортировка map по ключу
         Map sorted = collect
                .entrySet()
                .stream()
                .sorted(comparingByKey())
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e2, LinkedHashMap::new));
        return sorted;

    }


}
