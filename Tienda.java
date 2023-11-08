import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Tienda
{
    /* Embellecer la interfaz */
    public static void clear() 
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void lines()
    {
        System.out.println("------------------------------------------------------");
    }

    /* Main */
    public static void main(String[] args)
    {
        /* Variables */
        Scanner leer = new Scanner(System.in);
        DataBase db = new DataBase("db/store.txt");

        int num_productos = 0;
        float precio_acumulado = 0, descuento = 0;
        float total;
        final float IVA = 0.16f;

        String codigo_compra = "";
        String producto_en_mano = "";
        String en_carrito = "";
        
        /* Ciclo principal */
        do {
            // menu de inicio
            clear();

            lines();
            System.out.println("| TIENDA CINCO DE OROS");
            lines();

            System.out.println("| 1) Ver catalogo");
            System.out.println("| 0) Finalizar");
            System.out.println("|\n| Productos en el carrito: "+num_productos);
            lines();

            // elegir opcion
            System.out.println("Ingrese la opcion o el codigo de compra: ");
            codigo_compra = leer.nextLine();

            // verificar opcion
            if(codigo_compra.equals("1")) {
                clear();
                lines();

                System.out.println(db.get_all_data());
                lines();
                
                System.out.println("pulse una tecla para continuar...");
                leer.nextLine();
            }
            
            producto_en_mano = db.filter(codigo_compra);

            // Procesar la compra
            if(!producto_en_mano.equals("") && !producto_en_mano.equals("1")) {
                num_productos++;

                precio_acumulado += Float.parseFloat(db.filter_the_line(producto_en_mano, 2));

                en_carrito += producto_en_mano + "\n";

                // verificar los productos en el carrito para hacer descuento
                if(num_productos > 1 && num_productos <= 5) {
                    descuento = 0.02f;
                }
                else if(num_productos > 5 && num_productos <= 10) {
                    descuento = 0.05f;
                }
                if(num_productos > 11) {
                    descuento = 0.09f;
                }
            }

        } while(!codigo_compra.equals("0"));

        clear();

        total = precio_acumulado + (precio_acumulado*IVA) - (precio_acumulado*descuento);

        // Mostrar resultados
        clear();
        lines();
        System.out.println("\tTICKET DE COMPRA");
        lines();

        System.out.println(en_carrito);

        lines();
        System.out.println("Precio.....: "+precio_acumulado);
        System.out.println("IVA........: "+(precio_acumulado*IVA));
        System.out.println("\nDescuento..: "+(precio_acumulado*descuento));
        System.out.println("\nTotal......: "+total);
        lines();

        leer.close();
    }
}