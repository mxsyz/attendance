package attendance.service;

import attendance.entity.SignRecord;

import java.sql.Date;
import java.util.List;

public interface SignRecordSerivce  {
    public List<SignRecord> findSignRecordByMonthAndJobNumber(String month,int jobNumber);
    public List<Object> findSignRecordByDate(Date date);
    public List<Object> findSignRecordByDateAndKey(Date date,String key);
    public SignRecord findSignRecordByDateAndJobNumber(Date date,int jobNumber);
    public void insertSignRecord(Date date,int jobNumber);
    public void updateSignRecord(Date date,int jobNumber);
}
