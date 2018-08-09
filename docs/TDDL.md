# TDDL
TDDL 是基于 Java 语言的分布式数据库系统。

大型互联网架构中，数据存储会面临读写容量瓶颈问题，比如在“双十一网购狂欢节”活动中，核心数据存储集群读写日访问量可以达到100亿以上。这种场景下，单机数据库方式必定面临极大挑战。类似的场景也在一些传统使用 IOE 的企业中成为一种制约业务发展的致命要素，而 TDDL 就是解决此类场景的利器。

TDDL 体系核心作用在于两个方面：
+ 直接提供分库分表、读写分离等解决数据库 Scale Out 问题的功能。
+ 基于配置模型，构建数据库在线扩容、准实时数据同步、运维平台等支撑系统。

TDDL 主要解决了以下问题：
+ 单机数据库容量瓶颈： 随着数据量和访问量的增长，单机数据库会遇到很大的挑战，依赖硬件升级并不能完全解决问题。
+ 单机数据库扩展困难：传统数据库容量扩展往往意味着服务中断，很难做到业务无感知或者少感知。
+ 传统数据库使用成本高。
+ 跨语言支持：基于 TDDL 的 Corona 产品提供跨语言支持。Corona 是一个 MySQL 的 proxy，提供标准的 MySQL 协议。因此，用户可以像使用 MySQL 一样使用 Corona，从而提供跨语言支持。

1. `application.properties` 配置, 只需配置 App Name
```
# tddl配置，详见 http://gitlab.alibaba-inc.com/middleware-container/pandora-boot/wikis/spring-boot-tddl
spring.tddl.app=PB_TEST_APP
spring.tddl.sharding=true
```
2. MyBatis 配置和一般情况一样
3. MyBatis 查询和调用和一般情况一样

此外, TDDL 也支持 Spring 的 JdbcTemplate 调用
```java
@Component
public class JdbcQueryDemo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Salary> query() {
        return jdbcTemplate.query("select * from salaries where id > -1", new RowMapper<Salary>() {

            @Override
            public Salary mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                Salary salary = new Salary();
                salary.setEmpNo(paramResultSet.getLong("emp_no"));
                salary.setSalary(paramResultSet.getLong("salary"));
                salary.setFromDate(paramResultSet.getDate("from_date"));
                salary.setToDate(paramResultSet.getDate("to_date"));
                return salary;
            }
        });
    }
}
```
总体看来还是不如 MyBatis 好用. 
