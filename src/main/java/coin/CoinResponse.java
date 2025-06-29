package coin;

import java.util.List;

public class CoinResponse {
	private List<Double> output;
	private List<Double>coinDenoms;
	
	public CoinResponse(List<Double> output, List<Double>coinDenoms) {
		this.output = output;
		this.coinDenoms = coinDenoms;
	}

	public List<Double> getOutput() {
		return output;
	}

	public void setOutput(List<Double> output) {
		this.output = output;
	}

	public List<Double> getCoinDenoms() {
		return coinDenoms;
	}

	public void setCoinDenoms(List<Double> coinDenoms) {
		this.coinDenoms = coinDenoms;
	}

}
