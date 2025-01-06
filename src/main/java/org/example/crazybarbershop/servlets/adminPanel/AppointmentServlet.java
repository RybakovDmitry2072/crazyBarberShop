package org.example.crazybarbershop.servlets.adminPanel;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crazybarbershop.models.Appointment;
import org.example.crazybarbershop.services.impl.AppointmentServiceImpl;
import org.example.crazybarbershop.services.interfaces.AppointmentService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@MultipartConfig
@WebServlet("/admin/appointments")
public class AppointmentServlet extends HttpServlet {

    private AppointmentService appointmentService;
    private static final Logger logger = Logger.getLogger(AppointmentServlet.class.getName());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        appointmentService = (AppointmentServiceImpl) getServletContext().getAttribute("appointmentService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        req.setAttribute("appointments", appointments);
        req.getRequestDispatcher("/WEB-INF/view/adminView/manageAppointments.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        logger.info("Action: " + action);

        switch (action) {
            case "addAppointment":
                addAppointment(req, resp);
                break;
            case "updateAppointment":
                updateAppointment(req, resp);
                break;
            case "deleteAppointment":
                deleteAppointment(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
        }
    }

    private void addAppointment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int user_id = Integer.parseInt(req.getParameter("user_id"));
            int category_id = Integer.parseInt(req.getParameter("category_id"));
            int employee_id = Integer.parseInt(req.getParameter("employee_id"));
            int time_slot_id = Integer.parseInt(req.getParameter("time_slot_id"));


            appointmentService.bookAppointment(category_id, employee_id, time_slot_id, user_id);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            logger.severe("Error adding appointment: " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: " + e.getMessage());
        }
    }

    private void updateAppointment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            int user_id = Integer.parseInt(req.getParameter("user_id"));
            int category_id = Integer.parseInt(req.getParameter("category_id"));
            int employee_id = Integer.parseInt(req.getParameter("employee_id"));
            int time_slot_id = Integer.parseInt(req.getParameter("time_slot_id"));
            String status = req.getParameter("status");

            Appointment appointment = new Appointment();
            appointment.setId(id);
            appointment.setUserId(user_id);
            appointment.setCategoryId(category_id);
            appointment.setEmployeeId(employee_id);
            appointment.setTimeSlotId(time_slot_id);
            appointment.setStatus(status);

            appointmentService.update(appointment);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            logger.severe("Error updating appointment: " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: " + e.getMessage());
        }
    }

    private void deleteAppointment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            appointmentService.delete(id);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            logger.severe("Error deleting appointment: " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: " + e.getMessage());
        }
    }
}
