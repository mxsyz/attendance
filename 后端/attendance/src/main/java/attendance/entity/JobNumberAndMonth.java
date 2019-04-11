package attendance.entity;

import java.io.Serializable;
import java.sql.Date;

public class JobNumberAndMonth implements Serializable {
    private int jobNumber;
    private int year;
    private int month;
    public int getJobNumber(){
        return jobNumber;
    }
    public void setJobNumber(int jobNumber){
        this.jobNumber=jobNumber;
    }
    public int getYear(){
        return year;
    }
    public void setYear(int year){
        this.year=year;
    }
    public int getMonth(){
        return month;
    }
    public void setMonth(int month){
        this.month=month;
    }
}
