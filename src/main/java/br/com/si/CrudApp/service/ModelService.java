package br.com.si.CrudApp.service;

import br.com.si.CrudApp.model.ModelModel;
import br.com.si.CrudApp.repository.ModelRepository;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService {

    @Autowired
    private ModelRepository repository;

    public Optional<ModelModel> findById(long id) { return repository.findById(id); }

    public Page<ModelModel> findAll(Pageable pageable) { return repository.findAll(pageable); }

    public ModelModel save(ModelModel model) { return  repository.save(model);}

    public ModelModel update(ModelModel model) {
        Optional<ModelModel> p = repository.findById(model.getId());
        if(p.isPresent()){
            p.get().setDescription(model.getDescription());
            p.get().setColor(model.getColor());
            p.get().setDoors(model.getDoors());
            p.get().setEngine_power(model.getEngine_power());


            p.get().setBrand(model.getBrand());

            return repository.save(p.get());
        } else {
            return null;
        }
    }

    public void delete(long id) {
        Optional<ModelModel> p = repository.findById(id);
        if(p.isPresent()) {
            repository.delete(p.get());
        }
    }

    public List<ModelModel> findByDescription(String description){
        return repository.findByDescriptionContainsIgnoreCase(description);
    }

}
