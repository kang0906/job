package com.example.flow.extension.service;

import com.example.flow.extension.entity.BlockedExtension;
import com.example.flow.extension.entity.DefaultBlockedExtension;
import com.example.flow.extension.exception.ExtensionErrorCode;
import com.example.flow.extension.exception.ExtensionException;
import com.example.flow.extension.repository.BlockedExtensionRepository;
import com.example.flow.extension.repository.DefaultBlockedExtensionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ExtensionService {

    private final BlockedExtensionRepository blockedExtensionRepository;
    private final DefaultBlockedExtensionRepository defaultBlockedExtensionRepository;

    public List<BlockedExtension> getExtensionList(){
        return blockedExtensionRepository.findAll();
    }
    public List<DefaultBlockedExtension> getDefaultExtensionList(){
       return defaultBlockedExtensionRepository.findAll();
    }

    @Transactional
    public String defaultExtensionClick(String extensionName){
        DefaultBlockedExtension extension = defaultBlockedExtensionRepository.findByExtension(extensionName)
                .orElseThrow(() -> new ExtensionException(ExtensionErrorCode.NO_SUCH_DEFAULT_EXTENSION));

        extension.changeStatus();

        return "success";
    }

    @Transactional
    public String addCustomExtension(String extensionName){
        extensionName = extensionName.toLowerCase();
        Optional<DefaultBlockedExtension> byExtension = defaultBlockedExtensionRepository.findByExtension(extensionName);
        if(byExtension.isPresent()){
            byExtension.get().changeStatus();
            return "success(Set DefaultExtension)";
        }

        checkExtension(extensionName);

        if(blockedExtensionRepository.count()>=200){
            throw new ExtensionException(ExtensionErrorCode.TOO_MUCH_EXTENSION);
        }

        Optional<BlockedExtension> extension = blockedExtensionRepository.findByExtension(extensionName);
        if(extension.isEmpty()){
            blockedExtensionRepository.save(new BlockedExtension(extensionName));
        }else{
            throw new ExtensionException(ExtensionErrorCode.DUPLICATED_EXTENSION);
        }

        return "success";
    }

    @Transactional
    public String deleteCustomExtension(String extensionName){
        extensionName = extensionName.toLowerCase();

        BlockedExtension extension = blockedExtensionRepository.findByExtension(extensionName)
                .orElseThrow(()-> new ExtensionException(ExtensionErrorCode.NO_SUCH_EXTENSION));
        blockedExtensionRepository.delete(extension);

        return "success";
    }

    @Transactional
    public void addDefaultExtension(String defaultExtension){
        defaultExtension = defaultExtension.toLowerCase();

        checkExtension(defaultExtension);

        Optional<DefaultBlockedExtension> extension = defaultBlockedExtensionRepository.findByExtension(defaultExtension);
        if(extension.isEmpty()){
            defaultBlockedExtensionRepository.save(new DefaultBlockedExtension(defaultExtension));
        }else{
            throw new ExtensionException(ExtensionErrorCode.DUPLICATED_EXTENSION);
        }
    }

    @Transactional
    public String deleteDefaultExtension(String defaultExtension){
        defaultExtension = defaultExtension.toLowerCase();

        DefaultBlockedExtension extension = defaultBlockedExtensionRepository.findByExtension(defaultExtension)
                .orElseThrow(()-> new ExtensionException(ExtensionErrorCode.NO_SUCH_EXTENSION));
        defaultBlockedExtensionRepository.delete(extension);

        return "success";
    }

    private void checkExtension(String extension){
        final String regex = "^[a-z0-9]*$";
        if(extension.length()>20){
            throw new ExtensionException(ExtensionErrorCode.EXTENSION_LENGTH_ERROR);
        }

        if(Pattern.matches(regex,extension)){
            return ;
        }else{
            // todo: 영문 변환 로직
            throw new ExtensionException(ExtensionErrorCode.EXTENSION_UNKNOWN);
        }
    }
}
