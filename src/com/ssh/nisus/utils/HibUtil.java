package com.ssh.nisus.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * 生成session的工具类
 * 利用SessionFactory生成我们要使用的Session, 前者一个项目(应用)通常用一个.
 * - 静态代码块解决
 */
public class HibUtil {


    private static SessionFactory sessfactory;      // 持有

    static {

        try {
            // 读取根目录注配置文件
            Configuration conf = new Configuration().configure();
            sessfactory = conf.buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("初始化SessionFactory失败!");
        }
    }

    private static Transaction tx;
    private static Session sess;

    /**
     * @return
     */
    // 接下来利用工厂创建session
    public static Session openSession() {
        return sessfactory.openSession();       // $这个方法得到session不是线程绑定的\\需要手动关闭
    }

    /**
     * 获取线程绑定的session
     *
     * @return
     */
    public static Session getCurrentSession() {
        return sessfactory.getCurrentSession();     // 获取当前线程上的session\\不需要手动关闭

    }


    public static Session start() {
        // 获取Session
        sess = sessfactory.getCurrentSession();
        tx = sess.beginTransaction();
        return sess;
    }



    public static void commit() {
        tx.commit();
    }

    public static void rollback() {
        tx.rollback();
    }


    /**
     * 关闭sessionFactory
     */
    public static void closeSessionFactory() {
        sessfactory.close();
    }




}
