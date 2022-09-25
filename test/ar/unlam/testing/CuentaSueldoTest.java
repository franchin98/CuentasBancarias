package ar.unlam.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.unlam.abstractas.Cuenta;
import ar.unlam.cuentas.CuentaSueldo;

public class CuentaSueldoTest {

    @Test
    public void queSePuedaCrearUnaCuentaSueldoSinSaldo() {
	CuentaSueldo nueva = dadoQueExisteUnaCuentaSueldo();
	entoncesSuSaldoEs(0.00, nueva);
    }

    @Test
    public void queSePuedaCrearUnaCuentaSueldoConSaldo() {
	CuentaSueldo nueva = dadoQueExisteUnaCuentaSueldoConSaldo(100.00);
	entoncesSuSaldoEs(100.00, nueva);
    }

    @Test
    public void queSePuedaDepositarSaldoEnLaCuentaSueldo() {
	CuentaSueldo nueva = dadoQueExisteUnaCuentaSueldo();
	cuandoDepositoSaldoEnLaCuentaSueldo(nueva, 200.00);
	entoncesSuSaldoEs(200.00, nueva);
    }

    @Test
    public void queNoPuedaDepositarUnImporteNegativo() {
	CuentaSueldo nueva = dadoQueExisteUnaCuentaSueldo();
	cuandoDepositoSaldoEnLaCuentaSueldo(nueva, -100.00);
	entoncesSuSaldoEs(0.00, nueva);
    }

    @Test
    public void quePuedaExtraerDineroDeLaCuentaSueldo() {
	CuentaSueldo nueva = dadoQueExisteUnaCuentaSueldoConSaldo(1000.00);
	cuandoExtraigoSaldoDeLaCuenta(nueva, 500.00);
	entoncesSuSaldoEs(500.00, nueva);
    }

    @Test
    public void queNoPuedaExtraerUnImporteMayorAlSaldoActual() {
	CuentaSueldo nueva = dadoQueExisteUnaCuentaSueldoConSaldo(1000.00);
	cuandoExtraigoSaldoDeLaCuenta(nueva, 2000.00);
	entoncesSuSaldoEs(1000.00, nueva);
    }

    @Test
    public void queNoPuedaExtraerUnImporteNegativo() {
	CuentaSueldo nueva = dadoQueExisteUnaCuentaSueldoConSaldo(1000.00);
	cuandoExtraigoSaldoDeLaCuenta(nueva, -500.00);
	entoncesSuSaldoEs(1000.00, nueva);
    }

    @Test
    public void quePuedaTransferirAOtraCuenta() {
	CuentaSueldo cuentaOrigen = dadoQueExisteUnaCuentaSueldoConSaldo(1000.00);
	CuentaSueldo cuentaDestino = dadoQueExisteUnaCuentaSueldo();
	cuandoTransfieroDesde(cuentaOrigen, cuentaDestino, 500.00);
	entoncesSuSaldoEs(500.00, cuentaOrigen);
	entoncesSuSaldoEs(500.00, cuentaDestino);
    }

    @Test
    public void queNoPuedaTransferirUnImporteNegativo() {
	CuentaSueldo cuentaOrigen = dadoQueExisteUnaCuentaSueldoConSaldo(1000.00);
	CuentaSueldo cuentaDestino = dadoQueExisteUnaCuentaSueldo();
	cuandoTransfieroDesde(cuentaOrigen, cuentaDestino, -500.00);
	entoncesSuSaldoEs(1000.00, cuentaOrigen);
	entoncesSuSaldoEs(0.00, cuentaDestino);
    }

    @Test
    public void queNoPuedaTransferirUnImporteMayorAlSaldoActual() {
	CuentaSueldo cuentaOrigen = dadoQueExisteUnaCuentaSueldoConSaldo(1000.00);
	CuentaSueldo cuentaDestino = dadoQueExisteUnaCuentaSueldo();
	cuandoTransfieroDesde(cuentaOrigen, cuentaDestino, 2000.00);
	entoncesSuSaldoEs(1000.00, cuentaOrigen);
	entoncesSuSaldoEs(0.00, cuentaDestino);
    }

    private void cuandoTransfieroDesde(CuentaSueldo cuentaOrigen, Cuenta cuentaDestino, Double importe_A_Transferir) {
	cuentaOrigen.transferir(cuentaDestino, importe_A_Transferir);

    }

    private void cuandoExtraigoSaldoDeLaCuenta(CuentaSueldo actual, Double importe) {
	actual.extraer(importe);
    }

    private void cuandoDepositoSaldoEnLaCuentaSueldo(CuentaSueldo nueva, Double importe) {
	nueva.depositar(importe);

    }

    private CuentaSueldo dadoQueExisteUnaCuentaSueldoConSaldo(Double saldoInicial) {
	return new CuentaSueldo("Franco Skurnik", saldoInicial);
    }

    private void entoncesSuSaldoEs(Double saldoActual, CuentaSueldo actual) {
	assertEquals(saldoActual, actual.getSaldo());
    }

    private CuentaSueldo dadoQueExisteUnaCuentaSueldo() {
	return new CuentaSueldo("Franco Skurnik");
    }
}
