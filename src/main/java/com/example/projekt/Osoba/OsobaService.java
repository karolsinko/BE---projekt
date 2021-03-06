package com.example.projekt.Osoba;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class OsobaService {

    private final OsobaRepository osobaRepository;

    public OsobaService(OsobaRepository osobaRepository){
        this.osobaRepository = osobaRepository;
    }

    public static Osoba mapOsoba(OsobaEntity osobaEntity){
        Osoba osoba = new Osoba();

        osoba.setId(osobaEntity.getId());
        osoba.setMeno(osobaEntity.getMeno());
        osoba.setPriezvisko(osobaEntity.getPriezvisko());
        osoba.setRokNar(osobaEntity.getRokNar());
        osoba.setRodCislo(osobaEntity.getRodCislo());
        osoba.setBydlisko(osobaEntity.getBydlisko());
        osoba.setPohlavie(osobaEntity.getPohlavie());
        osoba.setTelCislo(osobaEntity.getTelCislo());
        return osoba;

    }

    @Transactional
    public List<Osoba> getOsobaByPriezvisko() {
        List<Osoba> ret = new LinkedList<>();
        for (OsobaEntity o1 : osobaRepository.findAll()){
            Osoba o2 = mapOsoba(o1);
            ret.add(o2);
        }
        return ret;
    }

    @Transactional
    public int createOsoba(Osoba osoba){
        OsobaEntity osobaEntity = new OsobaEntity();

        osobaEntity.setMeno(osoba.getMeno());
        osobaEntity.setPriezvisko(osoba.getPriezvisko());
        osobaEntity.setRokNar(osoba.getRokNar());
        osobaEntity.setRodCislo(osoba.getRodCislo());
        osobaEntity.setBydlisko(osoba.getBydlisko());
        osobaEntity.setPohlavie(osoba.getPohlavie());
        osobaEntity.setTelCislo(osoba.getTelCislo());

        this.osobaRepository.save(osobaEntity);
        return osobaEntity.getId();
    }

    @Transactional
    public Osoba getOsobaById(int id){
        for (OsobaEntity o1 : osobaRepository.findAll()){
            if (o1.getId() == (id)){
                return mapOsoba(o1);
            }
        }
        return null;
    }

    @Transactional
    public void deleteOsoba(int id){
        Optional<OsobaEntity> byId = osobaRepository.findById(id);
        byId.ifPresent(osobaRepository::delete);
    }

    @Transactional
    public void updateOsoba(int id, Osoba osoba){
        Optional<OsobaEntity> byId = osobaRepository.findById(id);
        if (byId.isPresent()){
            byId.get().setMeno(osoba.getMeno());
            byId.get().setPriezvisko(osoba.getPriezvisko());
            byId.get().setRokNar(osoba.getRokNar());
            byId.get().setRodCislo(osoba.getRodCislo());
            byId.get().setBydlisko(osoba.getBydlisko());
            byId.get().setPohlavie(osoba.getPohlavie());
            byId.get().setTelCislo(osoba.getTelCislo());
        }
    }
}