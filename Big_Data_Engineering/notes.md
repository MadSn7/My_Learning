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
>and reply back with response





 














