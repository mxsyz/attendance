package attendance.entity;

import attendance.constant.Reason;
import attendance.constant.State;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="`leave&field`")
@IdClass(JobNumberAndDate.class)
public class LeaveAndField {
    @Id
    @Column(name = "job_number", nullable = false)
    private int jobNumber;
    @Id
    @Column(name="date",nullable=false)
    private Date date;
    @Enumerated(EnumType.STRING)
    @Column(name="reason")
    private Reason reason;
    @Enumerated(EnumType.STRING)
    @Column(name="state")
    private State state;
    @Column(name="description")
    private String description;
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
    public Reason getReason(){
        return reason;
    }
    public void setReason(Reason reason){
        this.reason=reason;
    }
    public State getState(){
        return state;
    }
    public void setState(State state){
        this.state=state;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }
}
