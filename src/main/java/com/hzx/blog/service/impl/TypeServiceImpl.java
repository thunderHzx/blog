package com.hzx.blog.service.impl;

import com.hzx.blog.dao.TypeRepository;
import com.hzx.blog.model.Type;
import com.hzx.blog.service.TypeSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/27
 */
@Service
public class TypeServiceImpl implements TypeSerivce {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public  Page<Type> getPage(Pageable pageable) {

        Page<Type> page = typeRepository.findAll(pageable);

        return page;
    }

    @Transactional
    @Override
    public Type saveType(Type type) {

        Type queryType = typeRepository.findByName(type.getName());

        Type saveType = null;

        if(queryType != null){

            return saveType;

        }else {
            saveType = typeRepository.save(type);
        }

        return saveType;
    }

    @Override
    public Type editType(Type type) {

        Type type1 = typeRepository.saveAndFlush(type);

        return type1;
    }

    @Override
    public void deleteType(Type type) {

        typeRepository.delete(type);

    }

    @Override
    public List<Type> getAll() {
        return typeRepository.findAll();
    }

    @Override
    public Type getTypeById(Long id) {

        return typeRepository.getOne(id);
    }
}
