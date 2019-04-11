package attendance.service;

import attendance.dao.SignRuleRepository;
import attendance.entity.SignRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignRuleServiceImpl implements SignRuleService {
    @Autowired
    private SignRuleRepository signRuleRepository;
    public SignRule getSignRule(){
        return signRuleRepository.findAll().get(0);
    }
}
