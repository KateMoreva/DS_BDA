package ru.spbstu.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/analyse-it-market")
public class ClientController {
    @GetMapping(path = "/general")
    public String getWelcomePage(Model model) {

        return "general";
    }
    @GetMapping(path = "/tech")
    public String getTechPage(Model model) {
        return "tech";
    }
    @GetMapping(path = "/employers")
    public String getEmployersPage(Model model) {
        return "employers";
    }
}
