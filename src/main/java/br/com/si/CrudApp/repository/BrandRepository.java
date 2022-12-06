package br.com.si.CrudApp.repository;

import br.com.si.CrudApp.model.BrandModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandModel, Long>  {

    //..a optional return
    Optional<BrandModel> findById(long id);

    //..JPA derived query to retrive a list by name
    List<BrandModel> findByDescriptionContainsIgnoreCase(String description);


}
