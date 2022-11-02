package ru.bspl.pet.tradingmarket.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.dto.PriceListDTO;
import ru.bspl.pet.tradingmarket.models.PriceList;
import ru.bspl.pet.tradingmarket.models.PriceListId;
import ru.bspl.pet.tradingmarket.services.CounterpartsNomenclatureService;
import ru.bspl.pet.tradingmarket.services.CounterpartyService;
import ru.bspl.pet.tradingmarket.services.PriceListService;
import ru.bspl.pet.tradingmarket.services.PriceZoneService;
import ru.bspl.pet.tradingmarket.utils.PriseListNotCreatedException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/pricelists")
public class PriceListController {

    private final PriceListService priceListService;
    private final CounterpartyService counterpartyService;
    private final CounterpartsNomenclatureService counterpartsNomenclatureService;
    private final PriceZoneService priceZoneService;

    @Autowired
    public PriceListController(PriceListService priceListService, CounterpartyService counterpartyService, CounterpartsNomenclatureService counterpartsNomenclatureService, PriceZoneService priceZoneService) {
        this.priceListService = priceListService;
        this.counterpartyService = counterpartyService;
        this.counterpartsNomenclatureService = counterpartsNomenclatureService;
        this.priceZoneService = priceZoneService;
    }

    @PostMapping("/api")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ArrayList<PriceListDTO> priceListDTO , BindingResult bindingResult){

        /*if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errorList = bindingResult.getFieldErrors();

            for(FieldError error : errorList){
                errorMessage.append(error.getField()).append("-").append(error.getDefaultMessage()).append("; ");
            }

            throw new PriseListNotCreatedException(errorMessage.toString());
        }*/

        for (PriceListDTO lpDTO: priceListDTO ) {
            PriceList pl = convertToPriceList(lpDTO);
            priceListService.save(pl);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private PriceList convertToPriceList(PriceListDTO priceListDTO) {
        PriceList priceList = new PriceList();

        priceList.setCounterpartysPrice(priceListDTO.getCounterpartysPrice());
        priceList.setMultiplicityOf(priceListDTO.getMultiplicityOf());
        priceList.setShelfLife(priceListDTO.getShelfLife());
        priceList.setCounterpartysStock(priceListDTO.getCounterpartysStock());
        priceList.setId(new PriceListId(priceZoneService.findOne(priceListDTO.getPriceZoneId()),
                counterpartyService.findOne(priceListDTO.getCounterpartyId()),
                counterpartsNomenclatureService.findOne(priceListDTO.getCounterpartsNomenclatureId())));

        return priceList;
    }
    private PriceListDTO convertToPriceListDTO(PriceList priceList) {
        PriceListDTO priceListDTO = new PriceListDTO();

        priceListDTO.setPriceZoneId(priceList.getId().getPriceZone().getId());
        priceListDTO.setCounterpartsNomenclatureId(priceList.getId().getCounterpartsNomenclature().getId());
        priceListDTO.setCounterpartyId(priceList.getId().getCounterparty().getId());
        priceListDTO.setCounterpartysPrice(priceList.getCounterpartysPrice());
        priceListDTO.setCounterpartysStock(priceList.getCounterpartysStock());
        priceListDTO.setShelfLife(priceList.getShelfLife());
        priceListDTO.setMultiplicityOf(priceList.getMultiplicityOf());

        return priceListDTO;
    }



    @GetMapping()
    public List<PriceListDTO> getPrices(){
        List<PriceListDTO> priceListDTO = new ArrayList<>();

        for(PriceList pl: priceListService.findAll()) {
            priceListDTO.add(convertToPriceListDTO(pl));
        }

        return priceListDTO;
    }



}
