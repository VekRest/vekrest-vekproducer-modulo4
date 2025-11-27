package com.vekrest.vekproducer.vekproducer.integration.client;

import com.vekrest.vekproducer.vekproducer.integration.dto.request.VekSecurityLoginRequest;
import com.vekrest.vekproducer.vekproducer.integration.dto.response.VekSecurityLoginResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "VekSecurityIntegration",
        url = "${vekrest.veksecurity.api.url}/vekrest/veksecurity/v1"
)
public interface VekSecurityIntegrationWithFeign {
    @PostMapping("/login")
    VekSecurityLoginResponse login(@RequestBody VekSecurityLoginRequest request);
}