import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import deque.*;


public class IteratorTest {

    @Test
    public void ArrayDeque61BIteratorTest_Basic() {
        ArrayDeque61B<Integer> q = new ArrayDeque61B<>();

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
        ArrayDeque61B<Integer> q = new ArrayDeque61B<>();
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
        ArrayDeque61B<Integer> q = new ArrayDeque61B<>();

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
        ArrayDeque61B<Integer> q = new ArrayDeque61B<>();

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
        ArrayDeque61B<Integer> q = new ArrayDeque61B<>();

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
        ArrayDeque61B<Integer> q = new ArrayDeque61B<>();

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
        ArrayDeque61B<Integer> q = new ArrayDeque61B<>();

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
        ArrayDeque61B<Integer> q = new ArrayDeque61B<>();

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
}
