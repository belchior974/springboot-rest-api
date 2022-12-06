package br.com.si.CrudApp.controller;

import br.com.si.CrudApp.model.ModelModel;
import br.com.si.CrudApp.service.ModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/model")
@Api(value = "Model Controller" )
public class ModelController {

    @Autowired
    private ModelService service;


    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, "application/x-yaml"})
    @ApiOperation(value = "Get an Model by id", produces = "JSON", response = ModelModel.class)
    public ModelModel findById(
            @ApiParam(name = "id", required = true, type = "Integer")
            @PathVariable("id") long id)
    {
        var model = service
                .findById(id);
        if(model.isPresent()){
            buildEntityLinks(model.get());
        }

        return model.get();
    }

    @PostMapping(produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE, "application/x-yaml"},
            consumes ={ MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, "application/x-yaml"})
    public ModelModel save(@RequestBody ModelModel model) { return service.save(model);}

    @GetMapping
    public ResponseEntity<PagedModel<ModelModel>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<ModelModel> assembler
    ) {

        //..the direction of sort
        var sortDirection =
                "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        //..a PageAble object is an object containing the list of resources
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "description"));

        //..a Page containing the resource models
        Page<ModelModel> pageModel= service.findAll(pageable);

        //..create the HATEOAS links
        for(ModelModel model : pageModel){
            buildEntityLinks(model);
        }
        //return the ResponseEntity
        return new ResponseEntity(assembler.toModel(pageModel), HttpStatus.OK);
    }

    @PutMapping
    public ModelModel update(@RequestBody ModelModel model) { return service.update(model);}

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable("id") long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find/description/{description}")
    public List<ModelModel> findByDescription(@PathVariable("description") String description) { return service.findByDescription(description);}

    public void buildEntityLinks(ModelModel model) {

        model.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(this.getClass()).findById(model.getId())).withSelfRel());

        Link link = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(BrandController.class)
                        .findById(model.getBrand().getId())
        ).withSelfRel();
        model.getBrand().add(link);
    }


}
