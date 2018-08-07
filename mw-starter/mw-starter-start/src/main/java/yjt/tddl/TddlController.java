package yjt.tddl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yjt.tddl.jdbc.JdbcQueryDemo;
import yjt.tddl.mybatis.MybatisQueryDemo;

import java.util.List;

/**
 * JdbcQueryDemo是直接注入JdbcTemplate的方式来访问数据库，MybatisQueryDemo是通过mybatis的方式访问数据库。
 * 通过mybatis进行查询的例子，详见http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/
 * 
 * @author chengxu
 */
@Controller
public class TddlController {

    @Autowired
    private JdbcQueryDemo jdbcDemo;

    @Autowired
    private MybatisQueryDemo mybatisDemo;

    @RequestMapping(value = "/jdbc/salary", method = RequestMethod.GET)
    public @ResponseBody String queryByJDBC() {
        List<Salary> queryResult = jdbcDemo.query();
        return appendLink(queryResult);
    }

    @RequestMapping(value = "/mybatis/salary", method = RequestMethod.GET)
    public @ResponseBody String queryByMyBatis() {
        List<Salary> queryResult = mybatisDemo.query();
        return appendLink(queryResult);
    }

    private String appendLink(List<Salary> queryResult) {
        StringBuilder result = new StringBuilder("query result : ");
        result.append("<br/>");
        result.append(queryResult);
        result.append("<br>");
        result.append("<hr>");
        result.append("[<a href=\"/tddl\"> Dev Home </a>]");
        result.append("&nbsp");
        result.append("[<a href=\"/\"> Home </a>]");
        return result.toString();
    }
}
