package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bspl.pet.tradingmarket.models.ThresholdCategories;
import ru.bspl.pet.tradingmarket.models.ThresholdValues;
import ru.bspl.pet.tradingmarket.models.ThresholdValuesTable;
import ru.bspl.pet.tradingmarket.services.ThresholdValuesService;
import ru.bspl.pet.tradingmarket.services.ThresholdValuesTableService;

import java.util.Optional;

@Controller
@RequestMapping("/thresholdvalues")
public class ThresholdValuesController {
    
    private final ThresholdValuesService thresholdValuesService;
    private final ThresholdValuesTableService thresholdValuesTableService;

    @Autowired
    public ThresholdValuesController(ThresholdValuesService thresholdValuesService, ThresholdValuesTableService thresholdValuesTableService) {
        this.thresholdValuesService = thresholdValuesService;
        this.thresholdValuesTableService = thresholdValuesTableService;
    }
    @GetMapping()
    public String show(Model model){
        model.addAttribute("thresholdvalues", thresholdValuesService.findAll());
        model.addAttribute("header", "threshold values  list");
        return "thresholdvalues/index";
    }
    @GetMapping("/new")
    public String addFormNew(Model model){
        model.addAttribute("thresholdValue",new ThresholdValues());
        model.addAttribute("header", "threshold values add new");
        return "thresholdvalues/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("thresholdvalue") ThresholdValues thresholdValues){
        thresholdValuesService.save(thresholdValues);
        return "redirect:/thresholdvalues";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        ThresholdValues thresholdValue = thresholdValuesService.findOne(id);
        model.addAttribute("thresholdValue", thresholdValue);
        model.addAttribute("thresholdValueTables", thresholdValuesTableService.findByThresholdValueAll(thresholdValue));
        model.addAttribute("header", "Agreements-add new");
        return "thresholdvalues/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("thresholdvalue") ThresholdValues thresholdValues,
                         @PathVariable("id") Long id){
        thresholdValuesService.update(id, thresholdValues);
        return "redirect:/thresholdvalues";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        thresholdValuesTableService.deleteAll(thresholdValuesTableService.findByThresholdValueAll(thresholdValuesService.findOne(id)));
        thresholdValuesService.delete(id);
        return "redirect:/thresholdvalues";
    }

    @GetMapping("/{id}/newrow")
    public String addFormNewRow(Model model, @PathVariable("id") Long id){
        model.addAttribute("thresholdValuesTable",new ThresholdValuesTable(thresholdValuesService.findOne(id)));
        model.addAttribute("thresholdCategories", ThresholdCategories.values());
        model.addAttribute("header", "threshold values add new row");
        return "thresholdvalues/newrow";
    }

    @PostMapping("/{id}/newrow")
    public String addRow(@PathVariable("id") Long id, @ModelAttribute("thresholdValuesTable") ThresholdValuesTable thresholdValuesTable){
        thresholdValuesTable.setThresholdValues(thresholdValuesService.findOne(id));
        thresholdValuesTableService.save(thresholdValuesTable);
        return "redirect:/thresholdvalues/"+id+"/edit";
    }

    @GetMapping("/{id}/{rowid}/edit")
    public String editRow(Model model, @PathVariable("id") Long id, @PathVariable("rowid") Long rowid){
        ThresholdValuesTable thresholdValuesTable = thresholdValuesTableService.findOne(rowid);
        model.addAttribute("thresholdValuesTable", thresholdValuesTable);
        model.addAttribute("thresholdCategories", ThresholdCategories.values());
        model.addAttribute("header", "Agreements-add new");
        return "thresholdvalues/rowedit";
    }

    @PostMapping("/{id}/{rowid}/edit")
    public String updateRow(@ModelAttribute("thresholdvalue") ThresholdValuesTable thresholdValuesTable,
                         @PathVariable("id") Long id, @PathVariable("rowid") Long rowid){
        thresholdValuesTable.setId(rowid);
        thresholdValuesTable.setThresholdValues(thresholdValuesService.findOne(id));
        thresholdValuesTableService.update(thresholdValuesTable);
        return "redirect:/thresholdvalues/"+id+"/edit";
    }

    @GetMapping("/{id}/{rowid}/delete")
    public String deleteRow(@PathVariable("id") Long id, @PathVariable("rowid") Long rowid){
        thresholdValuesTableService.delete(rowid);
        return "redirect:/thresholdvalues/"+id+"/edit";
    }

}
