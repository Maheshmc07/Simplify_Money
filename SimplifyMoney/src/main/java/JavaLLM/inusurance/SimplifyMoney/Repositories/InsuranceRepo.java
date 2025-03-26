package JavaLLM.inusurance.SimplifyMoney.Repositories;

import JavaLLM.inusurance.SimplifyMoney.Enitity.InsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceRepo extends JpaRepository<InsuranceEntity,Long> {
    List<InsuranceEntity> findByAgeLimitLessThanEqualAndAgeLimitGreaterThanEqualAndMinincomeGreaterThanEqualAndMinincomeLessThanEqualAndGenderIn(
            int minAge, int maxAge, double minIncome, double maxIncome, List<String> genders
    );
}
