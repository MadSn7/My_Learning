# DevOps Basics/Pre-Requisites

## A story of DevOps Tools, All Tools Usage
-  You have an Idea and started coding to build for that idea and your application is running.
- Now your code is running in your laptop, your localhost, if your laptop is shut ode stops, even if you are able to share with world when your laptop is down application stops.
- So you need to host, either a physical device server in hosting center, cloud etc which doesn't goes off.
- But application can't run in server directly need to ahve packages, dependecies etc.
- So you have development environment your own local system and production environment the server.
- Need to build your application to run the executable file, like maven,gradle etc.and use that in server.
- Your applications is running in server but you don't want to share that ip so you purchase a domain name xyz.com and map it to the server.
- You bring your friend and collegues to work on your applications, now need to ahndle this many code changes etc, so git comes into picture.Online repo like gitHub, gitLab etc works.
- Still need to manually build the code whenever some changes takes place, so better to have a build server as well and build it there.
- Recommended to test it too so dev -> build -> test -> prod is the flow now.But it's still a manual job.
- CI/CD comes into picture to automate deployment.So everytime a code is pushed it automatically gets pushed and will automatically reach to prod, you can set permission and steps to check in between.
- Still the seamlessness is not there, we need to configure dependecies and libraries in every environment and it keeps changing as well.So need to manual do this.
- Container comes here and you can package all dependecies, os etc and all other environment can have some container technology and use this image.
- We can run multiple container, so separate isolated containers.
- Now you site has become live and famous, and gets lot of visitors.Now we want to increase containers accordingly but it is manual, now want some orchestration for it.(One of container orchestration is k8s)
- If we want to increase server quantities, it is still maual, tools like terraform can be used, so don't have to use UI and no human error.(IaC)
- Ansible can be used for automation to configure this architecture, terraforma and ansible overlap in various aspects, but terraform for provisioning, ansible is for post configurations activities.(Ansible has paybooks)
- Now deployment is there, we need to monitor now, prometheus etc works on that, it collects various metrics from different servers, for UI visulizing it grafana is there(charts and graphs).
- Above is devOps basically, basically is cycle from code to deployment to changes to code to deploy....

## Linux & it's Basics
-  Various technologies use linux environment heavily for their working specilly newer ones.
- Various flavours of linux availble like ubuntu,centOs(Basic for Red hat os) etc
- Usually CLI is used in development environment.
- Various types of shell is availble you can echo $shell to know the type, bash shell is available mostly is an upgrade from previous ones.

### Basic Commands
```
echo toPrintAnythingOnScreen 

ls //to list directory and folder inside
cd directoryName // to go the given directory
pwd //prints current working directory
mkdir newFolder // make a new directory
use ; to run one commands after one given together
mkdir -p /dir1/dir2/dir3... // creates tree of directory creates whatever is not availble
rm -r myDir1 // remove directory and all it's content
cp -r myDir1 temp/myDir // copies directory and it's contents
tree someLocations // prints tree of the location given 

touch newFile // creates newFile
cat > newFile // prompt appears gives content of file and it is then saved in the file,enter/return for new, (can give echo "content to give" in place of cat as well) line and ctrl + d to exit and save, 
cat newFile //prints content of file
cp newFile copyFile // to copy file
mv newFile sampleFile // to move file, with newname as well
rm aFile // removes a file
```
` basic flow is command fromHereLocation toHereLocation`

### Vi Text Editor
- cat can be used to copy and add simple files but not code, scripts etc so need a text editor.
- we mostly access from terminal, a number of text editor is availble like Vi,Vim,nano etc.Vi most popular.By default installed in most os
- vi someFileNameToOpen // to open a file
- has two mode commmand mode and insert mode.By default we are in command mode.
- Command Mode : can give commands here, navigate between line but can't change contents of file
- Insert Mode : to switch press i, can edit contents here, use esc key to switch back to command mode.
- Operations in command mode
    - move around with arrow keys or khjl
    - to delete a char use x, dd delete entire line
    - yy copy a line, p for paste
    - ctrl+u for up and ctrl+d for down.
    - type : allows to give commands
        - :w saves changes, :w filename can be given
        - :q to quit no save
        - :wq save and quit
    - /wordToFind to find word, press n to go find next occurence of wordToFind

### Some more Linux commands/Info
- whoami //to print the user 
- id //gives user id and more info of user
- su anotherUser //switch to anotherUser
- ssh userToBeUsedOnSshclient@clinetIp
- Every linux system has a super user called the root user, no restriction.
- Usually root user access is restricated,we use sudo to get various access.
- curl internetLinkFileDownload -o //-o saves file else it will download and just print the file 
- wget internetLinkFileDownload -O can also be used in place of curl
- ls /etc/* release * //some sort of release file to know version of OS, can use cat inplace of ls to print this file

### Package Managers in Linux
- Helps install software, dependecy needed for working of various applications
- CentOs uses RPM(Red hat PackageManager) as package manager
- rpm -i package // to install a package
- rpm -e package // to uninstall an package
- rpm -q package // to query package
- issue in installing some package which need some other package isntalled, like ansible need python etc to be installed in system and using rpm to directly install ansible might be an issue, so yum is used

*yum uses rpm as base, yum install ansible will install python and other dependecies*
- when you use yum, yum finds the dependecy as well as others that needed to be isntalled and intall them in order.
- repository for search comes by default according to os, and yum search for dependecy for there only, you can add other repos according to your needs.
- yum repolist // gives list of repos set in os
- ls /etc/yum.repos.d/ // shows all repos file which has link for repos
- yum list packageName // to see infor of a isntalled package
- yum remove packageName
- yum --showduplicates list packageName // to list all availble version of that package.
- yum insatll packag-version // to insatll particular version, add -y after insatll to give y so all prompt will have yes to install

### Services
- Services make sure your required softwares always runs on the background,also follow the order of start up.(Eg : Server and Dbs), automatically configured to service when we start these type of software.
- service httpd start 
- systemctl start httpd // use this, service use this only in backend
- systemctl stop httpd
- systemctl status httpd
- systemctl enable httpd // system boots up automatically start, use disable to disable
- Steps to configure a apllication as servcie
    - need to create a unit configuration file.
    - /etc/systemd/system here need to create a file
    - myApp.service is file create here
    - in file ExecStart give command used to run the application
    - can configure to run at start, order of start at boot up, and various other commands to be run before or after service start
    - systemctl daemon-reload to have our service loaded
    - now systemctl start myApp(same name as .service file) can start as servcie. 
    - can check docker file in systemd for better understanding in your system.

## Networking Basics

#### Switching
    - What is a network?Way to conenct between two different computers.
    - we need a switch in between them to connect
    - Need interface to connect to switch, can check by ip link.
    - swtching enable to connect between same network computers.
    ![Screenshot 2024-02-01 at 3 43 45 PM](https://github.com/MadSn7/My_Learning/assets/62552772/b0b67800-50d4-4b3e-956a-787ccb0b0c4d)
    - What happens if we havve several networks, and want to conenct between different networks system.

#### Routing
    Helps connect two network together.
    Get as many ip assigned as many network attached.
    Router is just another network like any other device.How switch know to send to router?
    ![Screenshot 2024-02-01 at 3 46 48 PM](https://github.com/MadSn7/My_Learning/assets/62552772/c42bb093-d405-4363-b7fb-8a138a56d06e)

#### Gateway
    A gateway or route, so gateway is door for a network to go out to other network.
    System need to know where is gateway, gateway need to be added, run route command to know route,ip route add ip to add route.
    Can add route to internet, and add route for it and this way internet access can be given, can be added as given default gateway.

- Can have an linux system to act as an router or path between two different networks.Can set various thing for it.

### DNS(Specifically for linux)
- Basically give some name for a particular ip like db for some ip of your database
- /etc/hosts can add ip and name here to be registered as dns in linux.Next time that name can be used to reach that ip.This is called name resolution.
- Earlier it was done like this for all systems in netwrok but comuter number grew.
- So we moved all this to a particular server holding all dns name called dns server, so to resolve names, always dns server is reached.So only dns server need to be configure in other network machines, and update in dns server is updated everywhere.Some or all machines can have dns names as well.First local system is read if not found then dns server.This can also be changed.
- Domain name, ip transalte to name in public domain(intenet) to not have to remember ip.
 <img width="984" alt="Screenshot 2024-02-02 at 1 59 24 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/e54580b0-d13d-43d5-ac8a-b0980e12c46c">
- Your router caches ip of domain name after first visit to load faster at next time of visit.
- Various record types for saving dns name and ip.
- Ping , maybe nslookup much better can be used.
- /etc/hosts file and /etc/resolv.conf file are used for dns name etc.

## Application Basics
- Various languages/platform build and deployment process.
- Languages two types
    - Compiled : java, c/c++ etc.(code,compile/build,run)
    - interpreted : js,python, ruby etc.(code,run)
- Packages/Modules/Libraries : Some code built earlier which can be used by others to build their code on top of it.
### Java
- When you install java not only libraries and various other things are installed but also JDK is installed as well.
- earlier JDK and JRE were downloaded sparately(1.8 and before), so can run only using JRe but now together(since java 9)
- earlier java version vere called 1.5,1.7,1.8 bu later on being called java 9,10,17,19 etc.
<img width="946" alt="Screenshot 2024-02-02 at 2 27 39 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/679101fd-26a9-4612-9a9f-9d9893598234">
- javac fileName.java -> java fileName, build method for a file
- Application has many files and many dependency,so an archiver help is needed.So JAR(java archive) helps compress and compile multiple files and their dependency.(web file image can be bundled in WAR)
- jar cf MyApp.jar file1.java file2.java ...... to create MyApp.jar with your given files.Automatically generated Manifest.MF file,inside META-INF folder
- Manifest.MF contains various info and metadata, like entrypoint/starting point of application.
- java -jar MyApp.jar // can be run anywhere where java is installed.
- javadoc can help documentation of you files.
<img width="899" alt="Screenshot 2024-02-02 at 8 16 05 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/2850b947-9024-45b6-9c00-363372889537">
- When files increase and various developers are involved this steps are difficult to perform and we instead use build tools like Maven(pom.xml),Gradle,Ant(build.xml) etc (used extensively with java)
- These tools does compile,pakcage and document.Have a file like xml containing instrudction for buil dependecny etc.Works similar.

### NodeJS
- Open Source and Free.
- Earlier JS was used inly on front-end and only on browser.
- NodeJS is server side Js environment used for running js in backend.
- node -v to check installed and version.
- node file.js to run file

#### NPM(Node Package Manager)
- Package management in NodeJs done by npm.
- npm -v for version, npm search p-ackage to list all package by that name, npm install package(add -g to install globally) to install.
- package.json has all package info which will be installed.(dependecies package -> version in json file)
- node first check for packages in local modeule not found then goes to global repo.

### Python
- Free,open source, interpreted language(need interpreter installed)
- simple use python or python3 to check if installed and it's version, python -v also works.pyhton file.py to run file.

#### PIP(Python Package manager)
- automatically isntalled with pthon isntallation, used for package management.
- pip and pip3 for respective pyhton version.pip -v to check version of pip(pip and python version is different)
- pip install packageName,packages are isntalled according to python version type and different bit package as well under site-packages folder.(user/lib/pythonVersion/site-packages)(pip show package to know location where it is installed)
- use import package in python to use a package,sys.path is the places where python looks for packages.
- Application have various packages not feasible to name all and do pip install, better approach is to keep some file.
- requirements.txt is the file containing all package detail, pip isntall -r requirements.txt, all will be installed.(package == version is better in requirement file)
- pip install package --upgrade, pip uninstall package
- earlier easy_isntall was used, .egg file was there like jar in java, then came wheels
- wheel ahve .whl package, can be use pip isntall some.whl to install

## GIT(Source Control Management)
- To track changes in your repo/application/code.
- git init, to initialize a directory to track it's changes.
- Steps To use git
    - Install git, git version to check
    - git init // at repo/ directory you want to track, create .git file to track changes.
    - git status to check status, make some changes
    - git add to track the changes for the files(. for all or by file name)
    - git commit to commit the changes with a message you can save to track

### Remote Repos
- A central remote place for repo to enable working with several users.We call it remote git repository.
- Remote git repository can be hosted in your own network or anywhere else on the internet.
- Others can push the changes, and people can pull the repo to keep track of changes.
- Most changes done git will handle it but conflicts occur when two or more users modify same file and same line, so explicity need to tell git which to take.
- Remote git
    - Either install git in a server and allow all other to access that repo
    - Use popular sites like github, gitlab etc.
    - git is system for tracking changes, and github is online server for keeping the repos having change.
- Steps after local code is setup and commited with git
    - create a remote repository in github etc.
    - some commands will appear to clone the repo and push changes etc commands.
    - git remote add <name> <url> // to add remotely created repo // name is our reference can be any
    - git push <nameRepo> <branch> // add -u as in start no branch is there so git push -u name branch
    - other can clone the repo if not availble, other can pull the repo.

## Server
- Two type of applications standalone and client-server
- Standalone like calculator application all logic is there.
- Client server like slack,teams etc which connects with server for various usecases.
- There are various type of applications running on server, so server can be called by different names
    - Web Server : Has you websites etc(front end),web content
    - Application Server : has you backend/logic.
    - DB Server : Data bases
    - Various others like backup server, email server etc.
- Any system that listens to port and does some thing and respons is a server or else it is just a system.
- Web server are for deploying/sending we content and application has your business logic.Small application bith can be in same server but usually they are in different servers.

## Web Server & Frameworks
- Web framework Two Types
    - BackEnd : Spring, NodeJs,Express etc.(server side)
    - fronEnd : reactJS,angular(client side)
- Both these parts are developed together and arepart of same applications but jsut handle different part of application working.
- We need to do mapping of endpoints/call at both backend and frontend part.
- Some applications have their owan way to run server along with appplication run but it's not recommended.
- web framework helps build applications, web server runs the applications, tomcat, nginx etc are some exaple of web server. and they are some process and by default listens onto some server(can be changed), can also host multiple applications
- Websites Two Types
    - Static : only html, css etc.No interaction needed with server
        - Apache HTTP web server, nginx are for static web server
    - dyanmic : Often have backend, have both static and dynamic content.
        - Tomcat, uWGSI etc are dynamic web server

### Apache Web Server
- It is open source, http server.Web Server, so static content oftenlly used with Tomcat in backend which is dyanmic
- centOs yum install httpd // to install apache web server , availble in default repo as service
- Every server has configuration and logs files.Can check at respective locations according to your usage.
- You can have one server host different type of site according to different subdomain and creating virtual server.
- Default port 80

### Apache Tomcat
- Provides web server environment to run java based applications.
- Pre-Requisite
    - java must be isntalled
    - download tomcat package
    - extract package
    - startup script run to start
    - basic start-up in prod can create users, register as service, run in background etc.
- Default port 8080.
- Extracted file has various folder
    - bin folder has various scripts like for start and shutdown.
    - conf folder for configuration like default port etc.
    - log folder for logs
    - webapps folder , should place applications here.
    - Java application needs to be packaged into war or jar nad move to webapps so localhost:8080/appNameOfWarJarFile // our application will run here

### Flask App(Python)
- With flask application you can run it has server ability but not recommended fro profuction basis.
- uWGSI,Gunicorn,twisted web etc are used for production grade deployment, of various applications.
 - gunicorn fileName:appName // to run

 ### Express.Js(NodeJS)
 - npm install inside the application folder to install dependecies and can then run using node
 - localhost:3000
 - production grade have some process manager, load balancers like pmp2 etc

 ### IPs and Ports(web Applciation perspective)
- You get connect to a network, you get an Ip address assigned.
- ip addr show // to know ip address
- we conenct with switch/router with cable get an Ip address, we connect with wifi to smae router get another ip address, other system can connect with us using any of this two ip address.
- Each ip is divided into ports, 65000+ ports available.
- Each port can be communicated and application can listen to one or more port.
- Each applciation can specity host/ip and the port for listening/ responsing etc.
- If we want to have application to run only on pur system and doesn't want to share with other, we can run on 127.0.0.1 address called loopback address(lo)
- Every host has lo address which smae ip of 127.0.0.1.Nothing leaves host when you sue this lo address
- You can also use `localhost` instead of this ip.

## Database Basics
- Bascally Sql : MySql, PostgreSql , NoSql:MongoDb etc
most famous one usage.
- Sql : Tabular format data storage, realtionsl
    NoSql : Key Value data storage., document

### MySql db/server
- In linux it is installed as service, so you can start by service mysqld start.
- Db has logs, default port is 3306
- By default has root user and automatic generated password
- It has cli or prompt where you can login then run commands etc.
- use dbName; create table;select * from table ;// some comamnds
- Should create other users and give them their respective permissions i.e grant permissions
- create user userName@ipaddressfrom where user connects etc..

### MongoDB
- NoSql Db, open source, json like document storage.
- Have community and enterprise version.
- Isntalled as a service, service mongod start
- HAve logs etc, 27017 default port
- ahve db, collections within collections have documents.Every document is given an ID.

## Security(SSL & TLS Basics)
- SSl(secure socket layer)
- A certificate is used to guarantee trust b/wn two parties during transaction.
- TLS certificate ensure that the communication between the user and server is encypted and server is who it say it is.
- In http connection, the data passed over the internet is in simple text format and can be compromised.So it should be encypted by adding the key.
- If key is sent over the network it can be compromised resulting into data compromised.Symmetric Encryption it is called.
- Asymmetric encryption has two keys private key and public key.Data is encrypted using public key which is also tranferred over the network but decryption can be done using only private key so it shouldn't be transferred.(similar to you use ssh using key pairs, a public and private key, ssh-keygen to generate keys for ssh)
- openssl command is used to generate key pair for webserver.
- Now with public and private keys data is encrypted so even if hacker gets the data he can't decrypt it.
- So now hacker can create a similar website and host it and will have keys too for showing it's secure.If somehow hacker overwrites your network info and makes you visit this dummy site and you sent the data over , you can be hacked.
- So you get the certificate as well when you visit a site, but certificate can be cloned, browser has inbuilt mechanism to check authenticity of the certificate
- CAs(certificate authority) generate certificates, many orgs like symantec,digicert etc are available.
- CAs itself uses keys to validate the certificate, the public key is availble to browser to check if they are legitimate.
- In company local envrionment you can have CAs local network and encrytion to have the authentication.
- This whole infra is called PKI(public key infrastructure)
- certificates/key which are public have .crt,.pem extension.private have .key or .key-pem (so private ones have key with them)

# Basic File Types
## YAML
- Used to genreally represent data, generally config data.
- Key Value pair
    - Type: Name (a space is after :)
![Screenshot 2024-02-05 at 7 31 38 AM](https://github.com/MadSn7/My_Learning/assets/62552772/da32b4a4-0583-44a0-9c5d-c3764b414e9e)
- No of spaces is very important, make sure they are equal.
- Dictionary is unordered, list is ordered(array).# to comment

## JSON & JSON Path
- Same yaml data can be represented in json, yaml uses indentation(spaces) whereas JSON uses curly braces.
- list is [{element1},{element2}]
- JSON Path is query language for JSON like sql for dbs
- root dictionary is $ so start query with $ like $.car $.bus etc to query.
![Screenshot 2024-02-05 at 2 58 31 PM](https://github.com/MadSn7/My_Learning/assets/62552772/36344bb0-32c4-44e3-9e42-a665acc3e5de)
- Any output is availble with [], form of array.
- if json is just array, $[0] for first, $[1,3] if want 1 and 3 index element.
![Screenshot 2024-02-05 at 3 04 40 PM](https://github.com/MadSn7/My_Learning/assets/62552772/d5d45984-07a2-4d71-af1e-cef13d717d63)


# SetUP Your OWN LAB(Linux)
- Can set in either a local system or cloud.
- Instead of installing and using in your system, you can use virtual machine and install and update software there so in case of issue you can delete vm or make a copy of it to create a newer one.

## VirtualBox
- Called hypervisor, to create vms, installed on bare metal.Expensive(Type 1 Hypervisor)
- (Type 2 hypervisor) Oracle Virtual box etc installed on some system 
![Screenshot 2024-02-06 at 4 08 27 PM](https://github.com/MadSn7/My_Learning/assets/62552772/6884de93-20c5-4875-9a71-03ddd95b492d)
- Oracle virtualbox is free and works on all OS like windows, mac,linux,solaris etc.
- Oracle VirtualBox require very small spaces and memory but recommended to have good enough specifications.
- You can install virtual box according to you system ewither windows, mac etc.
- You can either have os isntalled or in a disc or can download from intenet.
- Steps involve selecting the file we got from internet, space etcstting, cpu settings.

### Connectivity to our VMs/Networking
- from windows we can connect using rdp, in mac or linux we can use ssh.
- you can set vm ip according if not availble, in mac some difference need to do port forwarding in settings.
- Lot of ways you can do networking like allowing internet access or not , vms intercommunication allowed etc.

### Vagrant
- Used to deploy vms automatically which has you given configs.
- Vagrant has boxes(os and scripts to use in the vms)
- vagrant command is used in vlie, various command like status, stop, reload etc.
- Vagrant has vagrant file, it has os and other configs for you.



