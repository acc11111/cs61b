import deque.Deque61B;
import edu.princeton.cs.algs4.In;
import org.knowm.xchart.BitmapEncoder;

import java.util.LinkedList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    //做好Node类，方便后续使用
    private static class Node<T>{
        Node<T> next;
        T item;
        Node<T> prev;

        Node(Node<T> prev,T item,Node<T> next){
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    //设置大小，快速查询大小
    private int size;
    //设置哨兵节点
    private final Node<T> sentinel;


    //初始化一个新的链表
    public LinkedListDeque61B(){
        sentinel = new Node<T>(null,null,null);
        sentinel.prev = sentinel;//这里指向自己，后续可以同步
        sentinel.next = sentinel;//这里也是指向自己
        size = 0;
    }

    //初始化一个带数据的新链表
    public LinkedListDeque61B(T x){
        sentinel = new Node<T>(null,null,null);
        Node<T> newNode = new Node<T>(sentinel,x,sentinel.next);

        //更新哨兵节点
        sentinel.prev = newNode;
        sentinel.next = newNode;


        size += 1;
    }






    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x){
        //创建新节点
        Node<T> newNode = new Node<T>(sentinel,x,sentinel.next);
        //插入值，然后将哨兵节点指向新插入的值

        //原来哨兵节点的下一个节点的前一个指向新节点，即使链表是空的依旧成立
        newNode.next.prev = newNode;
        //哨兵节点的next节点更新为新的节点
        sentinel.next = newNode;

        size += 1;

    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x){

        //创建一个新节点，这个新节点指向原来最后的元素，然后next指向哨兵节点
        Node<T> newNode = new Node<T>(sentinel.prev,x,sentinel);

        //更新原来的next节点
        newNode.prev.next = newNode;

        //更新哨兵节点
        sentinel.prev  = newNode;

        size += 1;




    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList(){
        //记得更新大小
        List<T> returnList = new LinkedList<>();
        Node<T> p = sentinel;
        while(p.next != sentinel){
            //更新这个p.next的判断，如果指回了哨兵节点说明这个就是最后一个节点了，不需要再继续了
            p = p.next;
            returnList.add(p.item);

        }
        return returnList;

    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty(){
        if(size == 0){
            return  false;
        }
        return true;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size(){
        return size;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst(){
        Node<T> firstNode = sentinel.next;
        sentinel.next = firstNode.next;//指向第二个元素
        firstNode.next.prev = sentinel;
        size --;

        return firstNode.item;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast(){
        Node<T> lastNode = sentinel.prev;

        //更新节点之间的关系
        lastNode.prev.next = sentinel;
        sentinel.prev = lastNode.prev;

        size -= 1;
        return lastNode.item;

    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index){
        if(index > size - 1 || index < 0){
            return null;
        }else {
            Node<T> p = sentinel;
            for(int i = 0; i <= index; i++){
                //从哨兵节点开始往下，0的时候就可以往下一次，所以是和index对应的
                p = p.next;
            }
            return p.item;
        }
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index){
        if(index < 0 || index > size - 1){
            return null;
        }else {
            //注意这里是直接传入这个next，因为你看递归就知道index=0直接返回了，但是如果传入sentinel的话根本就没有元素可取
            return getRecursiveHelper(sentinel.next,index);
        }
    }
    private T getRecursiveHelper(Node<T> p,int index){
        if(index == 0){
            return p.item;
        }
        else{
            return getRecursiveHelper(p.next,index -1);
        }
    }


    public static void main(String[] args) {
        LinkedListDeque61B<Integer> test = new LinkedListDeque61B<>();
        test.addFirst(1);
        test.addFirst(2);
        test.addFirst(3);
        test.addFirst(4);
        test.addFirst(5);
        System.out.println(test.get(5));
        System.out.println(test.toList());


    }
}
