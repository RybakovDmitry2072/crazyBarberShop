package org.example.crazybarbershop.servlets.adminPanel;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.crazybarbershop.models.TimeSlot;
import org.example.crazybarbershop.services.impl.TimeSlotServiceImpl;
import org.example.crazybarbershop.services.interfaces.TimeSlotService;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;

@MultipartConfig
@WebServlet("/admin/timeSlots")
public class TimeSlotServlet extends HttpServlet {

    private TimeSlotService timeSlotService;
    private static final Logger logger = Logger.getLogger(TimeSlotServlet.class.getName());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        timeSlotService = (TimeSlotServiceImpl) getServletContext().getAttribute("timeSlotService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TimeSlot> timeSlots = timeSlotService.getAllTimeSlots();
        req.setAttribute("timeSlots", timeSlots);
        req.getRequestDispatcher("/WEB-INF/view/adminView/manageTimeSlots.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        logger.info("Action: " + action);

        switch (action) {
            case "addTimeSlot":
                try {
                    int employeeId = Integer.parseInt(req.getParameter("employeeId"));
                    Timestamp startTime = Timestamp.valueOf(req.getParameter("startTime"));
                    Timestamp endTime = Timestamp.valueOf(req.getParameter("endTime"));
                    boolean isBooked = Boolean.parseBoolean(req.getParameter("isBooked"));

                    TimeSlot timeSlot = new TimeSlot();
                    timeSlot.setEmployeeId(employeeId);
                    timeSlot.setStartTime(startTime.toLocalDateTime());
                    timeSlot.setEndTime(endTime.toLocalDateTime());
                    timeSlot.setBooked(isBooked);

                    timeSlotService.saveTimeSlot(timeSlot);
                    resp.setStatus(HttpServletResponse.SC_OK);
                } catch (Exception e) {
                    logger.severe("Error adding time slot: " + e.getMessage());
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding time slot");
                }
                return;

            case "updateTimeSlot":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    int employeeId = Integer.parseInt(req.getParameter("employeeId"));
                    Timestamp startTime = Timestamp.valueOf(req.getParameter("startTime"));
                    Timestamp endTime = Timestamp.valueOf(req.getParameter("endTime"));
                    boolean isBooked = Boolean.parseBoolean(req.getParameter("isBooked"));

                    TimeSlot timeSlotUpdate = TimeSlot.builder()
                            .id(id)
                            .endTime(endTime.toLocalDateTime())
                            .isBooked(isBooked)
                            .startTime(startTime.toLocalDateTime())
                            .employeeId(employeeId)
                            .build();

                    timeSlotService.update(timeSlotUpdate);
                    resp.setStatus(HttpServletResponse.SC_OK);
                } catch (Exception e) {
                    logger.severe("Error updating time slot: " + e.getMessage());
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating time slot");
                }
                return;

            case "deleteTimeSlot":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    timeSlotService.delete(id);
                    resp.setStatus(HttpServletResponse.SC_OK);
                } catch (Exception e) {
                    logger.severe("Error deleting time slot: " + e.getMessage());
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting time slot");
                }
                return;

            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
        }
    }
}
