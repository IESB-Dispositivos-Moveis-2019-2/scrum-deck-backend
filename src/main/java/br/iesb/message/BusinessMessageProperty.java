package br.iesb.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

@Getter
@RequiredArgsConstructor
public enum BusinessMessageProperty implements IMessageProperty {

    ESTORIA_SPRINT_NAO_ENCONTRADA("estoria.sprint-nao-encontrada"),
    DESENVOLVEDOR_EMAIL_JA_CADASTRADO("desenvolvedor.email-ja-cadastrado"),
    VOTO_ESTORIA_NAO_ENCONTRADA("voto.estoria-nao-encontrada"),
    VOTO_DESENVOLVEDOR_NAO_ENCONTRADO("voto.desenvolvedor-nao-encontrado"),
    VOTO_ESTORIA_FECHADA("voto.estoria-fechada");

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
