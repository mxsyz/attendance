package attendance.entity;

import attendance.constant.Reason;
import attendance.constant.State;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="leave_and_field")
//@IdClass(JobNumberAndDate.class)
public class LeaveAndField {
    public LeaveAndField(String id)
    {
        this.id = id;
    }
    public LeaveAndField(){

    }
    @Id
    @Column(name="id",nullable=false,updatable = false)
    private String id;
    @Column(name = "job_number", nullable = false)
    private int jobNumber;
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
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
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
    public String getReasonStr(){
        if(reason==null){
            return null;
        }else{
            return String.valueOf(reason);
        }
    }
    public String getStateStr(){
        if(state==null){
            return null;
        }else{
            return String.valueOf(state);
        }
    }
    public int getReasonIndex(){
        if(reason==null){
            return 3;
        }else{
            return reason.ordinal();
        }
    }
    public int getStateIndex(){
        if(state==null){
            return 3;
        }else{
            return state.ordinal();
        }
    }
    public String toString(){
        return id+" "+jobNumber+" "+date+" "+reason+" "+description+" "+state;
    }
}
