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
public interface TagService {


    Page<Tag> getPage(Pageable pageable);

    Tag saveType(Tag tag);

    Tag editType(Tag tag);

    void deleteType(Tag tag);

    List<Tag> getAll();

    List<Tag> listTag(String tagIds);

    List<Tag> listTopTag();

    Page<Tag> findTagsById(Long id, Pageable pageable);
}
