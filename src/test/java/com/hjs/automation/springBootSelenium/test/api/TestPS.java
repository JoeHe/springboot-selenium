package com.hjs.automation.springBootSelenium.test.api;

import com.hjs.automation.springBootSelenium.base.APIAbstractTest;
import com.hjs.automation.springBootSelenium.utils.YmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;


@Slf4j
public class TestPS extends APIAbstractTest {

//    @Autowired
//    Weather weather;
//

    @Test
    public void testReadYMLConfig() throws IOException {
        log.info("test read yml weather configs.");
        Map<String, Object> aws = (Map<String, Object>) YmlUtils.getFromYml(new File("src/test/resources/test.yml"), "aws");
        Map<String, Object> config = (Map<String, Object>) aws.get("config");
        String region = (String) config.get("region");
        Integer concurrent = (Integer) config.get("concurrent");
        log.info("got region:{}, concurrent:{}", region, concurrent);
    }

}
