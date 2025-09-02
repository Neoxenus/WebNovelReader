package com.neoxenus.webnovelreader.tag.service.impl;

import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.exceptions.EntityAlreadyInUseException;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import com.neoxenus.webnovelreader.tag.dto.TagDto;
import com.neoxenus.webnovelreader.tag.dto.request.TagRequest;
import com.neoxenus.webnovelreader.tag.entity.Tag;
import com.neoxenus.webnovelreader.tag.enums.TagType;
import com.neoxenus.webnovelreader.tag.mapper.TagMapper;
import com.neoxenus.webnovelreader.tag.repo.TagRepository;
import com.neoxenus.webnovelreader.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagServiceImpl implements TagService {

    private final TagRepository repository;

    private final TagMapper mapper;

    @Override
    public List<TagDto> getTagsByType(TagType type) {
        return mapper.toDto(repository.findTagByTagType(type));
    }

    @Override
    public TagDto getById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("No tag with an id: " + id)));
    }

    @Override
    public TagDto saveTag(TagRequest request) {
        Tag savedTag = repository.save(mapper.toTag(request, null));
        //todo: validation
        return mapper.toDto(savedTag);
    }

    @Override
    public TagDto updateTag(Long id, TagRequest request) {
        Optional<Tag> optionalTag = repository.findById(id);
        if(optionalTag.isPresent()) {
            Tag tagById = optionalTag.get();
            Tag updatedTag = mapper.toTag(request, tagById);
            return mapper.toDto(repository.save(updatedTag));
        } else {
            throw new NoSuchEntityException("No tag for this id: " + id);
        }
    }

    @Override
    public void delete(Long id, boolean force) {
        Optional<Tag> tag = repository.findById(id);
        if(tag.isPresent()){
            log.info("Deleting tag with id: {}", id);
            Tag toDelete = tag.get();
            if(!force && !toDelete.getBooks().isEmpty()) {
                throw new EntityAlreadyInUseException("Tag is used in some books, " +
                        "use force param if you commit to this deletion ");
            }
            if(force) {
                for(Book book: toDelete.getBooks()) {
                    book.getTags().remove(toDelete);
                }
            }
            repository.delete(toDelete);
            repository.flush();
        }
        else{
            log.error("No tag for this id: {}", id);
            throw new NoSuchEntityException("No tag for this id: " + id);
        }
    }
}
