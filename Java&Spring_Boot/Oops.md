# OOPS
- Revolves around real world entity. Has behaviour and properties.
- C and some other langauges are procedural/functional programming,no focuse on data and it's hiding. C++,Java,pyhton uses oops.


## Objects and Classes
- Objects has two things properties/state and behavior/function.
- Dog
    - Properties :  age,color,breed[data variables]
    - Behavior : Bark,walk, run,smell[data methods]
- Class is blueprint of object, you write properties and behavior in class.We create as many object we want out of it.
- ClassName objectReferenceName = new CLassName(); //syntax for creating a new object

## 4 Pillars/Concepts of OOPS
### Data Abstraction
- Hiding the internal implementation and showing only essential functionalities to user.
- Can be achieved through Interface and abstract class.[Interface can have variables and method declaration, in implementation class we declare it]
- Mobile we have a dial number method we are not sure of the whole process.
- Advantages
    - Increase security and confidentiality
    - Simplifies client/user working.

### Data Encapsultaion
- Bundling the data and code working on it in a single unit(class)[data hiding][medicine inside a capsule]
- Can be achieved through private variable in class and if required providing public getter and  public setters.
- Advantages
    - Loosly coupled data
    - Better access and security

### Inheritance
- Capability of Class to inherit properties[both func and variables] from their parent class, so no need to rewrite and redefine.
- Can be achieved through extends or through interface.
    - childClass extends parentClass{props.....}
- Types of inheritance
    - Single 
    - Multilevel
    - Heirarchial
    - Multiple[Java resolves diamond problem(same properties in two class and child inherit both,use interface) through interface]
Advantages
    - Code resubality
    - Achieve polymorphism

### PolyMorphism
- Poly mean many and Morphism means form
- Same method beahve differently in different situation
- water can be solid,liquid,gase
-Types
    - Compile Time/Static/Method Overloading(methods signature differs, return type doesn't matter)
    - Run Time/Dynamic/Method Overriding(inherited classes different implementations,signature same)

## Object Relationships

### Is-a Relationship
- Acheived through inheritance
- Dog is an animal
- Inheritance form an Is-a relationship b/w its parent child class

### Has-a Relationship
- When an object is used in other class, It's Has-A relationship
- Could be one-one,many-many,many-one
- School has students,School has classes,Class has roomNumber
- Association : relationship b/w 2 diff objects
    - Composition[Strong relation] : Ending of one object results in others enfding
    - Aggregration[weak relation] : Can surviv e Individually

# JDBC(Java Db Connectivity)
## Steps
1. Import the java package[java.sql.*]
2. Load & Register the driver[according to db used different driver]
    -  Load : download the jar driver and use it,load it
    - Register : forName("driverName) method fullfill
3. Establish the connection[Connection Interface]
4. Create the statement[different types of statement]
5. Execute the query
6. Process results
7. Close conenction
### Basic Program Syntax
```
import java.sql.*;

main(){
    forName("driverName.Driver);
    Connection con = DriverManager.getConnection(
        "url",//jdbc:dbName(mysql)://hostName:port/dbname
        "userNameDb",
        "password"
    );

    Statement st = con.createStatement();

    ResultSet rs = st.executeQuery("select * .....");//rs will be on first row in result set at start

    while(rs.next())
    {

    rs.getInt();//accordin to col use command
    rs.getString();//accordin to col use command
    }

    st.close();
    con.close();
}
```
### Statement Type
- DDL (create table),DML(data update),DQL(query and get result/read),TCL(transaction)
    - DQL : executeQuery(return ResultSet)
    - DML : executeUpdate(return int, no of rows affected)
- We can use preparedStement instead of simple Stement
```
String query = "insert into table values(?,?)";
PreparedStement ps = con.preparedStement(query);

ps.setInt(1,someNumber);
ps.setString(2,"someString"); //int or string depends on dataType of column

ps.executeQuery();
```

# Servlet
## Working
- Client sends request to server requiring a response(usually a static/dynamic page)
- Static page response is simple,dynamic is tricky.
- Dynamic is bit tricky,server send request to helper application(web container) and provide you a response, and server return after some operation return this html file.
- Webcontainer usually tomcat,glassFish,websphere.
- Server has a deployDescriptor file named  Web.xml which has info which servelet should be called for a particular type of request.
- Servlet has our working code. Basic
Syntax :
```
class myClass extends HttpServlet
```
- Above can be replace with @WebServlet(/abc.html), latest addtion(maybe more update might have added)
- Sends response in html format, can also send json,xml etc.
- And this is sent back to client in form as ResponseObject.

```
images

```

## Project Working
- Work on Intellij Ultimate or Eclipse need to create web project/J2EE project.Need to conenct with apache tomcat
- Create your java class which extends HttpServlet, so you have 3 methods doGet,doPost and service(whose default is get).can use according to you HTTP methods type you need.(HttpServletRequest req,HttpServletResponse res has this 2 as parameters ) 
- In folder structure[src/packageName/webapp/WEB-INF/web.xml] there is web.xml file which has our servelet info which class to execute and on which path
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd" id="WebApp_ID" version="4.0">
  
  <servlet>
  	<servlet-name>giveSomeName</servlet-name>
  	<servlet-class>com.package.ServletClassName</servlet-class>
  </servlet>
  
  <servlet-mapping>
  	<servlet-name>giveSomeName</servlet-name> //same name as in servlet
  	<url-pattern>/add</url-pattern> //the servlet will be accessed by this path
  </servlet-mapping>
  
</web-app>
```
- RequestDispatcher is a way to ping/contact with other servlet 
```
RequestDispatcher rd = req.getRequestDispatcher("yourOtherServletPath");
rd.forward(req, res);//can set attribute in req as well, which can be used by the servlet you called
```

## HttpServletRequest req and HttpServletResponse res
- Request has the values passed on by client, may include tags , images etc.Response has all the data info client needs that server provides.We can send type of text, html,video,json.
- We don't need to create this objects, service(req,res) like methods take this values, this object created by tomcat,This both are interfaces 

## Calling servlet from another Servlet
- RequestDispatcher : Using this class, we can create object and call other servlet with required req, res(add data in req to be sent to other servlet)(rd.forward())
- Above works if these servlet are part of same website/domain.
- Like if want to go to payment site it should be done from client side and redirected else client won't know what's happening
- sendRedirect(), client will know we are being redirected, basically tells client browser visit this site/servlet now
- in redirect req,res are different so we need to have sessionManagement, if need to pass args sendRedirect("/path?key"+valueTOSend)
- This comes under sessionManagement(URL reqriting), another way is cookie

## HttpSession
- Session : maintaining data throughout the session and can be used when calling different servlets/services, session you can get by tomcat servlet, by request so
```
HttpSession session = req.getSession();
session.setAttribute(name,value);
```
- can fetch value if present, you can remove the attribute as well.

## Cookies
- cookie comes first from server/servlet to client and then sent back from client to server/servlet
```
Cookie cookie = new Cookie("key","value") //both key/value take string

//fetch cookie like this on a servlet you called
Cookie cookie[] =  req.getCookies();
```
## Servlet Context & ServletConfig
- To specify initial info/parmaeter[looks like application.properties for spring boot], in web.xml we add this for servlet
```
<context-param>
    <param-name>NAME</param-name>
    <param-value>madhur</param-value>
</context-param>

<context-param>
    <param-name>PHONE</param-name>
    <param-value>lava</param-value>
</context-param>


//now in your servlet use above value
ServletContext ctx = req.getServletContext();
String str = ctx.getInitParameter("NAME");
```
- Above is for ServletContext, and this will be shared by all servlet
- What if we want separate context for servlet?We use servlet config
```
<servlet>
  	<servlet-name>abc</servlet-name>
  	<servlet-class>com.testservlet.AddServlet</servlet-class>
    //above was earlier used now adding for servletConfig
    <init-param>
  		<param-name>NAME</param-name>
  		<param-value>KhajurFromServletConfig</param-value>
  	</init-param> <!-- used as ServletConfig-->
</servlet>
```
- As servletConfig is servlet specific they will be preffered over servletContext in case of same param-name(i.e same key), usage servletConfig create it's object and use in servlet.

## Servlet Annotation Configuration
- Like @RestController,@Service etc over S.Boot 
@WebServlet("/path")
class someClass extends HttpServlet{}, it make that servelt to be called when calling that endpoint so no need to do in web.xml servlet and servletMapping.

## JSP
### Why JSP
- Currently we are only sending/printing some data.
- Now it is being used at client side browser so HTML, you can send some HTML tags along with your data, and it is troublesome to send all branckets in print statements.
- So new feature was Introduced JSP(Java Server Pages),JSP is like servlet but you can write java code inside the HTML code(in servlet we were trying to write/print html code inside java) and we get "request" object(use request.get....()) by default in jsp also out to be used as out.printn, need to create file like add.jsp for creating jsp.
- <% code written in between is java code and executed%>
- In fron end in actions instead of calling "add"(servlet name) servlet you can now call add.jsp, so now jsp will be called

### JSP/Servlet comparision/Working
- JSP code gets converted into servlet, just it is easier for developer to create HTML page in JSP
- How it converts JSP to servlet
    - demo.jsp becomes public class demo_jsp extends HttpServlet
    - your <% %>(called as scriptlet) will go inside service Method, and by deafult you get request, response and out object
    - <%! %>(called as declaration) can be used to declare method , variables outside the default service method.
    - <%@ page import="java.util.*,other package">(directive) for importing package
    - <%= k %>(called expression), will act as out.println(k)
- If you want to show data on HTML page use JSP else use servlet.
- Tools(in netbeans etc) availble to convert JSP to servlet 

### JSP Directive
- for directive <%@ %> '@' needs to be used
- @page : for impoting a package
- @include : for include an another jsp page in your jsp page
- @taglib : use external tags
- @page usage
    - various type of attributes are availble, like extends,import,session,contentType,language="java' by default etc etc
    - import staattributetement you can use several time else others are used only once
    ```
        <%@page % attribute="value" attribute="value"..... %>
    ```
- @include
    - <%@ include file="filneName.jsp"%>
    - like example we can have header.jsp and import it every jsp page you want
- @taglib
    - <%@ taglib uri="uri" prefix="fx" %>
    - for external tags

### Implicit Objects
- These are builin Objects(can be used in scriptlet and expression), so no need to instantiatem,directly refere them
- Objects which are auto created are
    - request (HttpRequest)
    - response (HttpResponse)
    - pageContext (PageContext)
        - can set the attribute and values, another option to use instead of request and response
        - By default scope remains in the same page only, not like session which is availble for whole session
        - you can change the scope as well if want
    - out(JspWriter ~ PrintWriter Object)
    - session (HttpSession)
    - application (servletContext)
    - config (ServletConfig)

### Exception Handling in JSP
- We are used to using try catch block, in servlet we can use try catch as normally we use in java
- Can do similar in jsp, but we don't want to show error shown in client screen, we can show a seaprate Error page to client.
- In Jsp diective @page can have attribute errorPage="error.jsp", so this jsp will be viewed if some error occurs, in error.jsp we can add attribute isErrorPage="true" so it will auto print error as well if we want

### JSP with JDBC
- You can import package and use that with same steps for jdbc, and you can write your code accordingly
- We have to add jar of driver to be used.

### MVC using Servlet & JSP
- JSP look easy to write code but takes time to convert to servlet so time issue, so don't write business code inside JSP
- So JSP is only for view, and we have a controller who has an ednpoint which client will call it will fetch data if any and get view and return to client

Client ---> /getPage ----> controller(gets data(model)/operations if any)--->View ---- Client
- So controller is created using Servlet, View is JSP, Model is POJOs(java class/object)
- Any process should be done in controller and realted servlet.
- N- Tier Architecture : intead of all processing in Controller servlet use Service Classes servlets, DAO Classes can also be added(base architecture of Spring and Spring Boot)

### JSTL(JSP Standard Tag Library)
```
RequestDispatcher rd = request.getRequestDispatcher("display.jsp");
rd.forward(request,response);
//this way can use jsp 
```
- in request object if any attribute is set by let's say "label" we can use it by ${label}, usage of JSTL, various other way to use, can use in jsp, servlet etc this ${attributeName}
- Need to import jstl core, need to download jar, or in maven can add dependency 
```
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```
- now use <c:someProperty> , usage of jstl , c is prefix
- we have for each , import loop, if etc options as well, import website full code
- if we want to use and pass object, need to create getters and setters (make it a bean), if print object use tostring()
- Other type of tags
    - sql tag : different import and tags, can connect with db and work on it, need db connector as well
    - functions tag : {fn:length(someString)} //calculate length of string, can use others 

### Servlet Filter 
- With filters a request to a servlet/servlets can be filtered,
like having logs checking security , checking trasaction etc.
- Filters can response back as well, so act like a servlet before your business logic, can append a filter to many servlet
- Tomcat says if you have many filter, you have to create chain i.e. filterchain
- so web.xml file you have to configure filter, we have filterConfig as well just like servletConfig
- for creating filter create class and extends servletFilter and complete the method init, doFilter,destroy with request and response objects 
- Filter can act as a servlet but their main working is to intersept a request and either return back response or visit next in filter chain accordingly.
- Can create new filter just like servlet, it extends HttpFilter and has it's methods to complete
- doFilter Methods has main logic, has FilterChain, last filter will call servlet , page -> filter/s ->servlet
- we can typecast Servletrequest to Httprequest and httpResponse
- can map in sml file or @WebFilter("/path") tag 

### Login Using JSP & Servlet
- Website has several pages so you should only have login once and maintain that so use Session/Cookies, and restrict allow access or restrict access according to pagetype
- Cookies are easier to work,client has cookie, but client can modify cookie, session is safer for login for various usages atleast for login
- Once you login, servlet has session can set value, and before executing any code in a JSP page that should have login we check for session and attribute set or not. Is set execute else call login page again
- When you logout call a servlet, remove data from session, session.removeAttribut("attributName"), we have to remove cache pages as well so on going back can't see secured pages.
- Do this for every secure pages.
- On logout remove attribute that you set and also do session.invalidate();

### Going Back After Logout secure Pages shoudn't show
- Cached page we get when go back, we can in headers say to browser so as to don't cache this page
```
    response.setHeader("Cache-Control", "no-cache ,no-store ,must-revalidate"); //Http 1.1 works

    depends on proxy or older newer version this header may differ
```
- can convert project to maven project and get other dependecny and conencter java file for conencting to db

### Servlet,JSP,JDBC,Maven
- When not using maven, various dependecy jar files we have to download the jar and attach, but in maven it will do that for you
- In eclipse new -> project-> other-> maven
- Can mention dependency in pom.xml, it will download it for you
- For file upload apache has apache commons FileUpload jar, can add this dependecy in pom.xml in maven
- Front end in form you can get files ecptype=for-data/multipart and it will go in you servlet in request object then you can fetch in server, basic syntax
```
ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
try {
    List<FileItem> multiFiles = sf.parseRequest(request);
    for(FileItem item : multiFiles)
    {
        item.write( new File("/Users/madhurnagar/eclipse-workspace/servlet/FileUploadDemo/clientUploadedFiles/"+item.getName()));
    }
    
    System.out.println("File Uploaded");
} catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}
```

# Spring Framework
## Dependecy Injection
- One of the design pattern
- Dependecy : An part of our application(object) needs other componenets/code so we will have dependecy on them.
- So our class will need other objects but if we write it explicitly it will be tight coupling.(eg Laptop class harddrive is of hitachi but we changed to samsung now)
- So we want some service to inject these dependency for us
- So Spring Dependecy Injection Container does that for us they create the object and inject it in for us
- To configure what object, from where, and to where it will be injected we have xml configs(so somewhat loose, we have to mention any change accoridngly)(Spring Boot you can write @Component over a class to makes them to be generated and given by spring boot whenever required, which will be @AutoWired)

```
Class Laptop{
    @Autowired //Spring boot will handle the lifecycle
    HardDrive obj;
}

@Component
class HitachiHD implements HardDrive{
    
}
```
- Other than loose coupling, this enables us to do testing for components separately by creating MockObject

## Maven
- Build tools Under the license of apache, have many libraries available
- Need? whenever you work on new project, you will need many third party libraries, like for Spring MVC we need various libraries, db conenction etc, it will be complicated to download all this, even if you can download on updates we need to change various things.
- So need JAR files, maven repository has those JAR files aka Dependencies.
- How does MAVEN does it
    - pom.xml : place where we mention dependenies we need, so Maven fetches it's JAR files accordingly
    - Project : Whatever you create in maven it's a project
    - group Id : Different compnay people will have unique group Id so com.{organizationName}
    - artifact id : you project Name 
    - Package Name : {groupId}+{artifactId}+{some package you create} this will be naming of your project packages you create
    - Now in pom.xml
    ```
    <dependecy>
        <groupId>someGroup</groupId>
        <artifactId>anArtifactOfsomeGroup</artifactId>
        <version>a version of anArtifactOfsomeGroup </version>
    </dependecy>
    ```
    - So JAR files will be downloaded from maven, mvnrepository for whatever you mention in pom.xml and available in mvnrepository.(So internet is required for first time and it will be downloaded and saved in local repository, next time it will fetch locally)
- There are many other tools like gradle etc but for spring , spring boot, web prject in java generally maven is used

## Application Context(Spring Core Framework)
### DI,XML Injections
```
Vehicle.java
public interface Vehicle {
	void drive();
}

Car.java
public class Car implements Vehicle {
	public void drive() {
		System.out.println("Car chal rhi hain....");
	}
}

Bike.java
public class Bike implements Vehicle {	
	public void drive() {
		System.out.println("Bike chal rhi hain....");
	}
}

App.java(Wherever you want to use a vehicle)
public static void main( String[] args )
    {
        Vehicle v1 = new Car();
        Vehicle v2 = new Bike();

        v1.drive();v2.drive(); //both can be used but need to change new Car() or new Bike() each time, so not fully loose coupled
    }

springFramework
    public static void main( String[] args )
    {
        AApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Vehicle v = (Vehicle) context.getBean("vehicle");
        v.drive();
    }

pom.xml of maven project need to add main spring dependecny of spring context

<!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-context</artifactId>
    <version>6.1.13</version>
</dependency>



spring.xml(can be other name as well but emntion in ClassPathXml context the path)
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
	// above is beans tag info context for xml
    <bean id="vehicle" class="classPathPackage.Car"> </bean>
</beans>
// we can change car or bike oe something else and it will be used same wherever you mention, and don't have to recompile java file only need to change xml
```
- getBean() : it is a method belong to/get from two interfaces implementation 
    - BeanFactory : use for smaller project
    - ApplicationContext : use for enterprise level/web project, superset of beanFactory so have all features of beanFactory
- With this you don't have to change java source code just need to change xml etc settings
- Basically Bean configuration can be done in 3 ways
    - XML configuration as above
    - Annotation Based Configuration
    - Java Configuration
### Annotation Based Configuration
- For annotation based configuration make sure you xml has bean xmlns = "" , has property added above has it
- No need to mention your bean configuration in xml file but need to mention what package/place need to be scanned for fetching beans
```
<context:component-scan base-package="com.madhur.springTest"></context:component-scan>
```
- Use @Component over class so don't have to mention in xml file about it's bean and it's id etc, default name of bean is smallCapital className Class Car bean is car or class Bike is bike in .getBean("beanName can be car or bike or small capital class name")

### Bean Property
- to give default value of the bean object some property 
```
<bean id="tyre" class="com.madhur.springTest.Tyre">
    <property name="brand" value="MRF"></property>
</bean>
```
- basically a setter

### Constructor Injection of Bean Param
- Create constructor of your class with params and add value in xml under bean
```
<constructor-arg value="MRF"></constructor-arg>
```
## Autowired Annotation
- We tag @Autowired on the property/object we want to use in some other class.
- Spring will check in xml for this type of declaration and fetch the object for you
```
class Car{

    @Autowired
    Tyre tyre;//tyre is defined in xml file or is component
    // in car bean declaration you can mention property of tyre another way in xml but autowired is simpler
    ....
    ....
    ...
}
```

## Annotaion Component Configuration /Spring Core Annotation
- pom.xml of spring project need to add spring context dependency
- Annotation Based Config, instead of xml we will have class with @Configuration, and the objects we need needs to Tagged @Bean over a method which will give that object
- Use autowired on every object you need, spring will search for all bean if it have that type
```
@Configuration
public class AppConfig {
	
	@Bean
	public Samsung getPhone() {
		return new Samsung();
	}
	
	@Bean
	public MobileProcessor getProcessor() {
		return new SnapDragon();
	}
}

app.java main function inside

ApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
        Samsung s7 = factory.getBean(Samsung.class);
        s7.config();

```
### Annotation Component Autowired Primary Qualifier
- @Bean above is replacement for xml bean declaration
- we can make @component of each class we want and in config class @ComponentScan(basePackage="yourPackageLocationWhereComponentFilesArePresent") can give multiple package with , separated
- by Default component name is Class == class(non qualified and de capitilize), can make @Componenet("myClassComponent")
- what if we are using interface and it's implemeted by several class @Component we can use @Primary with it so it will be preferred
or 
```
@Autowired
@Qualifier("yourChoiceClassImplemetaionOfInterface")
Interface interfaceReference;
```

# Hibernate
- One of the best ORM(Object Relation Mapping) Framework available for java.
- Any application connecting with DB to save/retrieve data we need connector for respective DB, JDBC implement it using Connector.
- We have objects and classes in code and table rows in DB(relational).To connect that objects to rows we write sql query etc, so ORM helps in seeing Tables and Rows in form of objects and classes
- Prerequisite/Uses are java and sql so both Java and SQL, and JDBC connectivity

## Theory
- It's an ORM tool for persisting the data.
- Working with data so it can be form of variable and objects(which has combination of variables/objects) which are temporary, so we need to save it and DB is used for persistence
- JDBC is used for conencting java to DB, so we need SQL language for working with DB, so might don't want to work with sql or writing sql query might create issue
- So what if we can store object directly to DB as form of Row in column
- ORM : Object relationship mapping, this does the mapping
    - Any class has variables which can be considered as columns and each object is a row and class is a tables
    - So creating relationa b/wn Object and Realtionship
    - Hiberante is one of the tools for implementing ORM, others are iBatis,JPA(Hibernate is related to JPA) etc
- We create object of session to save,get etc, for creating Session object we need to create object of session factory(like JDBC we will give configs of dB like url,user, pass etc can be given by java config, xml )

## Code Setup
- Create a maven project, can create you class etc pojo to be used for saving
- Dependecy will need hibernate core and mysql conenctor or any other DB connector that you are using
- Now we will create sessionFacotry so as to create session which will have save,get etc method but both SessionFactory and Session are interfaces
- So we need Configuration Class of hiberante and create configuration object which can be used for session facotry and session
```
SessionFactory sf = new Configuration().configure(new File("hibernate.cfg.xml")).addAnnotatedClass(objectToBeSaved.class).buildSessionFactory();
        		
Session session = sf.openSession();

Transaction txn = session.beginTransaction();

session.persist(objectToBeSavedObject);

txn.commit();


in class which is to be saved

@Entity
class objectToBeSaved{

    @Id
    type property;
    ....
    ....
}
```
- But not provided preperties like db type , name,url username Password etc
- In order to save an object of class need to give @Entity over the class which will be saved, also some property should have @Id showing primary key
- Also due to ACID property need to create object of transaction and begin and end transaction.
- Hiberante property can be save to allow to create tables or not, either create table yourself and allow hibernate to save or allow for table creation, like ddl auto, update ,create etc
- Basic xml configs, show_sql will show all sql query executed in console/logs
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC 
  "-//Hibernate/Hibernate Configuration DTD 3.0//EN" 
  "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<!-- Version 8 MySQL hiberante-cfg.xml example for Hibernate 5 -->
<hibernate-configuration>
  <session-factory>
    <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
    <!-- property name="connection.driver_class">com.mysql.jdbc.Driver</property -->
    <property name="connection.url">jdbc:mysql://localhost:3307/neon</property>
    <property name="dialect">org.hibernate.dialect.MySQL8Dialect</property>
    <property name="connection.username">root</property>
    <property name="connection.password">password</property>
    <property name="hbm2ddl.auto">update</property>
    <property name="show_sql">true</property>
    <!-- mapping class="com.mcnz.jpa.examples.Player" / -->
  </session-factory>
</hibernate-configuration>
```
## Hibernate Realted Annotations
- @Entity(name="name_table") : make sure your table is name "name_table" isntead of class name


