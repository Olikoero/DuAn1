/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.DAO;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author huanl
 */
public abstract class DuAnDAO<E,K> {
    abstract public void insert(E entity) throws SQLException;
    abstract public void update(E entity) throws SQLException;
    abstract public void delete(K key) throws SQLException;
    abstract public List<E> selectAll();
    abstract public E selectByID(K key);
    abstract protected List<E> selectBySql(String sql, Object...args);
}
