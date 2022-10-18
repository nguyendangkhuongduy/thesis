package com.duy.thesisManagement.thesis.service;


import com.duy.thesisManagement.thesis.model.Avatar;
import com.duy.thesisManagement.thesis.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;
public interface AvatarService {
    Avatar store(MultipartFile file) throws IOException;

    Stream<Avatar> getAllFiles();
}
