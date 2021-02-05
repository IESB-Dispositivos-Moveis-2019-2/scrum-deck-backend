package br.iesb.model;

import java.io.Serializable;

public abstract class BaseEntity<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = -7688927080527892653L;

    public abstract T getId();

    public abstract void setId(T id);

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseEntity)) {
            return false;
        }
        final var that = (BaseEntity<?>) o;
        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public final int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public final String toString() {
        final var entidade = this.getClass().getSimpleName();
        return "Entidade [ " + entidade + " ] {" + "id=" + getId() + '}';
    }

}
