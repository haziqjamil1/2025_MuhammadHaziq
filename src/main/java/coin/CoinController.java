package coin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CoinController {

    private static double[] coinTypes = {1000, 100, 50, 10, 5, 2, 1, 0.5, 0.2, 0.1, 0.05, 0.01};
    
    
    @PostMapping(value ="/calculate", produces = "application/json")
    public ResponseEntity<?> calculate(@Valid @RequestBody CoinRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(Map.of("error", errorMessage));
        } 
        
        double input = request.getAmount();
        ArrayList<Double> output = new ArrayList<>();

        int i = 0;
        while (input>0 && i < coinTypes.length) {
			if (input >= coinTypes[i]){
				input -= coinTypes[i];
				output.add(coinTypes[i]);
			} else {
				i++;
			}
		}
        Collections.sort(output);
        List<Double> coinDenoms = output.stream().distinct().collect(Collectors.toList());
        

        return ResponseEntity.ok (new CoinResponse(output, coinDenoms));
    }
}