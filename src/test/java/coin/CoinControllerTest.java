package coin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoinControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
    public void validTest() {
        CoinRequest request = new CoinRequest();
        request.setAmount(10.99);
        
        ResponseEntity<CoinResponse> response = restTemplate.postForEntity("/api/calculate", request, CoinResponse.class);
     
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getOutput()).isNotEmpty();
        assertThat(response.getBody().getCoinDenoms()).isNotEmpty();
       
	} 
	
	@Test
	public void negativeAmountTest() {
		CoinRequest request = new CoinRequest();
        request.setAmount(-10.0);
        
        ResponseEntity<Map> response = restTemplate.postForEntity("/api/calculate", request, Map.class);
     
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().get("error").toString()).contains("Amount must be >= 0");
		
	}
	
	@Test
	public void nullTest() {
		CoinRequest request = new CoinRequest();
		ResponseEntity<Map> response = restTemplate.postForEntity("/api/calculate", request, Map.class);
		
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	    assertThat(response.getBody().get("error").toString()).contains("Amount is required");
	    }
}