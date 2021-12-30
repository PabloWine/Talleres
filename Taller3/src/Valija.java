public class Valija extends Envio {
    private String material;

    public Valija(String codigo, double peso, String material) {
        super(codigo, peso);
        this.material = material;
    }

    @Override
    public double valor() {
        double precioMaterial = 0;
        if (material.equalsIgnoreCase("Cuero")) {
            precioMaterial = 200;
        } else if (material.equalsIgnoreCase("plastico")) {
            precioMaterial = 150;
        } else if (material.equalsIgnoreCase("Tela")) {
            precioMaterial = 100;
        }
        return getPeso() * 150 * precioMaterial;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}