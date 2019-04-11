package attendance.dao;

import attendance.entity.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalInfoRepository extends JpaRepository<PersonalInfo,Integer> {
    public PersonalInfo findByJobNumber(int jonNumber);
}
