package com.example.controller;

import com.example.entity.Employee;
import com.example.model.EmployeeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;


@WebServlet("/EmployeeController/*")
public class EmployeeController extends HttpServlet {
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String fullname = request.getParameter("fullname");
        String birthdayStr = request.getParameter("birthday");
        String address = request.getParameter("address");
        String position = request.getParameter("position");
        String department = request.getParameter("department");

        Date birthday = Date.valueOf(birthdayStr);

        Employee employee = new Employee(fullname, birthday, address, position, department);
        employeeDAO.addEmployee(employee);

        response.sendRedirect("EmployeeController?action=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("list".equals(action)) {
            listEmployees(request, response);
        } else {
            request.getRequestDispatcher("EmployeeController?action=employee.jsp").forward(request, response);
        }
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = employeeDAO.getAllEmployees();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}