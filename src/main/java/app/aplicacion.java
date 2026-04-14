package app;
import model.*;
import java.util.Scanner;
import java.util.Optional;

public class aplicacion {
    public static void main(String[] args) {
        Empresa miEmpresa = new Empresa("Uniquindio");
        Scanner lector = new Scanner(System.in);
        int opcion = 0;

        // Menú principal
        while (opcion != 9) {
            System.out.println("\n--- MENU DE NOMINA ---");
            System.out.println("1. Agregar Empleado Planta");
            System.out.println("2. Agregar Empleado Ventas");
            System.out.println("3. Agregar Empleado Temporal");
            System.out.println("4. Mostrar Todos los Empleados");
            System.out.println("5. Buscar por Documento");
            System.out.println("6. Mostrar el que mas gana");
            System.out.println("7. Ver Nomina Total");
            System.out.println("8. Ver Resumenes de Pago (Records)");
            System.out.println("9. Salir");
            System.out.print("Elija una opcion: ");

            opcion = lector.nextInt();
            lector.nextLine(); // Limpiar el teclado

            switch (opcion) {
                case 1: // Empleado de Planta
                    System.out.print("Nombre: "); String n1 = lector.nextLine();
                    System.out.print("Documento: "); String d1 = lector.nextLine();
                    System.out.print("Edad: "); int e1 = lector.nextInt();
                    System.out.print("Salario Base: "); float s1 = lector.nextFloat();
                    lector.nextLine();
                    System.out.print("Categoria (JUNIOR, SEMI_SENIOR, SENIOR): ");
                    CategoriaEmpleado c1 = CategoriaEmpleado.valueOf(lector.nextLine().toUpperCase());
                    System.out.print("Cargo: "); String cargo = lector.nextLine();
                    System.out.print("Horas Extra: "); int he = lector.nextInt();
                    System.out.print("Valor Hora Extra: "); float vhe = lector.nextFloat();

                    // Se agrega con el auxilio de transporte del taller
                    miEmpresa.agregarEmpleado(new EmpleadoPlanta(n1, d1, e1, s1, c1, 4, 4, cargo, he, vhe, 117000));
                    break;

                case 2: // Empleado de Ventas
                    System.out.print("Nombre: "); String n2 = lector.nextLine();
                    System.out.print("Documento: "); String d2 = lector.nextLine();
                    System.out.print("Edad: "); int e2 = lector.nextInt();
                    System.out.print("Salario Base: "); float s2 = lector.nextFloat();
                    lector.nextLine();
                    System.out.print("Categoria: ");
                    CategoriaEmpleado c2 = CategoriaEmpleado.valueOf(lector.nextLine().toUpperCase());
                    System.out.print("Total Ventas: "); float tv = lector.nextFloat();
                    System.out.print("Porcentaje Comision: "); float pc = lector.nextFloat();

                    miEmpresa.agregarEmpleado(new EmpleadoVentas(n2, d2, e2, s2, c2, 4, 4, tv, pc));
                    break;

                case 3: // Empleado Temporal
                    System.out.print("Nombre: "); String n3 = lector.nextLine();
                    System.out.print("Documento: "); String d3 = lector.nextLine();
                    System.out.print("Edad: "); int e3 = lector.nextInt();
                    System.out.print("Salario Base: "); float s3 = lector.nextFloat();
                    lector.nextLine();
                    System.out.print("Categoria: ");
                    CategoriaEmpleado c3 = CategoriaEmpleado.valueOf(lector.nextLine().toUpperCase());
                    System.out.print("Dias Trabajados: "); int dt = lector.nextInt();
                    System.out.print("Valor Dia: "); float vd = lector.nextFloat();

                    miEmpresa.agregarEmpleado(new EmpleadoTemporal(n3, d3, e3, s3, c3, 4, 4, dt, vd));
                    break;

                case 4: // Listar
                    System.out.println("\nLISTA GENERAL:");
                    for (Empleado emp : miEmpresa.getListaEmpleados()) {
                        System.out.println(emp.getNombre() + " (" + emp.getDocumento() + ")");
                    }
                    break;

                case 5: // Buscar empleado
                    System.out.print("Ingrese documento: ");
                    String id = lector.nextLine();
                    // Usamos Optional para evitar errores si no existe [cite: 119]
                    miEmpresa.buscarEmpleado(id).ifPresentOrElse(
                            e -> System.out.println("Encontrado: " + e.getNombre()),
                            () -> System.out.println("Empleado no registrado.")
                    );
                    break;

                case 6: // El que más gana
                    miEmpresa.obtenerMayorSalario().ifPresent(e ->
                            System.out.println("El que mas gana es " + e.getNombre() + " con $" + e.calcularSalarioNeto()));
                    break;

                case 7: // Nómina Total
                    System.out.println("Nomina total de la empresa: $" + miEmpresa.calcularNominaTotal());
                    break;

                case 8: // Mostrar los Records
                    System.out.println("\nRESUMENES DE PAGO:");
                    for (Empleado emp : miEmpresa.getListaEmpleados()) {
                        System.out.println(emp.generarResumenPago());
                    }
                    break;
            }
        }
    }
}