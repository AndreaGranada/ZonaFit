package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }
    private static void zonaFitApp(){
        var salir = false;
        var consola = new Scanner(System.in);
        // Creamos un objeto de la clase clienteDAO
        var clienteDao = new ClienteDAO();
        while(!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, clienteDao);

            } catch (Exception e){
                System.out.println("Error al ejecutar opciones");
            }
            System.out.println();
        }
    }

    private  static  int mostrarMenu(Scanner consola){
        System.out.print("""
                *** Zona Fit (Gym) ***
                1. Listar Clientes
                2. Buscar Clientes
                3. Agregar Clientes
                4. Modificar Clientes
                5. Eliminar Clientes
                6. Salir
                Elige una opción: \s""");

        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion, IClienteDAO clienteDAO){
        var salir = false;
        switch (opcion){
            case 1 -> {
                //1. Listar clientes
                System.out.println("--- Listado de Clientes ---");
                var clientes = clienteDAO.listarCliente();
                clientes.forEach(System.out::println);
            }
            case 2 -> {
                // 2. Buscar cliente por id
                System.out.print("Introduce el id del cliente a buscar: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var encontrado = clienteDAO.buscarClientePorId(cliente);
                if(encontrado){
                    System.out.println("Cliente encontrado: " + cliente);
                } else {
                    System.out.println("Cliente no encontrado: " + cliente);
                }
            }
            case 3 -> {
                // 3. Agregar un cliente
                System.out.println("--- Agregar cliente ---");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                // Creamos el objeto cliente(sin el id)
                var cliente = new Cliente(nombre, apellido, membresia);
                var agregado = clienteDAO.agregarCliente(cliente);
                if(agregado){
                    System.out.println("Cliente agregago: " + cliente);
                } else {
                    System.out.println("Cliente no agregado: " + cliente);
                }
            }
            case 4 -> {
                // 4. Modificar un cliente
                System.out.println("--- Modificar cliente ---");
                System.out.print("Id cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                // Creamos el objeto a modificar
                var cliente = new Cliente(idCliente, nombre, apellido, membresia);
                var modificado = clienteDAO.modificarCliente(cliente);
                if(modificado){
                    System.out.println("Cliente modificado: " + cliente);
                } else {
                    System.out.println("Cliente no modificado: " + cliente);
                }
            }
            case 5 -> {
                // 5. Eliminar cliente
                System.out.println("--- eliminar cliente ---");
                System.out.print("Id Cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var eliminado = clienteDAO.eliminarCliente(cliente);
                if(eliminado){
                    System.out.println("Cliente eliminado " + cliente);
                } else {
                    System.out.println("Cliente no eliminado " + cliente);
                }
            }
            case 6 -> {
                System.out.println("Hasta pronto");
                salir = true;
            }
            default -> System.out.println("Opcion no reconocida: " + opcion);
        }
        return salir;
    }
}
