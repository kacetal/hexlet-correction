package io.hexlet.hexletcorrection.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    List<T> findAll();

    /**
     * Returns all instances of the type with the given IDs.
     *
     * @param ids
     * @return all entities by Ids
     */
    List<T> findAllByIds(Iterable<ID> ids);

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id
     * or {@literal Optional#empty()} if none found
     * or if {@code id} is {@literal null}.
     */
    Optional<T> findById(ID id);

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id can be {@literal null}.
     * @return {@literal true} if an entity with the given id exists,
     * {@literal false} otherwise.
     */
    boolean existsById(ID id);

    /**
     * Returns whether an entity with the given id does not exist.
     *
     * @param id can be {@literal null}.
     * @return {@literal true} if an entity with the given id does not exist,
     * {@literal false} otherwise.
     */
    boolean notExistsById(ID id);

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    long count();

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity can be {@literal null}.
     * @return the saved entity will never be {@literal null},
     * {@literal null} in case the given entity is {@literal null}.
     */
    T save(T entity);

    /**
     * Saves all given entities.
     *
     * @param entities can be {@literal null}.
     * @return the saved entity will never be {@literal null},
     * {@literal Collections#empty} in case the given entity is {@literal null}.
     */
    List<T> saveAll(Iterable<T> entities);

    /**
     * Deletes a given entity.
     * Delete nothing in case the given {@code id} is {@literal null}.
     *
     * @param entity can be {@literal null}.
     */
    void delete(T entity);

    /**
     * Deletes the entity with the given id.
     * Delete nothing in case the given {@code id} is {@literal null}.
     *
     * @param id can be {@literal null}.
     */
    void deleteById(ID id);
}
