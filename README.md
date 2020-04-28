# Financial Asset Manager System

**「金融资产管理系统FAMS（Financial Asset Manager System），旨在建立一个涵盖金融投资行为的前中台的全覆盖产品，愿景为可以支持基金公司级的日常投资业务需求」**



## 功能特点

1. 基于spring-cloud的微服务体系，支持java、python版本的客户端接入

> java：核心业务服务  
> python：某些业务python更加合适，比如“向量型回测框架服务”  
> c/c++: 当前尚未开发  


2. 完整的业务链，计划覆盖投前、投中、投后业务范围 
> 投前：策略池构建、投前风控管理、投前估值模型、策略组合构建  
> 投中：指令流转、市场/系统风险管理、估值模型回归、持仓管理分析、组合交易、头寸管理  
> 投后：估值分析，因子分析、投资报表  



## 环境准备
> 对于要在本地搭建FAMS环境的用户

| 组件  | 版本 | 
| ------------- | ------------- |
| Zookeeper  |3.5.6  |
| Kafka  	 | 2.4.0  |
| Redis      |3.2.1  |
| Mysql  	 | 5.6.46  |

## get start

- 配置环境信息
> 修改fams-framework-common工程的application.yml，配置自己环境的mysql，redis，kafka信息
- 配置mysql数据
> 两种方式：使用mysql脚本，或通过设定好的python工程进行数据初始化
  mysql脚本：
- 启动monitor service（在fams环境下，需要要保证至少一个MonitorService是活跃的）
> 在fams-servcie-example-monitor工程下，启动FAMSRegisterCenter。启动参数如下：  
  -DServiceName=MonitorService  
  -DEnv=DEV  
  -DInstance=1  
  -DSpring.profiles.active=server  
- 开发自己的FAMS服务


## 任务列表

- [ ] fams-framework 框架
  - [x] fams-framework-core
    - [x] 序列化 bo
    - [x] 命令行服务
    - [x] 启动时数据加载服务
      - [x] 加载到ignite
      - [x] 加载到redis
    - [x] 全局参数配置服务
    - [x] redis服务
  - [ ] fams-framework-ignite
    - [x] 分布式缓存 cache
    - [ ] 分布式计算节点 compute
    - [ ] 流数据处理 DataStream
  - [ ] fams-framework-service
    - [x] MonitorService 注册服务
    - [ ] AuthService 鉴权服务
    - [ ] UpStreamService 上行到客户界面的服务
    - [ ] DownStreamService 下行到各外部系统的服务
  - [ ] fams-framework-springboot-starter
    - [x] fams-framework-springboot-starter-ignite
    - [x] fams-framework-springboot-starter-service
    - [x] fams-framework-example
      - [x] MonitorService
      - [x] RPC
      - [x] ignite


- [ ] fams-middle-service 交易中台	KING
  - [ ] Middle-Component 交易中台公共组件
    - [x] MarketDataContainer 行情容器
    - [x] StrategyContainer 策略容器
    - [x] TradeAnalysisContainer 交易分析容器
    - [ ] PortfolioContainer 资产组合容器
  - [ ] BacktestService 事件驱动型回测服务
    - [x] 单支证券回测
    - [ ] 组合交易回测
  - [ ] RiskManagerService 风险控制管理服务
  - [ ] ValuationService 估值模型服务
  - [ ] StraTradeService 策略交易服务
  - [ ] PortfolioTradeService 组合交易服务
  - [ ] MarketDataService 行情服务


- [ ] fams-ace-service 交易前台 ACE
  - [ ] Ace-Component 交易前台公共组件
  - [ ] OrderManagerService 订单管理服务（OMS）
  - [ ] MarketDataCenter 行情中心 (MDC)
    - [x] Sina 新浪行情中心
    - [ ] XTP 中泰量化


- [ ] fams-back-service 交易后台	QUEEN
  - [ ] Back-Component 交易后台公共组件
  - [ ] ProductService 产品库
  - [ ] AcctService 客户账户管理服务
  - [ ] OrderFlowService 指令流转服务


- [ ] Python服务
  - [x] TushareService 数据拉取服务
  - [x] BacktestService 向量型回测服务
