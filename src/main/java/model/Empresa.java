package model;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class Empresa {
    private String nombre;
    private ArrayList<Empleado> listaEmpleados = new ArrayList<>();

    public void agregarEmpleado(Empleado e) {
        listaEmpleados.add(e);
    }

    // Uso de Optional: Si no lo encuentra, devuelve un "contenedor vacío" en lugar de un error
    public Optional<Empleado> buscarPorDocumento(String doc) {
        return listaEmpleados.stream()
                .filter(e -> e.documento.equals(doc))
                .findFirst();
    }

    public Optional<Empleado> obtenerMayorSalarioNeto() {
        return listaEmpleados.stream()
                .max(Comparator.comparing(Empleado::calcularSalarioNeto));
    }

    public float calcularNominaTotal() {
        return (float) listaEmpleados.stream()
                .mapToDouble(Empleado::calcularSalarioNeto)
                .sum();
    }

    public void mostrarTodos() {
        listaEmpleados.forEach(e -> System.out.println(e.nombre + " - " + e.documento + " - Netto: $" + e.calcularSalarioNeto()));
    }


}
