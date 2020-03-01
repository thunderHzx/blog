package com.hzx.blog.service.impl;

import com.hzx.blog.dao.TypeRepository;
import com.hzx.blog.model.Tag;
import com.hzx.blog.model.Type;
import com.hzx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/27
 */
@Service
public class TypeServiceImpl implements TypeService {

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

    @Override
    public List<Type> listTopType() {

        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");

        Pageable pageable =  PageRequest.of(0,6,sort);

        return typeRepository.listTopType(pageable);
    }

    @Override
    public Page<Type> findTypesById(Long id, Pageable pageable) {

        Specification querySpe = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();
                if(id != null && id != 0 ){
                    predicates.add(cb.equal(root.<Tag>get("type").get("id"),
                            id));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        };

        return typeRepository.findAll(querySpe,pageable);
    }
}
