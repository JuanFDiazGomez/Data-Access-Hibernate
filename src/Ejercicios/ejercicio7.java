/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicios;

import clasesPojoS.Equipos;
import clasesPojoS.Estadisticas;
import clasesPojoS.EstadisticasId;
import clasesPojoS.Jugadores;
import java.math.BigDecimal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author juanfdg
 */
public class ejercicio7 {

    public static void main(String[] args) {
        if(args.length == 6){
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session sesionCreada = sesion.openSession();

        Transaction tx = sesionCreada.beginTransaction();
        //Primera parte
        /*
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session sesionCreada = sesion.openSession();

        Transaction tx = sesionCreada.beginTransaction();
        Jugadores jugador = (Jugadores) sesionCreada.get(Jugadores.class, 123);
        sesionCreada.save(
                new Estadisticas(
                        new EstadisticasId("05/06",jugador.getCodigo()),
                        jugador, 
                        new BigDecimal(7),
                        new BigDecimal(0), 
                        new BigDecimal(0), 
                        new BigDecimal(5))
        );
        sesionCreada.save(
                new Estadisticas(
                        new EstadisticasId("06/07",jugador.getCodigo()),
                        jugador, 
                        new BigDecimal(10),
                        new BigDecimal(0), 
                        new BigDecimal(3), 
                        new BigDecimal(0))
        );
        try{
            tx.commit();
        } catch (ConstraintViolationException cve) {
            System.out.println("No se pudo insertar los datos");
            System.out.println(cve.getMessage());
        }
        
        sesionCreada.close();
        */

        //Segunda parte
        try {
            Jugadores jugador = (Jugadores) sesionCreada.get(Jugadores.class, Integer.valueOf(args[0]));
            if (jugador != null) {

                sesionCreada.save(
                        new Estadisticas(
                                new EstadisticasId(args[1], jugador.getCodigo()),
                                jugador,
                                new BigDecimal(args[2]),
                                new BigDecimal(args[3]),
                                new BigDecimal(args[4]),
                                new BigDecimal(args[5]))
                );

                tx.commit();

            } else {
                System.out.println("El jugador " + args[0] + " no existe");
            }
        } catch (ConstraintViolationException cve) {
            System.out.println("Ya existen esas estadisticas");
        } catch (NumberFormatException nfe) {
            System.out.println("Los datos introducidos no son correctos");
        }
        sesionCreada.close();
    }else{
            System.out.println("No ha introducido todos los datos necesarios");
        }
    }
}
