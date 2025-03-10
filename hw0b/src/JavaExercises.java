import org.checkerframework.common.value.qual.EnsuresMinLenIf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class JavaExercises {

    /** Returns an array [1, 2, 3, 4, 5, 6] */
    public static int[] makeDice() {
        // TODO: Fill in this function.
//        检查出此处有警告，发现是arr并未产生使用所以可以直接创建就返回取消警告
//        int[] arr = {1,2,3,4,5,6};
//        return arr;
        return new int[]{1,2,3,4,5,6};
    }

    /** Returns the order depending on the customer.
     *  If the customer is Ergun, return ["beyti", "pizza", "hamburger", "tea"].
     *  If the customer is Erik, return ["sushi", "pasta", "avocado", "coffee"].
     *  In any other case, return an empty String[] of size 3. */
    public static String[] takeOrder(String customer) {
        // 定义不同客户的订单
        String[] ergunStringArr = {"beyti", "pizza", "hamburger", "tea"};
        String[] erikStringArr = {"sushi", "pasta", "avocado", "coffee"};

        // 创建一个 Map 来存储客户和订单的映射关系
        Map<String, String[]> map = new HashMap<>();
        map.put("Ergun", ergunStringArr);
        map.put("Erik", erikStringArr);

        // 使用 getOrDefault 方法，如果客户不存在，返回一个空数组
        return map.getOrDefault(customer, new String[]{"","",""});
    }

    /** Returns the positive difference between the maximum element and minimum element of the given array.
     *  Assumes array is nonempty. */
    public static int findMinMax(int[] array) {
        // TODO: Fill in this function.
        int min = array[0];
        int max = array[0];
        for(int i = 1;i < array.length;i++){

            // 这里之所以使用else if是因为注意到一个数不可能又是最大又是最小，因此只会进入一个分支
            if(array[i] > max){
                max = array[i];
            }else if(array[i] < min){
                min = array[i];
            }
        }

        return  max - min;
    }

    /**
      * Uses recursion to compute the hailstone sequence as a list of integers starting from an input number n.
      * Hailstone sequence is described as:
      *    - Pick a positive integer n as the start
      *        - If n is even, divide n by 2
      *        - If n is odd, multiply n by 3 and add 1
      *    - Continue this process until n is 1
      */
    public static List<Integer> hailstone(int n) {
        return hailstoneHelper(n, new ArrayList<>());
    }

    private static List<Integer> hailstoneHelper(int x, List<Integer> list) {
        // TODO: Fill in this function.
        list.add(x);
        if(x==1){
            return list;
        }else if(x%2==0){
            return hailstoneHelper(x/2,list);
        }else{
            return hailstoneHelper(x*3+1,list);
        }
    }

}
