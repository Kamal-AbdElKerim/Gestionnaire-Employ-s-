package controller;

import Service.EmployeeService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService = new EmployeeService();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long employeeIdStr = Long.parseLong(request.getParameter("employeeId"));

        if (employeeIdStr > 0) {
            try {
                // Call the service to delete the employee
                employeeService.deleteEmployee(employeeIdStr);

                request.setAttribute("message", "Employee deleted successfully!");
                request.setAttribute("color", "#7FFF00");

            } catch (NumberFormatException e) {
                // Handle the case where the ID is not a valid integer

                request.setAttribute("message", "Invalid employee ID!");
                request.setAttribute("color", "red");
            }
        } else {

            request.setAttribute("message", "Employee ID is required!");
            request.setAttribute("color", "red");
        }
        request.setAttribute("isupdating", true);
        request.getRequestDispatcher("/addEmployee").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
