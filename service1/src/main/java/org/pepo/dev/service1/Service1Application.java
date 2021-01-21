package org.pepo.dev.service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Service1Application {

    public static void main(String[] args) {
        SpringApplication.run(Service1Application.class, args);
    }

    @GetMapping(path = "/service1")
    public String service1() {
        return "Hello service 1";
    }

    @GetMapping(path = "/service-body")
    public String serviceBody(@RequestBody(required = false) final Foo foo) {
        return "Hello service body from " + foo.getName();
    }

}
