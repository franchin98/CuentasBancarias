package ar.unlam.cuentas;

import ar.unlam.abstractas.Cuenta;

public class CajaDeAhorro extends Cuenta {

	public CajaDeAhorro(String nombreDelTitular) {
		super(nombreDelTitular);
	}

	public CajaDeAhorro(String nombreDelTitular, Double saldoInicial) {
		super(nombreDelTitular, saldoInicial);
	}

	@Override
	public void debitar(Double importeADebitar) {
		if (this.getSaldo() >= importeADebitar && importeADebitar > 0.0)
			this.saldo -= importeADebitar;

	}

}
