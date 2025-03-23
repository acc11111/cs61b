import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }


     @Test
     /* test the add first method */
     public void addFirstTestBasic(){
         Deque61B<String> lld1 = new ArrayDeque61B<String>();


         lld1.addFirst("!");
         lld1.addFirst("say");
         lld1.addFirst("I");
         lld1.addFirst("can");
         lld1.addFirst("what");
         lld1.addFirst(",");
         lld1.addFirst("Man");



         assertThat(lld1.toList()).containsExactly("Man",",","what","can","I","say","!").inOrder();

     }

    @Test
    /* test the add first method */
    public void addLastTestBasic(){
        Deque61B<String> lld1 = new ArrayDeque61B<String>();

        lld1.addLast("Man");
        lld1.addLast(",");
        lld1.addLast("what");
        lld1.addLast("can");
        lld1.addLast("I");
        lld1.addLast("say");
        lld1.addLast("!");




        assertThat(lld1.toList()).containsExactly("Man",",","what","can","I","say","!").inOrder();

    }

    @Test
    /* test the add last method */
    public void isEmptyTestBasic(){
        Deque61B<String> lld1 = new ArrayDeque61B<String>();

        assertThat(lld1.isEmpty()).isTrue();

        lld1.addLast("Man");
        lld1.addLast(",");
        lld1.addLast("what");
        lld1.addLast("can");
        lld1.addLast("I");
        lld1.addLast("say");
        lld1.addLast("!");




        assertThat(lld1.toList()).containsExactly("Man",",","what","can","I","say","!").inOrder();

    }

    @Test
    /* test the add last method */
    public void sizeTestBasic(){
        Deque61B<String> lld1 = new ArrayDeque61B<String>("Man");

//        assertThat(lld1.isEmpty()).isTrue();

//        lld1.addLast("Man");
        lld1.addLast(",");
        lld1.addLast("what");
        lld1.addLast("can");
        lld1.addLast("I");
        lld1.addLast("say");
        lld1.addLast("!");




        assertThat(lld1.toList()).containsExactly("Man",",","what","can","I","say","!").inOrder();

    }

   @Test
    /* test the add last method */
    public void removeLastTestBasic(){
        Deque61B<String> lld1 = new ArrayDeque61B<String>("Man");

        assertThat(lld1.isEmpty()).isFalse();

//        lld1.addLast("Man");
        lld1.addLast(",");
        lld1.addLast("what");
        lld1.addLast("can");
        lld1.addLast("I");
        lld1.addLast("say");
        lld1.addLast("!");

        String  a = lld1.removeLast();


        assertThat(a).isEqualTo("!");

        assertThat(lld1.toList()).containsExactly("Man",",","what","can","I","say").inOrder();

    }

    @Test
    /* test the add last method */
    public void getTestBasic(){
        Deque61B<String> lld1 = new ArrayDeque61B<String>("Man");

        assertThat(lld1.isEmpty()).isFalse();

//        lld1.addLast("Man");
        lld1.addLast(",");
        lld1.addLast("what");
        lld1.addLast("can");
        lld1.addLast("I");
        lld1.addLast("say");
        lld1.addLast("!");

        String  a = lld1.getRecursive(2);


        assertThat(a).isEqualTo("what");

//        assertThat(lld1.toList()).containsExactly("Man",",","what","can","I","say").inOrder();

    }



}
