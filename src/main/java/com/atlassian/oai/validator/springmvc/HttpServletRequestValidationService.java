package com.atlassian.oai.validator.springmvc;

import com.atlassian.oai.validator.model.Request;
import com.atlassian.oai.validator.report.ValidationReport;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HttpServletRequestValidationService {
    private final OpenApiValidationService delegate;

    public HttpServletRequestValidationService(final EncodedResource specUrlOrPayload) throws IOException {
        delegate = new OpenApiValidationService(specUrlOrPayload, new UrlPathHelper());
    }

    public ValidationReport validateRequest(final HttpServletRequest request) throws IOException {
        return delegate.validateRequest(delegate.buildRequest(request));
    }
}
