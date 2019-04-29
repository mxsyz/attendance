package attendance.service;

import attendance.constant.Reason;
import attendance.constant.State;
import attendance.dao.LeaveAndFieldRepository;
import attendance.entity.LeaveAndField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Service
public class LeaveAndFieldServiceImpl implements LeaveAndFieldService{
    @Autowired
    private LeaveAndFieldRepository leaveAndFieldRepository;

    @Override
    public LeaveAndField findById(String id) {
        return leaveAndFieldRepository.findById(id);
    }

    @Override
    public Page<LeaveAndField> findAll(int page) {
        Pageable pageable=PageRequest.of(page,10, Sort.Direction.DESC,"id");
        return leaveAndFieldRepository.findAll(pageable);
    }

    @Override
    public LeaveAndField findByJobNumberAndDate(int jobNumber, String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date sqldate = new java.sql.Date(d.getTime());

        return leaveAndFieldRepository.findByJobNumberAndDate(jobNumber,sqldate);
    }

    @Override
    public Page<LeaveAndField> findByJobNumberAndState(int jobNumber, String state, int page) {
        Pageable pageable=PageRequest.of(page-1,10, Sort.Direction.DESC,"id");
        State stateEnum = State.valueOf(state);
        return leaveAndFieldRepository.findByJobNumberAndState(jobNumber,stateEnum,pageable);
    }

    @Override
    public Page<LeaveAndField> findByJobNumberAndReason(int jobNumber, String reason, int page) {
        Pageable pageable=PageRequest.of(page-1,10, Sort.Direction.DESC,"id");
        Reason reasonEnum = Reason.valueOf(reason);
        return leaveAndFieldRepository.findByJobNumberAndReason(jobNumber,reasonEnum,pageable);
    }

    @Override
    public Page<LeaveAndField> findByJobNumber(int jobNumber, int page) {
        Pageable pageable=PageRequest.of(page-1,10, Sort.Direction.DESC,"id");
        return leaveAndFieldRepository.findByJobNumber(jobNumber,pageable);
    }

    @Override
    public List<LeaveAndField> findByState(String state, int page) {
        Pageable pageable=PageRequest.of(page-1,10, Sort.Direction.DESC,"id");
        State stateEnum = State.valueOf(state);
        System.out.println(leaveAndFieldRepository.findByState(stateEnum,pageable));
        return leaveAndFieldRepository.findByState(stateEnum,pageable);
    }

    @Override
    public int Accept(String id) {
        LeaveAndField vo = new LeaveAndField(id);
        vo.setState(State.valueOf("Checked"));

        return leaveAndFieldRepository.update(vo);
    }

    @Override
    public int Reject(String id) {
        LeaveAndField vo = new LeaveAndField(id);
        vo.setState(State.valueOf("Reject"));
        return leaveAndFieldRepository.update(vo);
    }
    @Override
    public int Undo(String id) {
        LeaveAndField vo = new LeaveAndField(id);
        vo.setState(State.valueOf("Uncheck"));
        return leaveAndFieldRepository.update(vo);
    }
    @Override
    public int Revoke(String id) {
        LeaveAndField vo = new LeaveAndField(id);
            vo.setState(State.valueOf("Revoke"));
        return leaveAndFieldRepository.update(vo);
    }

    @Override
    public int Update(String id,String date,String reason,String description) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date sqldate = new java.sql.Date(d.getTime());
        LeaveAndField vo = new LeaveAndField();
        vo.setState(State.valueOf("Uncheck"));
        vo.setId(id);
        vo.setDate(sqldate);
        vo.setReason(Reason.valueOf(reason));
        vo.setDescription(description);
        return leaveAndFieldRepository.update(vo);
    }

    @Override
    public LeaveAndField insert(int jobNumber,String date, String reason, String description) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String id = UUID.randomUUID().toString().replace("-", "");
        java.sql.Date sqldate = new java.sql.Date(d.getTime());
        LeaveAndField vo = new LeaveAndField();
        vo.setState(State.valueOf("Uncheck"));
        vo.setId(id);
        vo.setDate(sqldate);
        vo.setReason(Reason.valueOf(reason));
        vo.setJobNumber(jobNumber);
        vo.setDescription(description);
        return leaveAndFieldRepository.save(vo);
    }
}
