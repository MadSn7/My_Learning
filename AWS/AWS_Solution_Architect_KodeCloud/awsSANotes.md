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


    





