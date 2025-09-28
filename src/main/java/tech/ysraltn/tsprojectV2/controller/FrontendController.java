package tech.ysraltn.tsprojectV2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    // Public route’lar
    @RequestMapping({ "/", "/login", "/register" })
    public String publicRoutes() {
        return "redirect:/index.html";
    }

    // Protected sayfalar da index.html’e yönlendiriliyor
    // Koruma işini frontend (JWT kontrolü) yapacak
    // @RequestMapping({
    //         "/profile",
    //         "/dashboard",
    //         "/users",
    //         "/assignments",
    //         "/institutions",
    //         "/my-products",
    //         "/products",
    //         "/cycles"
    // })
    // public String protectedRoutes() {
    //     return "redirect:/index.html";
    // }

    // // Fallback → bilinmeyen path’ler
    // @RequestMapping("/{path:^(?!api|swagger|v3).*$}")
    // public String fallback() {
    //     return "redirect:/index.html";
    // }
}
