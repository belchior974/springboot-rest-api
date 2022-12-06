package br.com.si.CrudApp.controller;

import br.com.si.CrudApp.model.BrandModel;
import br.com.si.CrudApp.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService service;

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    public BrandModel findById(@PathVariable("id") long id){
        return service.findById(id).get();
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    public List<BrandModel> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/find/{description}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    public List<BrandModel> findByName(@PathVariable("description") String description){
        return service.findByDescription(description);
    }

    @PostMapping
    public BrandModel save(@RequestBody BrandModel model){
        return service.save(model);
    }

    @PutMapping
    public BrandModel update(@RequestBody BrandModel model){
        return service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }


}
