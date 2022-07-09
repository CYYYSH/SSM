import com.atguigu.dao.EmployeeMapper;

import com.atguigu.pojo.Employee;
import com.atguigu.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.List;



@SpringJUnitConfig(locations = {"classpath:applicationContext.xml"})
        public class test01 {


     @Autowired
     EmployeeMapper employeeMapper;

     @Autowired
     SqlSession sqlSession;



    @Test
    public void testWithDept() throws IOException {

        /*InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);*/
        System.out.println(employeeMapper);

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
       /* for (int i = 1; i < 30; i++) {
            mapper.insertSelective(new Employee(null, "小毛" + i + "号", "女", "149" + i + "@qq.com", 2));
        }*/
        //int i = employeeMapper.insert(new Employee(null, "小鱼", "男", "1398425771@qq.com",1));
        /*Employee employee = employeeMapper.selectByPrimaryKeyWithDept(1);*/

        List<Employee> employees = employeeMapper.selectByExampleWithDept();
        System.out.println(employees);
    }
}






    

