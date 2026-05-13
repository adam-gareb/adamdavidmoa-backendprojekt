package se.yrgo.spring.servicesTests;

import org.junit.jupiter.api.extension.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.transaction.annotation.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "/other-tiers.xml", "/datasource-test.xml" })
@Transactional
public class UserServiceTest {
    
}
