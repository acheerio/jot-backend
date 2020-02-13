package jotbackend.controllers;

import jotbackend.classes.Attribute;
import jotbackend.repositories.AttributeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(allowedHeaders = "*")
@RequestMapping(path = "/attributes")
public class AttributeController {
    @Autowired
    private AttributeRepository attributeRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewContact (@RequestParam Integer userId,
                        @RequestParam String title,
                        @RequestParam String description) {
        Attribute newAttribute = new Attribute();
        newAttribute.setUserId(userId);
        newAttribute.setTitle(title);
        newAttribute.setDescription(description);
        attributeRepository.save(newAttribute);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Page<Attribute> getAllAttributesByUserId(@RequestParam Integer userId,
                                                              @RequestParam Integer pageNum,
                                                              @RequestParam Integer pageSize,
                                                              @RequestParam String sortField,
                                                              @RequestParam String sortDirection) {
        Pageable pageable = PageRequest.of(pageNum, pageSize,
                Sort.by(Sort.Direction.fromString(sortDirection), sortField));
        return attributeRepository.findByUserId(userId, pageable);
    }

}