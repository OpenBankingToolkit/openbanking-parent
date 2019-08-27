/**
 * Copyright 2019 ForgeRock AS. All Rights Reserved
 *
 * Use of this code requires a commercial software license with ForgeRock AS.
 * or with one of its affiliates. All use shall be exclusively subject
 * to such license between the licensee and ForgeRock AS.
 */
package com.forgerock.openbanking.serialiser.nimbus;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.nimbusds.jwt.JWTClaimsSet;

import java.io.IOException;

public class JWTClaimsSetSerializer extends StdSerializer<JWTClaimsSet> {

    public JWTClaimsSetSerializer() {
        super(JWTClaimsSet.class);
    }

    @Override
    public void serialize(JWTClaimsSet claimsSet, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeObject(claimsSet.toJSONObject());
    }
}
