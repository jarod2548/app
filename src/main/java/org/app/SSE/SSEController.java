package org.app.SSE;

import org.app.config.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sse")
public class SSEController {
    private final SSEService sseService;


    public SSEController(SSEService sseService) {
        this.sseService = sseService;
    }

    @GetMapping("/user/subscribe")
    public SseEmitter subscribe(@AuthenticationPrincipal UserPrincipal user){
        return sseService.subscribe(user.getId());
    }
}
