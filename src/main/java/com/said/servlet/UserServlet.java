package com.said.servlet;

import com.said.model.User;
import com.said.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = service.getAllUsers();
   //     List<User> users1 = new ArrayList<>();
/*        users1.add(new User(1, "name", "lastname", (byte)4));
        users1.add(new User(2, "name2", "lastname2", (byte)5));
        users1.add(new User(3, "name3", "lastname3", (byte)6));
        System.out.println(users1.size());*/
        req.setAttribute("list", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String lastName = req.getParameter("lastname");
        Byte age = Byte.parseByte(req.getParameter("age"));
        User user = new User(name, lastName, age);
        if(name == null || lastName == null || age == null) {
            resp.setContentType("text/html; charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else if(age > 150) {
            resp.setContentType("text/html; charset=utf-8");
            //resp.getWriter().println("Вы ввели не корректную дату рождения.");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        else{
            service.addUser(user);
            resp.sendRedirect("/users");
            resp.setStatus(HttpServletResponse.SC_OK);
        }

    }
}
