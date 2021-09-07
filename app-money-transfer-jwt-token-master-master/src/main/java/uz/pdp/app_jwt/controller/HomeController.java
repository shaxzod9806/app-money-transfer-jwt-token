package uz.pdp.app_jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/home")
public class HomeController {

    @GetMapping
    public String getMessage() {
        return "Hello home page";
    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello";
    }
}
