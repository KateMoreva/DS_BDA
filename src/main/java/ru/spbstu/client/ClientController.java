package java.ru.spbstu.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {
    @GetMapping(path = "/analyse-it-market")
    public String getWelcomePage(Model model) {
        return "index";
    }
}
