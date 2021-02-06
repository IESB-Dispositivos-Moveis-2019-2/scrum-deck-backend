package br.iesb.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

@Getter
@RequiredArgsConstructor
public enum BusinessMessageProperty implements IMessageProperty {

    ESTORIA_SPRINT_NAO_ENCONTRADA("estoria.sprint-nao-encontrada"),
    DESENVOLVEDOR_EMAIL_JA_CADASTRADO("desenvolvedor.email-ja-cadastrado");

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
