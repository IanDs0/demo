package com.ian.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ian.demo.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {
    
    private boolean isNumber(String number){
        try{
            if(number == null) return false;
            return true;
        }catch(Exception e){
            return false;
        }
    }

    private double convertToDouble(String number){
        String stringNumber = number.replace(",", ".");
        return Double.parseDouble(stringNumber);
    }

    @RequestMapping(value = "/math/{op}/{num}/{num2}", method = RequestMethod.GET)
    public Double opration(
            @PathVariable(value = "op") String op,
            @PathVariable(value = "num") String num,
            @PathVariable(value = "num2") String num2
        ) throws Exception {
        
        if(!isNumber(num) || !isNumber(num2)){
            throw new UnsupportedMathOperationException("Please set a numeric value");   
        }

        double result = 0D;

        switch (op) {
            case "+":
                result = convertToDouble(num) + convertToDouble(num2);
                break;
            
            case "-":
                result = convertToDouble(num) - convertToDouble(num2);
                break;

            case "*":
                result = convertToDouble(num) * convertToDouble(num2);
                break;

            case "pow":
                result = Math.pow(convertToDouble(num), convertToDouble(num2));
                break;

            default:
                break;
        }

        return result;
    }

}
