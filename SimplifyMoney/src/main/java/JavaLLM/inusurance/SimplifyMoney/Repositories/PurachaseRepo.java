package JavaLLM.inusurance.SimplifyMoney.Repositories;

import JavaLLM.inusurance.SimplifyMoney.Enitity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurachaseRepo extends JpaRepository<Purchase,Long> {
}
