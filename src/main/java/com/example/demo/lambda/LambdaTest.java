package com.example.demo.lambda;

import com.example.demo.pojo.po.Author;
import com.example.demo.pojo.po.Book;
import com.example.demo.pojo.po.Gameplayer;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author :Ligou
 * @date : 2023-6-3 19:55
 */
public class LambdaTest {
    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("args = " + args);
//            }
//        }).start();
//        //如上，可简写成
//        new Thread(() -> {
//            System.out.println("args = " + args);
//        }).start();

//        System.out.println("args = " + cal((int left, int right) -> {
//
//            return left;
//        }));
//        cal((left, right) -> 0);
//
//        pritN(value -> false);
        List<Author> auth = getAuthors();
//        System.out.println("auth = " + auth);
//        List<Author> collect = auth.stream().filter(author -> author.getId() == 0).collect(Collectors.toList());
//        System.out.println("collect = " + collect);
//        auth.stream().distinct()
//                .filter(author -> author.getAge() <= 11)
//                .map(author -> author.getName())
//                .forEach(author -> System.out.println("author.getName() = " +
//        auth.stream().distinct().sorted((o1, o2) -> o1.getAge()-o2.getAge()).forEach(author -> {
//            System.out.println("author.getName() = " + author.getName());
//        });
//        auth.stream().distinct().sorted((o1, o2) -> o2.getAge()- o1.getAge()).limit(2).forEach(author -> System.out.println("author = " + author));
//        auth.stream().distinct().sorted((o1, o2) -> o2.getAge()-o1.getAge()).skip(1).forEach(author-> System.out.println("auth = " + author));
//        auth.stream().flatMap( author -> author.getBooks().stream()).distinct().forEach(book -> System.out.println("book = " + book));
//        auth.stream().distinct().flatMap(author -> author.getBooks().stream()).map(book -> book.getCategory()).flatMap(category-> Arrays.stream(category.split(","))).distinct().forEach(category-> System.out.println("category = " + category));
//        Book book = auth.stream().distinct().flatMap(author -> author.getBooks().stream()).max((book1, book2) -> book1.getScore() - book2.getScore()).get();
//        System.out.println("book.getScore() = " + book.getScore());

//        Map<String, List<Book>> collect = auth.stream().distinct().collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()));
//        System.out.println("collect = " + collect);

//        System.out.println("collect = " + collect);

//        Author author1 = auth.stream().filter(author -> author.getAge() > 13).findAny().get();
//        System.out.println("author1 = " + author1);
//        Gameplayer gameplayer = null;
//        Optional<Gameplayer> optionalGameplayer = Optional.ofNullable(gameplayer);
//        optionalGameplayer.ifPresent(gameplayer1 -> System.out.println("gameplayer = " + gameplayer1));

        auth.stream().distinct().collect(Collectors.groupingBy(new Function<Author, Object>() {
            @Override
            public Object apply(Author author) {
                return null;
            }
        }));

    }

    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        Author author2 = new Author(2L, "亚拉索", 15, "狂风也追逐不上他的思考速度", null);
        Author author3 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
        Author author4 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学,爱情", 88, "用一把刀划分了爱恨"));
        books1.add(new Book(2L, "一个人不能死在同一把刀下", "个人成长,爱情", 99, "讲述如何从失败中明悟真理"));

        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(4L, "吹或不吹", "爱情,个人传记", 56, "一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L, "你的剑就是我的剑", "爱情", 56, "无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author, author2, author3, author4));
        return authorList;
    }


//    public static int cal(IntBinaryOperator intBinaryOperator) {
//        int a = 10;
//        int b = 20;
//        return intBinaryOperator.applyAsInt(a, b);
//    }
//
//    public static void pritN(IntPredicate intPredicate) {
//        int[] arr = {1, 2, 3, 4};
//        for (int i : arr) {
//            if (intPredicate.test(1)) {
//                System.out.println(i);
//            }
//        }
//    }
//
//    public static List<Author> createAuth() {
//        List<Author> list = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            Author author = new Author();
//            author.setId(new Long(i));
//            author.setAge(i + 10);
//            author.setName("auth" + i);
//            author.setLevel("level" + i);
//            if (i % 2 == 0) {
//                author.setSex("M");
//
//            } else {
//                author.setSex("W");
//
//            }
//            List<Book> bookList = new ArrayList<>();
//            for (int j = 0; j < 3; j++) {
//                Book book = new Book();
//                book.setId(new Long(j));
//                book.setName("book" + j);
//                book.setPrice(new BigDecimal(300 / (j + 1)));
//                book.setScore(i * j * 5);
//                book.setPublishDate(new Date());
//                bookList.add(book);
//            }
//            author.setBookList(bookList);
//            if (i == 0) {
//                list.add(author);
//
//            }
//            list.add(author);
//
//        }
//        return list;
//    }
}
