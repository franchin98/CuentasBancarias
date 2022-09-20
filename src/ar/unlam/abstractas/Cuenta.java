package ar.unlam.abstractas;

public abstract class Cuenta {

	protected Double saldo;
	private String nombreDeltitular;

	public Cuenta(String nombreDelTitular) {
		setSaldo(0.0);
		this.setNombreDeltitular(nombreDelTitular);
	}

	public Cuenta(String nombreDelTitular, Double saldoInicial) {
		this.setNombreDeltitular(nombreDelTitular);
		setSaldo(saldoInicial);
	}

	public String getNombreDeltitular() {
		return nombreDeltitular;
	}

	public void setNombreDeltitular(String nombreDeltitular) {
		this.nombreDeltitular = nombreDeltitular;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public void depositar(Double importe) {
		if (importe > 0.0)
			this.saldo += importe;
	}
	
	public abstract void debitar(Double importe);
}
