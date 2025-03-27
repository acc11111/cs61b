package deque;

import java.util.*;

public class ArrayDeque61B<T> implements Deque61B<T> {

    //根据提示我们需要使用一个循环数组
    private T[] arr;
    private int size = 0;
    private int front = 0;
    private int rear = 0;
    private int capatity = 1000;
    //rear 指向的是尾部元素的下一位
    //front 指向的是头部元素的当前头
    //判断的就是(rear + 1) % capatity == front?如果相等的话就是满了，这样虽然会浪费一个存储空间，但是可以区分空队列和满队列


    public ArrayDeque61B() {
        //空输入的构造函数
        arr = (T[]) new Object[capatity];
        size = 0;
    }

    public ArrayDeque61B(T x) {
        //空输入的构造函数
        arr = (T[]) new Object[capatity];
        arr[front] = x;
        rear++;
        size += 1;
    }


    @Override
    public void addFirst(T x) {
        //传入的数字是添加的元素
        if (size == capatity) {
            //此时数组超出范围了
            System.out.println("can not addFirst");
        } else {
            front = Math.floorMod(front - 1, capatity);//更新front指针
            arr[front] = x;
            size++;
        }
    }

    @Override
    public void addLast(T x) {
        //传入的数字是添加的元素
        if (size == capatity) {
            //此时数组超出范围了
            System.out.println("can not addrear");
        } else {
            arr[rear] = x;
            size++;
            rear = Math.floorMod(rear + 1, capatity);//更新front指针
        }
    }

    @Override
    public List<T> toList() {
        List<T> resList = new ArrayList<>();
        int i = front;
        while (i != rear) {
            resList.add(arr[i]);
            i = Math.floorMod(i + 1, capatity);
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
        front = Math.floorMod(front + 1, capatity);
        size--;
        return removeElement;
    }

    @Override
    public T removeLast() {
        T removeElement = arr[rear - 1];
        rear = Math.floorMod(rear - 1, capatity);
        size--;
        return removeElement;
    }

    @Override
    public T get(int index) {
        int i = Math.floorMod(front + index, capatity);
        return arr[i];
    }

    @Override
    public T getRecursive(int index) {
        T getElement = getRecursiveHelper(index, front);
        return getElement;
    }

    //下面这个辅助函数
    private T getRecursiveHelper(int index, int i) {
        if (index == 0) {
            return arr[i];
        } else {
            i = Math.floorMod(i + 1, capatity);
            return getRecursiveHelper(index - 1, i);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque61BIterator();
    }


    // 实现自己的数组列表迭代器
    private class ArrayDeque61BIterator<T> implements Iterator<T> {

        private int p; //初始化一个索引，后面用来和size作为比较来使用

        public ArrayDeque61BIterator() {
            p = 0;
        }

        @Override
        public boolean hasNext() {
            return p < size;
        }

        @Override
        public T next() {
            if (hasNext()) {
                // 此时说明还没有指向空元素
                p = p + 1;
                int i = (front + p - 1) % capatity; //防止越界
                return (T) arr[i];
            } else {
                // 此时说明已经指向空元素
                return null;
            }
        }
    }


    //检测通过了
    @Override
    public boolean equals(Object x) {
        // 检查是否是同一个对象
        if (this == x) {
            return true;
        }

        // 检查是否同类或者存在
        if (!(x instanceof ArrayDeque61B)) {
            //不同类就返回false
            return false;
        }

        //检查size
        if (this.size != ((ArrayDeque61B<?>) x).size) {
            return false;
        }

        //开始逐步检查元素
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capatity;
            if(this.arr[index] != ((ArrayDeque61B<?>) x).arr[index]){
                return false;
            }
        }

        //经过一系列的排查都没有返回false就是相同了
        return true;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < size; i++){
            int index = (i + this.front) % capatity;
            sb.append(this.arr[index]);
        }
        sb.append("]");

        return sb.toString();
    }

}


