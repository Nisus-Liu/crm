package com.ssh.nisus.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * dao层实现类生成工厂
 * Author: Nisus-Liu
 * Email: liuhejunlj@136.com
 * Date: 2017-11-16-22:58
 */
public class BeanFactory {
    // map用来粗放创建的bean们
    private static Map<String, Object> beans = new HashMap<String, Object>();
    // 工厂只需要在项目启动创建一次即可 --> 静态代码块
    static{
        SAXReader rder = new SAXReader();
        try {
            ClassLoader loader = BeanFactory.class.getClassLoader();
            Document xml = rder.read(loader.getResourceAsStream("beans.xml"));
            Element root = xml.getRootElement();
            List<Element> el2s = root.elements();
            for (Element el : el2s) {
                String id = el.attributeValue("id");
                String className = el.attributeValue("class");
                // 获取当前类
                Class clz = Class.forName(className);
                // 获取当前对象
                final Object o = clz.newInstance();
//                beans.put(id, o);     // 这是初级的获取对象自己

                // 进阶: 获取对象的代理对象
                Object proxyInstance = Proxy.newProxyInstance(
//                        loader,
                        BeanFactory.class.getClassLoader(),
                        clz.getInterfaces(),        // 返回代理对象刚好实现了id指定的接口, 这样用的时候就可以id指定的类接收
                        new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                // 代理对象调用方法时, 这个method就产生了
                                System.out.println("开始调用方法：" + o.getClass().getName() + "." + method.getName());

                                // 调用被对象自己的方法
                                Object result = method.invoke(o, args);

                                System.out.println("调用方法结束: " + o.getClass().getName() + "." + method.getName());
                                return result;
                            }
                        }
                );

                beans.put(id, proxyInstance);
            }


        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(String className) {
        return beans.get(className);
    }

}
