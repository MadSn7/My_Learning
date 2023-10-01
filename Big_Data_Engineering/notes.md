What is data?
Collection of info.

Data you can do two thing store and process.

Big Data
After internet and pc amount of data increased by many fold.
and storage and process became difficult, troublesome and costly.

Problems or charateristic of Big Data
1. Volume [There is always a bigger fish, some one says 25 GB, some says 100GB some petabytes]
2. Value(Quality)
3. Visulisation
4. Velocity
5. Variety(Strucured, Semi etc)
6. Volatility 7.Viability ....... etc etc

Under Big Data 10K+ technologies some of them Hadoop, Spark etc

Various stages of handling Big Data/ Data Layers

etc etc ..... Automation/Scheduling(Injetion) -> Storage -> Processing(Analysis) ->Testing -> Visualisation -> AI/ML(Analytics)  etc etc....

History of Hadoop
2002 Google released Paper on GFS(Google File System) to distribute the data, 2004 GMR(Google Map Reduce) process resuced data.
2005-06 : Hadoop Invented, Doug Cutting Ex-Yahoo and declared open source,Apache
            |
        _________
        |       |
      HDFS    MR(Map reduce) Basically from start to current hadoop has these 2 things only  //not looking good in github

Many companies built on top of hadoop add many features for helping, and sell services. Data bricks(sandbox), amazon(EMR),microsoft(Hdinsight),Ibm(Big Insight),Mapper(m3 etc)

Apache's Hadoop is base for it, work on it and help in all

Some things to know

Linux,SQL(Data query),Programming Language(Java(Also Scala), Python mainly but any works)

Data Engineering have many parts Big data is one, they are simialr but not same

The journey
Linux -> Sql -> Programming Languages -> Big Data Tools/Tech(Hadoop(HDFS,Hive),Spark(Spark batch Spark SQL) Mainly for begineer etc) -> ETL concepts and knowledge(Also Warehouse) -> Cloud computing -> Your Project Done,Use your knowledge


HDFS(Hadoop Distributed File System)(Derived from GFS) //Basis of any Big data Technology storage

What is file system?
>used to read and write data in your machine.windows mac linux each have their fs.Like NTFS,EXT,S3,HDFS..

What is a block?
>data is stored in parts, created by fs and saved as small small blocks.We divide so it will be better for read and write. Windows's NTFS has 16kb block size,Linux's QXT 512kb. same system in hdfs.

What is client and server? understood

Types of file system
>Standalone : NTFS,EXT
>Distributed : HDFS,S3,CFS (Any data stored will be separated and kept in different different machines)

Types of Distributed Systed 
>Master/Slave (One master communication b/w slaves happen to master) Hadoop, spark etc most follow this.SPOF,SPOC
(Single point of failure and communciation)
(Hadoop 4 version uptill now h0,h1 (Outdated) , Different version h2,h3)
>Peer to Peer (every node can connect with another) Cassandra

What is process?
>Program in execution.

What is daemon process? Process taking place in background. Hadoop has 5 daemon process 3 for HDFS 2 for MR(JP1, JP2,...JP5[JP means java process])

Node : Individual machine physical or VM
Cluster : group of nodes

What is client API?
>Application Program Interface.Any request sent is AN API or using an API.Handles request.

HDFS

Block Size : 64MB(Default upto version h1)/128MB( Default from version h2) Can change, in multiple of 2 .
Replication : 1Block = 3 Replication (Default)(Any data loaded is copied 3 times, happens in different node)(1 original + 2 Copy) so 1 gb need to store need 3 GB.

Hadoop support all major os windows,linux,mac but mostly work and nodes are using linux.

JP1 runs in master node.
JP2 runs in slaves node.

You can connect slaves to master but usually there is EDGE node and we avoid connecting them directly,edge node it's like a client.
Every node other than native FS , HDFS will also be present after connecting.When hdfs command is triggered hdfs saves files else native OS works.
When we trigger Hadoop Command for let's say saving some data, from any slave node, a client API is created, this directs the request to the master node, only info is transferred not the data,
Now in JP1 functions 
>creates a metadata
>Check data size and it creates block according to default or you set
>create replica for each block
>now decide and save metadata of infor how to save those blocks.RackAwareness Policy.
Also metadata isn't stored in HDFS it's stored in local os FS in master machine.
>and reply back with response which contains the block size and which block to save in which node.
>Now client api will create block create replica and client api will do process of pipeline which will distribute the data accordingly to all respective nodes. Acknowledgment is given by nodes to pipeline and pipeline to client api to master of confirmation.
>Another issue meta data is created but coudn't write a block to that node,cleint api will repond to master informing this and master will now reply to write in another node.
>Failure happens only when all nodes are down, or client api goes down.
>Pipeline is for write only.

>Every 3 seconds every node send a hearbeat to master confirming it is still functioning.

Failures Case
>Network/Software(Temp)
>Hardware

>Master will come to know through hearbeat when a node is down.And Hadoop has Auto Failure(AF) process and tries to save copy of failed node and save it to anotehr node which doesn't hasve same blocks. This is for hardware failure.

>Temp failure Node will get up again, the amount of data copied to other node is now considered by master to be in that node, and you have to clear those blocks from that node. So it's better to remove the full node and add it again, as AF
will take place.

>From h2 HA(High Availability) is ensured in Hadoop, meaning we can handle master going down.
How they implemented HA?
>Have more copy of master, One is active other are passive.Zookeeper handles that  , when active is down it activates one of other passive ones. We have a zonal node as well which saves the meta data.

Types of Node:
H1                         H2(Without HA)
>Master Node               >Master Node
>Slave Node                >Slave Node
>Checkpoint Node           >Secondary Name Node
                                                                                                    
Types of cluster

H1:
1.Single Node(Pseudo node) : In one node all master slaves etc.No replication
2.Distributed cluster: have more than one nodes.
Hadoop best practice have atleast 5 nodes.(1 master,3 Slave,1 secondary)
You can play around with less.

H2:
SNN is automatic.


{Not clear with daemon names, which maskes a master or slave}

##Hadoop Framework Overview

>2002 : GFS
>2004 : GMR(Data processing saved in GFS)
>2005-06 : Hadoop released (HDFS and MR)(Base hadoop has only this 2 componenet)

Various people added component on top this hadoop resulting in various hadoop framework.Some of them are:
>Hive : Most widely used with any hadoop project.Developed by fb.So hive has similar to sql language and you write your query and hive converts it to java and interact with MR.
>Pig:Invented by yahoo.PigLatin scripting language used, same issue don't want to use only java, same function as hive.
>Scoop : Group of memebers invented it.Need something to bring data from db to Hadoop, so need to write MR code.No language as such, has command to import/ export commands so data from hadoop to db and db to hadoop.Internally all commands or java.Any RDBMS works.
>OOzie : Some xml configuration for scheduling works.(Not majorly used, many other tools can be used).
All above uses MR so they are abstractions of MR.Many other componenets are there but these 4 are using MR and it's their abstraction.

>hBase : Hadoop's db.Developed by fb,language is different from sql.
>Mahout : Data science stuffs AI/ML.
>Flume : Like pipeline like scoop but can have real time data as well.Like messaging queue.Only for import data.

Basically these 9ish components are called hadoop framework.Various others are there as well.

All components are loosly connected.Most framwework in big data are loosly connected.
There is scenario to connect with other big data framework like spark etc.
Non big data tech is also allowed like scoop is used from RDBMS to hadoop and vice versa.

Layers of these componenets
>Storage : HDFS, hBase
>Processing : Hive,Pig,MR
>Data Pipeline : Scoop, flume
>Scheduling : OOzie
>Mahout : AI/ML Data Science.




 














