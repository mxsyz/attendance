package attendance.dao;

import attendance.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary,Integer>{
    @Query(value="select s.job_number,s.salary,p.name from personal_info p, salary s where  s.year = :years and s.month=:months and s.job_number = p.job_number order by s.job_number ",nativeQuery = true)
    public List<Object> findSalaryRecordByMonthOrderByJobNumber(@Param("years")int year, @Param("months")int month);
    public Salary findByJobNumberAndMonthAndYear(int jobNumber,int month,int year);
    @Query(value="select s.job_number,s.salary,p.name from personal_info p, salary s where  s.year = :years and s.month=:months and s.job_number = p.job_number and (s.job_number like %:keys% or s.salary like %:keys% or p.name like %:keys%) order by s.job_number",nativeQuery = true)
    public List<Object> findSalaryRecordByMonthAndKey(@Param("years")int year, @Param("months")int month,@Param("keys")String key);
}
