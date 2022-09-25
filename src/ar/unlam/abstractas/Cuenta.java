package ar.unlam.abstractas;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import ar.unlam.transacciones.TipoDeTransaccion;
import ar.unlam.transacciones.Transaccion;

public abstract class Cuenta {

    protected Double saldo;
    protected List<Transaccion> transaccionesDeLaCuenta;
    private String nombreDeltitular;

    public Cuenta(String nombreDelTitular) {
	setSaldo(0.0);
	this.setNombreDeltitular(nombreDelTitular);
	transaccionesDeLaCuenta = new LinkedList<Transaccion>();
    }

    public Cuenta(String nombreDelTitular, Double saldoInicial) {
	this.setNombreDeltitular(nombreDelTitular);
	setSaldo(saldoInicial);
	transaccionesDeLaCuenta = new LinkedList<Transaccion>();
    }

    public String getNombreDeltitular() {
	return nombreDeltitular;
    }

    public Double getSaldo() {
	return saldo;
    }

    public void depositar(Double importe) {
	if (importe > 0.00) {
	    this.saldo += importe;
	    transaccionesDeLaCuenta.add(new Transaccion(TipoDeTransaccion.DEPOSITO, importe, LocalDate.now()));
	}
    }

    public void consultarMovimientos() {
	for (Transaccion actual : transaccionesDeLaCuenta) {
	    if (actual != null)
		System.out.println(actual);
	}
    }

    public abstract void extraer(Double importe);

    public abstract void transferir(Cuenta destino, Double importe);

    private void setSaldo(Double saldo) {
	this.saldo = saldo;
    }

    private void setNombreDeltitular(String nombreDeltitular) {
	this.nombreDeltitular = nombreDeltitular;
    }
}
