import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        // TODO: Fill in this function.
        Map<Character,Integer> map = new HashMap<>();
        for(char c = 'a';c <= 'z';c++){
            int n = c - 'a' + 1;
            map.put(c,n);
        }
        return map;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        // TODO: Fill in this function.
        Map<Integer, Integer> smap = new HashMap<>();
        for(int i : nums){
            smap.put(i,i*i);
        }
        return smap;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        // TODO: Fill in this function.
        Map<String, Integer> map = new HashMap<>();
        for(String word : words){
            if(map.containsKey(word)){
                int currentVal = map.get(word);
                map.put(word,currentVal+1);
            }else{
                map.put(word,1);
            }
        }
        return map;
    }
}
