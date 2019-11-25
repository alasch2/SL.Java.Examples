package sl.ex;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.sl.ex.spring.cloud.producer.SpringCloudContractProducerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringCloudContractProducerApplication.class)
public class SpringContextIntegrationTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}
