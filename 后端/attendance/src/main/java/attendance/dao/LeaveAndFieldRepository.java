package attendance.dao;

import attendance.constant.Reason;
import attendance.constant.State;
import attendance.entity.LeaveAndField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

public interface LeaveAndFieldRepository extends JpaRepository<LeaveAndField,Integer> {
    public LeaveAndField findById(String id);
    public Page<LeaveAndField> findAll(Pageable pageable);
    public LeaveAndField findByJobNumberAndDate(int jobNumber, Date date);
    public Page<LeaveAndField> findByJobNumberAndState(int jobNumber,State state, Pageable pageable);
    public Page<LeaveAndField> findByJobNumberAndReason(int jobNumber, Reason reason, Pageable pageable);
    public Page<LeaveAndField> findByJobNumber(int jobNumber,  Pageable pageable);
    public List<LeaveAndField> findByState(State state, Pageable pageable);
    @Modifying
    @Transactional
    @Query(value="update leave_and_field a set " +
            "a.state = CASE WHEN :#{#leaveAndField.stateStr} IS NULL THEN a.state ELSE :#{#leaveAndField.stateStr} END ," +
            "a.date = CASE WHEN :#{#leaveAndField.date} IS NULL THEN a.date ELSE :#{#leaveAndField.date} END ," +
            "a.reason = CASE WHEN :#{#leaveAndField.reasonStr} IS NULL THEN a.reason ELSE :#{#leaveAndField.reasonStr} END ," +
            "a.description =  CASE WHEN :#{#leaveAndField.description} IS NULL THEN a.description ELSE :#{#leaveAndField.description} END " +
            "where a.id = :#{#leaveAndField.id}",nativeQuery = true)
    public int update(@Param("leaveAndField") LeaveAndField leaveAndField);

}
