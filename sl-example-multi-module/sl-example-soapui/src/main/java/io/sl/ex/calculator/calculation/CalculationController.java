package io.sl.ex.calculator.calculation;

import io.sl.ex.calculator.calculation.model.BinaryOperationRequest;
import io.sl.ex.calculator.calculation.model.BinaryOperator;
import io.sl.ex.calculator.calculation.model.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
public class CalculationController {
    private final CalculationService calculationService;

    @Autowired
    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping("addition")
    public ResponseBody add(@RequestParam double leftOperand,
                            @RequestParam double rightOperand) {
        return new ResponseBody(calculationService.add(leftOperand, rightOperand));
    }

    @GetMapping("subtraction")
    public ResponseBody subtract(@RequestParam double leftOperand,
                                 @RequestParam double rightOperand) {
        return new ResponseBody(calculationService.subtract(leftOperand, rightOperand));
    }

    @GetMapping("multiplication")
    public ResponseBody multiply(@RequestParam double leftOperand,
                                 @RequestParam double rightOperand) {
        return new ResponseBody(calculationService.multiply(leftOperand, rightOperand));
    }

    @GetMapping("division")
    public ResponseBody divide(@RequestParam double leftOperand,
                               @RequestParam double rightOperand) {
        return new ResponseBody(calculationService.divide(leftOperand, rightOperand));
    }

    @GetMapping("sqrt")
    public double sqrt(@RequestParam double operand) {
        return calculationService.squareRoot(operand);
    }

    @PostMapping("calculation")
    public ResponseBody calculation(@RequestBody BinaryOperationRequest request) {
        final BinaryOperator<Double> operator = request.getBinaryOperation().getOperator(calculationService);
        return new ResponseBody(operator.apply(request.getLeftOperand(), request.getRightOperand()));
    }
}
