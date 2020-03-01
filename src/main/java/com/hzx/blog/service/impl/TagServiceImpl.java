package com.hzx.blog.service.impl;

import com.hzx.blog.dao.TagRepository;
import com.hzx.blog.model.Tag;
import com.hzx.blog.model.Type;
import com.hzx.blog.service.TagService;
import com.hzx.blog.util.StringToListUtil;
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
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public  Page<Tag> getPage(Pageable pageable) {

        Page<Tag> page = tagRepository.findAll(pageable);

        return page;
    }

    @Transactional
    @Override
    public Tag saveType(Tag tag) {

        Tag queryTag = tagRepository.findByName(tag.getName());

        Tag saveTag = null;

        if(queryTag != null){

            return saveTag;

        }else {
            saveTag = tagRepository.save(tag);
           // return saveTag;
        }

        return saveTag;
    }

    @Override
    public Tag editType(Tag tag) {

        Tag tag1 = tagRepository.saveAndFlush(tag);

        return tag1;
    }

    @Override
    public void deleteType(Tag tag) {

        tagRepository.delete(tag);

    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String tagIds) {
        return tagRepository.findAllById(StringToListUtil.convertToList(tagIds));
    }

    @Override
    public List<Tag> listTopTag() {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,6,sort);
        return tagRepository.listTopTag(pageable);
    }

    @Override
    public Page<Tag> findTagsById(Long id, Pageable pageable) {


        Specification querySpe = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();
                if(id != null && id != 0){
                    predicates.add(cb.equal(root.<Tag>get("tag").get("id"),
                            id));
                }

                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        };

        return tagRepository.findAll(querySpe,pageable);
    }
}
