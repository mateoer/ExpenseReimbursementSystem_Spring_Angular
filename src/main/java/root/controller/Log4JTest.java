package root.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
//@CrossOrigin("*")
//@CrossOrigin("http://localhost:4200")
@CrossOrigin("https://localhost:4200")
public class Log4JTest {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Log4JTest.class);
    
    @RequestMapping("/logback")
    public String doStuff(final String value) {
        LOGGER.trace("doStuff needed more information - {}", value);
        LOGGER.debug("doStuff needed to debug - {}", value);
        LOGGER.info("doStuff took input - {}", value);
        LOGGER.warn("doStuff needed to warn - {}", value);
        LOGGER.error("doStuff encountered an error with value - {}", value);
        return "hello from the other side";
    }

}
