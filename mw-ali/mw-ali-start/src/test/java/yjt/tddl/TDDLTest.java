package yjt.tddl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import yjt.Application;
import yjt.tddl.jdbc.JdbcQueryDemo;
import yjt.tddl.mybatis.MybatisQueryDemo;
import com.taobao.pandora.boot.test.junit4.DelegateTo;
import com.taobao.pandora.boot.test.junit4.PandoraBootRunner;

import junit.framework.TestCase;

/**
 * tddl测试，通过jdbc方式和mybatis方式的查询
 * 
 * @author chengxu
 */
@RunWith(PandoraBootRunner.class)
@DelegateTo(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Application.class })
public class TDDLTest {

    @Autowired
    private JdbcQueryDemo jdbcDemo;

    @Autowired
    private MybatisQueryDemo mybatisDemo;

    @Test
    public void testQuery() {
        try {
            List<Salary> jdbcQueryResult = jdbcDemo.query();
            TestCase.assertTrue(jdbcQueryResult.size() > 0);

            List<Salary> mybatisQueryResult = mybatisDemo.query();
            TestCase.assertTrue(mybatisQueryResult.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            TestCase.fail(e.getMessage());
        }
    }

}
