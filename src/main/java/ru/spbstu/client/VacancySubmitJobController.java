package ru.spbstu.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ru.spbstu.storage.service.VacancyService;

@Controller
public class VacancySubmitJobController {
    private final VacancyService vacancyService;

    @Autowired
    public VacancySubmitJobController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @RequestMapping(value = "/submitLoadThisMonthTask", method = RequestMethod.POST)
    public String submitLoadThisMonthTask(Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Success");
        redirectAttributes.addFlashAttribute("alertClass", "success");
        return "redirect:/analyse-it-market/general";
    }

    @RequestMapping(value = "/submitLoadThisYearTask", method = RequestMethod.POST)
    public String submitLoadThisYearTask(Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Success");
        redirectAttributes.addFlashAttribute("alertClass", "success");
//        FetchIdsRangeTaskRequest request = new FetchIdsRangeTaskRequest(String.valueOf(50090178), String.valueOf(56090000));
//        vacancyService.submit(request);
//        FetchIdsRangeTaskRequest request = new FetchIdsRangeTaskRequest(String.valueOf(66090000), String.valueOf(85000000));
//        vacancyService.submit(request);
        return "redirect:/analyse-it-market/general";
    }

    @RequestMapping(value = "/submitLoadAllTimeTask", method = RequestMethod.POST)
    public String submitLoadAllTimeTask(Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Success");
        redirectAttributes.addFlashAttribute("alertClass", "success");
//        FetchIdsRangeTaskRequest request = new FetchIdsRangeTaskRequest(String.valueOf(0), String.valueOf(Integer.MAX_VALUE));
//        vacancyService.submit(request);
        return "redirect:/analyse-it-market/general";
    }
}
