package attendance.service;

import attendance.dao.SignRecordRepository;
import attendance.entity.SignRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class SignRecordServiceImpl implements SignRecordSerivce {
    @Autowired
    private SignRecordRepository signRecordRepository;

    @Override
    public List<SignRecord> findSignRecordByMonthAndJobNumber(String month, int jobNumber) {
        List<SignRecord> list=signRecordRepository.findSignRecordByMonthAndJobNumber(month,jobNumber);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String[] array=month.split("-");
        Calendar c = Calendar.getInstance();
        int year1=c.get(Calendar.YEAR);
        int month1=c.get(Calendar.MONTH)+1;
        int day1=c.get(Calendar.DATE);
        int year2=Integer.parseInt(array[0]);
        int month2=Integer.parseInt(array[1]);
        c.set(year2,month2, 0); //输入类型为int类型
        int num=c.get(Calendar.DAY_OF_MONTH);
        List<SignRecord> list1=new ArrayList<>();
        if(year1==year2&&month1==month2){
            num=day1;
        }
        int j=0;
        for(int i=1;i<=num;i++){
            if(j<list.size()){
                SignRecord sr=list.get(j);
                Calendar cal = Calendar.getInstance();
                cal.setTime(sr.getDate());
                if(cal.get(Calendar.DATE)==i){
                    list1.add(sr);
                    j++;
                    continue;
                }
            }
            java.util.Date date1=new java.util.Date();
            try {
                date1=sdf.parse(year2+"-"+month2+"-"+i);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            list1.add(new SignRecord(new java.sql.Date(date1.getTime()),jobNumber,0,0));
        }
        return list1;
    }

    @Override
    public List<Object> findSignRecordByDate(Date date) {
        return signRecordRepository.findSignRecordByDateOrderByJobNumber(date);
    }

    @Override
    public List<Object> findSignRecordByDateAndKey(Date date, String key) {
        return signRecordRepository.findSignRecordByDateAndKey(date,key);
    }

    @Override
    public SignRecord findSignRecordByDateAndJobNumber(Date date, int jobNumber) {
        SignRecord sr=signRecordRepository.findSignRecordByDateAndJobNumber(date,jobNumber);
        if(sr==null){
            return new SignRecord(date,jobNumber,0,0);
        }
        return sr;
    }

    @Override
    public void insertSignRecord(Date date, int jobNumber) {
        SignRecord sr=new SignRecord(date,jobNumber,1,0);
        signRecordRepository.save(sr);
    }

    @Override
    public void updateSignRecord(Date date, int jobNumber) {
        SignRecord sr=signRecordRepository.findSignRecordByDateAndJobNumber(date,jobNumber);
        if(sr==null){
            signRecordRepository.save(new SignRecord(date,jobNumber,0,1));
        }
        else{
            sr.setSignOut(1);
            signRecordRepository.save(sr);
        }
    }
}
