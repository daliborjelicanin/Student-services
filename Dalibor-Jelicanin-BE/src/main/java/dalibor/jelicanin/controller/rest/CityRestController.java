package dalibor.jelicanin.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dalibor.jelicanin.dto.CityDto;
import dalibor.jelicanin.service.CityService;

@RestController
@RequestMapping(path = "/api/city")
public class CityRestController {

	private final CityService cityService;

	@Autowired
	public CityRestController(CityService cityService) {
		this.cityService = cityService;
	}
	
	@GetMapping
	public @ResponseBody ResponseEntity<List<CityDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(cityService.getAll());
	}
}
