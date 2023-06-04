package pl.app;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {
    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/authorized")
    public ResponseEntity<String> authorized(Authentication authentication) {
        return ResponseEntity.ok("authorized");
    }
}
