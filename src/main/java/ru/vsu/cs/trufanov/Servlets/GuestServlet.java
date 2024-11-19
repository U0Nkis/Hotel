package ru.vsu.cs.trufanov.Servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.vsu.cs.trufanov.Config.DatabaseConfig;
import ru.vsu.cs.trufanov.Models.Guest;
import ru.vsu.cs.trufanov.Repositories.impl.GuestRepositoryImpl;
import ru.vsu.cs.trufanov.Services.GuestService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/guests")
public class GuestServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private GuestService guestService;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            guestService = new GuestService(new GuestRepositoryImpl(DatabaseConfig.getConnection()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getServletContext().setAttribute("guestService", guestService);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        guestService = (GuestService) getServletContext().getAttribute("guestService");
        if (guestService == null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Guest Service not found");
            return;
        }

        try {
            Guest guest = objectMapper.readValue(req.getInputStream(), Guest.class);
            Guest createdGuest = guestService.addGuest(guest);

            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_CREATED);
            objectMapper.writeValue(resp.getOutputStream(), createdGuest);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error creating guest: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GuestService guestService = (GuestService) getServletContext().getAttribute("guestService");
        if (guestService == null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Guest service not found");
            return;
        }

        String pathInfo = req.getPathInfo();
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                resp.setContentType("application/json");
                objectMapper.writeValue(resp.getWriter(), guestService.getAllGuests());
            } else {
                Long guestId = parseGuestId(pathInfo, resp);
                if (guestId == null) {
                    return;
                }

                Optional<Guest> guest = guestService.findGuestById(guestId);
                if (guest.isPresent()) {
                    resp.setContentType("application/json");
                    objectMapper.writeValue(resp.getWriter(), guest.get());
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("Guest not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error fetching guest: " + e.getMessage());
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        guestService = (GuestService) getServletContext().getAttribute("guestService");
        if (guestService == null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Guest Service not found");
            return;
        }

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Guest ID is required");
            return;
        }

        try {
            Long guestId = parseGuestId(pathInfo, resp);
            if (guestId == null) {
                return;
            }

            guestService.removeGuest(guestId);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error deleting guest: " + e.getMessage());
        }
    }

    private Long parseGuestId(String pathInfo, HttpServletResponse resp) throws IOException {
        try {
            return Long.parseLong(pathInfo.substring(1));
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid guest ID format: " + pathInfo.substring(1));
            return null;
        }
    }
}
