package web.servlet;

import db.POJO.User;
import org.apache.log4j.Logger;
import web.services.AllLotsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AllLots extends HttpServlet{
    private static final Logger log = Logger.getLogger(AllLots.class);
private AllLotsService allLotsService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("CP1251");
        req.setCharacterEncoding("UTF-8");

        allLotsService = new AllLotsService();

        req.setAttribute("lots",allLotsService.getAllLots());
        req.getRequestDispatcher("/alllots.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
//        allLotsService = new AllLotsService();
//        req.setAttribute("lots",allLotsService.getAllLots());
//        req.getRequestDispatcher("/alllots.jsp").forward(req,resp);
    }
}
