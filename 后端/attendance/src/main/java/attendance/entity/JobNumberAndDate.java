package attendance.entity;

import java.io.Serializable;
import java.sql.Date;

public class JobNumberAndDate implements Serializable {
    private int jobNumber;
    private Date date;
    public int getJobNumber(){
        return jobNumber;
    }
    public void setJobNumber(int jobNumber){
        this.jobNumber=jobNumber;
    }
    public Date getDate(){
        return date;
    }
    public void setDate(Date date){
        this.date=date;
    }
}
