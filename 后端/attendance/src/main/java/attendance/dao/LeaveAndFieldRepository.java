package attendance.dao;

import attendance.entity.LeaveAndField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface LeaveAndFieldRepository extends JpaRepository<LeaveAndField,Integer> {
    public LeaveAndField findByJobNumberAndDate(int jobNumber, Date date);
}
