package capstonprojectpyodol.infra;

import capstonprojectpyodol.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ExaminationHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Examination>> {

    @Override
    public EntityModel<Examination> process(EntityModel<Examination> model) {
        return model;
    }
}
