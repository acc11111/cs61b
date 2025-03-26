package deque;

import java.util.*;

public class ArrayDeque61B<T> implements Deque61B<T>{

    //根据提示我们需要使用一个循环数组
    private T[] arr ;
    private int size = 0;
    private int front = 0;
    private int last = 0;
    //last 指向的是尾部元素的下一位
    //front 指向的是头部元素的当前头


    public ArrayDeque61B(){
        //空输入的构造函数
        arr = (T[]) new Object[1000];
        size = 0;
    }

    public ArrayDeque61B(T x){
        //空输入的构造函数
        arr = (T[]) new Object[1000];
        arr[front] = x;
        last ++;
        size += 1;
    }


    @Override
    public void addFirst(T x) {
        //传入的数字是添加的元素
        if(Math.floorMod(last + 1,1000) == front){
            //此时数组超出范围了
            System.out.println("can not addFirst");
        }else{
            front = Math.floorMod(front - 1,1000);//更新front指针
            arr[front] = x;
            size ++;
        }
    }

    @Override
    public void addLast(T x) {
        //传入的数字是添加的元素
        if(Math.floorMod(last + 1,1000) == front){
            //此时数组超出范围了
            System.out.println("can not addLast");
        }else{
            arr[last] = x;
            size ++;
            last = Math.floorMod(last + 1,1000);//更新front指针
        }
    }

    @Override
    public List<T> toList() {
        List<T> resList = new ArrayList<>();
        int i = front;
        while(i != last){
            resList.add(arr[i]);
            i = Math.floorMod(i + 1,1000);
        }

        return resList;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T removeElement = arr[front];
        front = Math.floorMod(front + 1,1000);
        size --;
        return removeElement;
    }

    @Override
    public T removeLast() {
        T removeElement = arr[last - 1];
        last = Math.floorMod(last - 1,1000);
        size --;
        return removeElement;
    }

    @Override
    public T get(int index) {
        int i = Math.floorMod(front + index,1000);
        return arr[i];
    }

    @Override
    public T getRecursive(int index) {
        T getElement = getRecursiveHelper(index , front);
        return getElement;
    }

    //下面这个辅助函数
    private T getRecursiveHelper(int index, int i){
        if(index == 0){
            return arr[i];
        }else{
            i = Math.floorMod(i + 1,1000);
            return getRecursiveHelper(index - 1,i);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}

