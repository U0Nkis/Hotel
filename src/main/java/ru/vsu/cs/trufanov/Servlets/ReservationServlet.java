package ru.vsu.cs.trufanov.Servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.vsu.cs.trufanov.Config.DatabaseConfig;
import ru.vsu.cs.trufanov.Models.Reservation;
import ru.vsu.cs.trufanov.Repositories.impl.ReservationRepositoryImpl;
import ru.vsu.cs.trufanov.Services.ReservationService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/reservations")
public class ReservationServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private ReservationService reservationService;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            reservationService = new ReservationService(new ReservationRepositoryImpl(DatabaseConfig.getConnection()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getServletContext().setAttribute("reservationService", reservationService);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        reservationService = (ReservationService) getServletContext().getAttribute("reservationService");
        if (reservationService == null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Reservation Service not found");
            return;
        }

        try{
            Reservation reservation = objectMapper.readValue(req.getReader(), Reservation.class);
            Reservation createdReservation = reservationService.addReservation(reservation);

            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_CREATED);
            objectMapper.writeValue(resp.getOutputStream(), createdReservation);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error creating reservation: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReservationService reservationService = (ReservationService) getServletContext().getAttribute("reservationService");
        if (reservationService == null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Reservation Service not found");
            return;
        }

        String pathInfo = req.getPathInfo();
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                resp.setContentType("application/json");
                objectMapper.writeValue(resp.getWriter(), reservationService.getAllReservations());
            } else {
                Long reservationId = parseReservationId(pathInfo, resp);
                if (reservationId == null) {
                    return;
                }

                Optional<Reservation> reservation = reservationService.findReservationById(reservationId);
                if (reservation.isPresent()) {
                    resp.setContentType("application/json");
                    objectMapper.writeValue(resp.getWriter(), reservation.get());
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("Reservation not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error fetching reservation: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        reservationService = (ReservationService) getServletContext().getAttribute("reservationService");
        if (reservationService == null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Reservation Service not found");
            return;
        }

        String pathInfo = req.getPathInfo();
        if(pathInfo == null || pathInfo.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Reservation ID is required");
            return;
        }

        try {
            Long reservationId = parseReservationId(pathInfo, resp);
            if (reservationId == null) {
                return;
            }

            reservationService.removeReservation(reservationId);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error deleting reservation: " + e.getMessage());
        }
    }

    private Long parseReservationId(String pathInfo, HttpServletResponse resp) throws IOException {
        try {
            return Long.parseLong(pathInfo.substring(1));
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid guest ID format: " + pathInfo.substring(1));
            return null;
        }
    }
}
