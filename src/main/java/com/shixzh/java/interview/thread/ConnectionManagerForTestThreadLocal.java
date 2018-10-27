package com.shixzh.java.interview.thread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 假设有这样一个数据库链接管理类，这段代码在单线程中使用是没有任何问题的，
 * 但是如果在多线程中使用呢？很显然，在多线程中使用会存在线程安全问题：
 * 第一，这里面的2个方法都没有进行同步，很可能在openConnection方法中会多次创建connect；
 * 第二，由于connect是共享变量，那么必然在调用connect的地方需要使用到同步来保障线程安全，
 * 因为很可能一个线程在使用connect进行数据库操作，而另外一个线程调用closeConnection关闭链接。
 *
 * @author: ZhaoShixiang <br>
 * @date: 2018/10/27 10:55
 */
public class ConnectionManagerForTestThreadLocal {

    private static Connection connect = null;

    public static Connection openConnection() {
        if (connect == null) {
            String ip = "localhost:8080";
            try {
                connect = DriverManager.getConnection(ip);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connect;
    }

    public static void closeConnection() {
        if (connect != null) {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

class UserForThreadLocal {
    private int age;
    private String name;

    public UserForThreadLocal(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserForThreadLocal{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
/**
 * 从这段代码的输出结果可以看出，在main线程中和thread1线程中，
 * longLocal保存的副本值和stringLocal保存的副本值都不一样。
 * 最后一次在main线程再次打印副本值是为了证明在main线程中和thread1线程中的副本值确实是不同的。
 */
class LocalStringLong {
    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();
    ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
    UserForThreadLocal userDefault = new UserForThreadLocal(17, "cui");
    ThreadLocal<UserForThreadLocal> userThreadLocal = new ThreadLocal<>();

    public void setValue() {
        longThreadLocal.set(Thread.currentThread().getId());
        stringThreadLocal.set(Thread.currentThread().getName());
        userThreadLocal.set(userDefault);
    }

    public void setName(String name) {
        userDefault.setName(name);
    }

    public void setUser(UserForThreadLocal user) {
        userThreadLocal.set(user);
    }

    public long getLong() {
        return longThreadLocal.get();
    }

    public String getString() {
        return stringThreadLocal.get();
    }

    public String getUser() {
        return userThreadLocal.get().toString();
    }

    public static void main(String[] args) throws InterruptedException {
        final LocalStringLong localStringLong = new LocalStringLong();
        localStringLong.setValue();
        System.out.println("Before join main id=" + localStringLong.getLong() + ", name="
                + localStringLong.getString() + ", user=" + localStringLong.getUser());

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                localStringLong.setValue();
                UserForThreadLocal user = new UserForThreadLocal(24, "ping");
                localStringLong.setUser(user);
                localStringLong.setName("ping");
                System.out.println("thread1 id=" + localStringLong.getLong() + ", name="
                        + localStringLong.getString() + ", user=" + localStringLong.getUser());
            }
        };
        thread1.start();
        thread1.join();

        System.out.println("After join main id=" + localStringLong.getLong() + ", name="
                + localStringLong.getString() + ", user=" + localStringLong.getUser());

    }
}

/**
 * 最常见的ThreadLocal使用场景为: 用来解决 数据库连接、Session管理等。
 */
class ConnectionManagerForTestThreadLocal2 {

    private static final String DB_URL = "localhost:8080";

    private static ThreadLocal<Connection> connectionHolder
            = new ThreadLocal<Connection>() {
        @Override
        public Connection initialValue() {
            try {
                return DriverManager.getConnection(DB_URL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    public static Connection getConnection() {
        return connectionHolder.get();
    }
}
/*
class ConnectionManagerForTestThreadLocal3 {
    private static final ThreadLocal threadSession = new ThreadLocal();

    public static Session getSession() throws InfrastructureException {
        Session s = (Session) threadSession.get();
        try {
            if (s == null) {
                s = getSessionFactory().openSession();
                threadSession.set(s);
            }
        } catch (HibernateException ex) {
            throw new InfrastructureException(ex);
        }
        return s;
    }
}
*/
