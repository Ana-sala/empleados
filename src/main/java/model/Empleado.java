package model;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // Crea el constructor con todos los atributos

public abstract class Empleado {
    protected String nombre, documento;
    protected int edad;
    protected float salarioBase;
    protected CategoriaEmpleado categoria;
    protected float descuentoSalud;
    protected float descuentoPension;

    public Empleado(String nombre, String documento, int edad,
                    float salarioBase, CategoriaEmpleado categoria,
                    float descuentoSalud, float descuentoPension) {
        if (salarioBase < 0) throw new IllegalArgumentException("Salario no puede ser negativo");
        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
        this.salarioBase = salarioBase;
        this.categoria = categoria;
        this.descuentoSalud = descuentoSalud;
        this.descuentoPension = descuentoPension;
    }
    // Método abstracto: cada hijo decide cómo se calcula
    public abstract float calcularSalarioBruto();

    public float calcularBonificacionCategoria() {
        return switch (categoria) {
            case JUNIOR -> salarioBase * 0.05f;
            case SEMI_SENIOR -> salarioBase * 0.10f;
            case SENIOR -> salarioBase * 0.15f;
        };
    }

    public float calcularDescuentos() {
        float totalPorcentaje = (descuentoSalud + descuentoPension) / 100;
        return calcularSalarioBruto() * totalPorcentaje;
    }

    public float calcularSalarioNeto() {
        return calcularSalarioBruto() - calcularDescuentos();
    }

    public ResumenPago generarResumenPago() {
        return new ResumenPago(documento, nombre, this.getClass().getSimpleName(),
                calcularSalarioBruto(), calcularDescuentos(), calcularSalarioNeto());
    }

}
