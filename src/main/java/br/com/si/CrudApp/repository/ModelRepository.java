package br.com.si.CrudApp.repository;

import br.com.si.CrudApp.model.ModelModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<ModelModel, Long> {

    public Optional<ModelModel> findById(long id);

    List<ModelModel> findByDescriptionContainsIgnoreCase(String description);

//    Page<ModelModel> findAll(Pageable pageable);

    List<ModelModel>findAll();

}
