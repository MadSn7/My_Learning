># AWS SAA

## Introduction
### Why take this course
- Confirming your knowledge with a badge.
- Industry level knowledge and skills learning.
- Definitely job oppurtunity, pay recognisation.
- Main components in SA course to get knowledge about various services and to design
    - Securily
    - Reliabily
    - Efficiently
    - Frugly(Cost Optimization) [4 AreaS]

at a beginner level for various services.

- My dummy Cloud Practitioner Exam result
    52/63 == 80%.(Recommended ~70%)

- My dummy SAA Exam result
    31/63 == 48%.(Recommended ~70%)(Fail Currently)(Took I think 2 hours lengthy paper)
### Why/What is SAA(Solution Architect Associtate) cert?
- No coding experience required.
- Understand basic services and manage 4 AreaS about it.
- Broad exposure on various core services on aws.
- Do anything around on AWS or career it can be the base for that.

### What is A Solutions Architect?
- Design technical solutions based on business needs and requirements.
- Bridge gape between design and implementation of product using various services and keeping note of 4 AreaS.
- Enterprise Architect -> Solution Architect <- Technical Specialist Architect(python.java etc expert)

#### Exam pattern
- SAA-C03
- `65 question exam`
- `130 Minutes`
- Scenario based questions and not just simple ones like what vpcs are etc etc
- Two Type question
    - MCQ(One correct, 3 incorrect)
    - Multiple response(2 or more correct,total 5 or more options)
- 65 question, 50 will be cosidered for scoring not sure which, so treat all equal.
- 720/1000 to pass(~ 72% )
- Question weightage

|Tables |% of Exam |
|----- | ---- | 
|Security |30% |
|Resilient | 26% |
|High-Performance | 24% |
|Cost-optimize | 20% |
- Check and confirm above info before exam
### _Check if we can reattempt question review or not??? got answer as yes_

## Services - Networking
### VPC(Virtual Private Cloud)
- Isolated sections/part of aws cloud, so as to make resources from different users separate from each other , as well as ours if we want to deploy separate and different applications.
- Allow account owner to take full control of networking and usage, like subnetting, route tables,firewalls,gateways similarly like in physical place but aws makes it simple.
- A vpc is specific to a single region, cannot span over two region
- A resource deployed in one vpc by default couldn't contact other vpc's resources, automatically isolated.So have to explicitly enable communication to internet or other vpcs.So automatic security.
- Each vpc has range of cidr block associated with it, called CIDR block, these are the ip's a resource inside vpc can use.
- CIRD block is between /16 to /28 range(You can specify the amount you want)(/yy means out of 32 bits yy many are alloted rest bits can be you cidr range).
Ex
192.168.0.0/16 so range for CIDR blocks
192.168.0.0 to 192.168.255.255
- Optionally can enable secondary ipv4 block 
- Optionally can enable ipv6 block so /56 availaible
- have upto 5 IPv6 cidr blocks

<img width="1368" alt="Screenshot 2023-12-24 at 8 26 18 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/63424445-d3ae-4a33-b5ea-1f983c21d805">

>Two types VPC

1.Default : Whenever you create your aws account, aws configures a vpc for you , not one but for all regions you have access too.
- Idea is to have a start to create resources without much issues.
- /16 Ipv4 cidr block, and default vpc address 172.31.0.0 cidr block so 65,536 address available(for all accounts)
- For every region's vpc, every default subnet will be there with /20 so: 
    1. AZ1 : 172.31.16.0/20
    2. AZ2: 172.31.32.0/20
- Internet Gateway is attached to vpc, and a default route that points all traffic to internet gateway(0.0.0.0/0 i.e any address to internet gateway)
- So any reource in default subnets will have internet access and any device on internet will have this subnet reource access.
- Default security group: By default allows outbound traffic.
- Default Network Access Control List : Allows both inbound and outbound traffic.
<img width="1418" alt="Screenshot 2023-12-24 at 8 36 18 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/6b9a5a13-e8a7-47ff-a14d-7679c6e3fe22">


2.Custom :  You create it according to your needs and configuration.

### Subnets
- Group of ip addresses in vpc
- Reside within a single Availability zone
- Subnets can be made private or public to allow external access to resource within them.
- Subnet must be within vpc's cidr range
- Subnet block size between /16 to /28
- Subnets cannot overlap with other subnets within vpc(Np common ip address)
- A subnet allows for an optional ipv6 CIDR and can be used only with IPV6 and remove ipv4.
- By default subnets within a vpc can communicate with each other
- Auto assign ipv4/6 public address.
<img width="1416" alt="Screenshot 2023-12-24 at 10 27 45 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/05cd4c1f-7a15-4f6d-9fc4-a1f07d8c3c53">

### Routing in VPC(Route Table)
- Every Vpc has a vpc router, (subnet ip+1)first address is for router as shown in previous figure.
- Purpose is to route traffics between subnets and in/ out of vpc
- We can configure router by `route tables`, it contains the set of rules containing ip called route.
- Works similar to any router, have destination and target.Checks destination of the packet and sent it to it's respective target.
- By default route table has one local route configured(2 if both ipv4 and ipv6 is enabled);
- Every subnet is configured to route table
- A default route table is created along with vpc creation and every subnet is by default has this default route table attached, so multiple subnets can be associated with one route table, but a subnet always has one route table.

### Internet Gateways
- By default when you create a subnet it is private, so to give access for internet we need internet gateway.(And this makes subnet public)
- Now Internet gateway are region specific and are associated with vpc(Not each az's, and cover all az's), so if vpc has no Internet gateway by default all subnet will be private.
- VPC can have no or one Internet Gateway, and Internet Gateway is attached to one VPC only.
- Process: 
    - create Internet Gateway 
    - Attach to vpc 
    - Create custom route table 
    - Create default route to be targeted to internet gateway(If no other route matches this default will be matched to target to igw i.e internet)
    `0.0.0.0/0 -> IGW`
    - Create subnet and attach this previously created route(Now all resources can access internet)
- When you deploy resources in public subnet by default they will only have private ips,and you have to enable public ip explicitly.Public IP depends upon private ip, so internally everything is accessed through private ip, only for intenet public ip is used.

### NAT Gateways(Network Address Translation) 
- Used in condition where your subnet is private but your server needs to access internet in case of updates etc but from internet there can't be an incoming connection.
- If we use IGW it makes our vpc aka subnet public, not good for just taking updates or some info/data from internet etc
- Process
    - Attach IGW to VPC of your subnet
    - Create a public subnet, with default route to igw
    - Deploy NAT Gateway on public subnet
    - Now configure route of private subnet to access public subnet's NAT Gateway.
    - So flow server(private subnet) -> Nat Gateway(Public subnet) -> IGW -> Internet
    - And as server doesn't have public ip no access to it from internet.

<img width="1431" alt="Screenshot 2023-12-25 at 3 01 15 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/635a8e9f-ffdb-4c9f-bf6f-44f9e58f1ab9">

- Above process shows IGW is needed even when NAT gateways are used, can think of nat gateway as some server in public subnets
- Charged per hour and per GB of data processed(Can process 5 GB/hr can automatically scales upto 100GB/hr)
- Nat gateway are not region resilient like IGW,
(Each subnet must reside entirely within one Availability Zone and cannot span zones), you deploy NAT on subnet so have to deploy on multiple az's for resiliency(1 NAT for each AZs)
- Uses Elastic IPs(A fixed ip,won't change)

### DNS(Domain Name System)
- Vpc ip + 2, is the address for DNS server.So vpc 10.10.0.0/16 -> 10.10.0.2
- Every resource has an internal ip, they can have domain name as well which can be used.
- By default private subnets gets domain name, in public you need to explicitly need to enable it.(default vpc it is enabled even though it's public)
- Services internally ping dns server when domain name is encountered to get private ips to communicate.

- enableDnsHostnames	
Determines whether the VPC supports assigning public DNS hostnames to instances with public IP addresses.The default for this attribute is false unless the VPC is a default VPC.

- enableDnsSupport	
Determines whether the VPC supports DNS resolution through the Amazon provided DNS server.If this attribute is true, queries to the Amazon provided DNS server succeed. 
The default for this attribute is true.

### Elastic IPs
- When we deploy an EC2 instance, when they get redeployed the public ip will most time changes, so issue for end user to again access it.
- It is a static ipv4 address, it will be reserved for your account, and you can associate with ec2 instance.
- So when ec2 instance reboot, we will still have that ip address, can assign sg to elastic ip.
- Specific to a region,address are provided from aws provided or you canbring your own.
- When updating an ec2 we can assign this elastic ip to another one and keep our service running.
- `check it's pricing`

### Security Groups & NACLs
- They both act as a firewall.Firewall monitor traffic and only allow traffic permitted by a set of predefined rules.
- This rules break down to two parts
    - Inbound Rules
    - Outbound Rules
- Two types of firewall
    - Stateless : Both rules(in & out) must be configured.
    <img width="1432" alt="Screenshot 2023-12-26 at 6 58 28 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/ec0697ef-0999-4d51-b17b-141a16899a1c">


    - Stateful : It knows the response for a particular request, so if a request is permitted, response is automatically permitted.So no need for that particlar request's outbound rule.
    <img width="1427" alt="Screenshot 2023-12-26 at 7 02 12 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/25f94933-1083-473b-9009-20aae149d905">
(Grey ones not necessary above)

#### NACL(Network Access Control List)
- They monitor traffic entering and leaving a subnet, and don't work within subnet(Like RAW)
- They are stateless, so need both inbound and outbound rules.
- They can allow or deny traffic
- Rule Fields have rule no*,type,protocol,port, ip, Allow/Deny.(lower the rule no, earlier it will be processed)
- Every subnet must be associated with a NACLs
- NACLs can be associated with multiple subnets, but a subnet must have one NACl.
- Some traffic are not filtered by NACLs like amazon dns,ecs task metadata, windows update etc. 

#### SG(Security Groups)
- They act as a firewall for individual resources(EC2,LB,RDS etc)
- They are stateful, so only request need to be allowed, response will be automatically allowed(request may be inbound or outbound)
- A Rule have various property like protcols , port, from source ip/sg(same sg as well) etc etc.(similar inbound and outbound)
- Sg Group if there are no rules blocks everything
- All rules are for allow traffic, doesn't have block rule.
- Can assign multiple security group to a single resource, what ultimately happens is both groups rule get merged, combined will take place.
- By default sg group has rule to allow all outbound traffic,can delete this rule.
- Same sg can be applied to various resources.

### Elastic Load Balancer(ELB)
- Issue : Application running on ec2 instance(ip is x), now we need to handle high traffic and ensure availability so have several instance running, in different azs, now they will have different ip(ips x,y,z etc)?How to handle request now from user?
Client should have one ip address not all.So one elastic ip doesn't work.
- Now we have a load balancer whose ip will be hit by user and it will load balance the request to several attached ec2's for the application.
- So only ip to know is of elb, and we get abstraction for server as well.
<img width="1406" alt="Screenshot 2023-12-26 at 9 26 36 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/3fbe3b5c-4424-49de-b6a4-109c7bd4be76">

- AWS has 3 types of load balancer
    - Classic LB 
        -  First lb introduced by aws, not recommended for use now.
        - Various limitations like one ssl used.
        - Don't use for any new application, maybe some legacy ones using it.
    - Application LB
        - Works for web based applications, so works only with http/https/web-sockets
        - Operates at application layer(Layer 7)
        - Can Forward request based of conditions of path ips etc
        - Can perform application related health checks
        - SSL certificates resides on ALB
        - From ALB to server communication is unencrypted but you can make it encrypted by adding another ssl certificate you have to manage.
    
        <img width="1237" alt="Screenshot 2023-12-26 at 9 36 33 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/91005d96-6e27-4c53-a3db-a7549568265c">

    - Network LB
        - Based on Layer 4 (TCP/UDP)
        - Mean for use when using applications not using http/https
        - Fater than Application LBs
        - Health checks are based on ICMP/TCP connection
        - NLB forwards TCP to your instances, whereas in ALB it is terminated on ALB
        - Need ssl certificate on server, session is between client and server, NLB silent middleman
        <img width="1225" alt="Screenshot 2023-12-26 at 9 41 11 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/6260b2e8-00ac-4c14-ba75-8f869130a2c9">

- Process to deploy
    - Need to select the different AZs we want our LB to handle so different subnets basically.
    - So you have to deploy LB node into that subnet of your chosing which in turn will balance traffic in same subnet or another one, wherever your resources are present.
    <img width="1400" alt="Screenshot 2023-12-26 at 9 51 55 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/6a74c305-36c7-427f-b614-2f1389ef7524">
- Cross-Zone load Balancing : 
    - Issue If any azs has less or more number of servers running, so to LB node traffic is balanced but for each server gets imbalanced.As in fig
    <img width="1322" alt="Screenshot 2023-12-26 at 9 56 22 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/33c5a241-d256-4529-85a1-47b515e52d1a">


    - Reolution is allowing LB nodes to handle other az's too.
    <img width="1286" alt="Screenshot 2023-12-26 at 9 58 02 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/ac3c3ee2-e813-4f0d-99b3-fe1d3b00752b">

- Deployment
    - Similar deployment like ec2
    - Can deploy them as public or private load balancers(in public subnet or private subnet)
    - Two peices of configs needed
        - Listeners : It's a process, depend on your application, checks for says eg request on a prticular ip.
        - Target Groups : It is the place where the listened request is targetted to.Eg EC2's
        <img width="1335" alt="Screenshot 2023-12-26 at 10 08 21 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/d9b03e94-a7f9-4474-a584-a7ee16ccfdcd">
- Basic work, deploy elb with listener at recommended port like 80 and http, set target to like ec2, lambda etc and then hit dns provided for load balancer to hit your application(Above seen for ALB)
    - Usually keep server in private subnet, and lb in public one so only lb have public access.

### VPN and it's usage in AWS
- Need a secure way to connect on-prem devices network to aws's subnet usually private.
- 1.25 GBPS bandwidth
- Process
    - Deploy VPN gateway on vpc.(Terminates local vpc vpn)
    - On Prem Side, deploy customer gateway(Terminates on prem vpn)
    - Then customer gateway and vpn gateway will get public ip address
    - Then we will establish and IPSec tunnel over internet between them.
    - For routing we can have static protocols added or have dynamic rule added via BGP
- Pricing
    - For each available vpn connection per hour
    - For data outbound from aws to on prem.

![Screenshot 2023-12-26 at 2 18 09 PM](https://github.com/MadSn7/My_Learning/assets/62552772/3571bc69-54f4-47fa-bcad-fa664e08b1bd)


### Direct Connect
- Direct connection to aws resources, alternative to vpn where we go over the internet.
- Physically connect to aws resources, high speed reliable connection so it's a physical connection.
- for private subnet need to go thorugh vpn gateway again, for publi can go direct from direct location, on prem should have customer gateway
- Pricing
    - Port hours
    - Outbound data

![Screenshot 2023-12-26 at 2 25 33 PM](https://github.com/MadSn7/My_Learning/assets/62552772/90047dea-1891-4725-b4e4-f882a2b88bf8)


### VPC Peering
- By default two vpc can't communicate, but what if we want them to?
- We set vpc peering and the resources can communicate netween them.
- Transitive vpc peering not allowed, so for peering two vpc we need to set peering can't have trasitive.
- VPC peering can be set between
    - VPC in same region
    - VPC in different region
    - VPC in different account.
- Pricing
    - No cost for peering connection
    - Same azs data trasfer no charge
    - Differnent azs cost for data transfer
- Owner of one vpc send peering request to another vpc's owner, and that will create peering.
- We have to create routes both sides, and target will be peering name.

![Screenshot 2023-12-26 at 2 57 27 PM](https://github.com/MadSn7/My_Learning/assets/62552772/2bbd6da6-a8bf-4e37-9db6-f5a28b900adc)


### Transit Gateway
- Transitive peering is not allowed, so for n vpc's connection need nC2 peerings, also from on prem vpn connection require cusotmer gateway connection to all vpc    
![Screenshot 2023-12-26 at 4 28 05 PM](https://github.com/MadSn7/My_Learning/assets/62552772/a77c396b-d543-405d-81f4-0e25a33ff897)

- Create to avoid full mesh of vpc peering, so now only need to connect to aws transit gateway.
- O prem also need to connect to transit gateway.
- Transit gateway can connect with other transit gateways of other accounts too.
![Screenshot 2023-12-26 at 4 31 31 PM](https://github.com/MadSn7/My_Learning/assets/62552772/d83c17be-fe03-4624-ab2e-1c64fb4e7fd0)


### Private Link
- For s3 connection need to go over internet, so issue in private subnets if we don't want to go over internet.As S3 is public service.
- Private Link give direct access to reources even to other vpc

![Screenshot 2023-12-26 at 4 36 32 PM](https://github.com/MadSn7/My_Learning/assets/62552772/eddc9840-682c-49ce-ac9d-802ba361f05a)

### CloudFront
- Your server is in USA and user access from India or Australia, it going to cause latency issue.
- AWS has provided edge locations(more in number than regions), you can cache you files in this edge locations.Edge locations are hlepful for various selected services.
- Amazon CloudFront is a content delivery network (CDN) service.
- Speed up media files, css, html etc files.
- Architecture/Process
    - Origin : like s3 bucket, server containing images etc
    - Now cloudfront will cache this in edge locations.
    - Users can get content now from edge locations.
    -Distribution : Configuration unit/block, tells source file locations, provides a global domain name.
    - New content if not found, will be asked from origin and then later cached for upcoming requests.
    <img width="1316" alt="Screenshot 2023-12-27 at 7 34 15 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/f9350b94-8fbd-4165-81ba-34475d016457">

- TTL(Time to Live) :  Cached content timelife, default is 24 hours.Then again requests to origin and caches again.you can set your own TTL.
- Cache Invalidation : Allows to invalidate cached contents, you can do it when you update your original content.Manual process to clear caches.Can invalidate all objects from distrubuition or particalr object.EG : /* all object clear,/fileName,/folderName
-  Can have custom domainName, automatic cloudWatch logs extra cost you can include more metrics.
- Most useful uses are static websites, videos on demand, streaming etc.
<img width="1295" alt="Screenshot 2023-12-27 at 7 44 14 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/497013e5-e675-4ba9-9f8f-f7c960ff9393">


### Lambda@Edge and CloudFront Functions
- Can run lightweight lambdas, cloudFronFunctions at edge locations to do small works and verifications etc.So less server work.
<img width="942" alt="Screenshot 2023-12-27 at 8 15 34 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/684da6cc-0a70-4457-a132-b0b980e67d65">

- Both CloudFront functions and Lambdas are different
    - CloudFront Functions : Runs when recieves a request  from viewer and also before sending the response back.
        - Ideal for leighweight operqations, like cache key normalizations, header maniulations, ur redirects/rewrites, authorizations like json
    - Lambda@Edge : Both above as well as before forward request to origin and also after recieving a response from origin.
        - Ideal for little long running funciton, changing memory/cpu requiring functions, third party dependency, network-dependent functions, need body for http
<img width="1350" alt="Screenshot 2023-12-27 at 8 18 36 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/467fb3ed-edb8-456f-b092-82b4be533448">

### Global Accelator
- If request need to go to server it go through internet and it takes time, inefficient and take long path basically whatever available.
- They also have edge locations, bit different from cloudfrontEdge locatins
- When user hit the request, it goes to nearby global accelator edge locations and it then takes from there so fast, secure reliable than internet.
- cloudfront is for cache objects, whereas global accelator if for routing quickly to aws network. 
<img width="939" alt="Screenshot 2023-12-27 at 8 22 49 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/88c02c9b-078e-43f6-8484-86d798bb1933">


### Route 53
- AWS managed DNS service, acts as domain registrar, can purchase domain here and can manage your domain name from here.Global service not particular to region.
- Hosted Zones : Collection for dns rules and records, what happens and aws allocate 4 name servers for it.
- Have various routing policy based on locations etc.
- basically purchase a domain name, and attach alias for your server etc public ip, then using domain name can reach server ip.
<img width="1016" alt="Screenshot 2023-12-27 at 8 26 22 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/d98696cc-7128-4f8f-b012-2d4a6c897fd2">

### Route 53 Application Recovery Controller
- Way to check if you application is running or not, scale up etc instead of using various other tools.
<img width="1424" alt="Screenshot 2023-12-28 at 6 27 51 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/07338402-3b68-40ef-9c51-820202da0ef8">

>Quiz Networking

> 17/22 == 77% Correct

`AWS Site-to-Site vpn vs aws vpn vs Direct connect?!`

## Services - Storage
### Elastic Block Storage(EBS)
- Block Storage : It breaks data in blocks and stores them with unique identifer.A collection of this blocks can be attached to show as volume and OS can create File System onto it, as well as hard Drive on it so as to install OS/boot it
- So it is both mountable and bootable
- In AWS block storage is EBS service, provides block level storage volumes for your ec2 instances.
- As they are seaprate from EC2, we can move data from one EC2 to other too.Tradtionally one block is attached to one EC2 but now you can attach to more but make sure all don't overwrite each other's data.
- Available within AZ's, both EC2 and EBS should be in same az, can't if in another az.
- Basically create EBS storage and during creating EC2 attach that created storage.
- To migrate data between az, we can take snapshots, which get stored in S3,now we can deploy EBS in another AZ, similar step for cross regions too.
<img width="1405" alt="Screenshot 2023-12-28 at 10 34 16 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/a948c244-d71c-4726-a89f-7fa852835a1a">

- EBS volume types
    <img width="1411" alt="Screenshot 2023-12-28 at 10 35 36 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/ca013ed9-613b-4e5e-8d71-4eb09162fb37">
    
    - General-Purpose SSD(gp2 & gp3)
        - Volume backed by solid state drives(ssd)
        - Balanced price and perforamance
        - Recommended for most workloads, if not sure use this.
        - gp3 are latest generations and lowest cost,good for most application, 20% cheap than gp2
        - gp2 are default EBS volume, performance increase with size increase
    - Provisioned IOPS SSD(io1 & io2)
        - Also backed by ssd, highest performance EBS, for critical high throughput , intensive workloads.Like DB etc.
        <img width="1421" alt="Screenshot 2023-12-28 at 11 08 38 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/f2ec5a08-e391-4889-844b-e77fce4a2888">
        - io2 & io1 mostly similar, io2 more durable and io2 block express better.
    - Throughput - Optimized HDD(st1) and Cold HDD volumes(sc1)
        - Based on HDD
        <img width="1397" alt="Screenshot 2023-12-28 at 11 13 10 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/504d3ab8-ac8c-4600-9788-24c22aca1ff3">
        - st1 : Big Data ,warehouse,log processing
        - sc1 : cheapest one,data infrequent access.
    - Magnetic Volume(previous generation)
        - Depend on magnetic drive
        <img width="1217" alt="Screenshot 2023-12-28 at 11 27 13 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/5a29cb64-aea7-4b31-bfae-f74cdacadbde">

    - price per GB per month,faster iops  Cost
- lsblk command in ec2's terminal to check all blocks listed.linux /dev/(xvdf etc naming) usual path of block access.
- sudo file -s /dev/(xvdf etc path), if reply data means no file system present
- Then we can create file system sudo mkfs etc command, and various stuff in command line and linux specific you can do.
- you can mount the volume to a folder so as to use that folder store things there to be saved in ebs.


### Instance Store
- Temporary block level storage
- Physically on system your server is on, on the host machine, so issue is if host moves so data is lost, if same host no issue.
- REBOOT doesn't moves machines, but stop and stat does.
- Not availble with free tiers instances like t2 micro etc.

### EFS
- First of two files system storage service of aws(other is FSx)
- Uses NFS (NFSv4) file system, windows doesn't work with it, only linux.
- Can mount on multiple EC2
- VPC specific deploy, specify subnets to get mount target which are an ip adress
- Now ec2 instance can connect using this mount ip.
    <img width="1325" alt="Screenshot 2023-12-28 at 5 06 39 PM" src="https://github.com/MadSn7/My_Learning/assets/62552772/d3f2dce6-bed1-4748-a36c-c7917169c97d">
- Different types
    - Standard Storage Classes
        - EFS Standard
        - EFS Standard-Infrequesnt Access(Standard - IA)
    - One Zone Storage Classes
        - EFS One Zone
        - EFS One Zone-Infrequesnt Access(EFS One Zone-IA)
        
        ![Screenshot 2023-12-29 at 6 49 03 AM](https://github.com/MadSn7/My_Learning/assets/62552772/abac8f0c-3add-4fe1-9707-85bddbb45e74)
- Various Modes
    - General purpose perforamnce mode
    - Elastic Throughput mode
      
    ![Screenshot 2023-12-29 at 6 50 10 AM](https://github.com/MadSn7/My_Learning/assets/62552772/d7da403a-7019-47f3-a89c-93e99e062e3b)
    
    ![Screenshot 2023-12-29 at 6 50 17 AM](https://github.com/MadSn7/My_Learning/assets/62552772/332f620a-498f-4458-b5bd-65497e6cef81)


- Process
    ``` 
    sudo dnf -y install amazon-efs-utils
    ```
- Use above basic utilities, and then can mount efs file system to specific folder.Similar to any mounting, just get id from aws when creating efs
    ``` 
    sudo mount.efs efsId /directory
    ```
- EFS can be mounted but cannot be booted, can't isntall an operating system.Same commands for persisting reboot, 
- It has storage lifecycle management as well, have sg as well.

### FSx
- Fully managed service provides high performance file storage and wide range of workloads, similar to EFS.
- EFS works only with linux, fsx works with windows os as well including others.
- Also allows shared access like efs.
- Different types

    ![Screenshot 2023-12-29 at 7 10 24 AM](https://github.com/MadSn7/My_Learning/assets/62552772/2b02f764-9f58-4b95-a675-27ff52d3ff9d)

### S3(Simple Storage Service)
- Object based storage service, basically a place to save and drop files.Due to being in AWS will integrate well with other aws services.
- Object Based Storage is different from nfs based storage, can't be used as block based as well.Great for log files, media,static sites, ci/cd artifacts and many more usage.
- Media websites like netflix etc, storing files in all servers is problematic, not feasible, we can store media files in S3.Server will just have your logic/applications and can give that S3 links for downloading content from there.
- Files in S3 are stored in bucket say a Folder, can have as many buckets you want, or can just have different folders inside a bucket.
- Inside buckets there are objects, every file you upload is an object.
- Object has
    - Key : The File Name
    - Value : File Data
    - Version/Metadata/Other info(can enable/add)
- Has flat file structure, it looks like it has folders but /music/song1 is not a folder but s3 stores it as flat so /music/song1 is name of object.
- S3 replicate object in same azs as well as several azs to be resilent and available.
- S3 bucket name is unique for all aws account, so no two s3 bucket with same name not possible.(Cause s3 has links given to access object, and this should be unique)
- S3 can handle indifinite number of files.
- Size of one file can be max upto 5TB(Multi part upload allows you to brak up an object into parts before uploading)
- An aws account can have 100 S3 buckets, but can increase to 1000 after requests.
- By default public access disabled, we choose region for S3 but S3 is global service.
- Can't delete non empty buckets.

#### S3 - Storage Classes
- Offers varies level of storage classes, based on data access, resiliency and cost.Various types are :
    - S3 Standard
        - Object are replicated to 3 azs
        - 11 9s worth of reliability.
        - Cost per gb/per month
        - Low latency, can make file public.
        - No charge for upload data,egress cost per gb.
    - S3 Standard - IA
        - Same 3 AZs and 11 9
        - cost per gb per month , but will be cheap, low latency, can make public.
        - Has a retrieval fee for everytime access
        - Good for very less accessed data.
        - Min size charge 128kb
    - S3 One Zone-IA
        - Object not replicated in different azs so cheaper than above ones.
        - Low latency, charge on egress, same as S3 standard- IA.
    - S3 Glacier-Instant
        - Low Cost options for rarly accessed data
        - Still quick retrieval
        - Stored accross azs, 11 9s
        - Per gb per month cost, high retrieval cost, 90 days duration.
    - S3 Glacier Flexible
        - Objects not immediately availble.Cold start.Can't be publicly accessible.
        - Per gb per month, per gb egress charge, retrieval fee depends upon how quickly you want data.
            - bulk : 5-12 Hours
            - Standard : 3-5 Hours
            - Expedited: 1-5 Minutes
    - S3 Glacier Deep Archive
        - Lowest cost, not immeditely accessible same as above one.
        - Duration 180 days, 40 kb per object min size
        - Options for retrieval are standard 12 hours, Bulk 48 hours.
    - S3 Intelligent Tier
        - Automatically reduces storage costs by intelligently moving data.
        - Per 1000 object monitoring/ moving charges
        - Not sure and have mix of data use this.
- Under propeties can change storage types for each object, and can change it later as well.
- You can configure S3 storage classes at the object level, and a single bucket can contain objects stored across S3 Standard, S3 Intelligent-Tiering, S3 Standard-IA, and S3 One Zone-IA. You can also use S3 Lifecycle policies to automatically transition objects between storage classes without any application changes.
![Screenshot 2023-12-29 at 10 10 22 AM](https://github.com/MadSn7/My_Learning/assets/62552772/613d9708-62ae-4adc-9eb2-65c39db85919)


#### S3 - Versioning
- Without versioning, if we delete file no way to bring it back,also can't upload new file with same name to update file, it will delete old same name object if we have same name.
- To get older version, have some backup available versioning is created.
- Versioning is applied on S3 bucket itself not any particular object.
- S3 bucket can be Unversioned, Version Enabled and Versioning Suspend.Versioning suspend is different from removing it.
- Versioning works whenever you upload an object a version id is attached with it, so if new object same name is uploaded it will have another version.(v1 -> v2 - ...)
- Latest/current version user will see/get.
- When you delete an object a delete marker is added , hiding the object but still present, you can delete detlete marker to bring it back.
- Can permanently delete particular version of an object.
- Pricing : All version stored is charged.
- when you disable versioning, all objects version already there stays, but new objects will have version id NULL, have to manually delete all versioned objects.
- MFA Delete : Enable MFA to change versioning state as well as deleted older version objects.

#### S3 - ACL and Resources Policies
- By default user that created S3 and root user has access to S3, so no other user as well as no public.
- S3 bucket policy (resource policy) to change access.It's written in JSON, basic eg
```
{
    "Version": "2012-10-17",
    "Statement": [{
        "Sid": "AllowGetObject",
        "Principal": {
            "AWS": "*" // meaning apply to all, public,aws account, registered, not registered
        },
        "Effect": "Allow",
        "Action": "s3:GetObject",
        "Resource": "arn:aws:s3:::DOC-EXAMPLE-BUCKET/*",
        "Condition": {
            "StringEquals": {
                "aws:PrincipalOrgID": ["o-aa111bb222"]
            }
        }
    }]
}
```
- Can allow to access at particular folder object as well, or allow access from particular range of ip.
- IAM policy is for user, cannot be applied to anonymous user whereas a resource's policy is for a resource can include public etc rules/access.But they work together, so we need a allow from both.(For public only bucket policy works)
- ACLs are predated legacy access control that predates IAM , not reco to use, cause they only allow some fixed policy.
<img width="1404" alt="Screenshot 2023-12-29 at 4 54 01 PM" src="https://github.com/MadSn7/My_Learning/assets/62552772/9c132abf-7bed-49b0-907d-d8f7314e7eff">


#### S3 Static Webiste Hosting
- We can store html,css, js, media etc in S3 bucket, now we can use it as hosting.
- Along with data stored etc, per http request also has cost, pretty lower.We will get a url, can hit the url to access site.
- Can set Route53 to get fixed domain, but need to name s3 bucket same as domain name.
- Need to have public access for s3.

#### S3 Pres-Signed URLs
- We have a private S3 bucket and an IAM user have access to S3, now someone not registered need to have access to a file/files.Now we don't want to make it public, and not want to make user aws.
- You can create pre-signed url and give to public user, now public user can access the bucket then, and other users can't access S3 as S3 is still private.Not only get, we can post as well etc.
- Check object actions for creating pre-signed url, need to set expiration time for it.

#### S3 - Access Points
- Different users should have diferent access to s3 and it's objects, and bucket policy can become very complicated if we follow that.
- We can create separate access points, separate ARN so each user can have their view of S3, we can attach policy per access points.
- You can transfer policy of bucket to access points policy or write policy in both bucket and access point, first one is better.
![Screenshot 2023-12-30 at 8 32 49 AM](https://github.com/MadSn7/My_Learning/assets/62552772/7ad36e6b-3789-4119-b6fe-017fdd98d312)
![Screenshot 2023-12-30 at 8 34 24 AM](https://github.com/MadSn7/My_Learning/assets/62552772/3bef628a-f37b-433f-b50e-07650236e6c6)


### AWS Backup
![Screenshot 2023-12-30 at 9 55 29 AM](https://github.com/MadSn7/My_Learning/assets/62552772/7b576b4a-4f58-4000-9aea-b57c466dbe44)
-  Lots of service are present for disaster recovery
- S3 is great for storing backup data,EBS as well, you can take snapshot of your data, on your scheduling.
- AWS Backup is a service is unified console for managing AWS service, automates backup scheduling etc.Can access different regions and different accounts.
- Components
    - Backup Vault : Container that stores your backup data, can have in various regions as well.
    - BackUp Plan : Configs for back up, scheduling etc.
    - Recovery Point : A point in time upto where you want to backup.
![Screenshot 2023-12-30 at 10 01 22 AM](https://github.com/MadSn7/My_Learning/assets/62552772/89431ad9-b00a-47cd-817e-289e3b8e71eb)
- There are lot of service we can intergrate aws backup with ec2, rds, ebs etc, can monitor using eventbridge, cloudwatch, sns, cloudtrails etc. 

### Elastic Disaster Recovery
- Fully managed disaster recovery service for both physical, virtual and cloud.
- Utilise AWS as recovery site, so we don't have to pay anything until we use it.
- Easy to access with a disaster recovery infra.
- Can handle failover from on prem to aws, other cloud(gcp, azure) to aws,one aws region to another.
- Need to mention source servers, represent data we want to replicate.
- Staging area, where aws recieves the replicated data.
- Launch template used to coonfigure the specifications of recovery servers(size, regions, sg's etc)

### Storage Gateway
- Allows on prem to storage in cloud.
- Can act as backup for storage, assist migration to cloud,can help in disaster.
- It is either a virtual machine, or a physical machine in on prem env.
- If storage on prem is done on volume, can have volume mode or if file can have file mode.
- Storage gateway - Volume
    - Cached Mode
    - Stored Mode
  
`Check again in revision storage gateway maybe backups too?!`

>Quiz Storage

> 21/26 == 81% Correct


## Services - Compute

### EC2(Elastic Compute Cloud)
- A place where your applications runs is server, EC2 is similar service for that server.
- Earlier used to be manual physical ones, but setup deploy maintain a server, now you can use this aws ec2 service.
- An instance is a virtual host, with some properties and configurations.
- EC2 Instance Types
![Screenshot 2023-12-30 at 2 57 51 PM](https://github.com/MadSn7/My_Learning/assets/62552772/227cb0d5-f418-4c2a-a950-b2f3395c4758)

- EC2 AMI(Amazon Machine Image)(Like Blueprints)
    - When you have a server need to have some OS, so AMI is there for that, it is provided for nearly every main os and you can choose that.
    - We can customize the ami too, and deploy our ec2 with that properties.
    - Types
        - Public : From amazon and various other places.
        - Private : In particular account/organisations.
        - Shared : Private AMI can be shared according to your need.
    - Can create any no of instances from a AMI.
    - You can have an EC2 instance configure various things in it and then create AMI from it as well to be used for deploying other servers.

- When we deploy EC2 instance, we usually use SSH to connect to EC2 instance, and we create a public-private key for security.
- EC2 instance will have public key and you will have private key which needs to be used to do ssh.
- EC2 instance Lifecycle
![Screenshot 2023-12-30 at 3 06 03 PM](https://github.com/MadSn7/My_Learning/assets/62552772/c7ed4300-6734-463e-a969-4209ae2f0e19)

- You can pass some shell scripts while deploying ec2 at start to install imp files, or some other stuffs need to be done at startup, any instruction.
- Need sg configured when deploying EC2,if web server need to allow http/https etc.
- To save data somewhere persistent
    - Use EBS
- Can integrate various servers with Elastic Load Balancer(ELB)
- Autoscaling Groups
    - Can autoscale up down no of instaces to handle the demand.
- Can assign Elastic IP to an EC2, so as to have permanent public ip.
- EC2 launch template
    - Configs to deploy EC2 instance with several info, usually attached with autoscale group, so it will deploy accordingly.
- If you deploy EC2 it will be setup at random machine in your azs but you can give some info for better setups
    - Cluster Placement : As close to one another, good for low latency, high throughput.
    - Partition Placement : So one partition is away from another one, good for hadoop like distributed workloads.
    - Spread Palcememnt Group: Each Separate across machines.

    ![Screenshot 2023-12-30 at 3 16 37 PM](https://github.com/MadSn7/My_Learning/assets/62552772/b3b2c926-0906-4b02-97b3-2f47f8ce51b2)

- Pricing
    ![Screenshot 2023-12-30 at 3 17 47 PM](https://github.com/MadSn7/My_Learning/assets/62552772/b70a6950-b674-4688-9e01-d8efe8fcd904)

    - On demand : Buying at regular price, not sure when you will need it, pay for your usage, no cost after you shut and delete it off
    - Spot Instance : Buying at cheaper cost, on unused EC2 types.It is irregular.Cost savings, but interruptions.
    - Savings Plan : Commiting on particular EC2 type, but you will use that consistantly, good for consistent usage.
    - Reserved Instances : buying a monthly yearly plan, so discounts.Different from saving is saving you have spend some fixed amount atleast per month, reserved is certain amount of compute.
    - Dedicated host : No one can use this host, for your usage only.Whenever you deploy EC2 is on that host.Save cost on license costing.
    - Dedicated Instance : Get a dedicated server for you EC2, your host can change, physically.Guaranteed to have host but not same.
- Instance type is different type of ram, rom size, etc.
```
 ssh -i "pemFile" ec2-user@instanceIP
```

#### EC2 Image Builder
- Allows to customize images for yourself which can be used to deploy other servies.
- Have version management as well so can roll back to previous images.
- Process
  ![Screenshot 2023-12-31 at 10 28 07 AM](https://github.com/MadSn7/My_Learning/assets/62552772/2138c294-602b-4a4d-9f94-ea9e5180a7ab)

### Elastic Network Interfaces(ENIs)
- Can think of taking network configs separate from EC2 instance and using this for that.
- It's present inside the azs, contains things like ip4/6 ip mac address etc.Definitely can assign several ENI to ec2 not sure of vice versa
- A primary ENI is attached to EC2 when it is created and cannot be deleted, automatically gone when instance is terminated, if ec2 has public access primary/default ENI will have 
public ip attached to it.
![Screenshot 2023-12-31 at 10 34 12 AM](https://github.com/MadSn7/My_Learning/assets/62552772/682c5d9a-586a-4ddf-9f10-b23ce1632a5f)

- Elastic ip can be attached to the ENI.One or more sgs can be attached to ENI.
- Logs can be there for it as well.


### Elastic Beanstalk
- We need several services to deploy an application over to aws, can be challenging for you if we want to deploy a simple applications.
- It was created to do this simply and in background AWS will deploy several things for you.(vpc,sg,storage,netwrking etc)
- Support nearly all major runtime like java, python, docker,node etc.
- There is an environment to be created(contains several configs like lbs, sgs, ami, and several things) and this can be deployed then.Can upgrade evironmnet and updating it.
- We can see all ec2, sgs etc that was created when we create elastic beanstalk.We get a dns/ip to check our running applcation.
- You get managed updates, load balancer and autoscaling, monitoring with cloudwatch etc.

### LightSail
- Quickest and Easiest way to get you project up and running.Don't have all features but enough for most of the applications.
- It is virtual server provider with built in firewall, containers, lbs, managed dbs, global content delivery.
- Can use container image as well.
- Can easy trasition to EC2 platform, if and when you want
- Process
![Screenshot 2023-12-31 at 11 00 39 AM](https://github.com/MadSn7/My_Learning/assets/62552772/c1ba441e-c003-4f41-a47a-5b923e63845d)


### ECS(Elastic Container Service)
- Should have some knowledge of containers, images etc.
-Container Challenges
    - Need several host
    - Need Monitoring
    - Need Load balancing
    - To be able to scale up or down accordingly
- Container Orchestration solve this issue.ECS is aws's container orchestrator.
- Containers runs on Either EC2 instances or Fargate.
- ECS is the brain, we need to provide compute like EC2 or fargate.
- If we use ECS is not provided to other cloud providers so maybe difficult to transfer(EKS is there which is K8s version for cloud, easy for on prem to cloud or another cloud provider)
- Other are difficult to work with, ECS is simpler to start.
- Launch Types(Where our infra/cluster is placed)
    - EC2 : We have to manage underlying EC2 instance.We have to configure docker, ECS agent, firewall, pathces etc.Full control of infra.
    - Fargate : Aws manages underlying infrastructure too, serverless architecture.
    Will create servers on demand according to application need for run and demand.Pay for what you use.
    ![Screenshot 2023-12-31 at 1 44 51 PM](https://github.com/MadSn7/My_Learning/assets/62552772/276d4446-973d-47cd-95e0-1f6568f744ee)

#### ECS Task
- We have image now we want to run with configs like cpu memory, image name, other configs.
- Task Definition is created for that(a blueprint), we tell various info like above and ports, volumes etc.
Similar to docker compose.
- We can create a task(aka containers) from task definition(configs file).

#### ECS Services
- We create a service in ECS.Makes sure a certain no of tasks are running at all times.
- Responsible for creating tasks from task definition.
- We mention how many task we want for particular task def.
- In case of fail it will restart etc.Can think of as manager of tasks.
- We can create a frontend , backend, db service eg and mention each service how many task you want.
- Load Balancer : We can deploy usual Load Balancer, attach to ECS, it will make sure to load balance according to current tasks and future task as well.We attach it to our service.
(Optional but should create it for good usage)
- For every task we have sepoarate ip, and each new deploy ip changes, so Load balancer resolves that.

### EKS
- AWS's managed kubernetes service.
- EKS creates master nodes, control plane etc, so aws helps in managing it.
- Worker nodes is still you responsibility.
- We get integration with other aws services as well.
- For handling Worker nodes 3 options
    - Self Managed Nodes 
    - Managed Node Group
    - Fargate: similar to ECS,create worker nodes on demand.No need to maintain EC2 server.
    <img width="1005" alt="Screenshot 2023-12-31 at 7 04 16 PM" src="https://github.com/MadSn7/My_Learning/assets/62552772/93ef80f1-4ca6-4c25-b192-3ff9d615b957">
    <img width="1013" alt="Screenshot 2023-12-31 at 7 03 40 PM" src="https://github.com/MadSn7/My_Learning/assets/62552772/3e7cd1de-e1b4-4f38-8789-f14656348395">

- Process for creating EKS cluster
<img width="989" alt="Screenshot 2023-12-31 at 7 05 34 PM" src="https://github.com/MadSn7/My_Learning/assets/62552772/b1b039ef-915c-4055-94fd-1e8ac99ccd83">

- worker nodes depend upon mode you choose.
- Then connect worker node to your k8s cluster

### ECR(Elastic Container registery)
- To store the docker images, palce to put and get the docker image.
- ECS, EKS, On Prem can pull image from here, make sure they are authenticated.
- Two types : Public, Private.

### App Runner
- Service to have easily setup run of your code, so you just have to push your code.
- Also setup ci/cd pipeline, so push any git platform, when merged into master it will trigger build accordingly.(Setup codcommit codebuild in github)
- App Runner will provide http ip, so as to access your application.
- automatic deploy, after code is pushed.Compitable with codecommit ,ECR.

### Batch
- Helps with batch jobs/operations.
- We just give info of our jobs, we can also queue and prioritize the jobs.
- Batch Components
<img width="1003" alt="Screenshot 2023-12-31 at 7 25 11 PM" src="https://github.com/MadSn7/My_Learning/assets/62552772/04f176da-336f-4df2-bcb4-d9596d689041">


### AWS Lambda
- Serverless event driven service, just take your code set some event to start, aws will take care of the rest, it will scale up or down accordingly.
- Lot of triggers and integrations, like api gateway, s3 event notification etc.
- no server load, many runtime configs available.
- We can upload function and it's dependecy in zip file to run, and we can also add layer to lambda function which contains all libs and configs to make our lambda function run, layer can also be shared to other lambda functions
- Logs is set up default in cloudwatch.

### Step Functions
- Contains state machine, which is basically the flow of your workflow/logics/actions.Baically a flow chart.
- When you create a stateflow aws provided various types of workflow template so don't have to start from scratch.
- Contains integration with varioujs aws services like lambda, ecs, sns etc.

### Serverless Application Model
- The AWS Serverless Application Model (AWS SAM) is an open-source framework for building serverless applications. It provides shorthand syntax to express functions, APIs, databases, and event source mappings.
- It has cli with it as well.
- think of it as an extension of cloudformation on serverless side.
 <img width="898" alt="Screenshot 2024-01-01 at 8 12 10 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/b8a3d9ec-fd70-4b94-971b-714925fe80b7">


#### Serverless Application Repository
- You can publish your serverless package here to share with others and public, like open source for serverless application.integration with SAM.

### Amplify
- Service for mobile and webApp development.
- Abstract a lot of service, so you focus only on code.It has amplify studio, also has pre build react components.

### OutPosts
- If we can some on prem infra and want to be handled same way as aws, so as to compitable with on cloud infra.Truly hybrid.
- AWS will physically attach device to your on prem data center and then you can run aws services in your on prem servers.Same way as in cloud.
- We will have vpn or direct connect with on prem and cloud, feel like just two different accounts.

### ECS/EKS Anywhere
- What if we want to have EKS/ECS in your own data center/On Prem?

### VMware Cloud on AWS
- VMware is used to create VMs on your server.Virtulisation.
- So on prem has vms, and cloud has ec2 can be cumbersome.
- So you can make all run on vmware, can move vms to on  prem to cloud and vice versa.Can perform disaster recovery easily as both are similar.

### SnowCone(PArt of snow family of products)
- This one has compute power, rest snow familty is sotrage,data etc.
- Physically move data in/out of aws.They are also good for in extreme climate conditions.You can perform your operations and shift your data.Can run edge computing.

>Quiz Compute

> 21/26 == 81% Correct

## Services - Database
<img width="973" alt="Screenshot 2024-01-01 at 12 20 33 PM" src="https://github.com/MadSn7/My_Learning/assets/62552772/df04d098-1f4b-4e3f-aaa8-a48d6ae93049">

### AWS-RDS(Relational DB Service)
- When we have our own db, we need to do several tasks ourselves and keep db administrator for it.
- RDS is managed,scalable solution for running relational db in aws.RDS will manage security, durability etc according to your instruction.
- Various DB Engines are supported like MySQL,PostgreSQL,MariaDB,Oracle,SQL Server.
- Instance type for rds
    - General Purpose : Perfectly balanced for price and performance.
    - Memory Optimized : To Handle large amount of data.
- Different Instance Types
    - Single AZs : data will be lost, if az goes down.Good for staging and dev environment.
    - Multi AZ-Instance : Automatic backup in other az,secure.
    <img width="975" alt="Screenshot 2024-01-01 at 12 31 15 PM" src="https://github.com/MadSn7/My_Learning/assets/62552772/abf202a5-10b3-4969-85f6-2ddb0a653149">
    - Read Replicas : Basically load balance read operations to various place, data is copied to two instance, write is in only one.read from either.
    <img width="965" alt="Screenshot 2024-01-01 at 12 32 41 PM" src="https://github.com/MadSn7/My_Learning/assets/62552772/6f4b0578-1128-4c2a-b817-f45a68735677">
        - at least for read it is active-active both instances.
        - during dissater recovery, read replica can be made standlaone db.
    - Multi-AZ Cluster
        - have data in other regions
        <img width="1003" alt="Screenshot 2024-01-01 at 12 37 41 PM" src="https://github.com/MadSn7/My_Learning/assets/62552772/9d75d27e-a63c-43c7-b6d8-6c164b9600b3">

    - Blue-Green Deployment
        - Two alternate DBs Cluster can make another Db main after checking the updates etc.
        <img width="927" alt="Screenshot 2024-01-01 at 12 38 43 PM" src="https://github.com/MadSn7/My_Learning/assets/62552772/b5a0f18f-7073-4dbf-8570-4786e12e60a2">
- Multi AZs have additional cost,rds manages data consistency across instances,multi az have read in other azs too.
- Storage types, general purpose, IOPS SSD(Best for production), magnetic(old, redundant).
- RDS Configuration/Features
    - DB Parameter Group : Contains engine version,settings, perforamnce, reosurce allocations
    - DB Options Groups : Encryptions,
    - Subnet Groups,Security Groups,Db Snapshots(data backup can be automatic), Parameter Store,insigts,monitoring, audit and log data,ssl and encryptions
- Backup upto last five minutes either automatic or manual.

#### RDS Aurora and Aurora Serverless
- Designed to perform low cost high performance.
- Full combatibility with mysql,postgresql.5x throughput mysql, 3x postgressql.
- Traditional DB when have high data, gets troubled in update, copying data etc.
- So compute and storage decoupled is best fro scalibility, availability, durability.
![Screenshot 2024-01-01 at 1 51 46 PM](https://github.com/MadSn7/My_Learning/assets/62552772/7a1474e8-339c-4b34-a99a-131f73c356b0)
- Several qualities, size copy of data across azs.

![Screenshot 2024-01-01 at 1 53 30 PM](https://github.com/MadSn7/My_Learning/assets/62552772/69d0d9f1-d163-42d3-95e6-6eee5fbcc130)

- Two Types
    - Provisioned : pre configured capacity, scale up is manual, is aurora global
    - Serverless : on demand scaling, useful for variable and unpredictable workload, aurora globa v2 only
        - Aurora global meaning on primary db cluster and upto 5 different read cluster in different aws regions.
 - Measure quantiry ACU(Aurora capacity unit), 1 unit = 2GB.Min to take is 0.5
 ![Screenshot 2024-01-01 at 2 00 45 PM](https://github.com/MadSn7/My_Learning/assets/62552772/b1942412-6490-4fd3-a7dd-b3ade4ba3189)
 - v2 is now used mainly.
 - Have integraton with various aws services like s3, lambda, cloudwatch, redshift, etc.

 #### RDS Proxy
 - Aplications will open a number of connections to RDS, and if you have many instances/ servers running requiring connection to rds, there will be lot of connections.
 - RDS proxy will have a pool of connections and applications will hit rds proxy for query and rds proxy will reuse those connections for query.
 - RDS proxy is fully managed, fully compatiable with various engines of rds.Application can be on ec2, ecs,eks, lambda.
![Screenshot 2024-01-02 at 7 05 25 AM](https://github.com/MadSn7/My_Learning/assets/62552772/cf0d2fe3-5b30-4def-89ee-3acfdd514589)


### RedShift
- It is data warehouse(acquire data from various data sources db, logs, monitoring ,historical data)
- It is fully managed petabyte scale data warehouse service of aws.It is realtional and based on postgresql, but noat at all same.Columnar sotrage.Parallel processing.Two nodes in cluster, leader and owrker, we connect with leader.
![Screenshot 2024-01-02 at 7 24 00 AM](https://github.com/MadSn7/My_Learning/assets/62552772/92244015-91b9-4159-9cc1-a0c27b6645dc)

#### Redshift Serverless
- In case of normal one, we get assigned compoute capability which is not regular, so we have to bear cost for it as well.
- So we have serverless option,capacity gets automatically up or down.RPU hours is the unit for capacity utilizations.

### DynamoDB
- Solved problem of scalibilty in NoSQL type databases.
- Managed service so aws manage underlying architecture.
- No need to have defined and fix no of attributes like relational dbs.
- Components
    - Tables : Similar to rdbms(user)
    - Items : Entry within your tables, (user1,user2)
    - Attributes : Property of your item.(userName etc)
    - Each item in table will have a attribute as primary key, to distinguish between others.
    - Primary Key
        - Simple : have one attribute as primary key.
        - Composite : two attribute,partioion key(which server data present), sort key, in combination is unique
        eg : singerName, songName(both make it unique)
        ![Screenshot 2024-01-02 at 7 41 58 AM](https://github.com/MadSn7/My_Learning/assets/62552772/948afd78-f60f-413b-8c89-bf61f6938d46)
    - Secondary index(another attribute on which you can query )
        - eg singerName,SongNAme is composite key, genre and albumtitle can be seconday index so you can query onto it.
        ![Screenshot 2024-01-02 at 7 45 02 AM](https://github.com/MadSn7/My_Learning/assets/62552772/15d0c508-0e93-4fac-86e3-82890409144a)
        - Global S.I.
        - Local S.I.
- DynamoDB Streams
    - Any change in dynamoDb appears as a record in Streams.
    - Contains table data and metadata, 24 hours life.
    - Can attach to lambda as well.
- Table Class
    - Each table is either of the two, seconday index will have same type class
    - Standard Access(Good for general purpose)
    - Standard infrequent Access(Storage is main)
- Support both key-Value, Documnet data models.serveless.
- Global tables active-active, both read and write from here.
- integrate with several services.

- Secondary Index
    - Global Secondary Index (GSI): Allows querying on any attribute, not just the primary key.
    - Local Secondary Index (LSI): Must be created at the time of the table creation and allows additional query flexibility for items with the same partition key.

#### DynamoDB Accelarator
- Cache meant for dyanmoDb, if we can get result directly from DAX.So faster query resolution.

### OpenSearch
- Similar to ElasticSearch/Kibana, for logs etc monitoring.
- Also have serverless offering, and opeserach integrations from logs etc producer service.HAs clusters called as Domain.
- Can attach quicksight to visulize the data.

### ElastiCache
- Managed cached service.Have clusters.
- Two types
    - Redis
    - MemCached
- Add cache to RDS, have global quick access.

### MemoryDB for Redis
- db+elastiche = one, act as db, inMemory save data.

### DocumentDB
- mongoDB compatible service in aws, managed by aws, so no issue for scaling and managing structure for thi noSql.
- Has fetature of global cluster, can have data in five different aws regions
- Allows for read preferneces accordingly to your need.

### Keyspaces
- Apache cassandra managing aws service.
- On-demand or provisioned type.
- Multi region keyspace for replication across tregions so have global read write, active - active.

### Neptune
- Graph database, when you want to identify relationship.Global distribution.Also have ML part.
- Serverless option, high throughput option etc.

### QLDB
- Quantum Ledger db, in ledger data should not be changed, tradiotional db have this issue.
- Record of transactions and transaction logs.
- has a ds jpurnal type which has append only property.
- Stores history as well.Financial transaction best usage.

### TimeStream
- Time based db, usage for iot devices like real time data.good integration b/wn analytics tools among other services.

>Quiz database

> 10/25 == 40% Correct

>## Services – Application Integration
### AutoScaling
- Used to dynamically increase/decrease ec2 instances running according to demand.
- Property
    - min : min no of ec2 that should be running
    - desired : your ideal case(will get these at start)
    - max : upper limit
- Components
    - Auto Healing : When an Ec2 instance is not deemed healthy, it terminates the ec2 instance and deploy new one.
    - Scaling Policy : Scale the ec2 instances according to min, max no and according to use
        - Manual : Change desire number manually
        - Dyanmic
            - Target tracking Scaling : Measure some metrics and scale accordingly.
            - Simple Scaling : Setup cloudwatch alarms, and scaling takes place according to policy.(fixed value)
            - Step Scaling : Similar to above one, setup alarm, add some more conditions, like if else.(different value according to condition)
        - Scheduled : More time predictable workload, can setup at some time.Can have cron job for effecting these policy according to time;
- Need to have a launch template, it tells the specifications of new ec2 that need to be deployed, the ami image, sgs, access keys etc details.
- Integrate with ELB, to load balnce to various ec2 deployed, cloudwatch, sns topic message, ec2(for this itself).

### Elastic LoadBalancing
- There are usually more than on instance handling a website, now they will have separate ips so need a single point to balance request across every server as well as keep the ip constant.
- It is highly scalable, span across multiple azs service.
- Need to configure two things listeners(a ip) and target(server like ec2 etc).
- Can be both public and private lbs.Can have cross zone load balancing.
- types : Applicaction LB,Network LB

### API GATEWAY
-  Integrating various types of backend service,like lambda,ecs,ec2 etc, managing and deployment of apis
- API gateway does that and also request and transform request reponse, security and access control, authorisations types.Rate limiting and throttling.Monitoring and analytics.Also helps and support documentation
- Supported API Types
    - HTTP API
    - WebSocket API : used in real time applications
    - Rest API


### App Flow
- Amazon AppFlow is a fully managed integration service that enables you to securely transfer data between Software-as-a-Service (SaaS) applications like Salesforce, Marketo, Slack, and ServiceNow, and AWS services like Amazon S3 and Amazon Redshift, in just a few clicks.Bridge between different tools so data can flow to these services.
- Components
    - Connector
    - Source, Destination and their mappings
    - Trigger
- Integration with several of aws services, and many third party services.

### SNS(Simple Notification Service)
- AWS Service delivers real time messages from publishers to subscribers.
- Components
    - Topic : Message are published here, you add subscriber here which takes note of this message and work accordingly.
    - Subscriber : They will take message from the topic and can according to logic what should be done.Can be wide variety of services in aws like lambda , ec2 etc.
    - Publisher : They send the message to topic can be variety of different things.
Types of Topics
    - Standard : May not show up in order of generation and maybe be duplicated, high scalable.
    - FIFO : Will show in order, no duplications.
- 1-10 message per api request, batch to save cost.
- If you have large payload, can store payload in s3, and share the reference in topic message.
- over 60 aws service integrations,securely encrpt message, send 

### SQS(Simple Queue Service)
- When you have several services which works on after another, their may be backlog until the request is completed from one service to reach to another service.
- Can attach SQS between services so the services are free to do another task and can sned their request in queue in sqs to be resolved by next in order services.
- Message Decoupling, load leveling, asynchronous,serverless and scalable.
- Componenets
    - queue : act as buffer which retrieves and stores messages from producers
    - message : unit of communication in sqs, have message attributes as weel aka metadata.
    - producers : they produces message
    - consumers : they consumes the message from queue.
    - DLQ(dead letter queue) : Optional, a message can have a fixed no. of retries to be processed, to avoid and isolated failed message can be analysed later time.
- Two Types
    - Standard : not ordered, may duplicated, high throughput and scalable
    - FIFO : ordered and no duplicates
- Integration with lot of service.
- HAve only one condumer, sns can have many subscribers


#### The main difference lies in the foundation of the services. SQS is poll-based and SNS is push-based service. That means SNS is simply forwarding all messages to your subscribed consumers, whereas SQS really saves the messages in a queue and waits till they get picked up.

### Amazon MQ
- Managed message queing broker service, for application that use active mq / rabbit mq.
- Similar to sqs, if you are starting something new or already in aws use sqs, if using older application, just use amazon mq in that place.

### EventBridge
- Basis for event driven bridge system.Allows to be decoupled and can be scaled according to events generated.Can route events according to condition.
- event source -> bus -> according to tules -> reach target/s service
- Components
    - EventBus : Location where we sent event, and gets driven to its place from here.Different types of bus availble.
    - Pipes : Routing events from single source to single target.Can filter, tranform etc to data in between.
    - Scheduler : Create schedule according to pattern etc.
- Have event replay feature so can check waht went wrong to a particular event.Scheman registry to check structure.
- 200+ built in source and targets aws servce.
- have deault bus for an account alreasdy.

### SES(Simple E-Mail Service)
- Aws based email sending service, can scale up according to requirement.
- Componenets
    - Verified identities : Email/domain which used to send emails.
    - Configuration sets
    - Dedicated ips
    - email recieve info : what to do if we get some mail
- Various features.
- Can integrate with various aws services like lambda, sns topic, s3 for storage mail.

### Step Functions
- To monitor step by step flow of our application, workflow orchestration.
- 220 + aws services and 100000+ apis integrations.

### Simple Workflow service
-  Task coordinator, similar to step functions, use step function in general recommended, you can use python,java etc to build you logic for workflow.

### Managed Apache Airflow
- Amazon manage underlying apace airflow infra, no difference from the open source version.
- Build data pipeline in DAGs and define in code.Various integrations.

> QUIZ
> 10/27 = 37% 

## Services - Data and ML

### Kinesis
- Real time data streams.
- Several types
- Kinesis Data Streams
    - Takes various type of data like images, video, from different sources and send it to different services like lambda, ecs,emr etc.
- Kinesis Data Firehose
    - Firehose can do ETL, so we compute here only and save it to some data storage like s3, redshift etc.
    - near real time, but not fast like data streams
- Kinesis Video Stream
    - For video audio related to time as well, sent it to consumers and then can do several things.
    - producers to consumers.
### MSK(Managed service for Kafka)
- Run apache kafka in aws serverlessly, same architecture consumer producer topic.

### Glue(GLUE ETL)
- Main job is ETL, among various features.
- Spark environment, glue scripts working.

### EMR(Elastic Map Reduce)
- Power house of big data processing not just etl.
- Runs managed cluster having big data technologies like hive,pig, hadoop, spark etc.
- Have different types of nodes in cluster
    - Primary : Node manages the cluster by running softeware and manage other nodes.
    - Core Node : Stores data and , manages that.
    - Task node : they do the processing, not necessarily store data(optional)
- Has steps of process run one by one, if one fails , next one fails and other are cancelled.
- Use spots node to reduce price.
- Integrate with lot of aws services.

### Glue DataBrew
- Different from glue etl, this one is completely visual.We don't write any code.We pick one of the given options.
- no servers, all visual

### Lake Formation
- Aggreagration of meanigful data for a company from various sources.
- Done and stored in S3 and we view as tables, in a format you like for analytics optimization.
- Basically storage of data and supports analytics etc.

### Athena
- Enables to read data sets, pull the data into dashboard and query engine, has crawler like usage to identify the data structure and able to query.
- Can take data from s3, glue data catalog etc using sql like query.Serverless.Integration with various aws storage services.

### QuickSight
- Visualization dashboard, used when have to show graphics for data.
- Has spice enigne with it.QuickSight q for ML etc.

### SageMaker
- Premier machine learning servce for ML.Basically math, algo and patterns.
- Have data source, then preapare and improve data have notebooks etc for it.,then train models,model evaluation and tuning, check results and deploy it.

### Rekognition
- Given an image tags the image according to contents present inside it.For both images and videos.Can use it with S3 etc.Facial analysis, scene, text images,activity, unsafe content.

### Polly
- Convert text into voice, basically does that.
- Transcirption is uploaded we can use polly to convert it to voice.Transcribe does speech to text used with it.

### Lex
- Chatbot, user to chat bot.Lex governs it.To design chatbot process, NLU and ASR feature.

### Comprehend
- Text analysis machine, give it letters, email etc and it gives basic undersatding about it.
- It will identify PII(Personal info like number ,address etc.)NLP

### Forecast
- Take time series etc data, and it gives probability of something happening.Give it data and it work on it, sagemaker you need to know some ml, here it is easy and many steps it does it itself."No machine learning experience type questions"

### Augmented AI
- Augmented intelligence is a design pattern for a human-centered partnership model of people and artificial intelligence (AI) working together to enhance cognitive performance, including learning, decision making and new experiences

### Fraud Detector
- Helps to identify fraud/ malicious activity, don't need any prior ml knowledge
-  Uses ML,real time.You can attach request to it to identify frauds.

### Transcibe
- Convert speech to text, can hanlde 10 speakers.

### Translate
- Transaltion service,high quality 5000+ language translation

### Textract
- Automatically extrats text and data from scanned documents.So basically physical files data is converted in to digital data and then we can work onto it.
- test, form, sign, table extraction.

> Quiz
> 23/25  91%

## Services - Migrations and Transfer
- Services provided to aid tranfer data etc from onprem or other cloud env to aws environment.
- Need to have knowledge about our current infra and anslyze steps needs to be taken.

<img width="927" alt="Screenshot 2024-01-06 at 12 22 33 PM" src="https://github.com/MadSn7/My_Learning/assets/62552772/f1ce06fb-6ef2-4607-ad1e-4b656ae0dffb">
- Also snow family is included as well.

### Migration-Hub
- Centralized location to track and monitor of various migrations/transfer related services.
- Also have discovery process to get infor of our on prem info.
- Agent Discovery and Angentless Discovery.
- Assess the above info and give estimated cost if deployed to aws.
- Then migration can take place.

### Application Discovery Service
- This is the disovery discussed above,it is also able to identify depedency and relationship between various services present on prem.
- Two ways
    - Agent Discovery : Uses lighweight software deploy in our on prem called agents which collect relevant data.
    - Agentless Discovery : On prem we have an VM installed having agent, which will collect the info.
    <img width="1139" alt="Screenshot 2024-01-07 at 5 15 38 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/668d3662-61d8-492a-ac17-2a5f3a218792">

- Info is pushed every 15 minutes across secure stl tunnel and stored in s3, can be used for analysing and useful for other services when deploying.


### Application Migration Service
- Responsible and does actual migration.
- Physical, vms, other cloud etc will help in migration of all those.
- Staging area we deploy resources and then we have these deployed to our required region,subnet etc.

### DMS(Database Migration Service)
- To help db and analytics migrations from on prem to aws.
- Componenets
    - DMS Fleet Advisor(Has dms data collecter isntall it in db, and give db)
    - DMS Schema conversion : convert db object correctly, make schema changes accrdingly.
    - Replication Instance : take data from on prem and replcate to aws,we have replications tasks for it.Have types
        - Full Load : There is Outage long enough for copying to aws.
        - Full Load + CDC : simultaneusly keep trac of changes on source.
        - CDC Only : This process is called ongoing replication or change data capture (CDC).  

### Elastic Disaster Recovery
- In times of downtime in on prem/other cloud, we can have our services running in aws.

### AWS MainFrame Modernization
- Moderninzing legacy system to moderm technology.

### Datasync
- Provide fast data transfer between on-Prem to AWS environment.
- Have agent and locations (destination and source), tasks and task execution.
- On stop solution for transferring data to any sotrage service, also assist in cross regions data migration.

### AWS Transfer Family for FTP/AS2 Not running video

### AWS Snow Family
- Physical storage hard drive.
- So physically bringing data from elsewhere to AWS.
- We will have durable, rigid, safe storage devices.
- It has NFS endpoint,encyption with it.
- Componenets
    - Snowball Edge
    - Snowcone
    - Snowball
    - Snowmobile : get trucks, high amount of data.


>>> Quiz 6/24 = 25%

## Services - Management and Governance

### CloudFormation
- IaC(infrstructure as code), earlier it was cumbersome and manual too.
- Now we have blueprints i.e templates upon which we can build our infra.
- Cloudformation you create template and add all you resource you want, and cloudformation is gonna configure all resources according to it.
- Supports both json and yaml format.
- You create stacks to kind of keep resources together, resources are created/updated in stacks.Just edit the template to update your changes.It updates change set according to edit and that changes take place.
- Aws codecommit is aws's git like versioning, and we can integrate this with aws codebuild ci/cd pipeline to deploy.
- Features
    - It is IaC, so we get documents and git etc versioning.
    - Consistent and repeatable deployment.
    - Cost , time efficiency and tested templates.
- have various tags and property to attach according to resource provisioning condition.We can have !Ref someResourceInTemplate to attach elsewhere(ec2 instance attach a sg that is deployed together, got no name.)
- We can have parameters, so as to take values, and outputs as well, like some public ip after resource is deployed. !GetAtt resourceName.property you want.

### CDK
- Another IaC service, but in here you get to define your infra in Traditional Programming languages like python, java, js etc. 
- Benefits from cloudformation cause we get many libraries, and logic building.
- Utilizes cloudformation templates under the hood.
<img width="991" alt="Screenshot 2024-01-08 at 3 46 29 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/e12e35de-c64f-4049-991d-eb7e6e657e10">

- Uses AWS Construct library according to language of your choosing.
- Similar to templates codecommit and codebuild you can have pipeline for this as well.
- cdk cli tool uses nodeJs so have to install it.
- various commands like cdk diff, synth, deploy, destroy etc.
- cdk init app --language yourLang // for having base template
- cdk init sample-app --language yourLang // base template + quick deploy basic template

<img width="983" alt="Screenshot 2024-01-08 at 3 49 04 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/6a5ab8f3-943a-4557-9df6-b35024764230">


### Cloudwatch
- Monitors all your resources and applications you run on aws, and you can generate notification if any metric is not have value according to your need.
- It collects logs from various services, you can customize your application to send extra logs/info and we can also push logs to aws, and cloudwatch extract metrics from it, and we can set up alarms if according to conditions.
- Components
    - Metrics : Some measurable quantity like cpu utilization etc.Can make custom as well.
    - Alarms : Can place confition of metrics so if any is not working to our need it is triggered.
    - Log
    - Events : Was called cloudwatch events, now eventbridge.Define custom rules, on certain event react to that acccording.
    - Has Dashboard and Insights(sql like query on log groups).
- A good application is with auto scaling and cloudwatch alarms and metrics.

### X-Ray
- Distributed application has problem to identify what is the source of the issue, where the application is breaking.
- So x-ray recieves traces from many native applications components, it shows each steps of the request, time it took etc.
- A trace is single request, from entering our system to it going our with response.
- Segment : A part of our trace, like it goes through ec2, it is one segment, then goes to db, that is on segment.
- Service map : shopws all services and componenets that makes up our application.
<img width="968" alt="Screenshot 2024-01-08 at 7 06 13 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/b2ddd622-c828-43ad-86ae-ee36a4d560a9">

### AWS Health DashBoard
- Place where we can come to know the status of our application are running okay or not.
- Public events : Affected to all worldwide.
- Private : Some particular instance of a service issue.
- You can also have alerts attached with it to know if/any service goes down.

### Prometheus
- Collects different stats about metrics, and store them to check previous usage and status.
- Think of it as open source cloudwatch.
- uses PromQl
- Componenets
    - Targets : Application of which you get metrics, data about.
    - Inside server you have retriever which send http requests to collect info about targets.
    - Time Series DB : Insisde server data gets collected here.
    - Http Server : Query to here to get metrics from db.
- amazon provides managed server prometheus server.
- We can integrate with cloudwastch as well, as well as on prem prometheus.

### Grafana
- To view logs cloudwatch has UI, but it's not that great.
- We can have graphana to do that, it will query metrics.
- Very flexible, interactive.

### Trusted Advisor
- Provides real time guidance, to follow  aws best practices.
- Suggest for cost cutting, security groups, security etc.

### Launch Advisor
- Simplify process for deplying third party applications.EG : SAP, AD etc.
- Has templates for third party applications so it will work optimally.No extra charge paying only for applications running.

### Compute Optimizer
- Provides oprimization suggestion for our compute resources and EBS volumes as well.
- It does analysis, then provides reco to cost saving or size/capacity oprimization.

### AWS Organizations
- You or a corporate comany may have different accounts, managing different services and resources.Billing would be separate, but ideally we want to get them in one account.Security,scaling issues, sharing resources etc cause issues.
- This similify managing several accounts.We have a master account.So master can set company wide organizations, rules etc.
- Componenets/Architecture
    - Root Account : Holds all account, rules here apply to all other account.Created when you create an organization, can have only one root acccount.
    - organization unit : Groups accounts of one type together and apply policy on them
    - Management Account : handles management for working with all accounts, inviting new account, editing etc.
    <img width="1008" alt="Screenshot 2024-01-09 at 4 15 20 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/12f356d0-3ff7-49ee-ac49-858d7edfea52">
    - scp : service control policies, apply this policy to all accounts or a group of your account.
    Think like IAM policy for account.
- No additional charges, good integrations with IAM, SSO,cloudtrail etc.

`CloudWatch is a monitoring service for AWS resources and applications. CloudTrail is a web service that records API activity in your AWS account`

### Control Tower
- Helps organizations setup and maitains several accounts.
- Built on top of aws organizations.
- Control tower automates the process of creating accounts and it's policy.
- Have landing zone environment so if you are new can start up organizations and different account ytpe setup.Has Preventive and detective Gaurdrails thorugh IAM policy.
-Two Oraganizational units, foundational has logs and security auudit and addition OUs(production , dev etc)
- preventive gaurdrail say don't allowed to create a public s3 bucket, detective an be ec2 is launched without key-pair.
<img width="1006" alt="Screenshot 2024-01-09 at 4 16 45 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/b430c014-cbc4-4e5d-860e-f7c057076fb2">

### Systems Manager
- Goal is to help you manage server whether it's in ec2 or any other service.
![Screenshot 2024-01-10 at 2 20 13 PM](https://github.com/MadSn7/My_Learning/assets/62552772/e121316f-010e-4e8e-b002-847ed3e17bad)
- SSM agent is installed into EC2, VMs etc server which can connect with system manager.We can run some commands or push some changes in all server.
- Got various different features and services.
- Has application manager in which you can group all server, services of on application together so as to work on it and monitoring together.
- has Parametrer stores which saves you secret keys and password and application can access them from there.
- Change manager, Automations,Change calendar(scheduling something)

### Service Catalog
- Provides curated services for user, cloud administrator etc manages what services to be made available.

### License manager
- Handles license mangement, usage and costing for your according servies usage.

### Proton
- Standaririze microservices across environmnets.
`not sure get more info about it`

### Resource Group and Tag Manager
- depending upon tag we can group resources for better monitoring and understanding of our applications.

### Resilience Hub
- Set up your disaster recovery particular for aws environment, helps in that.Does continous monitoring.

### Resource Explorer
- find rspurces deployedd across regions.
`not sure get more info about it`

### Resource Access Manager 
- Allows to share resources across accounts, like share S3 bucket to various accounts. 

>>>> quiz 20/26 = 77%

## Services - Security

### IAM(Identity Access management)
- Performs Authentications and autherizations for users/applications.
- It's about security, centralisez management, compliance,auditing and least privelege Principle.
- IAM user is created for a paricular user.
- Groups are collections of IAM Users which needs to have similar permissions.(like dev group, audit group, management etc), easily apply rules for new user, as user can be assigned to a group.Also a user can have multiple groups assigned so as to have two or more groups property.
- A new user can't access anything by default,we create IAM policy for it, it is made in JSON.
Contains rules which has allow/deny,on which resource,what actions.
- IAM has options to manage USER Groups, USERS,Roles,Policies.
- When it comes to AWS roles vs. policies, a good rule of thumb is to remember that policies are applied to roles, which can then be assigned to individual users via roles.There are already various purpose policy created by aws, and you can create your own too.
- Users/services can assume a temp role and perform those operations provided in role's policy.
`check what is sts and assume role policy etc.`
- In place of creating users and assigning the policy to it and then using user's credentials for accessing resource you can create role and attach to your service, then it can access those resource(
    want EC2 to access S3, first way create a user which has s3 access, and provide it's access key and id to ec2's program and then run a program, or create a role and attach to ec2 it will be able to access without giving access key/ids.
)

### IAM identity Center (SSO)
- Slightly different from IAM, successor of AWS SSO service
- An user generally need access to more than one accounts.So yopu have create roles/ users for all of users, aand give permission to all.
- Identity center allows us to manage user in a central place.
- Permissions setting is similar to IAM.
- Here permission set is used similar to IAM policy, we give permission according to account.
<img width="922" alt="Screenshot 2024-01-13 at 7 10 14 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/929254e4-569a-43f0-a795-253d75a20b3f">

### Cognito
- Implement authentications service provided by aws for your application.It is at app level not aws users/accounts. etc.
- Implements password, authentications, forgot password etc.Integration with aws services.
- Also allows socail login/register like from google ids,fb etc.
- no minimum charge, pay as you use, scales automatically.
- has Two Pools
    - User Pools : Basic Authentication(have/give token to user after login)
    - Identity Pools : Gives access to aws resources as well temporarily, after authentication. 

### Directory Service
- What is active directory?
    - Directory created by MicroSoft that enables administrators to handle access of various users to various applications.
- AWS Directory Service, is managed implementation of AD like directory.
 - Modes
    - Simple AD mode : standalone directory in AWS(not full implementation, runs in isolation)
    - Managed Microsoft AD : Actual impletation of ms ad, any service in aws can utilize this, and if you have on prem AD can connect.

### Verified Permissions
- After you have done authentications of user, you need to handle it's permissions adn authorisations.
- Helps developer to quickly develop app as helping in authentications.

### CloudTrail
- Design to track and record all api activity inside you aws account, so record every service every access by every mode from users/applications.
- Can be stored in s3 or cloudwatch, default save is 90 days, can edit.
- 

### Config
- Monitor configurations and configuration changes of various aws resources.
- Before it we coudn't track all reosurces changes,manual process was cumbersome.
- Willl note who changes so configurqation for a particular service, and we can create cloudwatch laerts etc, have various predefined rules as well to select according to your own need.

### Artifact
- This are reports providing auditing reports security and compliance.

### GuardDuty
- Protect and monitors you aws environment.
- Have ML and other tech, check various logs,vpc log, and services for suspicious activites.
- It can report finding, score it according to severity, and we can have alert on it as well.
- you can provide Trusted IP list, Threat IP list, so it can keep look for it.

### Inspector
- Inspect compute services for software vulnerability, network exposure.
- Automatically scan your reosurce all it's lifetime, specially after an update, patch etc.
- NEED TO SPECIFY YOUR TARGET,and the assessment template i.e resource you want to be tracked.
- Produce finding if found some issue, and score it according to severity.
- Different types of vulnerability find
    - package vulnerability
    - code vulnerability
    - network reachibility(over permission of network)

### Macie
- uses ML and pattern matching to secure PII(personal data) and sensitive data saved in S3.
- Scans all objects and look for sensitive data, it will notify you about it.We can use it as well with other services to perform some actions.
- You create jobs mentioning S3, and can also add type of sensitive data to look for or can check normal type of templates present.

### Security Hub
- All security helping services, main portal for all those services and all report can be seen here.
- Central dashboard.
- Integrate with various services.Can integrate eventbridge onto it and can take automatic actions/alerts for us

### KMS(Key Management Service)
- Involved with encryption(transform data into non human readable format).
- Cryptographic key manages keys for encryption, and these keys management is done by KMS.
- Key has key ID/arn,key material, policy,state, rotation status etc
- Integrates with various other services, like S3 is automatically encypted.
- Keys Types
    - Customer Master Keys : Normal ones
    - Data key : large amount of data encrypt
    - Import key material
    - AWS managed keys : automatically created according to your service usage.
    - AWS owned keys : used across accounts by aws for several other data encyptions.
- Types of encyptions
    - Symmetric : single key for encrypt and decypt
    - assymmetric : a public(encrypt) and private key(for decrpt)

### CloudHSM(hardware security module)
- kms keys are stored at random servers, so can be leaked, so we send it to hsm and stored in one device which is highly secured.
- AWS gives managed HSM module so you can save keys here and send encyption/decyption request here.

### Certificate Manager(Public certificate, internet)
- Certificate for ssl/tls communications.
- It is managed service to generate certifcate whenver needed by customer, can also import and managing certificates.Auto renewals.
- Used with load balancer, api gateway etc.
- Not supported with many services like ec2, s3 etc.

### Private Certificate Authority(no internet, private)
- Designed to create certificates to identify internal users/ your company.Internal servers.Not for the internet.Scalable.

### Secrets Manager
- DB has user and pass, don't want to save it in code like on github, don't want to hardcode.
- AWS secret manager helps saves, load, distribute secrets to be sued by applications/services.
Any type of secret can be stored, basically a key value pair.
- Have access control way to limit who can access etc.
<img width="943" alt="Screenshot 2024-01-14 at 6 48 14 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/d03ca3fe-7e58-4422-b84c-31a28c900292">

### NACLs and Security Groups
- NACLs associate with subnet.Stateless.
- Security Group is associated with apllication specific.Stateful.A security group can be associated with two services of different subnets.VPC specific.
- Both acts like a firewall

### Security Lake
- Your service might be on prem, various cloud, so issue to see logs centralized.
- It collects logs from multiple services, locations, events and manages logs at a centralized locations.
- Stores the logs in S3 bucket, and can use athena etc fetures onto S3 stored logs.

### WAF(web Application Firewall)
- Sits infrom of your web applications and monitors all requests, and checks for threaths, sql injections etc.
- Attach to you ELB, api gateway etc, understand HTTPS.Integrate with other services as well.
- have webACls , are rules which requests to allow, check,conditions etc.Works according to webACLs.

### Shield and Sheild Advanced
- DDos Attack : Dirputing service functioning, with overwhelming flood of requests.It has WAF integrations, ROUTE 53 integrations etc.
- Shield is used for against DDos.
- Two Types
    - Standard : enabled by default
    - Advanced : Protect against specialised attacked as well,(3000 $ month), 24/7 access.(DDos attack , bill increase will not be counted)

### Network Firewall
- Network protection at the vpc layer.Traffic entering and leaving.
>>check what it does Firewall Endpoint ?? 
- when request enters vpc it is sent into firewall subnet, it get's sent into firewall check and then if allowed goes ahead to private subnet having resources.
- have both stateless and stateful engines.

### Firewall Manager
- Have various accounts, need to configure various firewalls for each accounts, and rule keeping becomes cumbersome.
- It provides centralised location for wafs, sgs, nacls,shield across various account as well.
- Centrally enforce rules and conditions across various account's firewalls.

>>> QUIZ 21/25 = 84%

## Conclusion
- Revise the contents
- Retake the topic end quizzes
- Try Mock Exam Until >= 95%
- Check for design section whenever available.
- Road Ahead
    - Check other associate certtificates first before trying professional.

Now Revision and retest
- My dummy SAA Exam result 
    - Prev
        - 31/65 == 48%.(Recommended ~70%)(Fail Currently)(Took I think 2 hours lengthy paper)
    - 19 Jan
        - 41/65 == 63%

