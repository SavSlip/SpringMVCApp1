package ruspringcourse.config.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FirstController {
    String resultCalk;

    @GetMapping("/hello")
    public String hello(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        System.out.println("Hello, " + name + " " + surname);

        model.addAttribute("message", "Hello, " + name + " " + surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbye(@RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "surname", required = false) String surname) {
        System.out.println("Goodbye, " + name + " " + surname);
        return "first/goodbye";
    }

    @GetMapping("/calk/result")
    public String calkResult(HttpServletRequest request, Model model) {
        int value1 = Integer.parseInt(request.getParameter("value1"));
        int value2 = Integer.parseInt(request.getParameter("value2"));
        String operation = request.getParameter("operation");
        try {
            switch (operation) {
                case "add":
                    resultCalk = String.valueOf(value1 + value2);
                    break;
                case "subtract":
                    resultCalk = String.valueOf(value1 - value2);
                    break;
                case "multiply":
                    resultCalk = String.valueOf(value1 * value2);
                    break;
                case "divide":
                    resultCalk = String.valueOf(value1 / value2);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + operation);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("TEST");
        }
        System.out.println(resultCalk);

        model.addAttribute("result", resultCalk);

        return "first/calkResult";
    }

    @GetMapping("/calk")
    public String clkCall() {
        return "first/calk";
    }
}
