
## 需求说明
```
请设计并实现一个完整的优惠券系统，
具体需求如下： 
1-系统分为用户和商家两个模块，用户从商家库存里面购买商品，通过用户账户
    的预存现金账户付款。
2-购买商品流程为，用户下单购买某个的商品，提供商品的 sku 和数量，相应的
    用户预存账户里面减掉对应的现金 (quantity * price)，商家账户里加上对应
    的现金，并且库存中减掉对应的商品数量
3-用户可以通过 REST API 的形式，向自己的预存账户里面充值，只支持一种货
    币即可。
4-商家可以通过 REST API 的形式，向库存中添加商品数量。
5-商家方有定时 job 每天进行结算，对库存中卖出的商品价值和商家账户的余额
    进行匹配。
    
要求： 
1-系统要求使用 Java 作为开发语言，基于 Spring，Spring Boot 和 Spring Cloud
    的相关技术栈。
2-系统对外 API 基于 REST API 来设计，follow 良好的 REST API 设计规范
3-系统要求是一个完成能本地运行的 Spring Boot 应用程序
4-核心代码单元测试覆盖率达 80%
5-代码具有良好的可配置，可扩展性。（线上面试会有新需求加入）
6-如果有 DDD 开发设计经验，可以根据 DDD 开发来设计，合理划分 Boundary 
    Context 和 Aggregate Root （Better to Have）

```

## 项目结构
```
./coupon-application   应用层

./coupon-common   公共

./coupon-domian    领域层

./coupon-infra   基础服务层

./coupon-api   接口层

./flyway  数据库sql

```

## 使用说明
### 1. 执行flyway 新建库表
- 新建本地数据库coupon,然后在flyway 模块下面，修改 application-dev.yml 配置文件中数据库的连接信息，然后执行flway

### 2. 运行 coupon-api
- 提供了控制器

### 3. 执行测试
-  cd  ddd-coupon
-  mvn test



