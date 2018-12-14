# ContractManagementSystem
数据库合同管理作业，我们爱冯妈！
### 配置数据库连接
在 database/dbConfig.java 中：  
~~~
public static String jdbcUrl = "<你的jdbcUrl>" + "?useUnicode=true&characterEncoding=utf8";  
public static String userName = "<你的用户名>";
public static String userPwd = "<你的密码>";
~~~
例如：  
~~~
public static String jdbcUrl = "jdbc:mysql://localhost:3306/wjh" + "?useUnicode=true&characterEncoding=utf8";
public static String userName = "root";
public static String userPwd = "123456";
~~~

### jsp/servelet 参考资料
https://www.cnblogs.com/cynchanpin/p/7151912.html  
知乎的这个也不错:https://www.zhihu.com/question/269916070/answer/390319061
#### 遇到“Error:(5, 50) java: 程序包javax.servlet.http不存在”解决办法  
File -> Project Structure -> Libraries -> 点"+"号，添加tomcat的lib路径（例如"C:\Program Files\tomcat8\lib"）

###2018.12.15更新
#####更改密码获取方式，不再直接将密码写入程序
#####小记：
#####如果idea提示找不到com.mysql.jdbc.Driver,试将连接jar包放入tomcat中的lib目录下
