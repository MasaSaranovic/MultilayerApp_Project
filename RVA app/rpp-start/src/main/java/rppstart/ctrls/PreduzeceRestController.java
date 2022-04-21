package rppstart.ctrls;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import rppstart.jpa.Preduzece;
import rppstart.repository.PreduzeceRepository;

@CrossOrigin
@RestController
@Api(tags = {"Preduzeće CRUD operacije"})
public class PreduzeceRestController {
	
	@Autowired
	private PreduzeceRepository preduzeceRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Vraća kolekciju svih preduzeća iz baze podataka")
	@GetMapping("preduzece")
	public Collection<Preduzece> getPreduzeca() {
		return preduzeceRepository.findAll();
	}
	
	@ApiOperation(value = "Vraća preduzeće iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	@GetMapping("preduzece/{id}")
	public Preduzece getPreduzece(@PathVariable Integer id) {
		return preduzeceRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraća kolekciju svih preduzeća iz baze podataka koji u nazivu sadrže string prosleđen kao path varijabla")
	@GetMapping("preduzeceNaziv/{naziv}")
	public Collection<Preduzece> getPreduzeceByNaziv(@PathVariable String naziv) {
		return preduzeceRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Upisuje preduzeće u bazu podataka")
	@PostMapping("preduzece")
	public ResponseEntity<Preduzece> insertPreduzece (@RequestBody Preduzece preduzece) {
		if(!preduzeceRepository.existsById(preduzece.getId())) {
			preduzeceRepository.save(preduzece);
			return new ResponseEntity<Preduzece>(HttpStatus.OK);
		}
		return new ResponseEntity<Preduzece>(HttpStatus.CONFLICT);
	}
	
	@ApiOperation(value = "Modifikuje postojeće preduzeće u bazi podataka")
	@PutMapping("preduzece")
	public ResponseEntity<Preduzece> updatePreduzece(@RequestBody Preduzece preduzece) {
		if(!preduzeceRepository.existsById(preduzece.getId())) {
			return new ResponseEntity<Preduzece>(HttpStatus.NO_CONTENT);
		}
		preduzeceRepository.save(preduzece);
		return new ResponseEntity<Preduzece>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Briše preduzeće iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	@DeleteMapping("preduzece/{id}")
	public ResponseEntity<Preduzece> deletePreduzece(@PathVariable Integer id) {
		if (!preduzeceRepository.existsById(id)) {
			return new ResponseEntity<Preduzece>(HttpStatus.NO_CONTENT);
		}
		
		preduzeceRepository.deleteById(id);
		if (id == -100)
			jdbcTemplate.execute(" INSERT INTO \"preduzece\" (\"id\", \"naziv\", \"pib\", \"sediste\", \"opis\") "
					+ "VALUES (-100, 'Test naziv', '548968547', 'Test sediste', 'Test opis')");
		return new ResponseEntity<Preduzece>(HttpStatus.OK);
	}

}
