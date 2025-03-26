package JavaLLM.inusurance.SimplifyMoney.Repositories;

import JavaLLM.inusurance.SimplifyMoney.Enitity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {
}
