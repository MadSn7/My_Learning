# Java(Basic & Advance)
## Method Overloading & Overriding
- Method Overloading(Static polymorphism) 
    - When the name of method is same but signature is same like some takes int, some takes float or string etc
    - return type difference in methods doesn't separate them as such, need to have to different types or number in parameters
    - can have overloaded constructors
    - constructor(double a), can be invoked by passing integer as java will assign int to double if no specific int method is found
- Method ovveriding in inherited calss same m,ethod different code
- When you pass primitive data-type it is passed by value whereas objects etc are passed by reference
## Access Control
- Java has 4 different types 
    1. public : can be accessed by any other code(that's why main is public)
    2. default : nothing mentioned, by default this, public for the same pacakge componenets, can't be accessed by other package
    3. protected : Inheritance usage
    4. private : can only be accessed by some other memeber/code of the class
- `a non-nested class can only be default or public and a .java file can have only one public class`

## `Static` Keyword
- static classElement : can be used without object by className.classElement
    - variables : will be global variables for all object of the class created, same value across objects anyone update value
    - methods : can be accessed directly , but have restrictions
        - can only call other static methods
        - can only call static variables in their class
        - can't refer to this/super in any way
    - static block static{//code} : this will be executed first when class is loaded

## `Final` Keyword
- final over variable make it constant so value won't change, need to declare value at time of declaration or with constructor, convention to name variable in CAPITAL for final variable to view as constant
- final over methods : so inherited class can't change implementation
- final list etc object can be changed so list.of is used to make immutable list

## Nested Class
- if class B is defined within class A, B doesn't exist independently of A.
- class B has access to all memeber including private of class A
- two types of nested class
    - static : can only access static things so need object of A to use other, rarely used
    - non-static(inner class) : access to all content of class A
    - anonnymoud inner class : don't have name
- can have nested class let's say inside methods or for loop so the scope of the inner class will be limited by scope of method/lopp etc

## String Class Basic
- By default every string is an object and it is immutable
- so on modificaations there is new string object created, and we have other option for mutability StringBuilder and StringBuffer
- Also concept of string pool etc

## VarArgs( type ...variable)
- don't have to create array always to handle varible number of parameters
- better readibility and no need to create array
- can cause ambiguity

## `var` keyword
- The var reserved type name (not a Java keyword) was introduced in Java 10. Type inference is used in var keyword in which it detects automatically the datatype of a variable based on the surrounding context.
- // int  var x = 100; // double var y = 1.90; // char var z = 'a';

## Inheritance
- Class that is being inherited is superclass , subclass that' doing inheriting
- Basically all behaviour of superclass i.e. parent is attached to child and child can add other as well
- class subClassName extends superClassName{} //syntax
- Java doesn't support mutiple inheritance so there can only be one superClass
- Can't access private member of superclass from subclass
- Major advantage of inheritance is that once you have created a superclass that defines the attributes common to a set of objects , it can be used to create any number of more specific subclass, and each subclass can precisely tailor it's own classfication and unique attributes
- superclass refName = new sublass();
    - now thus refName can call all function declared in superclass but implementation will be of sublcass so in a way abstraction etc
- super : calling constructor of superclass super(), or super.member calling variable method of parent class.
- in inheritance by default the superclass constructer is called first then the respective subclass/es one
- Method Ovveriding : when same signature method of superclass is again implemented by sub class so when it is called it is done executed according to object type, gives way for runtime polymorphism
- Dynamic Method Dispatch : main thing is the type of object not the type of reference it's being referred too
``` 
class A{
    void print(){
        System.out.println("inside a");
    }
}

class B extends A{
    void print(){
        System.out.println("inside b");
    }

    void printBOnly(){
        System.out.println("inside b only method");
    }
}
public class InheritanceTesting {
    public static void main(String[] args) {
        A a = new B();
        a.print();
        ((B) a).printBOnly();

    }
}
// op:
inside b
inside b only method
```

## Abstract Class
- Need for superclass where some methods are implemented and some are left for subclasses to be implemented, differnt from simple super class is it makes sure sub class have to implement them
- can't make objects of abstract class, can use abstract class as reference and implemented subclass implementation will be called
- abstract class className{abstract returnType methodName(); someotherImplementedMethodAsNormal()} //

## `final` keyword
- Three uses
    - over a variable so it becomes a constant
    - final over a method so inherited class can't override it basically to stop method overriding(can make program bit faster as final method can be assigned at compile time no dynamic time role paat)
    - final over class can stop inheritance of that class, so ultimately all methods are final are too now and can't have a final abstract class 

## `Object` Class
- All other classes are by default sublcass of this class, so by default Object refName can refer to object of any class
- Some imp methods
    - clone()
    - equals()
    - getClass(), hasCode()
    - notify(),notifyAll(), wait()
    - toString() : auto called when println of object
                        
## Packages
- A package in Java is used to group related classes. Think of it as a folder in a file directory. We use packages to avoid name conflicts, and to write a better maintainable code. Packages are divided into two categories:
    - Built-in Packages (packages from the Java API)
    - User-defined Packages (create your own packages)
- first command of your .java file can have package myPacakageName; so the class will be now inside that package
- basically pacakge a.b.c; will have a folder a/b/c and then you .lass/.java file needs to be there
- JRE finds pacakge by default current folder or look into the CLASSPATH
- default access control won't allow contents to be accessed of a class from antoehr package
- you can import pacakge use it's allowed components to be used in your code logic

## Interface
- a class can implement as many interface it want to implement, but have to specify the methods implementation
- interface can be default or public only like class adn only one public interface in a .java file
- prior to java8 interface only had abstract methods, now there can be added default implemeration or make a method private, static methods
- varibles can be defined inside interface but they are final and static by default, so must be initialised
- By default all methods and varible are public
- interface refName = new implementClass(); now refName can be used to call methods accordingly like superclass used to do in interface
- any clss implementing interface but not completing methods should be abstract
- Nested Interface
    - nested interface can be public private or protected
- If not method is there in interface and only constatn declared class implemetaing will essentially is importing constants
- Interfaces can be exteded by other interface so basically methods or constants will be added and any class implemeting this have to give logic to all methods or sub and super interface
- Default Method : what if you hjave code working and interace in implemeted by a lot of class , now you add a method in interface so need to implement in all implementing classes now , so kept a default method which can be added with implememtation without breaking all code, and some class can override the default method according to usage
    - default returnType methdName(){//impl}
    - can have any number of default methods
    - 2 interface same default method a class implemet both
        - if class overrides default method no issue class method will be used
        - class deosn't ovveride default method compile error
        - if in two interfaces on extends another then the subInterface implementation will be called, can use super to call other interfce method explicitly
        - so basically called will be the final one implementing if can be found
- static method in interface : after jdk 8 , can have one or more static methods
    - so no object is required to call static meaning this can be called without any impolkemetation jsut by interface name.staticMehtodName()
- prvate method in interface : can only be called by default or other private method of interface,rare usage but useful if needed

## Exception Handling
- Exception is runtime error, so find a way to hadnle it effectively intead of crashing and stopping the application
- Five Keywords : try, catch,throw, throws, finally , handle though try-catch block/s
- All exception types are subclasses of built-in class Throwable, has two subclass
    - Exception subclass RuntimeException
    - Error : stack overflow, not handled errors
 - By default exception is not cactched gets logged and program stops, so use try & catch(e), can be multiple catch for a single try adn try can be nested as well
 - throw new someException() fro throwing exception explicitly by us
 - throws is needed when throwing exception which are not runtime so need to metion throws over the methods
 - finally code exceutes no matter exception was called or not, even if earlier return is executed finally will run then only control is passed 
 - Java BuiltIn has several exceptions
 - Can create your own exception by extends Exception and shpuld be ok, it has several default methods, can override accordingly specially toString() method
 - Chained exceptions are allowed assume due to IO error we had ArithmeticError, so basically in cunstructor you can add previoud exception if any
 
## Multithreaded Programmin(Some in Chapter 18 520)
- Java provides built-in support for multithreaded programming
- A multithreaded program contains two or more parts which can run concurrently, where each such parts is called a `thread`,so it's a form of multitasking
- Process multitasking is like running browser, text editor, jvm etc but thread multitasking is smallest unit of dispatchable code so a process can perform various task simultaneously
- Interthread communication and switching is less heavy and less costly so better 
- Multithreaded program helps reduce idle time of system, as it runs different threads when one is not active
- In single core system various thread will share the cpu time when one is waiting for reso
ruce etc other thread can utilise it and in multicore various threads can be executed and so on
- Thread has several states
    - Running
    - Ready to Run : when get cpu time will be running
    - Suspended : temp halt
    - Resumed : after temp halt
    - Blocked : thread waiting for some resource/response
    - Terminated : Can be done any time, not can't be resumed
- Thread Prioritites : Determines how a thread should be treated in respect to others
- Syncronization : As multithreading introduces async behavior what if we want to force synchronity over some data structure/data i.e. prevent other thread from accessing it when other thread has access over it

### Thread Class & Runnable Interface
- Java's multithreading is built upon THread class, it's methods and it's companinion interface Runnable
- To create a thread either extend thread or imolement runnable
- Thread class mehtods
    - getName,getPriority,isAlive
    - join, run,sleep, start 
- Wheneve a java program start to run it has a main thread, all other thread formed will be children of this main thread, and it will the last to shutdown in most cases as well, can be accesses to currentThread() in psvm 
- By Default print of thread
```
Thread[main,5,main]
        name,priority,threadGroupName
```
- sleep(Long miliseconds) or sleep(mili,nansecond), can make a thread sleep so it stops execution for the time metnioned, throws exception 
### Ways to create Thread
- Implementing Runnable
    - You can implement runnable interface which abstracts a lot of code
    - mainly need to give implementation of run() method, can call other methods, will end when run return 
    - won't start until .start() is called over through thread
- Extending Thread
    - extends thread class and use super to create thread, need to start here as well
- can choose either to implement but runnable is easier to use, if you want to ovveride some methods etc can use thread class
```
    class MyThread extends Thread {
        public void run() {
            System.out.println("Thread is running.");
        }
    }

    class MyRunnable implements Runnable {
        public void run() {
            System.out.println("Runnable is running.");
        }
    }

    public class ThreadTesting {
        public static void main(String[] args) {
            MyThread t1 = new MyThread();
            t1.start(); // Starts a new thread

            Thread t2 = new Thread(new MyRunnable());
            t2.start(); // Starts a new thread
        }
    }
```
- can create as many threads as you wish, but needs to make sure main thread is finished after other threads, can have an approx sleep call in methods before exiting but there should be better way
- can check boolean isAlive() for a thread if it's running or not, but not used generally
- join() is used commonly, it waits for the thread is is called upon to be terminated before terminating itself
- Higher the priority of thread in theory it should get more CPU time,different OS has different scheduling etc
    - Can set thread priority by setPriority() values are from 10(max) to 1(min), default is 5(NORM_PRIORITY)

### Synchronization
- Can synchronize in two ways, both involves `synchronized` keyword
    - Using Synchronized methods, add keyword before methods
    - synchronized(object){
        //statement to be synchronised
    }, makes sure this object and statement executed are synchronised 
- Object classes had methods wait, notify, notifyAll() for interthread communication, used to notify other threads to start/stop execution

### Deadlock
- Suppose two threads access two separate synchronized block and that block calls each other synchronized block so access will not be given and it will cause a deadlock(circular dependency)

### Suspending,Resuming and Stopping Threads
- In Java, thread management has evolved, and methods like suspend(), resume(), and stop() are now considered deprecated and unsafe due to potential deadlocks, resource inconsistency, and other issues.
- Using wait() and notify() (or notifyAll()) methods, we can safely suspend and resume a thread.


## Enumeration(ENUM)
- create a data type with fixed values, this are basically staic final public 
```
enum Status{
    "RUNNING",
    "STOPPED",
    "PROCESSED"
}
```
- have two pre-defined methods values() and valueOf()
- It is similar with class and enums type is an object
```
public enum Day {
    MONDAY("Start of the work week"),
    TUESDAY("Second day of work"),
    WEDNESDAY("Midweek"),
    THURSDAY("Almost weekend"),
    FRIDAY("Last day of the work week"),
    SATURDAY("Weekend!"),
    SUNDAY("Rest day");

    // Instance variable to store the description for each day
    private final String description;

    // Constructor for the enum
    Day(String description) {
        this.description = description;
    }

    // Getter method to retrieve the description of each day
    public String getDescription() {
        return description;
    }
}
```
- can't inherit another class , can't be superclass
- each object have ordinal value like index array from 0 to n
- can have compareTo, equals etc methods        

## Wrappers & Autoboxing
- primitive data type(int,double etc) do not inherit objects and they are so for simplicity
- Type Wrapper : respective Type Object class of primitive data type to be used as and when needed(Integer == int, Float == float etc)
- As each is class they have their own methods and each object will have methods etc 
- Autoboxing : when primitive data type is automatically encapsulted to it's wrapper class and vice versa for auto unboxing(Generics specific various usage)

## Annotations
- Annotation contains supplemented information
- It is created through interface, can't include extends, but by default extends Annotation interface
```
    @Retention(RetentionPolicy.RUNTIME)
    @interface MyAnno{
        String str() default "defaultValue";
        int val() default 1000;
        //default make sure if that param is not metioned default value will be present
    }

    //uses
    @MyAnno(str = "someString", val = 100)
```
- Retention-Policy 
    -  @Retention annotation is used to specify how long an annotation should be retained. The @Retention policy helps the compiler determine whether an annotation should be discarded, retained in the compiled .class files, or kept available at runtime for reflection
    - SOURCE,CLASS(default) and RUNTIME is policy options
- obj.getClass.getAnnotaions() to get all anotations associated, can get fpr a particular method as well and various other methods availble
- Marker Annotations : Contains no memebers/method declaration.
- Single-Member Annotations : Single memeber is present @SingleMemebrAnotation(someValue) can be used like this as no need to mention memeber name
### Built-In Annotations
- Many are there but 9 are general purpose
- Imported from java.lang.annotation
    - @Retention : Specifies retention policy, used for other annotation
    - @Documented : Marker Annotation , tells toll an annotation is to be documneted, annotation for other annotation
    - @Target : Also annotation for other Annotation, Specifies the type of items which annotation can be applied which is enums of ElementType like FIELD,Constructor,Package,Type etc can be one or more takes an array of eleType.If not specified other than type parameters can be used for any better to explicitly mention
    - @Inherited : Marker annotation over other annotations which will be used over class,Allows an annotation to be inherited by subclasses from a superclass if it is being used in superclass 
- Imported from java.lang. 
    - @override : marker annotation can be used only on methods, method marked with it makes sure it is ovveriding method from superclass and not some other naming or overloading
    - @Depracated : To mention this method is not recommended to be used anymore
    - @ FuntionalInterface: Marker interface to mark an interface as functionalInterface , purely informational not compulsory
    - @SafeVarargs : marker annotation can be applied only to methods and constructors,Typically applied to methods that use varargs in a way that doesn’t violate type safety
    - @SuppressWarnings : Commonly used to suppress warnings for deprecated methods, unchecked casts, etc.
- Type Annotations : There are severeal of them, need to use @Target on annotation before using them
    1. @NonNull - Indicates that a variable, return type, or parameter should not be null.
    2. @Nullable - Suggests that a variable, return type, or parameter can be null.
    3. @NotNull - Specifies that a variable or parameter should not be null (used in libraries like JetBrains’ annotations).
    4. @Unique , @What,@Maxlen,@Recommended etc

### Repeating Annotation
- So a annotation can be repeated on same element , over the annotation need to use @Repetable, need to mention a container as well
## Chapter 13(I/O)

## Generics
- Allows to create a single class which can work with different data types
- In pre generic code we use to use Object type as it was the superclass of all types
```
class sampleClass<T>{
    T varName;
    //now T will be the type when we pass type of T while creating object
}
```
- can't use primitive data types
```
iob = new sampleClass<Integer>();
sob = new sampleClass<String>();

iob = sob; // will give compile error, so type safety is ensured due to generics if it was Object using and not generics would be assigned and gave error
```        
- can declare one or more generic type in Class and use accordingly, on declaration they can be of same type or different
- class sampleClass `<T extends Number >` for marking sure now T is a subtype of number , can be used to make sure yout T is of some types and not any 
- sampleClass `<?>` for taking parameter as any object with any generic of sampleClass, we can use ? extends someClass for similar making sure some subtypes arrive
- you can have specially mehtods only having generics and not class should be figured out while passing parameters, so constructor can be generic and class not (not sure what happens then with object creation `try`)
- can have generic interfaces as well , so class at the time of implmentaing mention the type of generic
- generic class can be extended and when you create object and specify type it will be passed to all super class, generic can extendws non generic class as well
```
className<genericTypeArList> verName = new className<genericTypeArList>(argsIfAnyList); //earlier only this allowed

className<genericTypeArList> verName = new className<>(argsIfAnyList);// now allowed

new className<>(argsIfAnyList);// this can be allowed as well as params has the type mentioned
```
- you can sue `var` as well for reference object of generic class if name is too long 
- can have static varible using T as not sure what other generic classes be and the values they pur get , methods can be generic
- generic class can't extedn throwable so cannot create generic exception class

## Chapter 15(Lambda)
- along with generics , streams , Lambdas are one of the major changes been done/added to Java after it's initial usages that change the way code been written(generics added in JDK 5 and lambda in JDK8)
- Lambda Expression : Essentially an anonymous method.Used to implement a method defined by a functional Interface 
- Functional Interface : An interface that contains one and only one abstract method often referred to as SAM(Single Abstract Method)
- -> arrow operator/lambda operator  is used 
```
    (zeroOrMoreParameters) -> whatToDoAndReturnGoesHere
    () -> 123.45
    //similar double someMethod(){return 123.45;}

    interface MyLambda{
        double calculate(double a,double b);
    }

    public class lambdaTesting {
        public static void main(String[] args) {
            MyLambda myLambda = (a,b) -> a+b;
            System.out.println(myLambda.calculate(3.0,2.0));
        }
    }

    creation of lambda with usage of functionalInterface
```
- Block Lambda Expression : when there is complex statement to be executed so you can declare variables, loop etc etc and have to explicitly return something and ; after } end
```
MyLambda myLambda = (a,b) -> {
            //somnething something can be written like a method
            return a+b;
        };
```
- Functional Interfaces can be generic but when you create lambda out of it need to specify type of params
- Passing Lambda Expressions as Arguments 
```
    @FunctionalInterface
    interface MathOperation {
        int operate(int a, int b);
    }

    public class LambdaExample {
        public static int executeOperation(int a, int b, MathOperation operation) {
            return operation.operate(a, b);
        }
    }

    public class Main {
        public static void main(String[] args) {
            int resultAdd = LambdaExample.executeOperation(5, 3, (a, b) -> a + b);
            System.out.println("Addition: " + resultAdd); // Output: Addition: 8

            int resultMultiply = LambdaExample.executeOperation(5, 3, (a, b) -> a * b);
            System.out.println("Multiplication: " + resultMultiply); // Output: Multiplication: 15
        }
    }
```
- Lambdas can throw exception, need to metnion in method declartion in functionalInterface
- There are various functional Interface already defined in java for general use like Function,Consumer,Predicate etc
### Method References
- Method references to Static Methods
    - ClassName::MethodName
- Method Reference to Instance Methods
    - objRef::methodName
- generic Type : need to mention which generic type is being used before calling method by ::
- className :: new //for constructors

## Modules
- Module is grouping of packages and resources

- Can look again not sure waht was in the book

- Modularity adds a higher level of aggregation above packages. The key new language element is the module—a uniquely named, reusable group of related packages, as well as resources (such as images and XML files) and a module descriptor specifying
    - the module’s name
    - the module’s dependencies (that is, other - modules this module depends on)
    - the packages it explicitly makes available to other modules (all other packages in the module are implicitly unavailable to other modules)
    - the services it offers
    - the services it consumes
    - to what other modules it allows reflection

## String Handling
- Three Classes, all are final, all implement CharSequence Interface
    - String : By default,immutable
    - StringBuilder : Immutable but not synchonised
    - StringBuffer : Thread Safe, but slower due to it thab StringBuilder
- "someLetters" is astring literal, java su[ports it and every literal is an object
- valueOf(otherData), covert other data into string
- reverse() methid is not with String class but with others, there are many other methods

## java.lang(Chapter 18 various thing can look)
- Default imported in java, contains fundamental classes and interface needed in all java programs
- Contains classes such as Object,Thread,System All Number Classes, Process, Runtime,Math etc etc
- Interfaces such as Runnable,APpendable,Comparable,Iterable etc

## Collections Framework(java.util Part_1)
- java.util has several classes and interface among them is Collections Framework
### Interfaces
- Collection Interface : Base, should be implementd by any class in part of collection it extends the Iterable interface and is generic, have many core methods which every collection will have like add,clear,remove,iterator,size etc and have various exceptions associated
- List Interface : It extends Collection interface, it is generic and sequential storage (which can be duplicate) of element which can be accessed through zero-based indexing, add some methods over the collection interface as well
- Set Interface : Extends collection interface, essentially a set like in maths etc so no duplicate.
- SortedSet Interface : Extedns Set interface and keeps element in sorted order add methods like first() and last() etc
- NavigableSet Interface : extends sortedSet and allows for retrieval of elements based on some behavior
- Queue Interface : extends Collection, define queuing on object often based on first in first out or others.Have methods added like poll()(return null if empty) , remove()(throws exception if empty), peek()
- Deque Interface : extends queue , essentially doubly ended queue have many methods added as well

### Classes
- Collction Interface have several abstract and other class  as well for implementation and are not synchronized by default
- ArrayList Class : Extends abstract class AbstractList and implements List interface basically dynamic array
- LinkedList Class : Extends AbstractSequentialList and implements the List,Queue and Deque Interface so can add remove from last , first etc 
- HashSet Class : Extends AbstractSet and implements Set Interface, uses hasing for storing objects, doesn't store order for element
- LinkedHashSet Class :  Extends HashSet , maintains order of objects in order they were added
- TreeSet Class : Extedns AbstractSet and Implements NavigableSet.Creates a collection that uses tree to store objects by default in ascenfding order, as it's stored in order can findSubset as well to get orderedObject in range
- PriorityQueue Class : Extends AbstractQueue and Implements Queue, creates queue based on comparator by default min, can iterate but order is not defined .
- ArrayDequeClass : Extends AbstractCollection and Implement Deque Interface dynamic array for addition deletion from both end
- EnumSet Class : Extends AbstractSet and implements Set.It is specially for use with Enum type, so generic given must be enum

### Iteration
- Use Iterator,SpliIterator (or ListIterator etc according to collection type)
```
Interator<genericType> itr = objCollectionRef.iterator();

//now can use it to iterate
```
- for each can be used as all collection objects implements Iterable
- Splititerator can be brough to use same as iterator but has several other methods as well, provides parallel iteration

#### RandomAccess Interface
- No member complete empty but signifies a random access, implemented bt arraylist and legacy Vector class

### Maps
#### Maps Interfaces
- Map is an interface it save key,value pair and not implement Collection/Iterableinterface, so can't use for each either
- SortedMap Interface : extedns map makes sure enteries are sorted in ac=scneding order in form of key
- Navigable Interface : Extends SortedMap , supports retrival of value based on some key etc
- Map.Entry : It is subpart of Map, defines an entry in Map 
#### Maps Classes
- Have various abstract classes as well for maps
- HashMap Class : extedns Abstract map and implements Map interface, saves key value pair not methods of it's own introduced
- TreeMap Class : extends AbstractMap and implements NavigableMap,creates map stored in Tree Structure sorted in ascending order, not other methods, can specify comparator for different order rule for sorting
- LinkedHashMap Class : extends HashMap class,maintains a linked list of entries, so insertion order maintained
- IdentityhashMap Class : extedns abstractMap and implements Map, similar to hashmap except it reference equality(same key object) when comparing elements,not for general use
- EnumMapClass : Extedns AbstractMap and implements Map, for usage of key type as ENUM


### Comparator
- TreeMap ,TreeSet use default comparator of Generic type you use to sort objects
- FunctionalInterface and has two mehtods compare and equals,several various static methods were added like reversed(), nulFirst(),nullLast()

#### Collection have several algos like sort etc to be used upon the collections , you can get synchtonizedList() and  synchtonizedSet() for having synchronized list and set respectively

### Arrays class
- There is arrays class which has several usefule methods like sort ,copyOf,equals,deepEquals(nestedArrayEquals or not),fill,parralelSort()(faster), binary search over the array
- Supports Stream and SplitIterator

### Legacy Classes and Interfaces
- Were there before collections framework got introduced now not useful directly and merged meaningfully in collection framework
- Classes were Dictionary(like Map,obselete),HashTable(reingeneered to be part of collection and map interface),Properties(subclass of hashtable, has store and load),Stack(subclass of vector, not depracated bu arrayDeque is better),Vector(dynamicArray, similar to ArrayList, fitted into List now)
- Enumeration Interface now superceded by iterator

#### Streams are intergrated with Collection , so consider using them when using collection

## java.util Part 2 More utility Classes
### StringTokenizer
- Separate string in tokens key values
- Similar to split

### BitSet
- creates a special type of dynamic array that holds values in form of boolean
- can perform bit operation on it with other objects as well

### Optional
- To handle a way when value may or may not be present, earlier it woul dbe null and can cause issues in some modification later in the program
- Have several methods including ifPresent to check if value is there or not
- Have OptionalInt,Long,Double for respective class as well

### Date
- Encapsulates current date and time
- It has changed a lot since start of java, has several methods
- new Date(); gives date object with current date

### Calendar 
- Abstract class Calendar also has several methods defined so subclasses of Claendar can refernce time and date the way they want
- Has various constants as well
- GregorianCalendar is concrete implementation of calendar

### TimeZone
- Abstract TimeZone class allows you to work with time zones offsets from GMT 
- SimpleTimeZone is subclass of TimeZone and implement it's methods, also computes dalight saving time, works with gregorian calendar

### Locale
- To produce object that describes geographical or cultural region
- Has several constants like US,JAPENESE etc

### Random
- Generator of pseudorandom numbers
- Random r = new Random(); then r.variousMethods() for number generation

#### There is Time and TimerTask Class, Currency Class, Formatter for string Formatter, and also printf type emthods etc

### Scanner 
- For getting input, hasNextX where x is Type like Int, Byte etc

### Utils also has concutrent subpacakges, regex, stream,loggin,zip etc


### Need to do Chapter 21,22,

## Networking
- java.net package, jdk 11 introduced java.net.http for HTTP client
- Java networking basis is socket, which means an endpoint in network, scoket allows computer to serve many different client through ports,which is numbered socket on a particular machine
- A server process is said to listen to a port until a cleint connects to it, server is allowed to accept multiple clients connected to same port number, although each session is unique.
- To manage multiple client connection, a server process must be multithreaded or have some other means of multiplexing simultenous i/o
- Socket connection takes place via IP protocol
- java.net has several classes and interfaces
- InetAddress Class : can handle both ipv4 and 6 , can get ip for any web address etc methods, have two subclasses for ipv4 and 6

### TCP/IP Sockets
- ServerSocket class is designed to be listener and for server, which waits for clients to connect to server
- Socket class is for clients, designed to connect to server socket 
- Socket two constructor connect by giving hostname and port or iNetAdress and port, and several methods for usage to get info or streams, connect() to new connection , isConnected to check etc, close() for closing connection/socket

### URL
- Modern connection is mostly all about www, and URL provides a way to find resources over the net, every browser uses them to identify resources
- URL is a class in java, and a URL has same basic general format with 4 parts
    - {protocol}:// starts ususally with http or https
    - //{hostname}/ like www.google.com
    - : (optional) specifies port number , default port 80
    - /{someFilePath} particular location/request/resorce from server
- has various exception
- have constructor either by giving full domain name or by above componenets 

### URLConnection
- general purpose class for accessing attribute of remote resource, has several methods
- after making URL you can open urlConenction, handles several header related info and other info you can work with, conenction details, expiration data
#### HTTPURLConnection
- Subclass of URLConnection, to provide support for HTTP connection
- can use several methods 

### URI class
- Unique Reosurce idetifier is similar to URL, URLs are subset of URIs

### Cookies
- Java has several classes and interface to handle cookies(creates stateful HTTPconnection)
- Classes CookieHandler,CookieManager,HttpCookie
- Interfaces CookiePolicy,CookieStore

### Datagrams are used in java to send data packets over TCP/IP , DatagramSocket is used to send DataGramPAcket

### java.net.http
- JDK 11 introduced this, earlier all were part of java.net, traditional usage
- Provides inhace, updated netwroking support for HTTP Clients referred to as HTTP Client API, also supports webscoket for bidrection communication
- Three Core Key Element
    - HttpClient : encapsulates http cleint, provides the means by which you send a request and obtain a response
    - HttpRequest : Encapsulates the Reuqest
    - HttpResponse : Encapsulates the Response
#### HttpClient
- Supports both synchronous and asynchronous communication
- Abstract class use factory builder to create instance, if want to change configs etc
- For default HttpClient client = HttpClient.newHttpClient();
- not with `client` you can call .send() to request synch response and you will get HttpResponse Back

#### HttpRequest
- Builder is used to create Request
- HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://jsonplaceholder.typicode.com/posts/1"))
                    .GET()
                    .build(); 
- HttpResponse is also there use to ge response, both ahve their own mehtods supported for http communication

## Concurrency utilities
- 

### Need to do Chapter 29,30  PART 2 OVER

# PART 3 nothing to do , doing PART 4

### Servlets Basic
- had basics in book covered in other notes