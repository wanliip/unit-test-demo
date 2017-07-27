### 项目目的

* 1 展示springboot项目中junit框架和mockito框架实现业务代码分层的单元测试
    * 1.1 在Controller层以spring提供的MockMvc为基础,实现api层的单元测试
    * 1.2 在Service层单测,主要以@InjectMocks注解和@Mock注解,实现service与dao的依赖注入,但是dao层所有结果以mock的形式生成
    * 1.3 在Dao层,剥离了运营时依赖的mysql数据库,以嵌入式内存数据库H2作为数据源进行sql的验证和测试
* 2 相关注解讲解
    * `@RunWith`:Junit框架提供注解,示例代码中每个测试类上声明了注解`@RunWith`,该注解声明junit测试类运行时的容器,常用的容器包括`MockitoJUnitRunner`、`SpringJUnit4ClassRunner`、`AndroidJUnit4`等,示例代码主要以第一种容器做分层测试,其中区别的原理未做深入研究,但是如果使用spring的测试容器,每次都需要拉起一个spring容器,构建时会非常慢。
    * `@InjectMocks`:Mockito框架提供注解,被该注解标记过的属性,Mockito框架会将`@Mockito`标记的属性注入到其中,也可以认为,被该注解标记的属性,是你真正要测试的,而其他依赖调用的输入、输出和内部逻辑是你不需要关心的,这些往往都是你mock出来
    * `@Mockito`:Mockito框架提供注解,在上面基本讲了,被该注解标记的属性,任何调用不会真实执行,输入输出都是mock出来的
    * `@Rule`:Junit框架提供注解,Junit4新特性,该注解功能很强大,本项目中只用到了异常情况的测试,配合`ExpectedException`来验证逻辑中会产生的异常
    
        