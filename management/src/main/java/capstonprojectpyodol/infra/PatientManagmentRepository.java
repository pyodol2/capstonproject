package capstonprojectpyodol.infra;

import capstonprojectpyodol.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "patientManagments",
    path = "patientManagments"
)
public interface PatientManagmentRepository
    extends PagingAndSortingRepository<PatientManagment, Long> {}
