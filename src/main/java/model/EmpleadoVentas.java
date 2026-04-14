package model;

public class EmpleadoVentas extends Empleado{
    private float totalVentas, porcentajeComision;

    public EmpleadoVentas(float totalVentas, float porcentajeComision,String nombre, String documento, int edad, float salarioBase, CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension) {
        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);
        if (porcentajeComision < 0 || porcentajeComision > 100) throw new IllegalArgumentException("Porcentaje inválido");
        this.totalVentas = totalVentas;
        this.porcentajeComision = porcentajeComision;
    }

    @Override
    public float calcularSalarioBruto() {
        float comision = totalVentas * (porcentajeComision / 100);
        return salarioBase + calcularBonificacionCategoria() + comision;
    }
}
