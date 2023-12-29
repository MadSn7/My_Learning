# AWS SAA

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
- No coding experience
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
- Check and confirm before giving one
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

### _Check if we can reattempt question review or not???_

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
- /16 Ipv4 cidr block, and default vpc address 172.31.0.0 cidr block so 65,536 address availble(for all accounts)
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
- A subnet allows for an oprional ipv6 CIDR and can be used only with IPV6 and remove ipv4.
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
- They monitor traffic entering and leaving a subnet, and don't work withing subnet(Like RAW)
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
Client should have one ip address not all.So one leastic ip doesn't work.
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
    - Lambda@Edge : Both above as well as before forward request to rigin and also after reciecving a response from origin.
        - Ideal for little long running funciton, changing memory/cpu requiring functions, third party dependency, network-dependent functions, need body for http
<img width="1350" alt="Screenshot 2023-12-27 at 8 18 36 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/467fb3ed-edb8-456f-b092-82b4be533448">

### Global Accelator
- If request need to go to server it go through internet and it takes time, inefficient and take long path basically whatever available.
- They also have edge locations, bit dierent from cloudfronEdge locatins
_ when user hit the request, it goes to nearby global accelator edge locations and it then takes from there so fast, secure reliable than internet.
- cloudfron is for cache objects, whereas global accelator if for routing quickly to aws network. 
`aws global accfelator image`
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
- As they are seaprate from EC2, we can move data from one EC2 to other too.Tradionally one block is attached to one EC2 but now you can attach to more but make sure all don't overwrite each other's data.
- Available within AZ's, both EC2 and EBS should be in same az, can't if in another az.
- Basically create EBS storage and during creating EC2 attach that created storage.
- To migrate data between az, we can take snapshots, which get stored in S3,now we can deploy EBS in another AZ, similar step for cross regions too.
  <img width="1405" alt="Screenshot 2023-12-28 at 10 34 16 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/a948c244-d71c-4726-a89f-7fa852835a1a">

- EBS volume types
    <img width="1411" alt="Screenshot 2023-12-28 at 10 35 36 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/ca013ed9-613b-4e5e-8d71-4eb09162fb37">
    - General-Purpose SSD(gp2 & gp3)
        - Volume backed by solid state drives(ssd)
        - Balance price and perforamance
        - Recommended for most workloads, if not sure use this.
        - gp3 are latest generations and lowest cost,good for most application, 20% cheap than gp3
        - gp2 are default EBS volume, performance increase with size increase
    - Provisioned IOPS SSD(io1 & io2)
        - Also backed by ssd, highest performance EBS, for critical high throughput , intensive workloads.Like DB etc.
        - <img width="1421" alt="Screenshot 2023-12-28 at 11 08 38 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/f2ec5a08-e391-4889-844b-e77fce4a2888">
        - io2 & io1 mostly similar, io2 more durable but io2 block express better.
    - Thoughput - Optimized HDD(st1) and Cold HDD volumes(sc1)
        - Based on HDD
        - <img width="1397" alt="Screenshot 2023-12-28 at 11 13 10 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/504d3ab8-ac8c-4600-9788-24c22aca1ff3">
        - st1 : Big Data ,warehouse,log processing
        - sc1 : cheapest one,data infrequent access.
    - Magnetic Volume(previous generation)
        - Depend on magnetic drive
        - <img width="1217" alt="Screenshot 2023-12-28 at 11 27 13 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/5a29cb64-aea7-4b31-bfae-f74cdacadbde">

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
- First of two files sytsem sotrage provides(other is FSx)
- Uses NFS (NFSv4) file system, windows doesn't work with it, only linux.
- Can mount on multiple EC2
- VPC specific deploy, specify subnets to get mount target which are an ip adress
- Now ec2 instance can connect using this mount ip.
- <img width="1325" alt="Screenshot 2023-12-28 at 5 06 39 PM" src="https://github.com/MadSn7/My_Learning/assets/62552772/d3f2dce6-bed1-4748-a36c-c7917169c97d">
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



