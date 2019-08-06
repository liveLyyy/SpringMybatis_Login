 自动注入
 =======
 1、在spring配置文件中,对象名和ref="id"id名相同使用自动注入，可以不配置<property/><br>
 2、两种配置方法<br>
 >2.1、在<bean>中通过autowire=""配置，只对这个bean生效<br>
 >2.2、在<beans>中通过default-autowire=""配置，表当前文件中所有的<bean>都是全局配置内容<br>
 
 3、autowire的取值范围<br>
 >3.1、default:默认值，根据全局default-autowire=""值，默认全局和局部都没有配置情况下，相当于no<br>
 >3.2、no不自动注入<br>
 >3.3、byName通过名称自动注入，在spring容器中找类的id<br>
 >3.4、byType:根据类型注入,在spring中不可以出现两个相同的bean<br>
 >3.5、constructor:根据构造方法注入：根据对应参数的构造方法（构造方法参数中包含注入），底层使用byName，构造方法参数和其他<bean>的id相同<br>
 
 Spring加载属性文件
 =================
 1、添加属性文件记载，并且在<beans>中开启自动注入需要注意的地方<br>
 >1.1、SqlSessionFactoryBean的id不能叫做SqlSessionFactory<br>
 >1.2、把sqlSessionFactory换成sqlSessionFactoryBeanName<br>
 ```xml
 <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!--扫描包路径-->
        <property name="basePackage" value="com.liyan.mapper"/>
<!--        <property name="sqlSessionFactory" ref="factory"/>-->
        <property name="sqlSessionFactoryBeanName" value="factory"/>
    </bean>
```
2、在被Spring管理的类中通过@Value("${key}")取出properties中的内容<br>
>2.1、添加扫描注解
```xml
   <context:component-scan base-package="com.liyan.service.Impl"/>
```
>2.2、在类中添加,key和变量名可以不相同，变量类型任意，只要保证key对应的value能转换这个类型就可以<br>
```java
  @Value("${}")
  private String name;
```

[Scope属性](https://www.cnblogs.com/liaojie970/p/8302749.html)
========
1、<bean>的属性<br>
2、控制对象有效范围(单例，多例等)<br>
>2.1、单例。一次只能创建一个对象，也就是只有一个实例<br>
>2.2、多例。一次可以创建多个对象，可以有多个实例，每次请求（将其注入到另一个bean中，或者以程序的方式调用容器的getBean()方法），都会产生一个新的bean实例<br>

3、<bean>标签对应的对象默认是单例<br>
>3.1、无论获取多少次都是同一个对象<br>

4、Scope取值对象<br>
>4.1、singleton：默认值，单例<br>
>4.2、prototype：多例，每次获取重新实例化<br>
>4.3、resquest ：每次请求重新实例化<br>
>4.4、session：：每个会话对象内，对象都是单例的<br>
>4.5、application：在application对象内是单例<br>
>4.6、global session：spring推出的一个对象，依赖于spring-webmvc-portlet，类似于session<br>

单例设计模式
===========
1、作用：在应用程序有保证最多只能有一个实例<br>
2、优点:提升运行效率；可以实现数据共享，application对象<br>
3、[懒汉式](https://www.cnblogs.com/crazy-wang-android/p/9054771.html)：
>3.1、对象只有被调用时才去创建<br>
>3.2、由于添加了锁所以效率很低<br>
```java
package com.liyan.test;

public class SingleTon {
    //由于对象需要被静态方法调用，把方法设置为static
    //由于对象时static，必须要设置访问权限修饰符为private，如果是public可以直接调用对象，不执行访问入口
    private static SingleTon singleTon;
    /**
     * 方法名和类名相同，无返回值
     * 其他类不能实例化对象
     * 对外提供访问入口
     */
    private SingleTon() {
    }
    /**
     * 实例方法，实例方法必须通过对象调用
     * 设置方法为静态方法
     */
    public static SingleTon getInstance() {
        //添加逻辑如果实例化过，直接返回
        if (singleTon == null) {
            /**
             * 多线程访问下，可能出现if同时成立的情况添加锁*/
            synchronized (SingleTon.class) {
                //双重验证
                if (singleTon == null) {
                    singleTon=new SingleTon();
                }
            }
        }
        return singleTon;
    }
}
```      
4.饿汉式<br>
>4.1、解决类懒汉式中多线程访问可能出现同一个对象和效率低的问题<br>
```java
public class SingleTons {
    //在类加载时进行实例化
    private static SingleTons singleTon=new SingleTons();
    private SingleTons() {
    }
    public static SingleTons getInstance() {
        return singleTon;
    }
}
```                                                                                                                                                                                                                                                                                                                                                    