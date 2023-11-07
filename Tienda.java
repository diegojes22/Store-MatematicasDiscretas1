import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Tienda
{
    public static void clear() 
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void lines()
    {
        System.out.println("------------------------------------------------------");
    }

    public static void main(String[] args)
    {
        /* Listas de productos */
        Scanner leer = new Scanner(System.in);

        int EN_VENTA = 15;
        String[] id_producto = {
            "A1", "A2", "A3", "A4", "A5", // Papas
            "B1", "B2", "B3", "B4", "B5", // Pastelitos
            "C1", "C2", "C3", "C4", "C5", // Refrescos
        };
        float[] precio_producto = {
            14.00f, 15.50f, 16.50f, 14.00f, 13.00f, // Papas
            20.00f, 18.00f, 13.00f, 22.00f, 21.25f, // Pastelitos
            20.00f, 18.00f, 17.50f, 23.20f, 50.00f  // Refrescos
        };
        String[] nombre_producto = {
            "Sabritas", "Doritos", "Takis"    , "Chetos"  , "Rufles",     // Papas
            "Gansito" , "Nito"   , "Pinguinos", "Barritas", "Bimbollos",  // Pastelitos
            "CocaCola", "Pepsi"  , "Jumex"    , "Sprite"  , "Agua"        // Refrescos
        };

        List<String> compras = new ArrayList<>();  // aqui guardaremos las compras del usuario
        int producto_a_comprar = 0;
        int productos_en_carrito = 0;
        float precio_acumulado = 0;
        float descuento = 0.0f, IVA = 0.16f;
        float total;
        
        /* Ciclo principal */
        do {
            // mostrar el menu
            clear();

            lines();
            System.out.println("|ID Nombre\t\tPrecio\t\tCodigo");
            lines();

            for(int i = 0; i < EN_VENTA; i++ ) {
                System.out.println("|"+i+") "+nombre_producto[i]+"\t\t$"+precio_producto[i]+"\t\t"+id_producto[i]);
            }

            System.out.println("|");
            System.out.println("|16) Finalizar compra");

            // leer datos
            lines();
            System.out.println("Productos comprados: "+productos_en_carrito);
            System.out.print("Producto a comprar <0-14>: ");
            producto_a_comprar = leer.nextInt();
            lines();

            // analizar los datos de la compra
            if(producto_a_comprar >= 0 && producto_a_comprar < 15) {
                productos_en_carrito++;

                compras.add(nombre_producto[producto_a_comprar]);
                precio_acumulado += precio_producto[producto_a_comprar];

                // verificar numero de productos para el descuento
                if(productos_en_carrito > 1 && productos_en_carrito <= 5) 
                    descuento = 0.02f;
                if(productos_en_carrito > 5 && productos_en_carrito <= 10) 
                    descuento = 0.05f;
                if(productos_en_carrito > 10 && productos_en_carrito <= 15) 
                    descuento = 0.09f;
            }
            
        } while(producto_a_comprar != 16);

        total = precio_acumulado + (precio_acumulado*IVA) - (precio_acumulado*descuento);

        // Mostrar resultados
        clear();
        lines();
        System.out.println("\tTICKET DE COMPRA");
        lines();
        for(String compra : compras) {
            System.out.println(compra);
        }

        lines();
        System.out.println("Precio.....: "+precio_acumulado);
        System.out.println("IVA........: "+(precio_acumulado*IVA));
        System.out.println("\nDescuento..:"+(precio_acumulado*descuento));
        System.out.println("\nTotal......: "+total);
        lines();

        leer.close();
    }
}