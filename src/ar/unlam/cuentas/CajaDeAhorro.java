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
	if (elSaldoDeLaCuentaEsMayor(importeADebitar)) {
	    ejecutarExtraccion(importeADebitar);
	}
    }
    
    @Override
    public void transferir(Cuenta destino, Double importeATransferir) {
	if(elSaldoDeLaCuentaEsMayor(importeATransferir)) {
	    descontarSaldoDeLaCuenta(importeATransferir);
	    destino.depositar(importeATransferir);
	}
    }
    
    private Boolean elSaldoDeLaCuentaEsMayor(Double importeADebitar) {
	return this.getSaldo() >= importeADebitar && importeADebitar > 0.0;
    }

    /*
     * A partir de la 5ta extracción se aplica el recargo de $ 6,00. De las dos
     * maneras, se descuenta saldo de la cuenta y se registra la extracción en las
     * transacciones del cliente con el importe de la operación.
     */
    private void ejecutarExtraccion(Double importeADebitar) {
	if (esLaQuintaExtraccion()) {
	    descontarSaldoDeLaCuenta(importeADebitar + COMISION_DE_EXTRACCION);
	    registrarExtraccion(importeADebitar);
	} else {
	    descontarSaldoDeLaCuenta(importeADebitar);
	    registrarExtraccion(importeADebitar);
	}
    }

    private void descontarSaldoDeLaCuenta(Double importe) {
	this.saldo -= importe;
    }

    private void registrarExtraccion(Double importeDeLaExtraccion) {
	transaccionesDeLaCuenta
		.add(new Transaccion(TipoDeTransaccion.EXTRACCION, importeDeLaExtraccion, LocalDate.now()));

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
