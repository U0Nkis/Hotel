package ru.vsu.cs.trufanov.Servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.vsu.cs.trufanov.Config.DatabaseConfig;
import ru.vsu.cs.trufanov.Models.Room;
import ru.vsu.cs.trufanov.Repositories.impl.RoomRepositoryImpl;
import ru.vsu.cs.trufanov.Services.RoomService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/rooms")
public class RoomServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private RoomService roomService;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            roomService = new RoomService(new RoomRepositoryImpl(DatabaseConfig.getConnection()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getServletContext().setAttribute("roomService", roomService);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        roomService = (RoomService) getServletContext().getAttribute("roomService");
        if (roomService == null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Room Service not found");
            return;
        }
        try{
            Room room = objectMapper.readValue(req.getInputStream(), Room.class);
            Room createdRoom = roomService.addRoom(room);

            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_CREATED);
            objectMapper.writeValue(resp.getOutputStream(), createdRoom);

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error creating room: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoomService roomService = (RoomService) getServletContext().getAttribute("roomService");
        if (roomService == null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Room Service not found");
            return;
        }

        String pathInfo = req.getPathInfo();
        try{
            if (pathInfo == null || pathInfo.equals("/")){
                resp.setContentType("application/json");
                objectMapper.writeValue(resp.getWriter(), roomService.getAllRooms());
            } else {
                Long roomId = parseRoomId(pathInfo, resp);
                if (roomId == null){
                    return;
                }

                Optional<Room> room = roomService.findRoomById(roomId);
                if (room.isPresent()){
                    resp.setContentType("application/json");
                    objectMapper.writeValue(resp.getWriter(), room.get());
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("Room not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error fetching room: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoomService roomService = (RoomService) getServletContext().getAttribute("roomService");
        if (roomService == null) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Room Service not found");
            return;
        }

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Room Id is required");
            return;
        }

        try{
            Long roomId = parseRoomId(pathInfo, resp);
            if (roomId == null){
                return;
            }

            roomService.removeRoom(roomId);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error deleting room: " + e.getMessage());
        }
    }

    private Long parseRoomId(String pathInfo, HttpServletResponse resp) throws IOException {
        try {
            return Long.parseLong(pathInfo.substring(1));
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid guest ID format: " + pathInfo.substring(1));
            return null;
        }
    }
}
