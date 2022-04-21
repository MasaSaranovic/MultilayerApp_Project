package rppstart.ctrls;

import java.util.Collection;

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
import rppstart.jpa.Radnik;
import rppstart.jpa.Sektor;
import rppstart.repository.ObrazovanjeRepository;
import rppstart.repository.RadnikRepository;
import rppstart.repository.SektorRepository;

@CrossOrigin
@RestController
@Api(tags = {"Radnik CRUD operacije"})
public class RadnikRestController {
	
	@Autowired 
	private RadnikRepository radnikRepository;
	
	@Autowired
	private SektorRepository sektorRepository;
	
	@Autowired
	private ObrazovanjeRepository obrazovanjeRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Vraća kolekciju svih radnika iz baze podataka")
	@GetMapping("radnik")
	public Collection<Radnik> getRadnici() {
		return radnikRepository.findAll();
	}
	
	@ApiOperation(value = "Vraća radnika iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	@GetMapping("radnik/{id}")
	public Radnik getRadnik(@PathVariable Integer id) {
		return radnikRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraća kolekciju svih radnika iz baze podataka koji za id Sektora sadrže integer prosleđen kao path varijabla")
	@GetMapping("radniciZaSektorID/{id}")
	public Collection<Radnik> getRadniciPoSektoru(@PathVariable Integer id) {
		Sektor s = sektorRepository.getOne(id);
		return radnikRepository.findBySektor(s);
	}
	
	@ApiOperation(value = "Vraća kolekciju svih radnika iz baze podataka koji za id Obrazovanja sadrže integer prosleđen kao path varijabla")
	@GetMapping("radniciZaObrazovanjeID/{id}")
	public Collection<Radnik> getRadnikByObrazovanje(@PathVariable Integer id) {
		Obrazovanje o = obrazovanjeRepository.getOne(id);
		return radnikRepository.findByObrazovanje(o);
	}
	
	@ApiOperation(value = "Upisuje radnika u bazu podataka")
	@PostMapping("radnik")
	public ResponseEntity<Radnik> insertRadnik (@RequestBody Radnik radnik) {
		if(!radnikRepository.existsById(radnik.getId())) {
			
			radnikRepository.save(radnik);
			return new ResponseEntity<Radnik>(HttpStatus.OK);
		}
		return new ResponseEntity<Radnik>(HttpStatus.CONFLICT);
	}
	
	@ApiOperation(value = "Modifikuje postojećeg radnika u bazi podataka")
	@PutMapping("radnik")
	public ResponseEntity<Radnik> updateRadnik(@RequestBody Radnik radnik) {
		if(!radnikRepository.existsById(radnik.getId())) {
			return new ResponseEntity<Radnik>(HttpStatus.NO_CONTENT);
		}
		radnikRepository.save(radnik);
		return new ResponseEntity<Radnik>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Briše radnika iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	@DeleteMapping("radnik/{id}")
	public ResponseEntity<Radnik> radnikPreduzece(@PathVariable Integer id) {
		if (!radnikRepository.existsById(id)) {
			return new ResponseEntity<Radnik>(HttpStatus.NO_CONTENT);
		}
		radnikRepository.deleteById(id);
		if (id == -100)
			jdbcTemplate.execute(" INSERT INTO \"radnik\" (\"id\", \"ime\", \"prezime\", \"broj_lk\", \"obrazovanje\", \"sektor\") "
					+ "VALUES (-100, 'Test ime', 'Test prezime', '058964789', 1, 1)");
		return new ResponseEntity<Radnik>(HttpStatus.OK);
	}

}
