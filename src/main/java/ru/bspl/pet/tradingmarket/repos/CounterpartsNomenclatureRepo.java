package ru.bspl.pet.tradingmarket.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bspl.pet.tradingmarket.models.CounterpartsNomenclature;

@Repository
public interface CounterpartsNomenclatureRepo extends JpaRepository<CounterpartsNomenclature, Long> {
}
