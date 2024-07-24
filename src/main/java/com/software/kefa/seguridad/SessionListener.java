package com.software.kefa.seguridad;

import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * This class represents a session listener that implements the HttpSessionListener interface.
 * It is responsible for managing active sessions and providing methods to retrieve and invalidate sessions.
 */
public class SessionListener implements HttpSessionListener {

    private static final ConcurrentHashMap<String, HttpSession> sesionesActivas = new ConcurrentHashMap<>();

    /**
     * This method is called when a new session is created.
     * It adds the session to the list of active sessions.
     *
     * @param se The HttpSessionEvent object representing the session event.
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        sesionesActivas.put(session.getId(), session);
    }

    /**
     * This method is called when a session is destroyed.
     * It removes the session from the list of active sessions.
     *
     * @param se The HttpSessionEvent object representing the session event.
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        sesionesActivas.remove(se.getSession().getId());
    }

    /**
     * This method retrieves a session by its ID.
     *
     * @param sessionId The ID of the session to retrieve.
     * @return The HttpSession object associated with the specified ID, or null if not found.
     */
    public static HttpSession getSessionById(String sessionId) {
        return sesionesActivas.get(sessionId);
    }

    /**
     * This method invalidates a session by its ID.
     * It also removes the session from the list of active sessions.
     *
     * @param sessionId The ID of the session to invalidate.
     */
    public static void invalidateSessionById(String sessionId) {
        HttpSession session = getSessionById(sessionId);
        if (session != null) {
            session.invalidate();
            sesionesActivas.remove(sessionId);
        }
    }
}