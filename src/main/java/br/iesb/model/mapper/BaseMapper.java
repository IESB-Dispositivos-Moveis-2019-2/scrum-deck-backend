package br.iesb.model.mapper;


import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Classe base para mapeamento <pre>{@code Entidade<E>}</pre> para <pre>{@code DTO<D>}</pre>.
 *
 * @param <E>
 * @param <D>
 */
public interface BaseMapper<E, D, R> {

    D toDto(E entity);

    E toEntity(D dto);
    E fromRegister(R dto);

    List<D> toDto(List<E> entities);

    List<D> toDto(Iterable<E> entities);

    List<E> toEntity(List<D> dtos);
    List<E> fromRegister(List<R> dtos);

    void fromDto(D dto, @MappingTarget E entity);

}
