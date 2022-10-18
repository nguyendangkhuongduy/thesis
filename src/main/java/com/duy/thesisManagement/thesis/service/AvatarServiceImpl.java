package com.duy.thesisManagement.thesis.service;


import com.duy.thesisManagement.thesis.model.Avatar;
import com.duy.thesisManagement.thesis.model.User;
import com.duy.thesisManagement.thesis.repository.AvatarRepository;
import com.duy.thesisManagement.thesis.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;

    private final UserRepository userRepository;


    @Override
    public Avatar store(MultipartFile file) throws IOException {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
////        Optional<User> userOption = this.userRepository.findById(id);
//        Avatar image = new Avatar( fileName, file.getContentType(), file.getBytes());

//        return avatarRepository.save(image);
        return null;
    }

    @Override
    public Stream<Avatar> getAllFiles() {
        return avatarRepository.findAll().stream();
    }
}
