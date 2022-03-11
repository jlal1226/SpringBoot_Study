package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) { // 스프링이 모델을 만들어서 넣어줌
        model.addAttribute("data", "hello!");
        return "hello";
    }
    // MVC
    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name); // key, value
        return "hello-template"; // hello-template.html로 감
    }
    // API 방식
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name")String name) {
        return "hello" + name; // ex) "hello spring" -> 문자 그대로 내려감
    }
    // API 방식 -> JSON 방식
    @GetMapping("hello-api")
    @ResponseBody // pdf 파일 참조
    public Hello helloApi(@RequestParam("name")String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // JSON으로 반환함
    }
    // java bin 표준 양식
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
