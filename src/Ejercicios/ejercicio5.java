/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicios;

import clasesPojoS.*;
import java.util.Iterator;
import java.util.Set;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author juanfdg
 */
public class ejercicio5 {
    public static void main(String[] args) {
        if(args.length == 1){
            try{
            SessionFactory sesion = NewHibernateUtil.getSessionFactory();
            Session sesionCreada = sesion.openSession();
            Jugadores jugador = (Jugadores) sesionCreada.load(
                    Jugadores.class,
                    Integer.parseInt(args[0])
            );
            System.out.println(jugador);
            Set <Estadisticas> estadisticas = jugador.getEstadisticases();
            System.out.println("Temp\tPtos\tAsis\tTab\tReb");
            System.out.println("============================================");
            for (Iterator it = estadisticas.iterator(); it.hasNext();){
                Estadisticas estadistica = (Estadisticas) it.next();
                System.out.println(estadistica);
            }
            System.out.println("============================================");
            System.out.println("Numero de registros: "+estadisticas.size());
            System.out.println("============================================");
            sesionCreada.close();
            }catch(ObjectNotFoundException onfe){
                System.out.println("El jugador introducido no existe");
            }finally{
                
            }
        }else{
            System.out.println("Ha de indicar el número en la ejecución");
        }
    }
    
}
