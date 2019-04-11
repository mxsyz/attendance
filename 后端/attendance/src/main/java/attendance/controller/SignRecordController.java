package attendance.controller;

import attendance.entity.SignRecord;
import attendance.service.SignRecordSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SignRecordController {
    @Autowired
    private SignRecordSerivce signRecordSerivce;
    @RequestMapping(value="/personalSignRecord")
    @ResponseBody
    public List<SignRecord> getPersonalSignRecord(HttpServletRequest request){
        String month=request.getParameter("month");
        int jobNumber=Integer.parseInt(request.getParameter("jobNumber"));
        return signRecordSerivce.findSignRecordByMonthAndJobNumber(month,jobNumber);
    }
    @RequestMapping(value="/allSignRecord")
    @ResponseBody
    public List<Object> getAllSignRecord(HttpServletRequest request){
        String date=request.getParameter("date");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=new Date();
        try {
            date1=sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return signRecordSerivce.findSignRecordByDate(new java.sql.Date(date1.getTime()));
    }
    @RequestMapping(value="/searchSignRecord")
    @ResponseBody
    public List<Object> searchSignRecord(HttpServletRequest request){
        String key=request.getParameter("key");
        String date=request.getParameter("date");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=new Date();
        try {
            date1=sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return signRecordSerivce.findSignRecordByDateAndKey(new java.sql.Date(date1.getTime()),key);
    }
    @RequestMapping(value="/signRecord")
    @ResponseBody
    public SignRecord getSignRecord(HttpServletRequest request){
        String date=request.getParameter("date");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=new Date();
        try {
            date1=sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int jobNumber=Integer.parseInt(request.getParameter("jobNumber"));
        return signRecordSerivce.findSignRecordByDateAndJobNumber(new java.sql.Date(date1.getTime()),jobNumber);
    }
    @RequestMapping(value="/signIn")
    @ResponseBody
    public void signIn(HttpServletRequest request){
        String date=request.getParameter("date");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=new Date();
        try {
            date1=sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int jobNumber=Integer.parseInt(request.getParameter("jobNumber"));
        signRecordSerivce.insertSignRecord(new java.sql.Date(date1.getTime()),jobNumber);
    }
    @RequestMapping(value="/signOut")
    @ResponseBody
    public void signOut(HttpServletRequest request){
        String date=request.getParameter("date");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=new Date();
        try {
            date1=sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int jobNumber=Integer.parseInt(request.getParameter("jobNumber"));
        signRecordSerivce.updateSignRecord(new java.sql.Date(date1.getTime()),jobNumber);
    }
}
