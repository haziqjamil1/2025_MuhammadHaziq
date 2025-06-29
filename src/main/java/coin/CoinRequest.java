package coin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CoinRequest {
    
	@NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be >= 0")
    @Max(value = 10000, message = "Amount must be <= 10000")
	private Double amount;
	
	public CoinRequest() {}

    public CoinRequest(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}