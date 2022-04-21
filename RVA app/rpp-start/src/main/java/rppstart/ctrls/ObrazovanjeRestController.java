package rppstart.ctrls;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rppstart.jpa.Obrazovanje;
import rppstart.repository.ObrazovanjeRepository;

@RestController
@Api(tags = {"Obrazovanje CRUD operacije"})
public class ObrazovanjeRestController {
    
	@Autowired
	private ObrazovanjeRepository obrazovanjeRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Vraća kolekciju svih obrazovanja iz baze podataka")
	@GetMapping("obrazovanje")
	public Collection<Obrazovanje> getObrazovanja() {
		return obrazovanjeRepository.findAll();
	}
	
	@ApiOperation(value = "Vraća obrazovanje iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	@GetMapping("obrazovanje/{id}")
	public Obrazovanje getObrazovanje(@PathVariable ("id") Integer id) {
		return obrazovanjeRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraća kolekciju svih obrazovanja iz baze podataka koji u nazivu sadrže string prosleđen kao path varijabla")
	@GetMapping("obrazovanjeNaziv/{naziv}")
	public Collection<Obrazovanje> getObrazovanjeByNaziv(@PathVariable("naziv") String naziv) {
		return obrazovanjeRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Upisuje obrazovanje u bazu podataka")
	@PostMapping("obrazovanje")
	public ResponseEntity<Obrazovanje> insertObrazovanje(@RequestBody Obrazovanje obrazovanje) {
		if (!obrazovanjeRepository.existsById(obrazovanje.getId())) {
			obrazovanjeRepository.save(obrazovanje);
			return new ResponseEntity<Obrazovanje>(HttpStatus.OK);
		}
		return new ResponseEntity<Obrazovanje>(HttpStatus.CONFLICT);
	}
	
	@ApiOperation(value = "Modifikuje postojeće obrazovanje u bazi podataka")
	@PutMapping("obrazovanje")
	public ResponseEntity<Obrazovanje> updateObrazovanje(@RequestBody Obrazovanje obrazovanje) {
		if (!obrazovanjeRepository.existsById(obrazovanje.getId())) {
			return new ResponseEntity<Obrazovanje>(HttpStatus.CONFLICT);
		}
		obrazovanjeRepository.save(obrazovanje);
		return new ResponseEntity<Obrazovanje>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Briše obrazovanje iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	@DeleteMapping("obrazovanje/{id}")
	public ResponseEntity<Obrazovanje> deleteObrazovanje(@PathVariable Integer id) {
		if (!obrazovanjeRepository.existsById(id)) {
			return new ResponseEntity<Obrazovanje>(HttpStatus.NO_CONTENT);
		}
		
		obrazovanjeRepository.deleteById(id);
		if (id == -100)
			jdbcTemplate.execute(" INSERT INTO \"obrazovanje\" (\"id\", \"naziv\", \"stepen_strucne_spreme\", \"opis\") "
					+ "VALUES (-100, 'Test naziv', 'VII', 'Test opis')");
		return new ResponseEntity<Obrazovanje>(HttpStatus.OK);
	}
}
