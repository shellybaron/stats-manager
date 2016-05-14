package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.StatsService;

import java.io.IOException;


/**
 * @author sbaron
 * @since 5/14/16
 */
@Controller
@RequestMapping("/")
public class StatsController {

    @Autowired
    StatsService statsService;

    @RequestMapping(value="main", method = RequestMethod.GET)
    public String getPage(ModelMap model) throws IOException {

        model.addAttribute("message", "Started");

        return "main";
    }

    @RequestMapping(value="stats", method = RequestMethod.GET)
    public String getEventType(ModelMap model) throws IOException {

        model.addAttribute("typesStats", statsService.getCountByType());
        model.addAttribute("wordsStats", statsService.getCountByWord());

        return "stats";
    }

}
