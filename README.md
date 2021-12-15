# Hunion-Traceability

A new type of blockchain traceability system based on SM2, SM3 and SM4

## Catalogue Description

```
Hunion-Traceability
├─ BackEnd
│    ├─ .DS_Store
│    ├─ .idea
│    ├─ certificates
│    ├─ mvnw
│    ├─ mvnw.cmd
│    ├─ pom.xml
│    ├─ springboottest.iml
│    ├─ src
│    └─ target
├─ ChainCode
│    ├─ .gradle
│    ├─ build
│    ├─ build.gradle
│    ├─ config
│    ├─ gradle
│    ├─ gradlew
│    ├─ gradlew.bat
│    ├─ settings.gradle
│    └─ src
├─ FabricSDK
│    ├─ fabcar-java.iml
│    ├─ pom.xml
│    ├─ src
│    ├─ target
│    └─ wallet
├─ Manage_FrontEnd
│    ├─ .editorconfig
│    ├─ .env.development
│    ├─ .env.production
│    ├─ .env.staging
│    ├─ .eslintignore
│    ├─ .eslintrc.js
│    ├─ .gitignore
│    ├─ .travis.yml
│    ├─ README.md
│    ├─ babel.config.js
│    ├─ build
│    ├─ jest.config.js
│    ├─ jsconfig.json
│    ├─ mock
│    ├─ package-lock.json
│    ├─ package.json
│    ├─ plop-templates
│    ├─ plopfile.js
│    ├─ postcss.config.js
│    ├─ public
│    ├─ src
│    ├─ tests
│    └─ vue.config.js
├─ ServiceMonitor
│    ├─ HaiUnion-Monitor.iml
│    ├─ pom.xml
│    ├─ src
│    └─ target
└─ User_FrontEnd
│      ├─ .babelrc
│      ├─ .editorconfig
│      ├─ .gitignore
│      ├─ .postcssrc.js
│      ├─ LICENSE
│      ├─ README.md
│      ├─ build
│      ├─ config
│      ├─ dist
│      ├─ index.html
│      ├─ package-lock.json
│      ├─ package.json
│      ├─ src
│      ├─ static
│      └─ yarn.lock
├─ README.md
```

## Operating Environment (for reference)

| ***Rule items***              | ***Specifications***          |
| ----------------------------- | ----------------------------- |
| CPU                           | Intel Xeon 8 cores 2.2GHz     |
| Memory                        | 8G DDR3 ECC                   |
| Hard Disk                     | 200G SSD                      |
| System Environment            | Ubuntu20.04 LTS               |
| Blockchain System Environment | Hyperledger Fabric2.3.0       |
|                               | go1.56.6                      |
|                               | docker20.10.6                 |
| Web Application Environment   | MySQL8.0                      |
|                               | Nginx1.18.0                   |
|                               | Java SE Development Kit 8u291 |

## Introduction to the modules and instructions for their use

* ### BackEnd

  Core server-side code, built using the SpringBoot framework, which allows other modules to be connected through this module.

  ```bash
  mvn compile
  mvn spring-boot:run
  ```

  or

  ```
  mvn install
  java -jar BackEnd-1.0.0.jar
  ```

* ### ChainCode

  Chaincode source code in the Hyperledger Fabric blockchain network, capable of meeting our business needs: up-linking and querying of relevant data summaries.

  ```
  peer chaincode install -n Hunion-ChainCode -v 1.0 -p /opt/gopath/src/github.com/chaincode/ChainCode -l java 
  peer chaincode instantiate -o orderer.example.com:7050 --tls --cafile /opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem -C mychannel -n Hunion-ChainCode -v 1.0 -c '{"Args":["init"]}' -P "AND ('Org1MSP.peer')"
  ```

* ### FabricSDK

  Code written using the official Fabric SDK as a vehicle to perform methods such as initialization, invoke and query on the chain.

  ```
  mvn compile
  mvn spring-boot:run
  ```

  or

  ```
  mvn install
  java -jar FabricSDK-1.0.0.jar
  ```

* ### Manage_FrontEnd

  后台管理前端面板，主要用于系统、区块链等的服务监控和供应链数据入库上链等操作，管理员准入

  ```
  npm install
  npm run dev
  ```

* ### ServiceMonitor

  Service monitoring module, and the core server in two separate ports, for all kinds of performance monitoring of the system.

  ```
  mvn compile
  mvn spring-boot:run
  ```

  or

  ```
  mvn install
  java -jar ServiceMonitor-1.0.0.jar
  ```

* ### User_FrontEnd

  User-side front-end code, written with Vue framework and ElementUI, mainly includes traceability interface, data display and certificate verification, etc.

  ```
  npm install
  npm run dev
  ```

## Support

1. Github Issue
2. Send an email to hao_meng_hainanu@163.com
