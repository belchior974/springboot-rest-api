package br.com.si.CrudApp.service;

import br.com.si.CrudApp.model.BrandModel;
import br.com.si.CrudApp.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepository repository;

    public Optional<BrandModel> findById(long id){
        return repository.findById(id);
    }

    public List<BrandModel> findAll(){
        return repository.findAll();
    }

    public List<BrandModel> findByDescription(String description){
        return repository.findByDescriptionContainsIgnoreCase(description);
    }

    public BrandModel save(BrandModel model){
        return repository.save(model);
    }

    public BrandModel update(BrandModel model){
        var found = repository.findById(model.getId());
        if(found.isPresent()){
            found.get().setDescription(model.getDescription());
            return repository.save(found.get());
        } else {
            return null;
        }
    }

    public void delete(long id){
        var found = repository.findById(id);
        if(found.isPresent()){
            repository.delete(found.get());
        }
    }


}
