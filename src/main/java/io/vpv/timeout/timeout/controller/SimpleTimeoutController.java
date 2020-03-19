package io.vpv.timeout.timeout.controller;

import io.vpv.timeout.timeout.model.TimeoutMessage;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SimpleTimeoutController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @GetMapping("/api/timeout/{timeout}")
    public ResponseEntity<TimeoutMessage> timeout(@PathVariable Long timeout) {
        logger.info("Received request with timeout for {}", timeout);
        TimeoutMessage message = new TimeoutMessage(timeout, "Called /api/timout endpoint successfully");
        try {
            Thread.sleep(timeout);

        } catch (InterruptedException e) {
            logger.error("InterruptedException triggered");
            logger.info("Response sent back to user {}", message);
            message.setMessage("Called /api/timout failed with InterruptedException");
            return new ResponseEntity<>(message, HttpStatus.BAD_GATEWAY);

        }
        logger.info("Response sent back to user {}", message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
