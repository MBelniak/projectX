package com.projectX.projectX.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.projectX.projectX.domain.Party;
import com.projectX.projectX.domain.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomItemSerializer extends StdSerializer<User> {

    public CustomItemSerializer() {
        this(null);
    }

    public CustomItemSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", user.getId());
        jsonGenerator.writeStringField("email", user.getEmail());
        jsonGenerator.writeStringField("hash_password", user.getHash_password());
        jsonGenerator.writeStringField("first_name", user.getFirst_name());
        jsonGenerator.writeStringField("surname", user.getSurname());
        jsonGenerator.writeStringField("date_of_birth", user.getDate_of_birth().toString());
        jsonGenerator.writeArrayFieldStart("attended_parties");
        int[] partiesIds = new int[user.getAttended_parties().size()];
        List<Party> parties = new ArrayList<>(user.getAttended_parties());
        for (int i = 0; i < parties.size(); i++) {
            partiesIds[i] = parties.get(i).getId().intValue();
        }
        jsonGenerator.writeArray(partiesIds, 0, partiesIds.length);
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
