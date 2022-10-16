package ru.bspl.pet.tradingmarket.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

public class PriceListId implements Serializable {

    private PriceZone priceZone;
    private Counterparty counterparty;
    private CounterpartsNomenclature counterpartsNomenclature;


    public PriceListId() {
    }

    public PriceListId(PriceZone priceZone, Counterparty counterparty, CounterpartsNomenclature counterpartsNomenclature) {
        this.priceZone = priceZone;
        this.counterparty = counterparty;
        this.counterpartsNomenclature = counterpartsNomenclature;
    }

    public PriceZone getPriceZone() {
        return priceZone;
    }

    public void setPriceZone(PriceZone priceZone) {
        this.priceZone = priceZone;
    }

    public CounterpartsNomenclature getCounterpartsNomenclature() {
        return counterpartsNomenclature;
    }

    public void setCounterpartsNomenclature(CounterpartsNomenclature counterpartsNomenclature) {
        this.counterpartsNomenclature = counterpartsNomenclature;
    }

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceListId that = (PriceListId) o;
        return Objects.equals(priceZone.getId(), that.priceZone.getId()) && Objects.equals(counterparty.getId(), that.counterparty.getId()) && Objects.equals(counterpartsNomenclature.getId(), that.counterpartsNomenclature.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(counterpartsNomenclature.getId(), counterparty.getId(), priceZone.getId());
    }
}
