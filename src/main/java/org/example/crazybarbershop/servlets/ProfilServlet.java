package org.example.crazybarbershop.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.crazybarbershop.Exceptions.CancelAppointmentException;
import org.example.crazybarbershop.FactoryDto.AppointmentDtoFactory;
import org.example.crazybarbershop.FactoryDto.UserDtoFactory;
import org.example.crazybarbershop.dto.*;
import org.example.crazybarbershop.models.Appointment;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.services.impl.*;
import org.example.crazybarbershop.services.interfaces.*;
import org.example.crazybarbershop.util.JSPHelper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@MultipartConfig
@WebServlet("/profile")
public class ProfilServlet extends HttpServlet {

    private AppointmentService appointmentService;

    private UserService userService;

    private CategoryService categoryService;

    private EmployeeService employeeService;

    private TimeSlotService timeSlotService;

    private UploadImageService uploadImageService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        appointmentService = (AppointmentServiceImpl) getServletContext().getAttribute("appointmentService");
        categoryService = (CategoryServiceImpl) getServletContext().getAttribute("categoryService");
        employeeService = (EmployeeServiceImpl) getServletContext().getAttribute("employeeService");
        timeSlotService = (TimeSlotServiceImpl) getServletContext().getAttribute("timeSlotService");
        userService = (UserServiceImpl) getServletContext().getAttribute("userService");
        uploadImageService = new UploadImageServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session =  req.getSession();
        User user = (User) session.getAttribute("user");

        UserDto userDto = UserDtoFactory.factory(user);
        req.setAttribute("user", userDto);

        List<Appointment> appointmentList = appointmentService.getAppointmentsByUserUd(user.getId());


        List<AppointmentDto> appointmentDtoListCompleted = appointmentList.stream()
                .filter(appointment -> !appointmentService.isAppointmentCompleted(appointment))
                .map(appointment -> AppointmentDtoFactory.factoryDto(
                        appointment.getId(),
                        userDto.getName() + " " + userDto.getSurname(),
                        categoryService.getCategoryById(appointment.getCategoryId()).getName(),
                        employeeService.getEmployeeByid(appointment.getEmployeeId()).getName() + " " + employeeService.getEmployeeByid(appointment.getEmployeeId()).getSurname(),
                        timeSlotService.getTimeSlotById(appointment.getTimeSlotId()).getStartTime(),
                        appointment.getStatus()
                ))
                .collect(Collectors.toList());

        List<AppointmentDto> appointmentDtoIsNotCompleted = appointmentList.stream()
                .filter(appointment -> appointmentService.isAppointmentCompleted(appointment))
                .map(appointment -> AppointmentDtoFactory.factoryDto(
                        appointment.getId(),
                        userDto.getName() + " " + userDto.getSurname(),
                        categoryService.getCategoryById(appointment.getCategoryId()).getName(),
                        employeeService.getEmployeeByid(appointment.getEmployeeId()).getName() +
                                      " " + employeeService.getEmployeeByid(appointment.getEmployeeId()).getSurname(),
                        timeSlotService.getTimeSlotById(appointment.getTimeSlotId()).getStartTime(),
                        appointment.getStatus()
                ))
                .collect(Collectors.toList());


        req.setAttribute("appointmentDtoCompleted", appointmentDtoListCompleted);
        req.setAttribute("appointmentDtoIsNotCompleted", appointmentDtoIsNotCompleted);

        req.getRequestDispatcher(JSPHelper.getPath("profil")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        switch (action){

            case "addImage":
                String dir = req.getParameter("dir");
                System.out.println("Directory parameter: " + dir); // Логирование
                if (dir == null || dir.isEmpty()) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Directory parameter is missing");
                    return;
                }

                String uploadDir = "/Users/rybakovdmitry/crazyBarberShop/src/main/webapp/static/" + dir;
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                System.out.println("Upload directory: " + uploadDir); // Логирование

                try {
                    Part filePart = req.getPart("file");
                    String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
                    uploadImageService.saveImageToStorage(filePart, uploadDir, uniqueFileName);
                    HttpSession httpSession = req.getSession();
                    User user = (User) httpSession.getAttribute("user");
                    user.setUrlImg(uploadDir + "/" + uniqueFileName);
                    userService.updateUser(user);
                    resp.sendRedirect(req.getContextPath() + "/profile");

                } catch (Exception e) {
                    e.printStackTrace(); // Логирование ошибки
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error uploading photo: " + e.getMessage());
                }
                return;

            case "cancelAppointment":
                int appointmentId = Integer.parseInt(req.getParameter("appointmentId"));
                Appointment appointment = appointmentService.getAppointmentById(appointmentId);
                TimeSlotDto timeSlot = timeSlotService.getTimeSlotById(appointment.getTimeSlotId());


                try {
                    appointmentService.cancelAppointment(appointmentId);
                    timeSlotService.updateCategoryFlag(timeSlot.getId(), false);
                    doGet(req, resp);
                } catch (CancelAppointmentException e){
                    req.setAttribute("CancelAppointmentException", e.getMessage());
                }
        }
    }
}
