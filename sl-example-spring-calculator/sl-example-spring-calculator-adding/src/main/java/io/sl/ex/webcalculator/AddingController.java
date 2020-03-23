package io.sl.ex.webcalculator;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.sl.ex.webcalculator.Calculator;

@RestController
public class AddingController {

    private Calculator calculator = new Calculator();

    @RequestMapping("/add/{a}/{b}")
    public int add(@PathVariable("a") int a, @PathVariable("b") int b) {
        return calculator.add(a, b);
    }

}
