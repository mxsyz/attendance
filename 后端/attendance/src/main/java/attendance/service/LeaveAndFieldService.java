package attendance.service;

import attendance.entity.LeaveAndField;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LeaveAndFieldService {
    public LeaveAndField findById(String id);
    public Page<LeaveAndField> findAll(int page);
    public LeaveAndField findByJobNumberAndDate(int jobNumber, String date);
    public Page<LeaveAndField> findByJobNumberAndState(int jobNumber, String state, int page);
    public Page<LeaveAndField> findByJobNumberAndReason(int jobNumber, String reason, int page);
    public Page<LeaveAndField> findByJobNumber(int jobNumber, int page);
    public List<LeaveAndField> findByState(String state, int page);
    public int Undo(String id);
    public int Accept(String id);
    public int Reject(String id);
    public int Revoke(String id);
    public int Update(String id,String date,String reason,String description);
    public LeaveAndField insert(int jobNumber, String date, String reason, String description);
}
