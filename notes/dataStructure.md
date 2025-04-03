

# WHY,BABY,WHY？

由于`CS61B`来到了`midterm review`，促使自己对前面学习的java在一些小坑点上做一些总结

# JAVA--BASIC

## 启动java程序

java所有的代码都需要在类里面完成，但是还是有一个程序入口，也就是`main`方法，虽然`Python`也是面向对象的语言，但是java就是直接强制你使用类，如何呢，是不是很权威

```java
public class demo {
    public static void main(String[] args) {
        System.out.println("Man, what can I say? I'm just a demo.");
    }	
}
```

## class

#### public
> 每个文件只有一个`public`类，每个类里面可以有嵌套类

```java
public class demo {
    
    class nestdemo {

        void nest() {
            System.out.println("I'm an nest class.");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Man, what can I say? I'm just a demo.");
        nestdemo nest = new demo().new nestdemo();
        nest.nest();
    }
    
}

class Innerdemo {

    void inner() {
        System.out.println("I'm an inner class.");
    }
}
```

在这里的代码中，有两个类，但是只能有一个类有拥有`public`这个关键字，同时每个类中可以产生嵌套类

#### static

> `static`关键字使得方法/变量是类的变量,而不是实例的方法/变量

例如Math.pow()这种方法就是static方法,不需要实例化。这种适合在你的类是一些工具类，需要实例化，导入就可以给别人进行使用，这就是很爽的

#### private

> 私有成员（方法/类/变量）都可以使用这个来修饰，用于封装自己的类

在你的类中，有的信息或者实现的细节不想让使用这个类的人知道，或者是他们也不想知道，此时就可以使用`private`来进行封装，对他们只是暴露了你想给他们使用的接口。这个在`List`的`proj`已经有使用，典型的就是`Node`节点的私有化。

#### this

> `this`用于指定为自己的变量/方法

当传入的参数是size，但是自己也有一个参数是size的时候，如果直接写`size = size`，那这时候编译器都懵逼了，真假美猴王是吧，此时如果指定`this.size = size`就可以很完美的解决，而且代码也更加容易读，这个`this`不是强制什么时候都要加的，但是在这种很容易混淆的时候肯定是跑不掉的

```java
public class thisDemo{
    public int size;
    
    public void changeSize(int size){
        //size = size这是不可取的
        this.size = size;//此时清晰明了是外部参数size赋值给内部的成员size
    }
}
```

## 继承

继承的时候牢记`is-a`原则

`extends`单继承，`implements`单/多继承

#### implements

> 多继承
>
> 只能继承interface
>
> 需要重写所有的方法

需要注意的是，子类必须要`override`所有在`interface`里面出现的方法，虽然后来引入了`default`方法使得某些方法需要在`@override`

```java
public interface Animal{
    void run();
}
```

```java
public class Dog implements Animal{
    @override //记住这个@override的语法，他可以帮你在检查自己的重写方法是否正确，因为参数不对也是会导致没有重写成功，简单来说就是要求一摸一样的外壳
    void run(){
        //do sth.
    }
}
```

在这里我们需要牢记`Dog is-a Animal`，并且只是实现了一个接口，实际上是可以实现多个接口的

#### extends

> 单继承
>
> 不可继承private成员

类与类之间，接口与接口之间的继承都是使用`extends`关键字，并且只能单继承，不能继承很多个

```java
class Animal {
    void eat() {
        System.out.println("Animal eats food.");
    }
}

class Dog extends Animal {
    public void eat() {
        System.out.println("Dog eats bones.");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.eat();
    }
}
```

`extends`不需要像`interface-implements`那样在父类需要声明是接口，这个是直接继承就行

#### 静态type&动态type

> 静态是编译时候的类型，动态是运行时的类型

在继承中有一个很关键的原则就是`is-a`原则，也就是子类都是`is-a`父类，就像人是动物，狗是动物一样的道理。

```java
class Animal {
    void eat() {
        System.out.println("Animal eats food.");
    }
}

class Dog extends Animal {
    public void eat() {
        System.out.println("Dog eats bones.");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog();//声明类型是Animal，运行类型是Dog
        dog.eat();
    }
}
```

还是使用上面那段的代码，但是只是改变了一下dog的`静态类型/声明类型`

需要注意的是，在实现继承的时候，Dog is-a Animal，因此Animal的静态类型可以接受Dog。但是Animal is-a Dog？？？这显然是不一定成立的，所以使用`Dog dog = new Animal()`这个就是错误的

换一种方式理解就是Dog是通过继承出来的，那么他拥有Animal的所有方法，因此就可以赋值给Animal的类型，但是转换过来，Animal的方法肯定是小于等于Dog的方法的，那Animal肯定是满足不了成为一个Dog的，所以这种赋值就是不合法的。因此`子类is-a父类`需要牢记

# 数据结构

## 链表

在cs61b实现了以下几种链表，由于链表比较简单就不再详细说明了

`单向链表`-->`含有哨兵节点的单向链表`-->`含有双哨兵节点的双向链表`-->`含有单哨兵节点循环链表`

## Disjoint Sets

`快速联合+路径压缩`

