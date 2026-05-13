package se.yrgo.spring.servicesTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import jakarta.transaction.Transactional;
import se.yrgo.spring.services.loan.LoanService;

// Adam
// Integration tests for LoanService to test the methods inside it

@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "/other-tiers.xml", "/datasource-test.xml" })
@Transactional
public class LoanServiceTest {
    @Autowired
    private LoanService loans;

    @Test
    public void 

}
