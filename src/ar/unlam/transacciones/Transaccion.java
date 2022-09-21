package ar.unlam.transacciones;

import java.time.LocalDate;

public class Transaccion {
    private TipoDeTransaccion transaccion;
    private Double importe;
    private LocalDate fecha;
  
    public Transaccion(TipoDeTransaccion transaccion, Double importe, LocalDate fecha) {
	this.setTransaccion(transaccion);
	this.setImporte(importe);
	this.setFecha(fecha);
    }

    public TipoDeTransaccion getTransaccion() {
	return transaccion;
    }

    public void setTransaccion(TipoDeTransaccion transaccion) {
	this.transaccion = transaccion;
    }

    public Double getImporte() {
	return importe;
    }

    public void setImporte(Double importe) {
	this.importe = importe;
    }

    public LocalDate getFecha() {
	return fecha;
    }

    public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
    }

    @Override
    public String toString() {
	return "Transaccion [transaccion: " + transaccion + ", importe: $ " + importe  + ", fecha= " + fecha + "]";
    }
    
    
    
    
}
