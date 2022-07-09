package com.atguigu.controller;

import com.atguigu.pojo.Department;
import com.atguigu.pojo.Employee;
import com.atguigu.pojo.Msg;
import com.atguigu.service.DepartmentService;
import com.atguigu.service.EmployeeService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
public class EmployeeController {


    @Autowired
    EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;


    //根据id删除单个员工信息
    @ResponseBody
    @RequestMapping(value = "/emp/{ids}" ,method = RequestMethod.DELETE)
    public Msg deleteEmp(@PathVariable("ids") String ids){

        List<Integer> integers = new ArrayList();

        if (ids.contains("-")){

            String[] split = ids.split("-");
            for (String id : split) {
                int i = Integer.parseInt(id);
                integers.add(i);
            }
            employeeService.deleteBatch(integers);
            return Msg.success();

        }else {

            int i = Integer.parseInt(ids);
            int delete = employeeService.delete(i);
            if (delete < 0){
                return Msg.success();
            }else {
                return Msg.fail();
            }
        }










    }







    //保存修改的员工信息{empId} 为了能够将其赋值给employee中的empId，所以名字要相同
    @ResponseBody
    @RequestMapping(value = "/emp/{empId}" ,method = RequestMethod.PUT)
    public Msg saveEmp(Employee employee){


        System.out.println(employee);
        System.out.println(employee.getEmpId());

        int i = employeeService.saveEmp(employee);
        return Msg.success();
    }


    //根据id获取员工信息
    @ResponseBody
    @RequestMapping(value = "/emp/{id}")
    public Msg getEmp(@PathVariable("id") Integer id){

        Employee emp = employeeService.getEmp(id);

        Msg emp1 = Msg.success().add("emp", emp);

        return emp1;
    }



    //校验用户名是否存在
    @ResponseBody
    @RequestMapping(value = "/checkuser")
    public Msg checkUser(@RequestParam("empName") String empName){

        String pat = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";

        if (!empName.matches(pat)){

            return Msg.fail().add("va_msg","用户名必须是6-16位数字和字母的组合或者2-5位中文");
        }



        int empByName = employeeService.findEmpByName(empName);

        if (empByName == 1){

           return Msg.fail();

        }else {

           return Msg.success();

        }

    }






    /**
     * 员工保存
     * 1、支持JSR303校验
     * 2、导入Hibernate-Validator
     *
     *
     * @return
     */
    //添加员工
    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Msg insertEmp(@Valid Employee employee, BindingResult result){

        //@Valid用于验证注解是否符合要求，直接加在变量user之前，在变量中添加验证信息的要求，
        // 当不符合要求时就会在方法中返回message 的错误提示信息。

        if (result.hasErrors()){

            //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            HashMap<String, Object> map = new HashMap<>();

            List<FieldError> fieldErrors = result.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {

                System.out.println("错误的字段名：" + fieldError.getField());
                System.out.println("错误信息：" + fieldError.getDefaultMessage());

                map.put(fieldError.getField(), fieldError.getDefaultMessage());

            }
            return Msg.fail().add("errorFields",map);


        }else {
            int i = employeeService.insertEmp(employee);

            System.out.println(i);

            Msg success = Msg.success();

            System.out.println(employee);

            return success;

        }


    }





    //查询部门
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){

        List<Department> departments = departmentService.selectAllDepts();

        Msg depts = Msg.success().add("depts", departments);

        return depts;
    }




    /**
     * 导入jackson包。
     * @param pn
     * @return
     */
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(
            @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 这不是一个分页查询
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        // startPage后面紧跟的这个查询就是一个分页查询
        List<Employee> emps = employeeService.selectAllEmp();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数

        PageInfo page = new PageInfo(emps, 5);
        return Msg.success().add("pageInfo", page);
    }

   /* @RequestMapping("/emps")
    public String  emp(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){

        PageHelper.startPage(pn, 5);


        List<Employee> employees = employeeService.selectAllEmp();

        PageInfo<Employee> employeePageInfo = new PageInfo<>(employees,5);

        model.addAttribute("pageInfo",employeePageInfo);

        return "list";
    }*/
}
