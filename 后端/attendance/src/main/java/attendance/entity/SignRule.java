package attendance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sign_rule")
public class SignRule {
    @Id
    @Column(name="sign_in_time",nullable=false)
    private String signInTime;
    @Column(name="sign_out_time")
    private String signOutTime;
    @Column(name="latitude")
    private float latitude;
    @Column(name="longitude")
    private float longitude;
    @Column(name="radius")
    private float radius;
    public String getSignInTime(){
        return signInTime;
    }
    public void setSignInTime(String signInTime){
        this.signInTime=signInTime;
    }
    public String getSignOutTime(){
        return signOutTime;
    }
    public void setSignOutTime(String signOutTime){
        this.signOutTime=signOutTime;
    }
    public float getLatitude(){
        return latitude;
    }
    public void setLatitude(float latitude){
        this.latitude=latitude;
    }
    public float getLongitude(){
        return longitude;
    }
    public void setLongitude(float longitude){
        this.longitude=longitude;
    }
    public float getRadius(){return radius;}
    public void setRadius(float radius){this.radius=radius;}
}
