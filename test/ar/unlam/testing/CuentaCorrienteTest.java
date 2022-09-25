package ar.unlam.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.unlam.abstractas.Cuenta;
import ar.unlam.cuentas.CuentaCorriente;

public class CuentaCorrienteTest {

    @Test
    public void queSeCreaUnaCuentaCorrienteConSaldoCero() {
	CuentaCorriente nueva = dadoQueExisteUnaCuentaCorriente();
	entoncesSuSaldoEs(0.0, nueva);
    }

    @Test
    public void queSePuedaCrearUnaCuentaCorrienteConSaldoYDescubierto() {
	CuentaCorriente nueva = dadoQueExisteUnaCuentaCorrienteConSaldoYDescubierto(100.00, 100.00);
	entoncesSuSaldoEs(100.00, nueva);
	entoncesSuSaldoDescubiertoEs(100.00, nueva);
    }
    
    @Test
    public void quePuedaExtraerSaldoDeLaCuentaCorriente() {
	CuentaCorriente nueva = dadoQueExisteUnaCuentaCorrienteConSaldoYDescubierto(200.00,100.00);
	cuandoExtraigoSaldoDeLaCuentaCorriente(nueva, 100.00);
	entoncesSuSaldoEs(100.00, nueva);
    }
    
    @Test
    public void quePuedaExtrarSaldoEnDescubiertoYSeCobreComision() {
	CuentaCorriente nueva = dadoQueExisteUnaCuentaCorrienteConSaldoYDescubierto(100.00, 100.00);
	cuandoExtraigoSaldoDeLaCuentaCorriente(nueva, 200.00);
	entoncesSuSaldoEs(-105.00, nueva);
	entoncesSuSaldoDescubiertoEs(0.00, nueva);
    }
    
    @Test
    public void queNoSePuedaExtraerSaldoNegativo() {
	CuentaCorriente nueva = dadoQueExisteUnaCuentaCorrienteConSaldoYDescubierto(200.00, 100.00);
	
	// Cuando intento extraer saldo negativo de la cuenta...
	cuandoExtraigoSaldoDeLaCuentaCorriente(nueva, -100.00);
	
	// Entonces no debe haber cambios en el saldo.
	entoncesSuSaldoEs(200.00, nueva);
    }
    
    @Test
    public void queNoExcedaElLimiteDeExtraccion() {
	CuentaCorriente nueva = dadoQueExisteUnaCuentaCorrienteConSaldoYDescubierto(100.00, 100.00);
	cuandoExtraigoSaldoDeLaCuentaCorriente(nueva, 300.00);
	entoncesSuSaldoEs(100.00, nueva);
	entoncesSuSaldoDescubiertoEs(100.00, nueva);
    }
    
    @Test
    public void quePuedaHacerUnaTransferenciaAOtraCuenta() {
	CuentaCorriente cuentaOrigen = dadoQueExisteUnaCuentaCorrienteConSaldoYDescubierto(100.00, 100.00);
	CuentaCorriente cuentaDestino = dadoQueExisteUnaCuentaCorriente();
	cuandoTransfieroDineroDesde(cuentaOrigen, cuentaDestino, 50.00D);
	entoncesSuSaldoEs(50.00, cuentaOrigen);
	entoncesSuSaldoEs(50.00, cuentaDestino);
    }
    
    @Test
    public void queNoPuedaTransferirUnImporteNegativo() {
	CuentaCorriente cuentaOrigen = dadoQueExisteUnaCuentaCorrienteConSaldoYDescubierto(100.00, 100.00);
	CuentaCorriente cuentaDestino = dadoQueExisteUnaCuentaCorriente();
	cuandoTransfieroDineroDesde(cuentaOrigen, cuentaDestino, -50.00);
	entoncesSuSaldoEs(100.00, cuentaOrigen);
	entoncesSuSaldoEs(0.00, cuentaDestino);
    }
    
    @Test
    public void quePuedaDepositarEnLaCuentaCorriente() {
	CuentaCorriente nueva = dadoQueExisteUnaCuentaCorriente();
	cuandoDepositoSaldoEnLaCuenta(nueva, 100.00);
	entoncesSuSaldoEs(100.00, nueva);
    }
    
    
    @Test
    public void queNoPuedaDepositarImporteNegativo() {
	CuentaCorriente nueva = dadoQueExisteUnaCuentaCorriente();
	cuandoDepositoSaldoEnLaCuenta(nueva, -500.00);
	entoncesSuSaldoEs(0.00, nueva);
    }

    private void cuandoDepositoSaldoEnLaCuenta(CuentaCorriente nueva, Double importe) {
	nueva.depositar(importe);
	
    }

    private void cuandoTransfieroDineroDesde(CuentaCorriente cuentaOrigen, Cuenta cuentaDestino, Double importe) {
	cuentaOrigen.transferir(cuentaDestino, importe);
    }

    private void cuandoExtraigoSaldoDeLaCuentaCorriente(CuentaCorriente actual, Double saldoAExtraer) {
	actual.extraer(saldoAExtraer);	
    }
 
    private void entoncesSuSaldoDescubiertoEs(Double saldo, CuentaCorriente nueva) {
	assertEquals(saldo, nueva.getSaldoDescubierto());
    }
    
    private CuentaCorriente dadoQueExisteUnaCuentaCorrienteConSaldoYDescubierto(Double saldoInicial, Double saldoDescubierto) {
	return new CuentaCorriente("Franco Skurnik", saldoInicial, saldoDescubierto);
    }

    private void entoncesSuSaldoEs(Double saldo, CuentaCorriente cuentaConSaldo) {
	assertEquals(saldo, cuentaConSaldo.getSaldo());
    }

    private CuentaCorriente dadoQueExisteUnaCuentaCorriente() {
	return new CuentaCorriente("Franco Skurnik");
    }
}
