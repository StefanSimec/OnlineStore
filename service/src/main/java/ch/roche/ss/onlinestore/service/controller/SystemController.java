package ch.roche.ss.onlinestore.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController
{
    @GetMapping("/ping")
    public String ping()
    {
        return "pong";
    }
}
