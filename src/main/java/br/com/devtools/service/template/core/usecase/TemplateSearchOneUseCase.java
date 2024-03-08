package br.com.devtools.service.template.core.usecase;

import br.com.devtools.service.template.core.usecase.model.TemplateResponse;
import java.util.UUID;

public interface TemplateSearchOneUseCase {

  TemplateResponse execute(UUID id);
}
