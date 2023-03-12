package kz.timka.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {


//    @GetMapping("/hello")
//    public String helloPage(HttpServletRequest request) {
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        System.out.println(request.getMethod());
//        System.out.println(request.getRequestURI());
//        System.out.println(request.getContextPath());
//        System.out.println(request.getRemoteUser());
//        request.getRequestedSessionId();
//        System.out.println(name);
//        System.out.println(surname);
//        return "first/hello";
//    }

    @GetMapping("/hello")
    public String helloPage(@RequestParam(required = false) String name,
                            @RequestParam(required = false) String surname,
                            Model model) {
        model.addAttribute("message", "Hello " + name + " " + surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage() {
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam int a, @RequestParam int b,
                             @RequestParam String action, Model model) {
        double result = 0.0;
        String errMsg = null;
        switch (action) {
            case "plus":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / (double) b;
                break;
            default:
                errMsg = "Unknown action";
        }
        if(errMsg == null) {
            model.addAttribute("result", result);
            model.addAttribute("a", a);
            model.addAttribute("b", b);
            model.addAttribute("action", action);
        } else {
            model.addAttribute("result", errMsg);
        }


        return "first/calculator";
    }
}
