package com.hzx.blog.service.impl;

import com.hzx.blog.dao.TagRepository;
import com.hzx.blog.dao.TypeRepository;
import com.hzx.blog.model.Tag;
import com.hzx.blog.model.Type;
import com.hzx.blog.service.TagSerivce;
import com.hzx.blog.service.TypeSerivce;
import com.hzx.blog.util.StringToListUtil;
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
public class TagServiceImpl implements TagSerivce {

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
}
