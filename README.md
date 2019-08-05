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
>4.3、resquest