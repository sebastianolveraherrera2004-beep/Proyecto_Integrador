import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Shingu Coffee_codigo.java {

    static String[][] productos;
    static String ventas[][];
    static int tamventas = 100;
    static String fecha;

    public static String MostrarMenu(String[] opciones) {
        String cadena = "";
        for (String info : opciones) {
            cadena = cadena + info + "\n";
        }
        return cadena;
    }

    public static boolean EsNumeroEntero(String dato) {
        if (dato == null || dato.trim().isEmpty()) {
            return false;
        }
        for (char c : dato.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean EsNumeroDouble(String dato) {
        if (dato == null || dato.trim().isEmpty()) {
            return false;
        }
        boolean punto = false;
        boolean digito = false;
        for (char c : dato.toCharArray()) {
            if (Character.isDigit(c)) {
                digito = true;
            } else if (c == '.' && !punto) {
                punto = true;
            } else {
                return false;
            }
        }
        return digito;
    }

    public static boolean EvaluarNumerico(String dato, int tipo) {
        boolean valido = false;
        switch (tipo) {
            case 1:
                valido = EsNumeroEntero(dato);
                break;
            case 2:
                valido = EsNumeroDouble(dato);
                break;
        }
        return valido;
    }

    public static String Dialogo(String texto) throws IOException {
        String cadena;
        System.out.println(texto + " : ");
        BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
        cadena = lectura.readLine();
        return cadena;
    }

    public static String Leer(String texto) throws IOException {
        String cadena = "";
        cadena = Dialogo(texto);
        if (cadena != null) {
            cadena = cadena.trim();
            if (cadena.isEmpty()) {
                cadena = null;
            }
        } else {
            cadena = null;
        }
        return cadena;
    }

    public static String LeerValidado(String texto_dialogo, int tipo_dato) throws IOException {
        String cadena;
        do {
            cadena = Dialogo(texto_dialogo);
            if (cadena != null) {
                cadena = cadena.trim();
            }

            if (cadena != null && !cadena.isEmpty() && EvaluarNumerico(cadena, tipo_dato)) {
                return cadena;
            } else {
                System.out.println("Dato incorrecto. Intente nuevamente.");
            }
        } while (true);
    }

    public static String DesplegarMenu(String Titulo1, String[] menu) throws IOException {
        String cadena;
        cadena = Titulo1 + "\n" + "\n";
        cadena = cadena + MostrarMenu(menu);
        cadena = cadena + "\n Que opcion deseas ";
        return cadena = Dialogo(cadena);
    }

    public static String RellenarEspacios(String dato, int tamano) {
        if (dato == null) {
            dato = "";
        }
        return String.format("%1$-" + tamano + "s", dato);
    }

    public static String Fecha() {
        Date fecha = new Date();
        SimpleDateFormat formatodia = new SimpleDateFormat("dd-MM-yyyy");
        return formatodia.format(fecha);
    }

    public static String IdTicketSiguiente(String idticket) {
        String idticketnext = "";
        int num = Integer.parseInt(idticket) + 1;
        if (num < 10) {
            idticketnext = "00" + String.valueOf(num).trim();
        } else if ((num > 9) && (num < 100)) {
            idticketnext = "0" + String.valueOf(num).trim();
        } else {
            idticketnext = String.valueOf(num).trim();
        }
        return idticketnext;
    }

    public static int ObtenerUltimaPosicion(String[][] matriz) {
        int ultimaPosicion = -1;
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][0] != null && !matriz[i][0].isEmpty()) {
                ultimaPosicion = i;
            }
        }
        return ultimaPosicion;
    }

    public static String[][] CargarProductos() {
        String[][] producto = {
            { "001", "Espresso", "45", "10", "16" },
            { "002", "Americano", "50", "10", "16" },
            { "003", "Cappuccino", "65", "10", "16" },
            { "004", "Latte", "68", "10", "16" },
            { "005", "Mocha", "72", "10", "16" },
            { "006", "Flat White", "70", "10", "16" },
            { "007", "Caramel Macchiato", "78", "10", "16" },
            { "008", "Cold Brew", "75", "10", "16" },
            { "009", "Frappe de Cafe", "82", "10", "16" },
            { "010", "Affogato", "80", "10", "16" },
            { "011", "Chocolate Caliente", "60", "10", "16" },
            { "012", "Te Chai Latte", "70", "10", "16" },
            { "013", "Matcha Latte", "76", "10", "16" },
            { "014", "Tisana Frutal", "58", "10", "0" },
            { "015", "Croissant Mantequilla", "42", "10", "0" },
            { "016", "Panini Jamon y Queso", "85", "10", "0" },
            { "017", "Bagel con Queso Crema", "55", "10", "0" },
            { "018", "Cheesecake Rebanada", "68", "10", "0" },
            { "019", "Brownie", "48", "10", "0" },
            { "020", "Galletas Artesanales", "40", "10", "0" }
        };
        return producto;
    }

    public static String MostrarProducto(String[] vproducto) {
        String codigo = RellenarEspacios(vproducto[0], 5);
        String producto = RellenarEspacios(vproducto[1], 30);
        String precio = RellenarEspacios(vproducto[2], 10);
        String cantidad = RellenarEspacios(vproducto[3], 10);
        String iva = "";
        if (vproducto.length > 4) {
            iva = RellenarEspacios(vproducto[4], 10);
        }
        String cadena = codigo.concat(producto + precio + cantidad + iva);
        return cadena;
    }

    public static String MostrarLista(String[][] vproductos) {
        String salida = "Codigo Producto                      Precio    Cantidad  IVA %\n";
        for (int ciclo = 0; ciclo < vproductos.length; ciclo++) {
            String[] vproducto = {
                vproductos[ciclo][0], vproductos[ciclo][1], vproductos[ciclo][2],
                vproductos[ciclo][3], vproductos[ciclo][4]
            };
            String cadena = MostrarProducto(vproducto);
            salida = salida.concat(cadena + "\n");
        }
        return salida;
    }

    public static int ExisteProducto(String codigo, String[][] vproductos) {
        int enc, pos, tam, ciclo;
        enc = -1;
        pos = 0;
        tam = vproductos.length;
        for (ciclo = 0; ciclo < tam; ciclo++) {
            if (vproductos[ciclo][0].compareTo(codigo.trim()) == 0) {
                enc = pos;
            }
            pos++;
        }
        return enc;
    }

    public static double ObtenerIva(String codigo, String[][] vproductos) {
        int pos = ExisteProducto(codigo, vproductos);
        if (pos > -1) {
            return Double.parseDouble(vproductos[pos][4]);
        }
        return 0;
    }

    public static int DescontarStock(String[][] mproductos, String codigo, int cantidad) {
        int posp = ExisteProducto(codigo.trim(), mproductos);
        if (posp == -1) {
            return -2;
        }

        int stock = Integer.parseInt(mproductos[posp][3]);
        if (stock == 0) {
            return 0;
        }

        if (cantidad > stock) {
            return -1;
        }

        mproductos[posp][3] = String.valueOf(stock - cantidad);
        return 1;
    }

    public static void ModificarProducto(String[][] vproductos) throws IOException {
        String codigo, precio;
        int posicion;
        String info = MostrarLista(vproductos);
        codigo = Leer(info + "\nIntroduce el codigo del producto a modificar");
        if (codigo != null) {
            posicion = ExisteProducto(codigo, vproductos);
            if (posicion > -1) {
                String[] vproducto = {
                    vproductos[posicion][0], vproductos[posicion][1], vproductos[posicion][2],
                    vproductos[posicion][3], vproductos[posicion][4]
                };
                precio = LeerValidado("\nIntroduce el precio de " + MostrarProducto(vproducto) + " ", 2);
                vproductos[posicion][2] = precio;
                System.out.println("Precio actualizado correctamente.");
            } else {
                System.out.println("no existe el codigo");
            }
        } else {
            System.out.println(" dato nulo");
        }
    }

    public static void MenuProductos(String[][] vproductos) throws IOException {
        String[] datosmenuproductos = { "1.-Modificar ", "2.-Listado ", "3.-Salida " };
        String opcion = "0";
        do {
            opcion = DesplegarMenu("Opciones de Productos", datosmenuproductos);
            if (opcion == null) {
                System.out.println("opcion incorrecta ");
            } else {
                switch (opcion) {
                    case "1":
                        ModificarProducto(vproductos);
                        break;
                    case "2":
                        System.out.println(MostrarLista(vproductos));
                        break;
                    case "3":
                        System.out.println("Salida del Sistema ");
                        break;
                    default:
                        System.out.println("No existe esta opcion ");
                        break;
                }
            }
        } while (opcion.compareTo("3") != 0);
    }

    public static String[][] CrearVenta() {
        return new String[tamventas][5];
    }

    public static String UltimoTicket(int pos, String[][] mventa) {
        String idticket = "000";
        if (pos > -1) {
            idticket = mventa[pos][0];
        }
        return idticket;
    }

    public static String[][] CrearTicket() {
        return new String[20][4];
    }

    public static int ExisteTicketCodigo(String[][] mticket, String codigo) {
        int enc = -1;
        int pos = ObtenerUltimaPosicion(mticket);
        for (int ciclo = 0; ciclo <= pos; ciclo++) {
            if (mticket[ciclo][0].compareTo(codigo.trim()) == 0) {
                enc = ciclo;
                return enc;
            }
        }
        return enc;
    }

    public static boolean InsertarProductoTicket(String[][] mticket, String[] datos, int tamticket) {
        boolean sucedio = true;
        int posticket = ObtenerUltimaPosicion(mticket);
        int enc = ExisteTicketCodigo(mticket, datos[0]);

        if (posticket < tamticket - 1) {
            if (enc > -1) {
                int cantidadactual = Integer.parseInt(mticket[enc][3]);
                mticket[enc][3] = String.valueOf(cantidadactual + 1);
            } else {
                posticket++;
                mticket[posticket][0] = datos[0];
                mticket[posticket][1] = datos[1];
                mticket[posticket][2] = datos[2];
                mticket[posticket][3] = datos[3];
                System.out.println("Producto agregado al ticket.");
            }
        } else {
            sucedio = false;
        }
        return sucedio;
    }

    public static String TotalProducto(String precio, String cantidad) {
        double total = Double.parseDouble(precio) * Double.parseDouble(cantidad);
        return String.format("%.2f", total);
    }

    public static String MostrarProductoTicket(String[][] mticket, int pos) {
        String codigo = RellenarEspacios(mticket[pos][0], 5);
        String producto = RellenarEspacios(mticket[pos][1], 30);
        String precio = RellenarEspacios(mticket[pos][2], 10);
        String cantidad = RellenarEspacios(mticket[pos][3], 5);
        String totalproducto = RellenarEspacios(TotalProducto(mticket[pos][2], mticket[pos][3]), 10);
        String cadena = codigo.concat(producto + precio + cantidad + totalproducto);
        return cadena;
    }

    public static String MostrarTicket(String[][] mticket) {
        String salida = "Codigo Producto                      Precio    Cant. Total\n";
        int pos = ObtenerUltimaPosicion(mticket);
        if (pos == -1) {
            return "";
        }
        for (int ciclo = 0; ciclo <= pos; ciclo++) {
            salida = salida.concat(MostrarProductoTicket(mticket, ciclo) + "\n");
        }
        return salida;
    }

    public static double SubTotalTicket(String[][] mticket) {
        double subtotal = 0;
        int pos = ObtenerUltimaPosicion(mticket);
        for (int ciclo = 0; ciclo <= pos; ciclo++) {
            subtotal = subtotal + Double.parseDouble(TotalProducto(mticket[ciclo][2], mticket[ciclo][3]));
        }
        return subtotal;
    }

    public static double IvaTicket(String[][] mticket, String[][] mproductos) {
        double ivatotal = 0;
        int pos = ObtenerUltimaPosicion(mticket);
        for (int ciclo = 0; ciclo <= pos; ciclo++) {
            String codigo = mticket[ciclo][0];
            double iva = ObtenerIva(codigo, mproductos);
            if (iva != 0) {
                double precio = Double.parseDouble(mticket[ciclo][2]);
                double cantidad = Double.parseDouble(mticket[ciclo][3]);
                ivatotal = ivatotal + ((precio * cantidad) * (iva / 100));
            }
        }
        return ivatotal;
    }

    public static double TotalTicket(String[][] mticket, String[][] mproductos) {
        double total = SubTotalTicket(mticket);
        if (total > 0) {
            total = IvaTicket(mticket, mproductos) + total;
        }
        return total;
    }

    public static String MostrarTicketVenta(String[][] mticket, String idticket, String fecha, String[][] mproductos) {
        String salida = "";
        String subtotal = String.format("%.2f", SubTotalTicket(mticket));
        String iva = String.format("%.2f", IvaTicket(mticket, mproductos));
        String total = String.format("%.2f", TotalTicket(mticket, mproductos));
        salida = "Fecha " + fecha + " Ticket No. " + idticket;
        salida = salida + "\n" + MostrarTicket(mticket);
        salida = salida + "\nEl total sin iva " + subtotal;
        salida = salida + "\nEl iva total es " + iva;
        salida = salida + "\nEl total de la venta fue " + total;
        return salida;
    }

    public static String MostrarListaProductosVenta(String[][] vproductos) {
        String salida = "Codigo Producto                      Precio    Cantidad  IVA %\n";
        for (int ciclo = 0; ciclo < vproductos.length; ciclo++) {
            int existencia = Integer.parseInt(vproductos[ciclo][3]);
            if (existencia > 0) {
                String[] vproducto = vproductos[ciclo].clone();
                String cadena = MostrarProducto(vproducto);
                salida = salida.concat(cadena + "\n");
            }
        }
        return salida;
    }

    public static void CapturaVentaProducto(String[][] mticket, String[][] mproductos, String idticket, int tamticket)
            throws IOException {
        String codigo, info;
        info = MostrarListaProductosVenta(mproductos);
        codigo = Leer(info + "\nIntroduce el codigo del producto");
        if (codigo != null) {
            int posp = ExisteProducto(codigo.trim(), mproductos);
            if (posp > -1) {
                int resultado = DescontarStock(mproductos, codigo.trim(), 1);
                if (resultado == 1) {
                    String[] venta = new String[4];
                    venta[0] = mproductos[posp][0];
                    venta[1] = mproductos[posp][1];
                    venta[2] = mproductos[posp][2];
                    venta[3] = "1";
                    if (!InsertarProductoTicket(mticket, venta, tamticket)) {
                        System.out.println("el Arreglo esta lleno \n");
                        DevolverProductoInventario(mproductos, codigo.trim(), 1);
                    }
                } else if (resultado == -1) {
                    System.out.println("la cantidad es mayor al stock disponible");
                } else if (resultado == 0) {
                    System.out.println("no hay productos para venta");
                } else if (resultado == -2) {
                    System.out.println("el codigo no existe no se puede agregar");
                }
            } else {
                System.out.println("el codigo no existe no se puede agregar\n");
            }
        } else {
            System.out.println("dato nulo\n");
        }
    }

    public static void DevolverProductoInventario(String[][] mproductos, String codigo, int cantidad) {
        int posproducto = ExisteProducto(codigo, mproductos);
        if (posproducto > -1) {
            int nuevacantidad = Integer.parseInt(mproductos[posproducto][3]) + cantidad;
            mproductos[posproducto][3] = String.valueOf(nuevacantidad);
        }
    }

    public static void RemoverProductoTicket(String[][] mticket, int pos) {
        int tam = ObtenerUltimaPosicion(mticket);
        if (tam > pos) {
            for (int i = pos; i < tam; i++) {
                mticket[i] = mticket[i + 1];
            }
            mticket[tam] = new String[4];
        } else {
            mticket[pos] = new String[4];
        }
    }

    public static void EliminarProductoTicket(String[][] mticket, int pos) {
        int cantidad = Integer.parseInt(mticket[pos][3]);
        if (cantidad > 1) {
            mticket[pos][3] = String.valueOf(cantidad - 1);
        } else {
            RemoverProductoTicket(mticket, pos);
        }
    }

    public static void Eliminar(String[][] mticket, String[][] mproductos) throws IOException {
        String codigo, info;
        info = MostrarTicket(mticket);
        if (info.trim().isEmpty()) {
            System.out.println("El ticket esta vacio");
            return;
        }

        codigo = Leer(info + "\nIntroduce el codigo del producto");
        if (codigo != null) {
            int pos = ExisteTicketCodigo(mticket, codigo);
            if (pos > -1) {
                DevolverProductoInventario(mproductos, codigo.trim(), 1);
                EliminarProductoTicket(mticket, pos);
                System.out.println("Producto eliminado del ticket.");
            } else {
                System.out.println("El codigo no existe en el ticket.");
            }
        } else {
            System.out.println("dato nulo");
        }
    }

    public static void AgregarProductoAVenta(String[][] mticket, String[][] mventa, String idticket) {
        int posventas = ObtenerUltimaPosicion(mventa);
        int posticket = ObtenerUltimaPosicion(mticket);
        for (int i = 0; i <= posticket; i++) {
            if (mticket[i][0] != null) {
                posventas++;
                mventa[posventas][0] = idticket;
                mventa[posventas][1] = mticket[i][0];
                mventa[posventas][2] = mticket[i][1];
                mventa[posventas][3] = mticket[i][2];
                mventa[posventas][4] = mticket[i][3];
            }
        }
    }

    public static void Pagar(String idticket, String[][] mventa, String[][] mticket) {
        int posventas = ObtenerUltimaPosicion(mventa);
        int post = ObtenerUltimaPosicion(mticket);
        if (post == -1) {
            System.out.println("No hay productos en el ticket para pagar.");
            return;
        }
        if ((posventas + post) < tamventas) {
            AgregarProductoAVenta(mticket, mventa, idticket);
        } else {
            System.out.println("Desbordamiento de Memoria de ventas");
        }
    }

    public static void DevolucionTicket(String[][] mticket, String[][] mproductos) {
        int posmticket = ObtenerUltimaPosicion(mticket);
        for (int pos = 0; pos <= posmticket; pos++) {
            String codigo = mticket[pos][0];
            int cantidad = Integer.parseInt(mticket[pos][3]);
            DevolverProductoInventario(mproductos, codigo.trim(), cantidad);
        }
    }

    public static void CancelarVenta(String[][] mticket, String[][] mproductos) {
        DevolucionTicket(mticket, mproductos);
        for (int i = 0; i < mticket.length; i++) {
            for (int j = 0; j < mticket[i].length; j++) {
                mticket[i][j] = null;
            }
        }
        System.out.println("Venta cancelada. Productos devueltos al inventario y ticket limpiado.");
    }

    public static void MenuPuntoVenta(String[][] ventas, String idticket, String[][] productos) throws IOException {
        String opcion, membrete;
        Boolean pago = false;
        int tamticket = 50;
        String[][] Vticket = new String[tamticket][4];

        idticket = IdTicketSiguiente(idticket);
        String fechadia = Fecha();
        opcion = "";

        do {
            membrete = "Fecha del Dia " + fechadia + " Ticket No " + idticket;
            membrete = membrete + "\n-----------------------------------------------------\n";

            String Tickettexto = MostrarTicket(Vticket).trim();
            if (!Tickettexto.trim().isEmpty()) {
                membrete = membrete + "\n" + Tickettexto + "\n";
            }

            String[] datosmenu = { "1.-Agregar  ", "2.-Eliminar ", "3.-Listado ", "4.-Pagar ", "5.-Salida " };
            opcion = DesplegarMenu(membrete + "\n Menu de Punto de Venta", datosmenu);

            if (opcion == null) {
                System.out.println("dato incorrecto introducido");
            } else {
                switch (opcion) {
                    case "1":
                        CapturaVentaProducto(Vticket, productos, idticket, tamticket);
                        break;
                    case "2":
                        Eliminar(Vticket, productos);
                        break;
                    case "3":
                        if (ObtenerUltimaPosicion(Vticket) > -1) {
                            System.out.println(MostrarTicket(Vticket));
                        } else {
                            System.out.println("El ticket esta vacio.");
                        }
                        break;
                    case "4":
                        if (ObtenerUltimaPosicion(Vticket) > -1) {
                            System.out.println(MostrarTicketVenta(Vticket, idticket, fechadia, productos).trim());
                            Pagar(idticket, ventas, Vticket);
                            pago = true;
                            opcion = "5";
                        } else {
                            System.out.println("No hay productos en el ticket para pagar.");
                        }
                        break;
                    case "5":
                        System.out.println("Salida del Ventas ");
                        if (!pago && ObtenerUltimaPosicion(Vticket) > -1) {
                            CancelarVenta(Vticket, productos);
                        }
                        break;
                    default:
                        System.out.println("No existe esta opcion");
                        break;
                }
            }
        } while (opcion.compareTo("5") != 0);
    }

    public static String MostrarVenta(String[] venta) {
        String idticket = RellenarEspacios(venta[0], 6);
        String codigo = RellenarEspacios(venta[1], 5);
        String producto = RellenarEspacios(venta[2], 30);
        String precio = RellenarEspacios(venta[3], 10);
        String cantidad = RellenarEspacios(venta[4], 10);
        String cadena = idticket.concat(codigo + producto + precio + cantidad);
        return cadena;
    }

    public static String MostrarListaVentas(String[][] ventas) {
        int posventas = ObtenerUltimaPosicion(ventas);
        String salida = "ID    Codigo Producto                      Precio    Cantidad\n";
        if (posventas == -1) {
            return "No hay ventas registradas.\n";
        }
        for (int ciclo = 0; ciclo <= posventas; ciclo++) {
            String[] venta = { ventas[ciclo][0], ventas[ciclo][1], ventas[ciclo][2], ventas[ciclo][3], ventas[ciclo][4] };
            String cadena = MostrarVenta(venta);
            salida = salida.concat(cadena + "\n");
        }
        return salida;
    }

    public static void AgregarStock(String[][] vproductos) throws IOException {
        String codigo, cantidad;
        int posicion;
        String info = MostrarLista(vproductos);
        codigo = Leer(info + "\nIntroduce el codigo del producto a modificar");
        if (codigo != null) {
            posicion = ExisteProducto(codigo, vproductos);
            if (posicion > -1) {
                String[] vproducto = {
                    vproductos[posicion][0], vproductos[posicion][1], vproductos[posicion][2],
                    vproductos[posicion][3], vproductos[posicion][4]
                };
                cantidad = LeerValidado("\nIntroduce la Cantidad de Stock a Agregar " + MostrarProducto(vproducto), 1);
                String nuevacantidad = String.valueOf((Integer.valueOf(cantidad) + Integer.valueOf(vproductos[posicion][3])));
                vproductos[posicion][3] = nuevacantidad;
                System.out.println("Stock actualizado correctamente.");
            } else {
                System.out.println("no existe el codigo");
            }
        } else {
            System.out.println(" dato nulo");
        }
    }

    public static void MenuInventario(String[][] vproductos) throws IOException {
        String[] datosmenuinventario = { "1.-Listado ", "2.-Agregar ", "3.-Salida " };
        String opcion = "0";
        do {
            opcion = DesplegarMenu("Opciones de Inventarios", datosmenuinventario);
            if (opcion == null) {
                System.out.println("opcion incorrecta ");
            } else {
                switch (opcion) {
                    case "1":
                        System.out.println(MostrarLista(vproductos));
                        break;
                    case "2":
                        AgregarStock(vproductos);
                        break;
                    case "3":
                        System.out.println("Salida del Sistema ");
                        break;
                    default:
                        System.out.println("No existe esta opcion ");
                        break;
                }
            }
        } while (opcion.compareTo("3") != 0);
    }

    public static void MenuPrincipal(String[][] vproductos, String[][] vventas) throws IOException {
        String[] datosmenuprincipal = { "1.-Productos ", "2.-Punto de Venta ", "3.- Inventario", "4.-Ventas", "5.-Salida " };
        String opcion = "0";
        String idticket;
        do {
            idticket = ObtenerUltimoValorVentas(vventas);
            opcion = DesplegarMenu("Menu de Punto de Venta - Shingu Coffee Shop", datosmenuprincipal);
            if (opcion == null) {
                System.out.println("opcion incorrecta ");
            } else {
                switch (opcion) {
                    case "1":
                        MenuProductos(vproductos);
                        break;
                    case "2":
                        MenuPuntoVenta(vventas, idticket, vproductos);
                        break;
                    case "3":
                        MenuInventario(vproductos);
                        break;
                    case "4":
                        System.out.println(MostrarListaVentas(vventas));
                        break;
                    case "5":
                        System.out.println("Salida del Sistema ");
                        break;
                    default:
                        System.out.println("No existe esta opcion ");
                        break;
                }
            }
        } while (opcion.compareTo("5") != 0);
    }

    public static String ObtenerUltimoValorVentas(String[][] ventas) {
        int ultimaposicion = ObtenerUltimaPosicion(ventas);
        String ultimoValor = "000";
        if (ultimaposicion >= 0) {
            ultimoValor = ventas[ultimaposicion][0];
        }
        return ultimoValor;
    }

    public static void main(String[] args) throws IOException {
        productos = CargarProductos();
        ventas = CrearVenta();
        MenuPrincipal(productos, ventas);
    }
}
