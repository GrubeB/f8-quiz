package pl.app.core.security.user_details;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class CustomDetails {
    private Map<String, String> tokens;
}
