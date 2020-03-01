package com.hzx.blog.service;

import com.hzx.blog.model.Tag;
import com.hzx.blog.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 给我一瓶朗姆酒
 * 2020/2/27
 */
public interface TypeService {


    Page<Type> getPage(Pageable pageable);

    Type saveType(Type type);

    Type editType(Type type);

    void deleteType(Type type);

    List<Type> getAll();

    Type getTypeById(Long id);

    List<Type> listTopType();

    Page<Type> findTypesById(Long id, Pageable pageable);
}
