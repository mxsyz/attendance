package attendance.entity;

import javax.persistence.*;

@Entity
@Table(name="salary")
@IdClass(JobNumberAndMonth.class)
public class Salary {
    @Id
    @Column(name="job_number",nullable=false)
    private int jobNumber;
    @Id
    @Column(name="year",nullable=false)
    private int year;
    @Id
    @Column(name="month",nullable=false)
    private int month;
    @Column(name="basicWage")
    private float basicWage;
    @Column(name="bonus")
    private float bonus;
    @Column(name="absence_deduction")
    private float absenceDeduction;
    @Column(name="leave_deduction")
    private float leaveDeduction;
    @Column(name="salary")
    private float salary;
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
    public float getBasicWage(){
        return basicWage;
    }
    public void setBasicWage(float basicWage){
        this.basicWage=basicWage;
    }
    public float getBonus(){
        return bonus;
    }
    public void setBonus(float bonus){
        this.bonus=bonus;
    }
    public float getAbsenceDeduction(){
        return absenceDeduction;
    }
    public void setAbsenceDeduction(float absenceDeduction){
        this.absenceDeduction=absenceDeduction;
    }
    public float getLeaveDeduction(){
        return leaveDeduction;
    }
    public void setLeaveDeduction(float leaveDeduction){
        this.leaveDeduction=leaveDeduction;
    }
    public float getSalary(){
        return salary;
    }
    public void setSalary(float salary){
        this.salary=salary;
    }
}
