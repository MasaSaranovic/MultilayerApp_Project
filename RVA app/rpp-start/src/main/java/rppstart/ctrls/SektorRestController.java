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
import rppstart.jpa.Sektor;
import rppstart.repository.RadnikRepository;
import rppstart.repository.SektorRepository;

@CrossOrigin
@RestController
@Api(tags = {"Sektor CRUD operacije"})
public class SektorRestController {
	
	@Autowired
	private SektorRepository sektorRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Vraća kolekciju svih sektora iz baze podataka")
	@GetMapping("sektor")
	public Collection<Sektor> getSektori() {
		return sektorRepository.findAll();
	}
	
	@ApiOperation(value = "Vraća sektor iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	@GetMapping("sektor/{id}")
	public Sektor getSektor(@PathVariable("id") Integer id) {
		return sektorRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraća kolekciju svih sektora iz baze podataka koji u nazivu sadrže string prosleđen kao path varijabla")
	@GetMapping("sektorNaziv/{naziv}")
	public Collection<Sektor> getSektorByNaziv(@PathVariable("naziv") String naziv) {
		return sektorRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Upisuje sektor u bazu podataka")
	@PostMapping("sektor")
	public ResponseEntity<Sektor> insertSektor(@RequestBody Sektor sektor) {
		if (!sektorRepository.existsById(sektor.getId())) {
			sektorRepository.save(sektor);
			return new ResponseEntity<Sektor>(HttpStatus.OK);
		}
		return new ResponseEntity<Sektor> (HttpStatus.CONFLICT);
	}
	
	@ApiOperation(value = "Modifikuje postojeći sektor u bazi podataka")
	@PutMapping("sektor")
	public ResponseEntity<Sektor> updateSektor(@RequestBody Sektor sektor) {
		if (!sektorRepository.existsById(sektor.getId())) {
			return new ResponseEntity<Sektor> (HttpStatus.NO_CONTENT);
		}
		sektorRepository.save(sektor);
		return new ResponseEntity<Sektor>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Briše obrazovanje iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	@DeleteMapping("sektor/{id}")
	public ResponseEntity<Sektor> deleteSektor(@PathVariable("id") Integer id) {
		if(!sektorRepository.existsById(id)) {
			return new ResponseEntity<Sektor>(HttpStatus.NO_CONTENT);
		}
		
		sektorRepository.deleteById(id);
		if (id == -100)
			jdbcTemplate.execute(" INSERT INTO \"sektor\" (\"id\", \"naziv\", \"oznaka\", \"preduzece\") "
					+ "VALUES (-100, 'Test naziv', 'Test ozn', 1)");
		return new ResponseEntity<Sektor>(HttpStatus.OK);
	}

}
