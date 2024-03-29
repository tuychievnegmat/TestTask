import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

public class Main {
    public static void main(String[] args) {
        String s = "сапог сарай арбуз болт бокс биржа серебро фильм баржа барабан вода ветер вино ворон вокзал";
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

        for (Map.Entry<Character, List<String>> string: collect.entrySet()) {

            Collections.sort(string.getValue(), new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if(o1.length() > o2.length())return -1;
                    else if(o1.length() < o2.length()) return 1;
                    else return o1.compareTo(o2);
                }
            });
        }

        // cортировка map по ключу
         return collect
                .entrySet()
                .stream()
                .sorted(comparingByKey())
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e2, LinkedHashMap::new));

    }
    
}
