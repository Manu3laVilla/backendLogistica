package com.example.backendlogistica.utils.generator;

import com.example.backendlogistica.services.interfaces.ILogisticaService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;

@Service
public class Generator {

    @Autowired
    private static ILogisticaService logisticaService;

    @Autowired
    private ILogisticaService logisticaServicee;

    @PostConstruct
    private void init() {
        logisticaService = this.logisticaServicee;
    }

    public int getCosto(int id){
        return logisticaService.findByIdLogistica(id).getPrecioEnvio();
    }

    public int getCostoPagar(int id, int cantidad){

        int precio = logisticaService.findByIdLogistica(id).getPrecioEnvio();

        if(cantidad <= 10){
            return precio;
        }else if(id == 1 && cantidad >= 10){
            precio = (int) (precio - precio*0.05);
        } else if (id == 2 && cantidad >= 10) {
            precio = (int) (precio - precio*0.03);
        }
        return precio;
    }

    public Date dateEntrega(Date fechaRegistro) {
        Calendar calendar =  Calendar.getInstance();
        calendar.setTime(fechaRegistro);
        calendar.add(Calendar.DATE, 10);

        Date fecha = calendar.getTime();

        return fecha;
    }

    public String generateGuia(){
        String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        String NUMBER = "0123456789";
        int length = 10;

        String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
        SecureRandom random = new SecureRandom();

        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            sb.append(rndChar);
        }

        return sb.toString();
    }

}
