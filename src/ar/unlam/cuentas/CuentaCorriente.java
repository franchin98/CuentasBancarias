package ar.unlam.cuentas;

import ar.unlam.abstractas.Cuenta;

public final class CuentaCorriente extends Cuenta {

    private final Double COMISION_USO_SALDO_DESCUBIERTO = 0.05D;

    private Double saldoDescubierto;

    public CuentaCorriente(String nombreDelTitular) {
	super(nombreDelTitular);
	saldoDescubierto = 0.00D;
    }

    public CuentaCorriente(String nombreDelTitular, Double saldoInicial, Double saldoDescubierto) {
	super(nombreDelTitular, saldoInicial);
	this.saldoDescubierto = saldoDescubierto;

    }

    @Override
    public void extraer(Double importe) {
	if (elSaldoEsSuficienteParaDebitar(importe) && noEsNegativo(importe))
	    descontarSaldoDeLaCuenta(importe);
	else if (saldoYDescubiertoEsSuficienteParaDebitar(importe) && noEsNegativo(importe))
	    descontarSaldoYDescubierto(importe);
    }

    @Override
    public void transferir(Cuenta destino, Double importe) {
	if (elSaldoEsSuficienteParaDebitar(importe) && noEsNegativo(importe)) {
	    this.descontarSaldoDeLaCuenta(importe);
	    destino.depositar(importe);
	}
    }

    public void setSaldoDescubierto(Double saldoDescubierto) {
	this.saldoDescubierto = saldoDescubierto;
    }

    public Double getSaldoDescubierto() {
	return saldoDescubierto;
    }

    private void descontarSaldoYDescubierto(Double importe) {
	saldoDescubierto -= Math.abs(this.saldo - importe);
	descontarSaldoDeLaCuenta(importe + ((Math.abs(this.saldo - importe) * COMISION_USO_SALDO_DESCUBIERTO)));
    }

    private Boolean elSaldoEsSuficienteParaDebitar(Double importeADebitar) {
	return this.saldo >= importeADebitar;
    }

    private Boolean noEsNegativo(Double importe) {
	return !(importe <= 0.00D);
    }

    private Boolean saldoYDescubiertoEsSuficienteParaDebitar(Double importe) {
	return (saldo + saldoDescubierto) >= importe;
    }

    private void descontarSaldoDeLaCuenta(Double importeADebitar) {
	this.saldo -= importeADebitar;
    }

}
