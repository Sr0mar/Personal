package tienda;
import java.util.*;

public class Tienda {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        
        String producto_1,producto_2,producto_3,producto_4,producto_5,usuario,respuesta="si",opcion;
        double valor_p1,valor_p2,valor_p3,valor_p4,valor_p5,total_sin=0,IVA_F,total_con,IVA,precio_final_doble;
        int cantidad_1,cantidad_2,cantidad_3,cantidad_4,cantidad_5,plazos;
        int stock_silla,stock_Mesa,stock_lampara,stock_cama,stock_Estanteria,Stock_General,stock_usuario;
        boolean ProductoAceptado,valido;
        double total_tarjeta,porcentaje_tarjeta,plazos_pagar,plazos_porcentaje,porcentaje_suma,plazos_porcentaje_final,total_extremo,mes_pagar;
        boolean corrector=true,prueba=true,comprobante=true;
        
        int contador_array=0;
        
        
        ProductoAceptado = true;
        valido = true;
        
        String[] productos={"Silla","Mesa","Lampara","Cama","Estanteria"};
        
        int[] stock ={10,10,10,10,10};
        plazos_porcentaje_final = 0;
        
        int[] cantidad_p ={0,0,0,0,0};
     
        
        plazos = 0;
        precio_final_doble = 0;
        
        stock_usuario = 0;       
        Stock_General = 50;
        
        porcentaje_suma = 1;
        IVA = 21;
        
        double[] valores={5.0,15.0,9.0,51.0,122.0};
 
        do{
            // Catalogo -----------------------------------------------------------------
            System.out.println("Bienvenido a tu tienda de muebles online");
            System.out.println("             [Catalogo]");
            System.out.println("-------------------------------");
            System.out.println("");
            for(int i=0;i<productos.length;i++){
                System.out.println("|* "+productos[i] +" ... " +valores[i]+"€");
            }
            System.out.println("");
            System.out.println("-------------------------------");

            
            // Los 5 productos -------------------------------------------------------------------
            while(prueba)
            {
                if (stock[contador_array] > 0)
                {
                    while(ProductoAceptado){
                        
                            
                            System.out.println("Nuestro Stock de " + productos[contador_array] + " es: " + +stock[contador_array]);
                            System.out.println("¿Cuantas " +productos[contador_array] +"s quieres comprar?");
                            cantidad_p[contador_array] = teclado.nextInt();


                            if (cantidad_p[contador_array] >= 0 && cantidad_p[contador_array] <= stock[contador_array])
                            {
                                stock[contador_array] = stock[contador_array]-cantidad_p[contador_array];
                                stock_usuario = stock_usuario+cantidad_p[contador_array];
                                Stock_General = Stock_General-cantidad_p[contador_array];
                                System.out.println("Has comprado: " +cantidad_p[contador_array]+ " " +productos[contador_array]);
                                ProductoAceptado = false;

                            }else if (cantidad_p[contador_array] <= 0)
                            {
                                System.out.println("No puedes comprar con numeros negativos");
                            }else if (cantidad_p[contador_array] > stock[contador_array])
                            {
                                System.out.println("No puedes comprar mas material del que tenemos");
                            }
                    }
                }
                
                ProductoAceptado = true;
                contador_array++;
                if(contador_array == 5){
                    prueba = false;
                }
            }
            // Factura -------------------------------------------------------------------------
            prueba = true;
            contador_array=0;
            
            for(int i=0; i<cantidad_p.length; i++){
                total_sin = (cantidad_p[i]*valores[i])+total_sin;
                
            }
            
            IVA_F = (total_sin*21)/100;
            total_con = total_sin+IVA_F;

            
            teclado.nextLine();
                
            
            do{
                System.out.print("Escriba su nombre completo:");
                usuario = teclado.nextLine();
            }while(usuario.equals(""));
            
            
            
            //--------------------------------Factura-------------------------------
            if(stock_usuario > 0)
            {
                
                
                System.out.println("---------------------------------------");
                System.out.println("|       Resumen de la factura");
                System.out.println("|");
                System.out.println("|Nombre: " +usuario);
                System.out.println("|             Prouctos");
                System.out.println("|");
                for(int i=0; i<productos.length;i++){
                    if(cantidad_p[i] > 0){
                        System.out.println("|"+productos[i] +": " +cantidad_p[i]);
                    }
                }
                System.out.println("|");
                System.out.println("|              A pagar");
                System.out.println("|");
                System.out.println("|Total sin IVA: "+total_sin +" €");
                System.out.println("|IVA (21%) "+IVA_F +"€|");
                System.out.println("|Total con IVA "+total_con +" €");
                System.out.println("|---------------------------------------");
                
                do{
                    System.out.println("Con que quieres pagar Tarjeta o AlContado");
                    opcion = teclado.next();
                    switch(opcion.toLowerCase())
                    {
                        case "tarjeta":
                            
                            System.out.println("");
                            System.out.println("");
                            System.out.println("Pagar con Tarjeta supone un recago del 2% sobre el precio final");
                            total_tarjeta = ((total_con*2)/100)+total_con;
                            System.out.println("El precio ha pagar con el recargo es: " +total_tarjeta +"€");
                            porcentaje_tarjeta = (total_con*2)/100;
                            System.out.println("Se le ha sumado un total de:" +porcentaje_tarjeta +"€");
                            System.out.println("");
                            System.out.println("");
                            
                            precio_final_doble = total_tarjeta;
                            valido = false;
                            break;
                            
                        case "alcontado":
                            System.out.println("Tu precio no varia es: " +total_con+"€");
                            System.out.println("");
                            System.out.println("");
                            precio_final_doble = total_con;
                            valido = false;
                            break;
                        default:
                            System.out.println("Opcion no valida.");
                    }
                    
                    
                }while(valido);
                
                do{
                    
                    System.out.println("Tienes la posibilidad de pagarlo a plazos");
                    System.out.println("¿Cuantos plazos quieres?");
                    System.out.println("El maximo es 24 plazos y el minimo 1");
                    System.out.println("Cada plazo tiene un cargo de 1% al precio final");
                    
                    plazos = teclado.nextInt();
                    
                }while(plazos <= 0 || plazos > 24 );
                
                if (plazos > 1){
                    
                    
                    plazos_porcentaje = (precio_final_doble*(plazos-1))/100;
                    
                    System.out.println("La cantidad aumentada por cada plazo es de: " +plazos_porcentaje);
                    
                    plazos_porcentaje_final = plazos_porcentaje*plazos;
                    System.out.println("La cantidad total aumentada es: " +(plazos_porcentaje_final-plazos_porcentaje));
                    
                    mes_pagar = ((precio_final_doble+plazos_porcentaje)/plazos);
                    System.out.println("");
           
                    
                    total_extremo = precio_final_doble+(plazos_porcentaje_final-plazos_porcentaje);
                    System.out.println("--------------------------------------");
                    System.out.println("Cada mes debes pagar: "+mes_pagar);
                    System.out.println("Total a pagar es: " +total_extremo);
                    System.out.println("--------------------------------------");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    
                }else{
                    System.out.println("Tu precio a 1 plazo es: " +precio_final_doble);
                }            
            
                System.out.println("Muchas gracias " +usuario +" por comprar en nuestra querida tienda.");
            }else{
                System.out.println("Sentimos no tener cosas de su agrado");
            }
            
            if(Stock_General > 0)
            {
                while(corrector){
                        System.out.println("Hay clientes esperando contesta con si o no");
                        respuesta = teclado.next();
                        if (respuesta.toLowerCase().equals("si") || respuesta.toLowerCase().equals("no")){
                            corrector = false;
                        }else{
                            System.out.println("Escribe bien la opcion");
                            System.out.println(" ");
                        }
                            
                }
            }
                
            //Reinicio del programa -------------------------------------------
            
            
            valido = true;
            
            for(int k=0; k<productos.length;k++){
                cantidad_p[k] = 0;
                
            }
            
            plazos_porcentaje_final = 0;
            mes_pagar = 0;
            total_extremo = 0;
            precio_final_doble = 0;
            plazos = 0;
            plazos_porcentaje = 0;
            total_tarjeta = 0;
            plazos_pagar = 0;
            stock_usuario = stock_usuario*0;
            total_tarjeta = 0;
            porcentaje_tarjeta = 0;
            IVA_F = IVA_F*0;
            total_con = total_con*0;
            total_sin = total_sin*0;
            corrector= true;
            
        }while( Stock_General > 0 && respuesta.toLowerCase().equals("si"));
        
        
        
        // Despedida ---------------------------------------------------------------------
        
        if(Stock_General <= 0){
            System.out.println("Lo sentimos no nos queda stock disponible");
            System.out.println("Vuelva mañana");
        }else if(respuesta.equals("no"))
        {
            System.out.println("No hay mas clientes cerramos por hoy");
        }else
        {
            System.out.println("Error. Este mensaje no debe salir comprobar fallo.");
        }

    
    }
}
