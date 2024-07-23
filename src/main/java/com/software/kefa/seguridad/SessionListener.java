package com.software.kefa.seguridad;

import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    private static final ConcurrentHashMap<String, HttpSession> sesionesActivas = new ConcurrentHashMap<>();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        sesionesActivas.put(session.getId(), session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        sesionesActivas.remove(se.getSession().getId());
    }

    public static HttpSession getSessionById(String sessionId) {
        return sesionesActivas.get(sessionId);
    }

    public static void invalidateSessionById(String sessionId) {
        HttpSession session = getSessionById(sessionId);
        if (session != null) {
            session.invalidate();
            sesionesActivas.remove(sessionId);
        }
    }
}