package model;
import lombok.Getter;

@Getter
public class EmpleadoTemporal extends Empleado {
    private int diasTrabajados;
    private float valorDia;

    public EmpleadoTemporal(int diasTrabajados, float valorDia, String nombre, String documento, int edad, float salarioBase, CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension){
    super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);
        if (diasTrabajados < 0 || valorDia < 0) throw new IllegalArgumentException("Días o valor negativo");
        this.diasTrabajados = diasTrabajados;
        this.valorDia = valorDia;
    }
    @Override
    public float calcularSalarioBruto() {
        return (diasTrabajados * valorDia) + calcularBonificacionCategoria();
    }

    @Override
    public String toString() {
        return "EmpleadoTemporal{" +
                "diasTrabajados=" + diasTrabajados +
                ", valorDia=" + valorDia +
                '}';
    }
}
