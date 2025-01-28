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
      &lt;dependency&gt;
            &lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;
            &lt;artifactId&gt;spring-boot-starter-validation&lt;/artifactId&gt;
        &lt;/dependency&gt;
  </code>
</pre>


<pre>
  <code>
        /*
     * Mijozni tahrirlash
     * @param id
     * @param customerDto
     * @return ApiResponse
     * Bizga id va Customer tipida json Object beradu*/
    @PutMapping("/customers/{id}")
    public HttpEntity<ApiResponse> updateCustomer(@PathVariable int id, @Valid @RequestBody CustomerDto customerDto) {

        ApiResponse apiResponse = customerService.updateCustomer(id, customerDto);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/customer/{id}")
    public ApiResponse deleteCustomer(@PathVariable int id) {
        return customerService.deleteCustomer(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

  </code>
</pre>
