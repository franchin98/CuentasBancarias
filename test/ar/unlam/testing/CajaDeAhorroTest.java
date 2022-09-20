package ar.unlam.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.unlam.abstractas.Cuenta;
import ar.unlam.cuentas.CajaDeAhorro;

public class CajaDeAhorroTest {

	@Test
	public void queSeCreaUnaCajaDeAhorroConSaldoEnCero() {
		CajaDeAhorro nueva = dadoQueExisteUnaCajaDeAhorro();
		entoncesSuSaldoEs(0.0, nueva);
	}

	@Test
	public void queSeCreaUnaCajaDeAhorroConSaldo() {
		CajaDeAhorro nueva = dadoQueExisteUnaCajaDeAhorroConSaldo(200.00);
		entoncesSuSaldoEs(200.00, nueva);
	}

	@Test
	public void queSePuedaDepositarSaldoEnLaCajaDeAhorro() {
		CajaDeAhorro nueva = dadoQueExisteUnaCajaDeAhorro();
		cuandoDepositoSaldoEnLaCajaDeAhorro(nueva, 200.00D);
		entoncesSuSaldoEs(200.00, nueva);
	}

	@Test
	public void queNoSePuedaDepositarSaldoNegativoALaCajaDeAhorro() {
		CajaDeAhorro nueva = dadoQueExisteUnaCajaDeAhorro();

		// Cuando deposito saldo negativo en la cuenta...
		cuandoDepositoSaldoEnLaCajaDeAhorro(nueva, -200.00D);

		// Entonces no debe permitir depositar el saldo negativo.
		entoncesSuSaldoEs(0.0, nueva);
	}

	@Test
	public void queSePuedaExtraerSaldoDeLaCajaDeAhorro() {
		CajaDeAhorro nueva = dadoQueExisteUnaCajaDeAhorroConSaldo(200.00);
		cuandoExtraigoSaldoDeLaCajaDeAhorro(nueva, 100.00);
		entoncesSuSaldoEs(100.00, nueva);
	}

	@Test
	public void queNoSePuedaExtraerSaldoNegativoDeLaCajaDeAhorros() {
		CajaDeAhorro nueva = dadoQueExisteUnaCajaDeAhorroConSaldo(200.00);

		// Cuando intento extraer saldo negativo de la cuenta...
		cuandoExtraigoSaldoDeLaCajaDeAhorro(nueva, -100.00);

		// Entonces el saldo debería ser el mismo. No debe haber cambios
		entoncesSuSaldoEs(200.00, nueva);

	}

	@Test
	public void queNoSePuedaExtraerSaldoMayorAlActual() {
		CajaDeAhorro nueva = dadoQueExisteUnaCajaDeAhorroConSaldo(200.00);

		// Cuando intento extraer saldo mayor al actual de la cuenta...
		cuandoExtraigoSaldoDeLaCajaDeAhorro(nueva, 300.00);

		// Entonces el saldo debe ser el mismo. No debe haber cambios.
		entoncesSuSaldoEs(200.00, nueva);

	}

	private void cuandoExtraigoSaldoDeLaCajaDeAhorro(CajaDeAhorro nueva, Double importe) {
		nueva.debitar(importe);

	}

	private void cuandoDepositoSaldoEnLaCajaDeAhorro(CajaDeAhorro nueva, Double importeADepositar) {
		nueva.depositar(importeADepositar);

	}

	private CajaDeAhorro dadoQueExisteUnaCajaDeAhorroConSaldo(Double saldoInicial) {
		return new CajaDeAhorro("Franco Daniel Skurnik", saldoInicial);
	}

	private CajaDeAhorro dadoQueExisteUnaCajaDeAhorro() {
		return new CajaDeAhorro("Franco Daniel Skurnik");
	}

	private void entoncesSuSaldoEs(Double saldoEsperado, Cuenta cuentaNueva) {
		assertEquals(saldoEsperado, cuentaNueva.getSaldo());
	}

}
