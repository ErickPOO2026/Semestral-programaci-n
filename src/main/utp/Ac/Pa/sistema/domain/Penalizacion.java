package utp.Ac.Pa.sistema.domain;

public class Penalizacion {
    private String idPenalizacion;
    private double monto;
    private String motivo;
    private boolean pagada;
    private Usuario usuario;

    public Penalizacion(String idPenalizacion, double monto, String motivo, Usuario usuario) {
        this.idPenalizacion = idPenalizacion;
        this.monto = monto;
        this.motivo = motivo;
        this.usuario = usuario;
        this.pagada = false;
        this.usuario.setEstado("SUSPENDIDO");
    }

    public void pagar() {
        this.pagada = true;
        this.usuario.setEstado("ACTIVO");
        System.out.println("[✓] Penalizacion " + idPenalizacion + " pagada. Usuario habilitado.");
    }

    public boolean isPagada() { return pagada; }
    public double getMonto() { return monto; }
    public String getIdPenalizacion() { return idPenalizacion; }
}