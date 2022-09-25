package ar.unlam.cuentas;

import java.time.LocalDate;

import ar.unlam.abstractas.Cuenta;
import ar.unlam.transacciones.TipoDeTransaccion;
import ar.unlam.transacciones.Transaccion;

public final class CuentaSueldo extends Cuenta {

    public CuentaSueldo(String nombreDelTitular) {
	super(nombreDelTitular);
    }

    public CuentaSueldo(String nombreDelTitular, Double saldoInicial) {
	super(nombreDelTitular, saldoInicial);
    }

    @Override
    public void extraer(Double importe) {
	if (elSaldoEsSuficienteParaDebitar(importe)) {
	    this.saldo -= importe;
	    registrarExtraccion(importe);
	}
    }

    @Override
    public void transferir(Cuenta destino, Double importe) {
	if (elSaldoEsSuficienteParaDebitar(importe)) {
	    this.saldo -= importe;
	    destino.depositar(importe);
	    registrarTransferencia(importe);
	}
    }

    private Boolean elSaldoEsSuficienteParaDebitar(Double importe) {
	return saldo >= importe && !(importe < 0.00);
    }

    private void registrarTransferencia(Double importeDeLaTransferencia) {
	transaccionesDeLaCuenta
		.add(new Transaccion(TipoDeTransaccion.TRANSFERENCIA, importeDeLaTransferencia, LocalDate.now()));
    }

    private void registrarExtraccion(Double importeDeLaExtraccion) {
	transaccionesDeLaCuenta
		.add(new Transaccion(TipoDeTransaccion.EXTRACCION, importeDeLaExtraccion, LocalDate.now()));
    }

}
