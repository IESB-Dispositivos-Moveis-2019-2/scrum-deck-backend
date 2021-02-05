package br.iesb.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

@Getter
@RequiredArgsConstructor
public enum CoreMessageProperty implements IMessageProperty {

    API_ACCESS_FORBIDDEN("api.access-forbidden"),
    API_UNDENTIFIED_ERROR("api.unidentified-error"),
    API_RESOURCE_NOTFOUND("api.resource-notfound"),

    ;


    private final String key;

    private String[] args = {};

    public String key() {
        return key;
    }

    public IMessageProperty bind(String... pArgs) {
        this.args = ArrayUtils.isNotEmpty(pArgs) ? pArgs : null;
        return this;
    }
}
