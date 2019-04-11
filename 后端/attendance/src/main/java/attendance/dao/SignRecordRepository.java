package attendance.dao;

import attendance.entity.SignRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface SignRecordRepository extends JpaRepository<SignRecord,Integer> {
    @Query(value="select * from sign_record  s where s.date like :months% and s.job_number= :jobNumber order by s.job_number",nativeQuery = true)
    public List<SignRecord> findSignRecordByMonthAndJobNumber(@Param("months")String month,@Param("jobNumber")int jobNumber);
    @Query(value=" select p.job_number,p.name,s.sign_in,s.sign_out from personal_info p left  join sign_record s on  (s.date = :dates and s.job_number=p.job_number) order by p.job_number",nativeQuery = true)
    public List<Object> findSignRecordByDateOrderByJobNumber(@Param("dates")Date date);
    @Query(value=" select p.job_number,p.name,s.sign_in,s.sign_out from personal_info p left  join sign_record s on  (s.date = :dates and s.job_number=p.job_number) where p.job_number like %:keys% or p.name like %:keys% order by p.job_number",nativeQuery = true)
    public List<Object> findSignRecordByDateAndKey(@Param("dates")Date date,@Param("keys") String key);
    public SignRecord findSignRecordByDateAndJobNumber(Date date,int jobNumber);
}
