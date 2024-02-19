# Jenkins
- Challenges Before Jenkins
    - Slower releases
    - Manual Builds
    - No Automations and non repeatable process
- Solution Jenkins
>>>>> Image jenkins solutions
- Before deployment we need to do several test and after deployment too which if are manual will be very troublesome and time consuming.
- With jenkins you can test all this in CI to enable testing with just a command or automate.
- Works with CI/CD pipeline.Important to know the basics of it.
- Continuous Delivery meaning there will be some commands/ steps between build and deploy whereas Continuous Deployment means after you have pushed you code it will go from build to deploy in one go.
- Why Jenkins?
    - Free
    - Lot of plugins
    - Open Source
    - Have paid/enterprise version as well.
    - Best description automations tasks server.
- Install Jenkins
    - Need to have java installed.
    - Pretty straight forward, after installing it has a auto generated password need to check in the folder, and default port run is 8080.
    - In linux installed as a service so can start service jenkins and edits it files for config.
    - After giving default password which is generated have to install plugins and create an admin user.

## Jenkins CLI
- Other than UI you can connect to jenkins from cli
- Can configure public ssh key and use ssh to conenct to jenkins from cli and run commadns accordingly.
- Need to install Jenkins clie first.
- Need to authecticate jenkins , can generate token and use it for authentication.
- Now can do various things with cli, like list jobs
```
    java -jar jenkins-cli.jar -s http://{jenkinsServerIP}:8080/ -auth:{your token} -webSocket {now_give_commands}
```
- commands like list-jobs, build jobName etc.

## Jenkins Plugins
- Plugin allows us to connect between different services
- So jenkins plugins allows jenkins to connect with various services , there are many number of plugins available.
- Inside UI -> Manage Jenkins -> Manage Plugins to add/remove etc for plugins.Can also go to jenkins index site which has host of plugins availble.
- Some plugins require to restart jenkins after installations(sudo systemctl restart jenkins)

## Jenkins UI
- UI can be useful to view and perform many activities.
- New Iteam sections has options to create pipeline etc.
- People has option to create users.
- Build History all ci/cd history
- manage Jenkins has plugin, security other configs.
    - manage users has option to create user and have it's settings like what they can do.
    - Role-Based Authorization, can install role base authorization strategy plugin.
        - Now in configure you have option for role strategy, create attach etc.
- My views, locakable resources and new view etc option.
- My queue to view jobs queued.
- Global configurations/ other configurations are under configuration system settings.Many configuration.
- front page has get started items but once you build some test/run some pipeline it removes getting started and showing the test/build results.

## System Administration With Jenkins
- Any server running will need administrator and various things like backup,restore policy,monitoring,scaling,manage.
- BackUp and Restore
    - Many types of backup availble like full, snapshots etc.
    - $JENKINS_HOME folder has .xml file , jobs folder(your pipline) , so need to make sure this is backed up
- BackUp Jenkins Ways
    - SnapShots backup
    - Plugins for backup(ThinBackup etc)
    - Shell script to backUp.
    - Can restore accordingly then
- Monitoring : Have plugins for many services for monitoring
    - Data Dog
    - Prometheous and Grafana(need to update prmoetheous target to our jenkins)
    - JavaMelody

## Pipelines
### JenkinsFile
- Contains templated/instructions,tells pipeline what it should be doing and what services and plugins it should be interacting with.Sample file
```
pipeline{
    agent any //build agent the place that runs the pipeline
    tools{
        go 'goversion' //some other language you like
    }
    stages{
        stage('Build){
            steps{
                echo 'building'
            }
        }
        stage('Test){
            steps{
                echo 'testing'
            }
        }
        stage('Deploy){
            steps{
                echo 'deploying'
            }
        }
    }//can add docker build and many other parts
}
```
- Also have multistage pipelines so dev->UAT-> prod one by one if any fails stops. 
- Jenkinsfile is named like this.
- use in steps git 'repoLink' to get code from a git repo
- Language used is Groovy(Java's extended lnaguage but easier,like scripting)

## Build Agents
- This are the systems that run the entire workload,task executor, could be any like windows, linux,macOs,Docker
- Can have separate jenkins and build server as well.
- In manage jenkins you can add nodes and it's user passwords(need to create credentials and add when configring node).
- In pipeline you can restrict where the project/pipeline can be run and add particular build agent you want to use.
- In pipeline script you can name agent that you added, using any result in build on any build agent
- For using docker
```
agent{
    docker {image 'golang:latest} // will pull this omage and use, no issue of vm creation and maintaining
}
```

## Blue Ocean
- Gives the new UI experience no additional functinality so as to say.
- Install plugin of Blue Ocean.
- now in localHost:8080/blue is you location to see that new ui. There are ui options added as well in both jenkins ui and blueOCean to switch alernatively between them.Can create jenkinsFile for you according to steps you choose in ui etc.

## Security(Securing Jenkins)
- As Jenkins is not just SaaS and we are using on server and isntalling several things, so need security
- Login users shoudn't be allowed to do anything.
- Manage section has security section, and we can use various plugin like role based authorisation,matrix authorisation etc.
