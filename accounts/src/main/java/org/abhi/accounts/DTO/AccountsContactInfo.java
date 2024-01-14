package org.abhi.accounts.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "vegeta")   //This will search for vegeta tag in application file

// Record is changed to Class for Setter Methods
//public record AccountsContactInfo(String message, Map<String,String> contactDetails, List<String> onCallSupport) {
//}
@Getter
@Setter
public class AccountsContactInfo {

    private String message;
    private Map<String,String> contactDetails;
    private List<String> onCallSupport;
}