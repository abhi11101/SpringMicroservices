package org.abhi.loans.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(value = "loans")
//public record LoanContactInfo(String message, Map<String,String> contactDetails, List<String> onCallSupport) {
//}
@Getter
@Setter
public class LoanContactInfo {

    private String message;
    private Map<String,String> contactDetails;
    private List<String> onCallSupport;
}