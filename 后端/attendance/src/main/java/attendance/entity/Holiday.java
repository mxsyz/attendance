package attendance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="holiday")
public class Holiday {
    @Id
    @Column(name="date",nullable=false)
    private Date date;
    public Date getDate(){
        return date;
    }
    public void setDate(Date date){
        this.date=date;
    }
}
