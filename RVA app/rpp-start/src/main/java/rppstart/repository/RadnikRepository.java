package rppstart.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rppstart.jpa.Obrazovanje;
import rppstart.jpa.Preduzece;
import rppstart.jpa.Radnik;
import rppstart.jpa.Sektor;

public interface RadnikRepository extends JpaRepository <Radnik, Integer>{
	Collection<Radnik> findBySektor(Sektor s);
	Collection<Radnik> findByObrazovanje(Obrazovanje o);
}
