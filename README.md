<pre>
  <code>
    import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CompanyDto {
    @NotNull(message = "corpId bo'sh bo'lmasligi kerak")
    private String corpName;

    @NotNull(message = "directorName bo'sh bo'lmasligi kerak")
    private String directorName;

    @NotNull(message = "street bo'sh bo'lmasligi kerak")
    private String street;

    @NotNull(message = "homeNumber bo'sh bo'lmasligi kerak")
    private String homeNumber;
}

  </code>
</pre>

<pre>
  <code>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
  </code>
</pre>
