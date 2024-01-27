# AWS CodePipeline (CI/CD Pipeline)

## Basics of CodePipeline
- A pipeline connects different componenets together for smoother processing.
- CI : Continuous Integration
- CD : Continuous deployment
- CI/CD connects different components for continuous deployment.
- CodePipeline is AWS's CI/CD pipeline service.

## AWS CodePipeline
- Has componeents
    - Source : Mainly source code.
    - Build : Can be automaticaaly triggered after code is submitted/pushed.
    - Test : Checking the s/w before deploying the new componenets.can have pre configured tests.
    - Deploy : Deploy according to your need like on server or on containers etc.
- Has ability to integrate with aws and non aws services.
- Use Cases
    - Source can be aws aws codecommit(can also be s3, or artifact), build is aws codeBuild, deploy is with aws CodeDeploy.
![Screenshot 2024-01-15 at 2 12 07 PM](https://github.com/MadSn7/My_Learning/assets/62552772/0f73c5c7-e3f6-4cfc-b31f-abfac7491bb5)
    - can mix-match aws services and non aws services like source can be github, build with jenkins and dpeloy with aws codeDeploy etc.

### Integrations of CodePipeline
- Built to work with various third party products through integrations
- Integrations according to stage of you codePipeline
    - Source
        - S3 : files can be stored has versions and triggers can be assciated to an event
        - AWS CodeCommit : Similar to github(code repository).
        - ECR : Docker images here, can be used by ECS etc.
        - Github & Bitbucket : Non-aws, can be integrated.Need to setup connections for them.
    - Build
        - Aws CodeBuild : Fully managed aws service,compile code, run test,produce artifacts etc
        - Jenkins/TeamCity/CloudBees : Non AWS.Need to have jenkins project made, and plugin for codePipeline, security allowance.
        - ALL above both aws, and non-aws, can do build and test both.
    - Test(Other than above build ones)
        - AWS Device Farm
        - Non AWS : RunScope,Balxemeter,MicroFocus,Ghost Inspector.
    - Deploy
        - depend you want server, serverless, containers etc.
        - S3, AWS CodeDeploy, EBS, ECS, ECS, APP Config, Cloudformations etc etc.
- Approvals/Invocations can be also there you can include them in pipeline, omit it, or generate notification for them according to you requirement in the pipeline.

### Security
- Security for codepipeline is very critical as it also involves various services and compenents.
- Main Areas
    - IAM
    - Authetication
    - Authorization
    - Encryption(Both data stored and in transit)
    - Managing Secrets

### Cost Structure
- Free Tier
    - Can have 1 active pipeline/month at no cost.
    - Can have many pipeline but if one change it pushed it is considere active and can be charged.
- 1$/Month charge per pipeline.
- Above cost for codepipeline only services used with it have their respective charges.

### Monitoring
- Helps troubshoot and identify status of codePipeline.
- Mainly use of eventBridge, can create events conditions, and targets/actions.
    - Can configure evets with codePipeline according to pipiline-level,stage-level, action-level.
- CloudTrail can also be used, can be attached to sns.

## CI/CD pipeline with Codecommit,CodeBuild,CodeDeploy

### CodeCommit
- Repository(Source/Repo)
    - Pipeline start/base logic saved.
    - Version controlled code saver.
    - GitHub
    - CodeCommit : AWS's version of GitHub.
    - BitBucket : GitBased.
    - S3 : can be used, not a repo.
- Working of CodeCommit
    - Create repo in codeCommit
    - Can now clone repo and edit or directly add/upload file.
    - Can create branches(main is default branch)
    - Basically similar to any git service.

### CodeBuild
- Build(and Test) Portion
    - Several products in market, codeBuild is aws managed one.
    - All above soruces and more it integrates with.
    - Jenkins can be replaced with codeBulild if we have all Aws deployment, also both can be used all together or migrate from one to another(plugin is added between them)
- Features
     - Comes with preconfigured run environment for java, python,docekr etc.Can create your own.
    <img width="991" alt="Screenshot 2024-01-21 at 1 11 22 PM" src="https://github.com/MadSn7/My_Learning/assets/62552772/3b588a23-f042-4bdd-8d58-7a540d2ec64c">
    - Supports monitoring through cloudwatch and notification through SNS.
    - Can be integrated with codePipeline or used by itself.
- Working
    - There will be a source(like a git repo,s3 etc)
    - Create build project in aws codeBuild service
    - Select source according to your requirement in above build project.
    - Choose runtime/os etc.
    - Select artifact location save place, S3.
    - Can Start Build.

### CodeDeploy
- for Deployment, works well with codePipeline
- For deployment to EC2, on-prem,Lambda,ECS etc
- Supports deployment of code, lambda functions,web files, config files,packages,scripts etc.
- Supports many sources like s3, bitbucket etc.
- Fully managed,scalable,dashboard,simultaneous deployment etc. featured.
- Supports Various type of deployments
    - In Place : Update the running service.
    - Rolling : Update one by one(can roll back, pause etc.)
    - Immutable : new infra,server stup
    - Blue/Green : set new environment say green, then send starting traffic from blue to green.Several ways to shift traffic
        - canaray
        - linear
        - All-at-once
- there is appSpec file mentioning some details of file,scripts etc.
- Working
    - Create an application,Select compute platform(lie EC2)
    - Create deployment group(inside application)
        - Selct roles,deployment type and setting,env config etc etc.
    - After creating deployment group can select deployment and you code stored place like S3, github etc.
    - so application -> deployment group -> deployment

### CodePipeLine Limitations
- Each pipeline name must have unique name within an account,each stages within pipeline must be unique, and actions within stage must be unique.
- Some quotas are applied per regions,
    - 1000 max no of pipelines per region per aws account.
    - min2, max 50 stages per pipeline.
    - min1 actions,max 50 action per stage(can be parallel or actions)
    - 7 days before approval time out
    - etc etc.

## CodePipeline
- Working
    - You create pipeline give it a name and role to be used.
    - Add source(s3 and the object etc)
    - Add Build
    - Add Deploy, deploy provider named codedeploy and add you application created in codeDeploy and also deployment group.
    - Sample one can add other aws / non aws service as well at various stages.
    - By default any change in codecommit new deploy takes place need to set stages etc for making steps one by one.
    - To add test, we need add a stage, set actions and provider etc.
