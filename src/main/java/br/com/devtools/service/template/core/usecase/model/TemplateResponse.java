package br.com.devtools.service.template.core.usecase.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class TemplateResponse {

  private UUID id;
  private String name;
  private OffsetDateTime createdAt;

  public static TemplateResponse mapper(Object object) {
    var result = TemplateResponse.builder().build();
    BeanUtils.copyProperties(object, result);
    return result;
  }
}
