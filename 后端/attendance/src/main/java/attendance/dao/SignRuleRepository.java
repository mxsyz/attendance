package attendance.dao;

import attendance.entity.SignRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SignRuleRepository extends JpaRepository<SignRule,Integer> {
    public List<SignRule> findAll();
}
