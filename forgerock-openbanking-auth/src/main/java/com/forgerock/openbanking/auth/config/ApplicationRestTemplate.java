/**
 * Copyright 2019 ForgeRock AS. All Rights Reserved
 *
 * Use of this code requires a commercial software license with ForgeRock AS.
 * or with one of its affiliates. All use shall be exclusively subject
 * to such license between the licensee and ForgeRock AS.
 */
package com.forgerock.openbanking.auth.config;

import com.forgerock.openbanking.auth.config.ssl.SslConfiguration;
import com.forgerock.openbanking.auth.exceptions.RestTemplateLoadingException;
import com.forgerock.openbanking.auth.exceptions.SslConfigurationFailure;
import com.forgerock.openbanking.auth.model.ForgeRockApplicationResponse;
import com.forgerock.openbanking.auth.services.CertificateLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.client.*;

import java.net.URI;

public class ApplicationRestTemplate extends RestTemplate {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationRestTemplateConfig.class);

    private boolean checkHostname;
    private boolean initialised = false;
    private SslConfiguration sslConfiguration;
    private CertificateLoader certificateLoader;
    //for debugging purposes
    private ForgeRockApplicationResponse application;

    public ApplicationRestTemplate(CertificateLoader certificateLoader,
                                   SslConfiguration sslConfiguration,
                                   ClientHttpRequestFactory requestFactory,
                                   boolean checkHostname) {
        super(requestFactory);
        this.checkHostname = checkHostname;
        this.certificateLoader = certificateLoader;
        this.sslConfiguration = sslConfiguration;
    }

    public void setApplication(ForgeRockApplicationResponse application) throws SslConfigurationFailure {
        this.application = application;
        HttpComponentsClientHttpRequestFactory factory =
                sslConfiguration.factory(application.getTransportKey().getKeyID(), checkHostname);
        setRequestFactory(factory);
        initialised = true;
    }

    protected <T> T doExecute(URI url, @Nullable HttpMethod method, @Nullable RequestCallback requestCallback,
                              @Nullable ResponseExtractor<T> responseExtractor) throws RestClientException {
        LOGGER.trace("RestTemplate->doExecute url {} and method {}", url, method);
        try {
            return super.doExecute(url, method, requestCallback, responseExtractor);
        } catch (RestClientException e) {
            if (e instanceof HttpClientErrorException) {
               if (((HttpClientErrorException) e).getStatusCode() == HttpStatus.FORBIDDEN ||
                      ((HttpClientErrorException) e).getStatusCode() == HttpStatus.UNAUTHORIZED) {
                    LOGGER.warn("Couldn't access the service, received 401 or 403. Let's try to reload our client certificate");
                    try {
                        setApplication(certificateLoader.refreshCurrentApplication());
                        return super.doExecute(url, method, requestCallback, responseExtractor);
                    } catch (RestTemplateLoadingException | SslConfigurationFailure e1) {
                        LOGGER.error("Couldn't refresh the application, we throw back the initial exception", e1);
                    }
                }
            }
            throw e;
        }
    }

}
