package pl.coderslab.beans;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

@Controller
public class CookieController {

    @RequestMapping("/setcookie/{value1}/{value2}")
    @ResponseBody
    public String setCookie(@PathVariable String value1, @PathVariable String value2, HttpServletResponse response) {
        Cookie cookie1 = new Cookie("cookie1", value1);
        Cookie cookie2 = new Cookie("cookie2", value2);

        cookie1.setPath("/");
        cookie2.setPath("/");

        response.addCookie(cookie1);
        response.addCookie(cookie2);

        return "Cookies set";
    }

    @RequestMapping("/getcookies")
    @ResponseBody
    public String getCookies(HttpServletRequest request,
                             @CookieValue("cookie1") String value,
                             @CookieValue("cookie2") String value2) {

        Cookie cookie1 = WebUtils.getCookie(request, "cookie1");
        Cookie cookie2 = WebUtils.getCookie(request, "cookie2");

        return "cookie1 " + cookie1.getValue() + "</br>" + "cookie2 " + cookie2.getValue() + String.format(
                "Andotacja value1 " + value + " value2" + value2);
    }
}
