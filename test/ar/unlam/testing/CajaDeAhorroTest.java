package ar.unlam.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.unlam.abstractas.Cuenta;
import ar.unlam.cuentas.CajaDeAhorro;

public class CajaDeAhorroTest {

    /*
     * Este atributo se implementa en el test
     * "queSeCobreComisionAPartirDeLaQuintaExtraccion". Nos permite agregarle
     * extracciones y corroborar que se aplica la comisión de extracción.
     */
    private CajaDeAhorro fakeCuenta;

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

    @Test
    public void queSeCobreComisionAPartirDeLaQuintaExtraccion() {
	fakeCuenta = dadoQueExisteUnaCajaDeAhorroConSaldo(1000.00);

	// Cuando realizo una extraccion a partir de la 5ta...
	cuandoHagoUnaExtraccionDespuesDeLaQuintaEnLaCajaDeAhorro(fakeCuenta, 100.00);

	// Entonces se tiene que cobrar un 5% adicional de la extracción.
	entoncesSuSaldoEs(394.00, fakeCuenta);
    }
    
    @Test
    public void queSePuedaRealizarUnaTransferenciaAOtraCuenta() {
	CajaDeAhorro cuentaOrigen = dadoQueExisteUnaCajaDeAhorroConSaldo(500.00);
	CajaDeAhorro cuentaDestino = dadoQueExisteUnaCajaDeAhorro();
	cuandoRealizoUnaTransferenciaDesde(cuentaOrigen, cuentaDestino, 250.00);
	entoncesSuSaldoEs(250.00, cuentaOrigen);
	entoncesSuSaldoEs(250.00, cuentaDestino);
    }
    
    @Test
    public void queNoSePuedaTransferirUnImporteMayorAlSaldoActual() {
	CajaDeAhorro cuentaOrigen = dadoQueExisteUnaCajaDeAhorroConSaldo(500.00);
	CajaDeAhorro cuentaDestino = dadoQueExisteUnaCajaDeAhorro();
	
	// Cuando intento transferir un importe mayor al que tengo en la cuenta..
	cuandoRealizoUnaTransferenciaDesde(cuentaOrigen, cuentaDestino, 550.00);
	
	// Entonces no se debe permitir ejectuar la transacción.
	// Los saldos no deben alterarse.
	entoncesSuSaldoEs(500.00, cuentaOrigen);
	entoncesSuSaldoEs(0.00, cuentaDestino);
    }
    
    @Test
    public void queNoSePuedaTransferirUnImporteNegativo() {
	CajaDeAhorro cuentaOrigen = dadoQueExisteUnaCajaDeAhorroConSaldo(500.00);
	CajaDeAhorro cuentaDestino = dadoQueExisteUnaCajaDeAhorro();
	
	// Cuando intento transferir un importe negativo...
	cuandoRealizoUnaTransferenciaDesde(cuentaOrigen, cuentaDestino, -50.00);
	
	// Entonces no se debe permitir ejectuar la transacción.
	// Los saldos no deben alterarse.
	entoncesSuSaldoEs(500.00, cuentaOrigen);
	entoncesSuSaldoEs(0.00, cuentaDestino);
    }


    private void cuandoRealizoUnaTransferenciaDesde(CajaDeAhorro cuentaOrigen, CajaDeAhorro cuentaDestino, Double importe) {
	cuentaOrigen.transferir(cuentaDestino, importe);
    }

    private void cuandoHagoUnaExtraccionDespuesDeLaQuintaEnLaCajaDeAhorro(CajaDeAhorro fake, Double importe) {
	agregarExtraccionesEnFakeCuenta();
	fakeCuenta.extraer(importe);
    }

    private void cuandoExtraigoSaldoDeLaCajaDeAhorro(CajaDeAhorro nueva, Double importe) {
	nueva.extraer(importe);
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

    private void agregarExtraccionesEnFakeCuenta() {
	for (Integer i = 0; i < 5; i++) {
	    fakeCuenta.extraer(100.00);
	}
    }

}
