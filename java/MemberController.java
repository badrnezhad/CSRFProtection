package nws.controllers;

import hb.fwu.HttpUtility;
import hb.fwu.IPUtility;
import hb.fwu.SecurityUtility;
import hb.fwu.csrfHelper;
import nws.config.AppContext;
import nws.entities.ActionLogging;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Scope(value = "request")
public class MemberController extends _BaseController {

    //region variables
    private MemberService service = AppContext.getMemberService();
    private MemberRulesService memberRulesService = AppContext.getMemberRulesService();
    private ActionLoggingService actionLoggingService;
    //private NewsService newsService = AppContext.getNewsService();
    private String pageId = PAGES.INDEX.toString();
    private List<NewsDoc> newsList = null;
    private LOGGINGSTATUS LogStatus;
    private LOGGINGACTIONS LogAction;
    private String csrfToken = "";
    private String formCSRFToken = "";

    //endregion

    //region actions
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String Login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        csrfToken = csrfHelper.Generate();
        request.setAttribute("csrfToken", csrfToken);
        return "Home";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Members member) throws Exception {
        try {

           formCSRFToken = (String) request.getParameter("formCSRFToken");

            //region check csrf token
            if (!csrfHelper.isValid(formCSRFToken)) {
                LogStatus = LOGGINGSTATUS.FAILED;
                ALERT = "CSRF Token Invalid";
            }
            //endregion
            
            //Do Something
         
        } catch (Exception ex) {
        }

        return "Home";
    }

 
    //endregion

  
}
