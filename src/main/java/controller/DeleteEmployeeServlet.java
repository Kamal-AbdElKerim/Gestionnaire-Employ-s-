package controller;

import Service.EmployeeService;
import model.Employee;
import utility.EmployeeValidator;
import utility.InputClearer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


public class DeleteEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService = new EmployeeService();


    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long employeeIdStr = Long.parseLong(request.getParameter("employeeId"));


        if (employeeIdStr > 0) {
            try {
                // Call the service to delete the employee
                employeeService.deleteEmployee(employeeIdStr);

                // Set a success message
                request.getSession().setAttribute("message", "Employee deleted successfully!");

            } catch (NumberFormatException e) {
                // Handle the case where the ID is not a valid integer
                request.getSession().setAttribute("error", "Invalid employee ID!");
            }
        } else {
            request.getSession().setAttribute("error", "Employee ID is required!");
        }

        response.sendRedirect("addEmployee");
    }




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

}}

