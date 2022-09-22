package ar.unlam.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.unlam.cuentas.CuentaCorriente;

public class CuentaCorrienteTest {

	@Test
	public void queSeCreaUnaCuentaCorrienteConSaldoCero() {
		CuentaCorriente nueva = dadoQueExisteUnaCuentaCorriente();
		entoncesSuSaldoEs(0.0, nueva);
	}
	
	@Test
	public void queSePuedaCrearUnaCuentaCorrienteConSaldo() {
		CuentaCorriente nueva = dadoQueExisteUnaCuentaCorrienteConSaldo(100.00);
		entoncesSuSaldoEs(100.00, nueva);
	}
	
	@Test
	public void queSePuedaCrearUnaCuentaCorrienteConAcuerdoEnDescubierto() {
		CuentaCorriente nueva = dadoQueExisteUnaCuentaCorriente();
		cuandoEsteblecenSaldoEnDescubiertoEnLaCuenta(nueva, 1000.00);
	}

	private void cuandoEsteblecenSaldoEnDescubiertoEnLaCuenta(CuentaCorriente nueva, Double saldoDescubierto) {
		nueva.setSaldoDescubierto(saldoDescubierto);
		
	}

	private CuentaCorriente dadoQueExisteUnaCuentaCorrienteConSaldo(Double saldoInicial) {
		return new CuentaCorriente("Franco Skurnik", saldoInicial);
	}

	private void entoncesSuSaldoEs(Double saldo, CuentaCorriente cuentaConSaldo) {
		assertEquals(saldo, cuentaConSaldo.getSaldo());
		
	}

	private CuentaCorriente dadoQueExisteUnaCuentaCorriente() {
		return new CuentaCorriente("Franco Skurnik");
	}
}
