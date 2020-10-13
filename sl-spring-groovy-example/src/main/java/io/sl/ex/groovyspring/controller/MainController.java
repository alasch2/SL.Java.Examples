package io.sl.ex.groovyspring.controller;

import io.sl.ex.groovyspring.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @Autowired
    private DepartmentService department;

    @RequestMapping(value = "/")
    public String handleRequest(Model model) {
        String message = "Person List:";
        model.addAttribute("message", message);
        model.addAttribute("persons", department.getPersons());
        return "index";
    }

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "HELLO !!!");
        return "index";
    }

    @RequestMapping(value = "/stop")
    public String stop(Model model) {
        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Going to sleep for 2 sec before exit...");
                    Thread.sleep(2000);
                }
                catch(Exception e) {
                }
                System.out.println("shutting down now !!!");
                System.exit(0);
            }
        });
        newThread.start();
        model.addAttribute("message", "STOPPED");
        return "index";
    }

}
