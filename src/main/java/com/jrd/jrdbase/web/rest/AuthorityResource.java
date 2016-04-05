package com.jrd.jrdbase.web.rest;

/**
 * Created by jakub on 05.04.16.
 */

import com.codahale.metrics.annotation.Timed;
import com.jrd.jrdbase.domain.Authority;
import com.jrd.jrdbase.repository.AuthorityRepository;
import com.jrd.jrdbase.web.rest.dto.AuthorityDTO;
import com.jrd.jrdbase.web.rest.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AuthorityResource {

    @Inject
    private AuthorityRepository authorityRepository;

    @RequestMapping(value = "/authorities",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<AuthorityDTO>> getAllAuthorities(Pageable pageable)
        throws URISyntaxException {

        Page<Authority> page = authorityRepository.findAll(pageable);
        List<AuthorityDTO> authorityDTOs = page.getContent().stream()
            .map(authority -> new AuthorityDTO(authority))
            .collect(Collectors.toList());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/authorities");
        return new ResponseEntity<>(authorityDTOs, headers, HttpStatus.OK);
    }
}
