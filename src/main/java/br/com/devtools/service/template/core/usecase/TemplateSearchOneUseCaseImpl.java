package br.com.devtools.service.template.core.usecase;

import br.com.devtools.service.template.core.domain.shared.exception.BusinessException;
import br.com.devtools.service.template.core.domain.shared.exception.ExceptionCode;
import br.com.devtools.service.template.core.usecase.model.TemplateResponse;
import br.com.devtools.service.template.gateway.domain.template.TemplateSearchOneGateway;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateSearchOneUseCaseImpl implements TemplateSearchOneUseCase {

  private final TemplateSearchOneGateway searchOneGateway;

  @Override
  public TemplateResponse execute(UUID id) {
    return searchOneGateway
        .execute(id)
        .map(TemplateResponse::mapper)
        .orElseThrow(() -> new BusinessException(ExceptionCode.TEMPLATE_NOT_FOUND));
  }
}
