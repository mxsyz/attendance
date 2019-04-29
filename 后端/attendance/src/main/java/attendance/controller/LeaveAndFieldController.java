package attendance.controller;

import attendance.entity.LeaveAndField;
import attendance.service.LeaveAndFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LeaveAndFieldController {
    @Autowired
    private LeaveAndFieldService leaveAndFieldService;
    @RequestMapping("/leaveAndFieldById")
    @ResponseBody
    public Object findById(HttpServletRequest request)
    {
        String id=request.getParameter("id");
        return leaveAndFieldService.findById(id);
    }
    @RequestMapping("/leaveAndFieldByPage")
    @ResponseBody
    public Page<LeaveAndField> findAll(HttpServletRequest request){
        int page=Integer.parseInt(request.getParameter("page"));
        return leaveAndFieldService.findAll(page);
    }
    @RequestMapping("/leaveAndFieldByJobNumberAndDate")
    @ResponseBody
    public LeaveAndField findByJobNumberAndDate(HttpServletRequest request){
        int jobNumber=Integer.parseInt(request.getParameter("jobNumber"));
        String date=request.getParameter("date");
        return leaveAndFieldService.findByJobNumberAndDate(jobNumber,date);
    }
    @RequestMapping("/leaveAndFieldByJobNumberAndStateAndPage")
    @ResponseBody
    public Page<LeaveAndField> findByJobNumberAndState(HttpServletRequest request){
        int jobNumber=Integer.parseInt(request.getParameter("jobNumber"));
        String state=request.getParameter("state");
        int page=Integer.parseInt(request.getParameter("page"));
        return leaveAndFieldService.findByJobNumberAndState(jobNumber,state,page);
    }
    @RequestMapping("/leaveAndFieldByJobNumberAndReasonAndPage")
    @ResponseBody
    public Page<LeaveAndField> findByJobNumberAndReason(HttpServletRequest request){
        int jobNumber=Integer.parseInt(request.getParameter("jobNumber"));
        String reason=request.getParameter("reason");
        int page=Integer.parseInt(request.getParameter("page"));
        return leaveAndFieldService.findByJobNumberAndReason(jobNumber,reason,page);
    }
    @RequestMapping("/leaveAndFieldByStateAndPage")
    @ResponseBody
    public List<LeaveAndField> findByState(HttpServletRequest request){
        String state=request.getParameter("state");
        int page=Integer.parseInt(request.getParameter("page"));
        return leaveAndFieldService.findByState(state,page);
    }
    @RequestMapping("/leaveAndFieldByJobNumberAndPage")
    @ResponseBody
    public Page<LeaveAndField> findByJobNumberAndPage(HttpServletRequest request){
        int jobNumber=Integer.parseInt(request.getParameter("jobNumber"));
        int page=Integer.parseInt(request.getParameter("page"));
        return leaveAndFieldService.findByJobNumber(jobNumber,page);
    }
    @RequestMapping("/leaveAndFieldAccept")
    @ResponseBody
    public int Accept(HttpServletRequest request){
        String id=request.getParameter("id");
        return leaveAndFieldService.Accept(id);
    }
    @RequestMapping("/leaveAndFieldReject")
    @ResponseBody
    public int Reject(HttpServletRequest request){
        String id=request.getParameter("id");
        return leaveAndFieldService.Reject(id);
    }
    @RequestMapping("/leaveAndFieldRevoke")
    @ResponseBody
    public int Revoke(HttpServletRequest request)
    {
        String id=request.getParameter("id");
        return leaveAndFieldService.Revoke(id);
    }
    @RequestMapping("/leaveAndFieldUndo")
    @ResponseBody
    public int Undo(HttpServletRequest request)
    {
        String id=request.getParameter("id");
        return leaveAndFieldService.Undo(id);
    }
    @RequestMapping("/leaveAndFieldUpdate")
    @ResponseBody
    public int Update(HttpServletRequest request){
        String id=request.getParameter("id");
        String date=request.getParameter("date");
        String reason=request.getParameter("reason");
        String description=request.getParameter("description");
        return leaveAndFieldService.Update(id,date,reason,description);
    }
    @RequestMapping("/leaveAndFieldInsert")
    @ResponseBody
    public LeaveAndField Insert(HttpServletRequest request){
        int jobNumber= Integer.parseInt(request.getParameter("jobNumber"));
        String date=request.getParameter("date");
        String reason=request.getParameter("reason");
        String description=request.getParameter("description");
        return leaveAndFieldService.insert(jobNumber,date,reason,description);
    }
}
