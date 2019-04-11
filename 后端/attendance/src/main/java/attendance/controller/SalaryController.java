package attendance.controller;

import attendance.entity.Salary;
import attendance.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SalaryController {
    @Autowired
    private SalaryService salaryService;
    @RequestMapping("/allSalaryRecord")
    @ResponseBody
    public List<Object> getAllSalaryRecord(HttpServletRequest request) {
        int year=Integer.parseInt(request.getParameter("year"));
        int month=Integer.parseInt(request.getParameter("month"));
        List<Object> list=salaryService.findSalaryRecordByMonth(year,month);
        return list;
    }
    @RequestMapping("/personalSalary")
    @ResponseBody
    public Salary getPersonalSalary(HttpServletRequest request) {
        int jobNumber=Integer.parseInt(request.getParameter("jobNumber"));
        int year=Integer.parseInt(request.getParameter("year"));
        int month=Integer.parseInt(request.getParameter("month"));
        Salary salary=salaryService.findSalaryByJobNumberAndYearAndMonth(jobNumber,year,month);
        return salary;
    }
    @RequestMapping(value="/searchSalaryRecord")
    @ResponseBody
    public List<Object> searchSalaryRecord(HttpServletRequest request) {
        int year=Integer.parseInt(request.getParameter("year"));
        int month=Integer.parseInt(request.getParameter("month"));
        String key=request.getParameter("key");
        List<Object> list=salaryService.findSalaryRecordByMonthAndKey(year,month,key);
        return list;
    }
}
