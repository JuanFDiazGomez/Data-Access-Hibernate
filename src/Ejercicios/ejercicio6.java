/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicios;

import clasesPojoS.Equipos;
import clasesPojoS.Jugadores;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author juanfdg
 */
public class ejercicio6 {

    public static void main(String[] args) {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session sesionCreada = sesion.openSession();

        Query q = sesionCreada.createQuery("from Equipos");
        List<Equipos> listaEquipos = q.list();
        Iterator<Equipos> iteraEquipos = listaEquipos.iterator();
        System.out.println("Número de equipos: " + listaEquipos.size());
        while (iteraEquipos.hasNext()) {
            System.out.println("==============================================");
            Equipos equipo = iteraEquipos.next();
            System.out.println("Equipo: " + equipo.getNombre());
            for (Iterator<Jugadores> it = equipo.getJugadoreses().iterator(); it.hasNext();) {
                Jugadores jugador = it.next();
                String hql = "select avg(puntosPorPartido) "
                        + "from Estadisticas e "
                        + "where e.id.jugador = :codJugador";
                q = sesionCreada.createQuery(hql);
                q.setParameter("codJugador", jugador.getCodigo());
                Double puntuacionMedia = (Double) q.uniqueResult();
                if(puntuacionMedia == null){puntuacionMedia = 0D;}
                    
                System.out.println(jugador.getCodigo() + ", "
                        + jugador.getNombre() + ": "
                        + puntuacionMedia.toString());
                
            }
            System.out.println(". . . . . . . . . . . . . . .");
        }
        sesionCreada.close();
    }
}
