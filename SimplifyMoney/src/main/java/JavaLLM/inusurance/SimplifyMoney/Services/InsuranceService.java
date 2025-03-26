package JavaLLM.inusurance.SimplifyMoney.Services;

import JavaLLM.inusurance.SimplifyMoney.Enitity.InsuranceEntity;
import JavaLLM.inusurance.SimplifyMoney.Repositories.InsuranceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class InsuranceService {
    @Autowired
    private InsuranceRepo insuranceRepo;

    @Transactional
    public InsuranceEntity saveInsurance(InsuranceEntity insurance) {
        return insuranceRepo.save(insurance);
    }

    @Transactional(readOnly = true)
    public List<InsuranceEntity> getAllInsurances() {
        return insuranceRepo.findAll();
    }

    @Transactional(readOnly = true)
    public List<InsuranceEntity> recommendInsurance(int age, double income, String gender) {
        List<String> genderCriteria = Arrays.asList(gender, "Any");
        return insuranceRepo.findByAgeLimitLessThanEqualAndAgeLimitGreaterThanEqualAndMinincomeGreaterThanEqualAndMinincomeLessThanEqualAndGenderIn(
                age, age, income, income, genderCriteria
        );
    }
}