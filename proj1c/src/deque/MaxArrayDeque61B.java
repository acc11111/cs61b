package deque;
import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T>{
    private Comparator<T> defaultComparator;

    // 创建一个新的队列
    public MaxArrayDeque61B(Comparator<T> c){
        defaultComparator = c;
    }

    //使用默认的比较器
    public T max(){
        if(isEmpty()){
            return null;
        }else{
            return max(defaultComparator);
        }
    }

    //使用指定的比较器，有点类似于回调函数
    public T max(Comparator<T> c){
        if(isEmpty()){
            return null;
        }


        //开始比较取出最大值
        T maxElement = this.get(0);

        for(T i : this){
            if(c.compare(maxElement,i) < 0){
                maxElement = i;
            }
        }

        return maxElement;
    }


}
