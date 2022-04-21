

-- OBRAZOVANJE PODACI

INSERT INTO obrazovanje (id, naziv, stepen_strucne_spreme, opis)
VALUES (nextval('obrazovanje_seq'), 'Mast. biol.', 'VII-1b', 'Master biolog Prirodnog-matematickog fakulteta u Novom Sadu'),
	   (nextval('obrazovanje_seq'), 'Dipl. mat.', 'VII-1a', 'Diplomirani matematicar Matematickog fakulteta u Beogradu'),
	   (nextval('obrazovanje_seq'), 'Mast. infor.', 'VII-1b', 'Master informaticar Ekonomskog fakulteta u Subotici'),
	   (nextval('obrazovanje_seq'), 'Dipl. inz. arh.', 'VII-1a', 'Diplomirani inzenjer arhitekture Gradjevinskog fakulteta u Beogradu'),
	   (nextval('obrazovanje_seq'), 'Dr. soft. inz.', 'VIII', 'Doktor nauka - softversko inzenjerstvo Fakulteta tehnickih nauka u Novom Sadu');
		
-- PREDUZECE PODACI
		
INSERT INTO preduzece (id, naziv, pib, sediste, opis)
VALUES (nextval('preduzece_seq'), 'Unit Sport doo', '485967845', 'Crvenka', 'Kompanija za proizvodnju sportske opreme'),
	   (nextval('preduzece_seq'), 'AD Fidelinka Skrob', '489562315', 'Subotica', 'Kompanija koja se bavi proizvodnjom skroba i glutena, modifikata'),
	   (nextval('preduzece_seq'), 'BeeIT', '109878569', 'Novi Sad', 'IT kompanija za pruzanje raznih usluga i proizoda'),
	   (nextval('preduzece_seq'), 'Gimnazija Svetozar Markovic', '123654789', 'Backa Topola', 'Srednja skola'),
	   (nextval('preduzece_seq'), 'OS Matko Vukovic', '526354789', 'Brus', 'Osnovna skola');
		
-- SEKTOR PODACI

INSERT INTO sektor (id, naziv, oznaka, preduzece)
VALUES (nextval('sektor_seq'), 'Nastavni sektor', 'Prof.', 4),
	   (nextval('sektor_seq'), 'Sektor za logistiku', 'Logi.', 1),
	   (nextval('sektor_seq'), 'Sektor za odrzavanje', 'Odrz.', 5),
	   (nextval('sektor_seq'), 'Sektor za proizvodnju', 'Proiz.', 2),
	   (nextval('sektor_seq'), 'Sektor za projektovanje i razvoj', 'ProjRaz.', 3);
		
-- RADNIK PODACI

INSERT INTO radnik (id, ime, prezime, broj_lk, obrazovanje, sektor)
VALUES (nextval('radnik_seq'), 'Masa', 'Saranovic', '789652789', 5, 3),
	   (nextval('radnik_seq'), 'Selena', 'Delcev', '356878654', 3, 4),
	   (nextval('radnik_seq'), 'Darko', 'Dulic', '859674512', 2, 2),
	   (nextval('radnik_seq'), 'Tijana', 'Smajic', '112568214', 1, 5),
	   (nextval('radnik_seq'), 'Ugljesa', 'Grubacic', '356658997', 4, 1);
		
--TEST PODACI

INSERT INTO obrazovanje (id, naziv, stepen_strucne_spreme, opis)
VALUES (-100, 'Test naziv', 'VII', 'Test opis');
INSERT INTO preduzece (id, naziv, pib, sediste, opis)
VALUES (-100, 'Test naziv', '485967845', 'Test sediste', 'Test opis');
INSERT INTO sektor (id, naziv, oznaka, preduzece)
VALUES (-100, 'Test naziv', 'Test ozn', 4);
INSERT INTO radnik (id, ime, prezime, broj_lk, obrazovanje, sektor)
VALUES (-100, 'Test ime', 'Test prezime', '789652789', 5, 3);