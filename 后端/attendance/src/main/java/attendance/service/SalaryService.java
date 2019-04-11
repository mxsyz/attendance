package attendance.service;

import attendance.entity.Salary;

import java.util.List;

public interface SalaryService {
    public List<Object> findSalaryRecordByMonth(int year, int month);
    public Salary findSalaryByJobNumberAndYearAndMonth(int jobNumber,int year,int month);
    public List<Object> findSalaryRecordByMonthAndKey(int year,int month,String key);
}
