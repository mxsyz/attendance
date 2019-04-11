package attendance.entity;

import attendance.constant.Position;

import javax.persistence.*;

@Entity
@Table(name="personal_info")
public class PersonalInfo {
    @Id
    @Column(name="job_number",nullable=false)
    private int jobNumber;
    @Column(name="wechat_account",nullable=false)
    private String wechatAccount;
    @Column(name="name",nullable=false)
    private String name;
    @Column(name="photo")
    private String photo;
    @Enumerated(EnumType.STRING)
    @Column(name="position")
    private Position position;
    public int getJobNumber(){
        return jobNumber;
    }
    public void setJobNumber(int jobNumber){
        this.jobNumber=jobNumber;
    }
    public String getWechatAccount(){
        return wechatAccount;
    }
    public void setWechatAccount(String wechatAccount){
        this.wechatAccount=wechatAccount;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getPhoto(){
        return photo;
    }
    public void setPhoto(String photo){
        this.photo=photo;
    }
    public void setPosition(Position position){
        this.position=position;
    }
}
