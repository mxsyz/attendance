package attendance.controller;

import attendance.entity.SignRule;
import attendance.service.SignRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignRuleController {
    @Autowired
    private SignRuleService signRuleService;
    @RequestMapping(value="/signRule")
    @ResponseBody
    public SignRule getSignRule(){
        return signRuleService.getSignRule();
    }
    @RequestMapping(value="/cookie")
    @ResponseBody
    public String getCookie(HttpServletRequest request){
        String sessionId = request.getSession().getId();
        return sessionId;
    }
}
