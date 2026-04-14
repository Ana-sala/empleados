package model;

public class EmpleadoPlanta extends Empleado {
    private String cargo;
    private int horasExtra;
    private float valorHoraExtra, auxilioTransporte;

    public EmpleadoPlanta(String cargo, int horasExtra, float valorHoraExtra, float auxilioTransporte, String nombre, String documento, int edad, float salarioBase, CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension){
        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);
        if (horasExtra < 0 || valorHoraExtra < 0) throw new IllegalArgumentException("Valores negativos no permitidos");
        this.cargo = cargo;
        this.horasExtra = horasExtra;
        this.valorHoraExtra = valorHoraExtra;
        this.auxilioTransporte = auxilioTransporte;
    }
    @Override
    public float calcularSalarioBruto() {
        return salarioBase + calcularBonificacionCategoria() + (horasExtra * valorHoraExtra) + auxilioTransporte;
    }
}
