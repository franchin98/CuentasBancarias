package ar.unlam.cuentas;

import java.time.LocalDate;
import java.util.Iterator;

import ar.unlam.abstractas.Cuenta;
import ar.unlam.transacciones.TipoDeTransaccion;
import ar.unlam.transacciones.Transaccion;

public final class CajaDeAhorro extends Cuenta {

    private final Double COMISION_DE_EXTRACCION = 6.00;

    public CajaDeAhorro(String nombreDelTitular) {
	super(nombreDelTitular);
    }

    public CajaDeAhorro(String nombreDelTitular, Double saldoInicial) {
	super(nombreDelTitular, saldoInicial);
    }

    @Override
    public void extraer(Double importeADebitar) {
	if (elSaldoDeLaCuentaEsSuficiente(importeADebitar)) {
	    ejecutarExtraccion(importeADebitar);
	    registrarExtraccion(importeADebitar);
	}
    }

    @Override
    public void transferir(Cuenta destino, Double importeATransferir) {
	if (elSaldoDeLaCuentaEsSuficiente(importeATransferir)) {
	    descontarSaldoDeLaCuenta(importeATransferir);
	    destino.depositar(importeATransferir);
	    registrarTransferencia(importeATransferir);
	}
    }

    private Boolean elSaldoDeLaCuentaEsSuficiente(Double importeADebitar) {
	return this.getSaldo() >= importeADebitar && importeADebitar > 0.0;
    }

    /*
     * A partir de la 5ta extracción se aplica el recargo de $ 6,00.
     */
    private void ejecutarExtraccion(Double importeADebitar) {
	if (esLaQuintaExtraccion()) {
	    descontarSaldoDeLaCuenta(importeADebitar + COMISION_DE_EXTRACCION);
	} else {
	    descontarSaldoDeLaCuenta(importeADebitar);
	}
    }

    private void descontarSaldoDeLaCuenta(Double importe) {
	this.saldo -= importe;
    }

    private void registrarExtraccion(Double importeDeLaExtraccion) {
	transaccionesDeLaCuenta
		.add(new Transaccion(TipoDeTransaccion.EXTRACCION, importeDeLaExtraccion, LocalDate.now()));
    }

    private void registrarTransferencia(Double importeDeLaTransferencia) {
	transaccionesDeLaCuenta
		.add(new Transaccion(TipoDeTransaccion.TRANSFERENCIA, importeDeLaTransferencia, LocalDate.now()));
    }

    private Boolean esLaQuintaExtraccion() {
	Iterator<Transaccion> iteradorDeLaLista = transaccionesDeLaCuenta.iterator();
	Integer cantExtracciones = 0;

	while (iteradorDeLaLista.hasNext()) {
	    Transaccion auxiliar = iteradorDeLaLista.next();
	    if (auxiliar.getTransaccion().equals(TipoDeTransaccion.EXTRACCION)) {
		cantExtracciones++;
		if (cantExtracciones.equals(5))
		    return true;
	    }
	}
	return false;
    }

}
