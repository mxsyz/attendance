package attendance.service;

import attendance.dao.SalaryRepository;
import attendance.entity.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;
    @Override
    public List<Object> findSalaryRecordByMonth(int year, int month) {
        return salaryRepository.findSalaryRecordByMonthOrderByJobNumber(year,month);
    }

    @Override
    public Salary findSalaryByJobNumberAndYearAndMonth(int jobNumber, int year, int month) {
        return salaryRepository.findByJobNumberAndMonthAndYear(jobNumber,month,year);
    }

    @Override
    public List<Object> findSalaryRecordByMonthAndKey(int year, int month, String key) {
        return salaryRepository.findSalaryRecordByMonthAndKey(year,month,key);
    }
}
