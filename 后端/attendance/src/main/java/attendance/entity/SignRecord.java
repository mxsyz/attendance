package attendance.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="sign_record")
@IdClass(JobNumberAndDate.class)
public class SignRecord {
    @Id
    @Column(name="job_number",nullable=false)
    private int jobNumber;
    @Id
    @Column(name="date",nullable=false)
    private Date date;
    @Column(name="sign_in")
    private int signIn;
    @Column(name="sign_out")
    private int signOut;

    public SignRecord() {
    }

    public SignRecord( Date date,int jobNumber, int signIn, int signOut){
        this.jobNumber=jobNumber;
        this.date=date;
        this.signIn=signIn;
        this.signOut=signOut;
    }
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
    public int getSignIn(){
        return signIn;
    }
    public void setSignIn(int signIn){
        this.signIn=signIn;
    }
    public int getSignOut(){
        return signOut;
    }
    public void setSignOut(int signOut){
        this.signOut=signOut;
    }
}
