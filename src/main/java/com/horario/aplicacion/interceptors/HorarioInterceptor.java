package com.horario.aplicacion.interceptors;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("horario")
public class HorarioInterceptor implements HandlerInterceptor {

    @Value("${config.horario.apertura}")
    private Integer apertura;
    @Value("${config.horario.cierre}")
    private Integer cierre;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Calendar calendar = Calendar.getInstance();
        int hora = calendar.get(calendar.HOUR_OF_DAY);
        if(hora >= apertura && hora < cierre){
            String mensaje = String.format("Bienvenido usuario, atendemos desde las %d a %d", apertura, cierre);
            request.setAttribute("mensaje", mensaje);
            return true;
        }
        response.sendRedirect(request.getContextPath().concat("/cerrado"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        String mensaje = (String) request.getAttribute("mensaje");
        if(modelAndView != null){
            modelAndView.addObject("mensaje", mensaje);
        }
    }

    
}
