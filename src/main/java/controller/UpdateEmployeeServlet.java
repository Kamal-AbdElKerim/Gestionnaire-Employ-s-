package controller;

import Service.EmployeeService;
import model.Employee;
import utility.Validator.EmployeeValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class UpdateEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService = new EmployeeService();
    private String employeeIdStr;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String department = request.getParameter("department");
        String position = request.getParameter("position");

        Employee employee = new Employee(name, email, phone, department, position, LocalDateTime.now());

        // Validate the employee data
        EmployeeValidator validator = new EmployeeValidator();
        String validationError = validator.validate(employee);

        if (validationError != null) {
            request.setAttribute("error", validationError);
            // Set the input values to be retained
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("department", department);
            request.setAttribute("position", position);

            request.getRequestDispatcher("/Employee/updateEmployee.jsp").forward(request, response);

            return;
        }
        Long employeeId = Long.parseLong(employeeIdStr);
        if (employeeId > 0) {
            try {

                System.out.println(employeeId + "employeeId");
                employeeService.updateEmployee(employee, employeeId);
                // response.sendRedirect("addEmployee");
                request.setAttribute("message", "employee " + name + " updated successfully");
                request.setAttribute("color", "#2E8B57");
                request.setAttribute("isupdating", true);
                request.getRequestDispatcher("/addEmployee").forward(request, response);
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("error", "Invalid employee ID!");
                System.out.println("Invalid employee ID!");
            }
        } else {
            request.getSession().setAttribute("error", "Employee ID is required!");
            System.out.println("Employee ID is required!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        employeeIdStr = request.getParameter("employeeId");

        if (employeeIdStr != null && !employeeIdStr.isEmpty()) {
            try {
                Long employeeId = Long.parseLong(employeeIdStr); // Parse as Long
                Employee employee = employeeService.getEmployeeById(employeeId);
                request.setAttribute("employee", employee);
                request.getRequestDispatcher("/Employee/updateEmployee.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("error", "Invalid employee ID!");
                response.sendRedirect("AfficheEmployeeServlet");
            }
        } else {
            request.getSession().setAttribute("error", "Employee ID is required!");
            response.sendRedirect("AfficheEmployeeServlet");
        }
    }
}
