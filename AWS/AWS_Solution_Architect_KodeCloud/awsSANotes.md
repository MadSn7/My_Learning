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

- - My dummy SAA Exam result
    31/63 == 48%.(Recommended ~70%)(Fail Currently)
### Why/What is SAA(Solution Architect Associtate) cert?
- No coding ecperience
- understand basic services and manage 4 AreaS about it.
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
- Scenario based questions and not just what vpcs are etc etc
- Two Type question
    - MCQ(One correct, 3 incorrect)
    - Multiple response(2 or more correct,total 5 or more options)
- 65 question, 50 will be cosidered for scoring not sure which, so treat all equal.
- 720/1000 to pass(~ 72% )
- Question format


|Tables |% of Exam |
|----- | ---- | 
|Security |30% |
|Resilient | 26% |
|High-Performance | 24% |
|Cost-optimize | 20% |

### _Check if we can reattempt question review or not?????_

## Services - Networking
### VPC(Virtual Private Cloud)
- Isolated sections/part of aws cloud, so as to make resources from different users separate from each other , as well as ours if we want to deploy separate and different applications.
- Allow account owner to take full control of networking and usage, like subnetting, route tables,firewalls,gateways similarly like in physical place but aws makes it simple.
- A vpc is specific to a single region, cannot span over two region
- A resource deployed in one vpc by default couldn't contact other vpc's resources, automatically isolated.So have to explicitly enable communication to internet or other vpcs.So automatic security.
- Each vpc has range of cidr block associated with it, called CIDR block, these are the ip's a resource inside vpc can use.
- CIRD block is between /16 to /28 range(You can specify the amount you want)(/yy means out of 32 bits yy many availble to be CIDR blocks so this many resoucres).
Ex
192.168.0.0/16 so range for CIDR blocks
192.168.0.0 to 192.168.255.255
- Optionally can enable secondary ipv4 block 
- Optionally can enable ipv6 block so /56 availaible
- have upto 5 IPv6 cidr blocks

>Two types VPC
<img width="1368" alt="Screenshot 2023-12-24 at 8 26 18 AM" src="https://github.com/MadSn7/My_Learning/assets/62552772/63424445-d3ae-4a33-b5ea-1f983c21d805">

1.Default : Whenever you create your aws account, aws is configure a vpc for you , not one but for all regions you have access too.
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


2.Custom :  You create it according to you needs and configuration.

    





