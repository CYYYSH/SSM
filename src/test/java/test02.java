import com.atguigu.pojo.Department;
import com.atguigu.pojo.Employee;
import com.atguigu.service.DepartmentService;
import com.atguigu.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

@SpringJUnitConfig(locations = {"classpath:applicationContext.xml"})
public class test02 {


    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;


    @Test
    public void testService(){

        //List<Employee> employees = employeeService.selectAllEmp();
        //int delete = employeeService.delete(5);

        ArrayList<Integer> integers = new ArrayList<>();

        integers.add(10);
        integers.add(11);
        integers.add(12);
        integers.add(13);

        employeeService.deleteBatch(integers);



    }



    @Test
    public void testDeptService(){

        List<Department> departments = departmentService.selectAllDepts();

        System.out.println(departments);
    }

    @Test
    public void testEmpService(){

        int count = employeeService.findEmpByName("小毛1000号");

        System.out.println(count);
    }



}
