package br.com.devtools.service.template.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.devtools.service.template.core.domain.shared.exception.BusinessException;
import br.com.devtools.service.template.core.domain.shared.exception.ExceptionCode;
import br.com.devtools.service.template.core.usecase.TemplateSearchOneUseCase;
import br.com.devtools.service.template.core.usecase.TemplateSearchOneUseCaseImpl;
import br.com.devtools.service.template.fixtures.template.TemplateGatewayResponseFixture;
import br.com.devtools.service.template.gateway.domain.template.TemplateSearchOneGateway;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class TemplateSearchOneUseCaseUnitTest {

  private TemplateSearchOneUseCase useCase;

  @Mock private TemplateSearchOneGateway searchOneGateway;

  @BeforeEach
  public void setUp() {
    useCase = new TemplateSearchOneUseCaseImpl(searchOneGateway);
  }

  @Test
  public void testSearchOneTemplateWithErrorWhenTemplateNotFound() {
    when(searchOneGateway.execute(any(UUID.class))).thenReturn(Optional.empty());

    var be =
        assertThrows(
            BusinessException.class,
            () -> useCase.execute(UUID.fromString("b32c7c77-37ab-4839-b6fd-e3c79aa16f89")));

    assertEquals(ExceptionCode.TEMPLATE_NOT_FOUND.getMessage(), be.getMessage());
    assertEquals(ExceptionCode.TEMPLATE_NOT_FOUND.getCode().toString(), be.getCode());
  }

  @Test
  public void testSearchOneTemplateWithSuccess() {
    when(searchOneGateway.execute(any(UUID.class)))
        .thenReturn(Optional.of(TemplateGatewayResponseFixture.validTemplateGatewayResponse()));

    assertDoesNotThrow(
        () -> {
          var result = useCase.execute(UUID.fromString("b32c7c77-37ab-4839-b6fd-e3c79aa16f89"));

          assertNotNull(result);
          assertEquals(UUID.fromString("b32c7c77-37ab-4839-b6fd-e3c79aa16f89"), result.getId());
          assertEquals("Template 1", result.getName());
        });
  }
}
