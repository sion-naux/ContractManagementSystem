# ContractManagementSystem
数据库合同管理作业，我们爱冯妈！
## 配置数据库连接
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
