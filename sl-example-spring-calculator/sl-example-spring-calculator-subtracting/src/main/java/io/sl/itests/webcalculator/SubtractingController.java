package io.sl.itests.webcalculator;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubtractingController {

    private Calculator calculator = new Calculator();

    @RequestMapping("/subtract/{a}/{b}")
    public int subtract(@PathVariable("a") int a, @PathVariable("b") int b) {
        System.out.println();
        return calculator.subtract(a, b);
    }

}
