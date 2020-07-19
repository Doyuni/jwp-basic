package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.JdbcQuestionDao;
import core.annotation.Controller;
import core.annotation.RequestMapping;
import core.mvc.ModelAndView;
import core.nmvc.AbstractNewController;

@Controller
public class HomeController extends AbstractNewController {
    private JdbcQuestionDao jdbcQuestionDao = JdbcQuestionDao.getInstance();

    @RequestMapping("/")
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return jspView("home.jsp").addObject("questions", jdbcQuestionDao.findAll());
    }
}
