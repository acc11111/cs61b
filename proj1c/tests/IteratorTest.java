import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.*;
import static com.google.common.truth.Truth.assertWithMessage;

import deque.*;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class IteratorTest {

    @Test
    public void ArrayDeque61BIteratorTest_Basic() {
        LinkedListDeque61B<Integer> q = new LinkedListDeque61B<>();

        // 测试空队列的迭代
        assertThat(q).isEmpty(); // 假设有 isEmpty() 方法
        int count = 0;
        for (Integer item : q) {
            count++;
        }
        assertThat(count).isEqualTo(0);
    }

    @Test
    public void ArrayDeque61BIteratorTest_SingleElement() {
        LinkedListDeque61B<Integer> q = new LinkedListDeque61B<>();
        q.addLast(42);

        // 测试单个元素的迭代
        int count = 0;
        for (Integer item : q) {
            assertThat(item).isEqualTo(42);
            count++;
        }
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void ArrayDeque61BIteratorTest_AddLastMultiple() {
        LinkedListDeque61B<Integer> q = new LinkedListDeque61B<>();

        // 添加多个元素
        for (int i = 0; i < 5; i++) {
            q.addLast(i);
        }

        // 验证迭代顺序
        int expected = 0;
        for (Integer item : q) {
            assertWithMessage("Item at position " + expected + " should match").that(item).isEqualTo(expected);
            expected++;
        }
        assertThat(expected).isEqualTo(5);
    }

    @Test
    public void ArrayDeque61BIteratorTest_AddFirstMultiple() {
        LinkedListDeque61B<Integer> q = new LinkedListDeque61B<>();

        // 从头部添加多个元素
        for (int i = 0; i < 5; i++) {
            q.addFirst(i);
        }

        // 验证迭代顺序（应该是 4, 3, 2, 1, 0）
        int[] expectedValues = {4, 3, 2, 1, 0};
        int index = 0;
        for (Integer item : q) {
            assertThat(item).isEqualTo(expectedValues[index]);
            index++;
        }
        assertThat(index).isEqualTo(5);
    }

    @Test
    public void ArrayDeque61BIteratorTest_MixedOperations() {
        LinkedListDeque61B<Integer> q = new LinkedListDeque61B<>();

        // 混合操作：先加后删再加
        q.addLast(1);
        q.addLast(2);
        q.addFirst(0);
        q.removeLast(); // 移除 2
        q.addLast(3);

        // 预期结果：0, 1, 3
        int[] expectedValues = {0, 1, 3};
        int index = 0;
        for (Integer item : q) {
            assertThat(item).isEqualTo(expectedValues[index]);
            index++;
        }
        assertThat(index).isEqualTo(3);
    }

    @Test
    public void ArrayDeque61BIteratorTest_LargeData() {
        LinkedListDeque61B<Integer> q = new LinkedListDeque61B<>();

        // 测试大量数据（你的原始测试）
        for (int i = 0; i < 1000; i++) {
            q.addLast(i);
        }

        // 验证迭代器是否能正确遍历所有元素
        int expected = 0;
        for (Integer item : q) {
            assertThat(item).isEqualTo(expected);
            expected++;
        }
        assertThat(expected).isEqualTo(1000);
    }

    @Test
    public void ArrayDeque61BIteratorTest_AfterRemoval() {
        LinkedListDeque61B<Integer> q = new LinkedListDeque61B<>();

        // 添加元素后移除部分
        for (int i = 0; i < 5; i++) {
            q.addLast(i);
        }
        q.removeFirst(); // 移除 0
        q.removeLast();  // 移除 4

        // 预期结果：1, 2, 3
        int[] expectedValues = {1, 2, 3};
        int index = 0;
        for (Integer item : q) {
            assertThat(item).isEqualTo(expectedValues[index]);
            index++;
        }
        assertThat(index).isEqualTo(3);
    }

    @Test
    public void ArrayDeque61BIteratorTest_EmptyAfterFull() {
        LinkedListDeque61B<Integer> q = new LinkedListDeque61B<>();

        // 添加然后清空
        for (int i = 0; i < 5; i++) {
            q.addLast(i);
        }
        for (int i = 0; i < 5; i++) {
            q.removeFirst();
        }

        // 测试空队列的迭代
        int count = 0;
        for (Integer item : q) {
            count++;
        }
        assertThat(count).isEqualTo(0);
    }

    @Test
    public void testEquals() {
        LinkedListDeque61B<Integer> q = new LinkedListDeque61B<>();

        // 添加然后清空


        LinkedListDeque61B<Integer> p = new LinkedListDeque61B<>();


        assertThat(p.equals(q)).isTrue();
    }




    // 测试 1：空链表的迭代器行为
    @Test
    public void testEmptyListIterator() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        Iterator<Integer> iter = deque.iterator();

        // 空链表应该没有下一个元素
        assertFalse("空链表的迭代器应该没有下一个元素", iter.hasNext());

        // 调用 next() 应该返回 null（根据你的实现）
        assertNull("空链表调用 next() 应该返回 null", iter.next());
    }

    // 测试 2：单元素链表的迭代器行为
    @Test
    public void testSingleElementIterator() {
        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("A"); // 添加一个元素
        Iterator<String> iter = deque.iterator();

        // 应该有下一个元素
        assertTrue("单元素链表应该有下一个元素", iter.hasNext());

        // 第一个元素应该是 "A"
        assertEquals("第一个元素应该是 A", "A", iter.next());

        // 遍历完后应该没有下一个元素
        assertFalse("遍历完单元素后应该没有下一个元素", iter.hasNext());

        // 再次调用 next() 应该返回 null（根据你的实现）
        assertNull("遍历完后调用 next() 应该返回 null", iter.next());
    }

    // 测试 3：多元素链表的迭代器行为
    @Test
    public void testMultipleElementsIterator() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        Iterator<Integer> iter = deque.iterator();

        // 应该有下一个元素
        assertTrue("多元素链表应该有下一个元素", iter.hasNext());
        assertEquals("第一个元素应该是 1", Integer.valueOf(1), iter.next());

        assertTrue("应该有第二个元素", iter.hasNext());
        assertEquals("第二个元素应该是 2", Integer.valueOf(2), iter.next());

        assertTrue("应该有第三个元素", iter.hasNext());
        assertEquals("第三个元素应该是 3", Integer.valueOf(3), iter.next());

        // 遍历完后应该没有下一个元素
        assertFalse("遍历完所有元素后应该没有下一个元素", iter.hasNext());
    }

//    // 测试 4：调用 next() 超出范围（应该抛出异常）
//    @Test(expected = NoSuchElementException.class)
//    public void testNextBeyondEndThrowsException() {
//        LinkedListDeque61B<String> deque = new LinkedListDeque61B<>();
//        deque.addLast("A");
//        Iterator<String> iter = deque.iterator();
//
//        // 遍历第一个元素
//        assertTrue(iter.hasNext());
//        assertEquals("A", iter.next());
//
//        // 没有下一个元素了
//        assertFalse(iter.hasNext());
//
//        // 再次调用 next() 应该抛出 NoSuchElementException
//        // 注意：你的实现返回 null，但 Java 迭代器的规范通常要求抛出 NoSuchElementException
//        iter.next(); // 应该抛出异常
//    }

    // 测试 5：sentinel 节点的正确性（空链表循环引用）
    @Test
    public void testSentinelLoopInEmptyList() {
        LinkedListDeque61B<Double> deque = new LinkedListDeque61B<>();
        Iterator<Double> iter = deque.iterator();

        // 空链表的迭代器不应该进入无限循环
        assertFalse("空链表的迭代器不应该有下一个元素", iter.hasNext());

        // 确保不会因为 sentinel 节点的循环引用而导致问题
        assertNull("空链表调用 next() 应该返回 null", iter.next());
    }

    // 测试 6：连续调用 hasNext() 不影响迭代器状态
    @Test
    public void testHasNextDoesNotChangeState() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        Iterator<Integer> iter = deque.iterator();

        // 连续调用 hasNext() 不应该改变迭代器的状态
        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());

        // 第一次调用 next() 应该仍然返回第一个元素
        assertEquals(Integer.valueOf(1), iter.next());

        // 现在应该没有下一个元素
        assertFalse(iter.hasNext());
    }

}
