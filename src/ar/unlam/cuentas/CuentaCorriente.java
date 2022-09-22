package ar.unlam.cuentas;

import ar.unlam.abstractas.Cuenta;

public class CuentaCorriente extends Cuenta {

	private Double saldoDescubierto;

	public CuentaCorriente(String nombreDelTitular) {
		super(nombreDelTitular);

	}

	public CuentaCorriente(String nombreDelTitular, Double saldoInicial) {
		super(nombreDelTitular, saldoInicial);

	}

	@Override
	public void extraer(Double importe) {

	}

	@Override
	public void transferir(Cuenta destino, Double importe) {

	}

	public void setSaldoDescubierto(Double saldoDescubierto) {
		this.saldoDescubierto = saldoDescubierto;
	}

	public Double getSaldoDescubierto() {
		return saldoDescubierto;
	}
	
	

}
