package com.example.flow.extension.service;

import com.example.flow.extension.entity.BlockedExtension;
import com.example.flow.extension.entity.DefaultBlockedExtension;
import com.example.flow.extension.exception.ExtensionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ExtensionServiceTest {

    @Autowired
    private ExtensionService extensionService;

    @BeforeEach
    public void beforeEach(){
        extensionService.addCustomExtension("abc");
        extensionService.addCustomExtension("def");
        extensionService.addDefaultExtension("bat");
        extensionService.addDefaultExtension("cmd");
    }

    @Test
    public void defaultExtensionClickTest(){
        extensionService.defaultExtensionClick("bat");
        List<DefaultBlockedExtension> defaultExtensionList = extensionService.getDefaultExtensionList();
        for (DefaultBlockedExtension defaultBlockedExtension : defaultExtensionList) {
            if(defaultBlockedExtension.getExtension().equals("bat")){
                Assertions.assertTrue(defaultBlockedExtension.getIsChecked());
            }else{
                Assertions.assertFalse(defaultBlockedExtension.getIsChecked());
            }
        }
        extensionService.defaultExtensionClick("bat");
        for (DefaultBlockedExtension defaultBlockedExtension : defaultExtensionList) {
            Assertions.assertFalse(defaultBlockedExtension.getIsChecked());
        }
    }

    @Test
    public void addDefaultExtensionTest(){
        extensionService.addDefaultExtension("com");
        extensionService.addDefaultExtension("EXE");
        List<DefaultBlockedExtension> defaultExtensionList = extensionService.getDefaultExtensionList();
        assertEquals(defaultExtensionList.size(), 4);

        for (DefaultBlockedExtension defaultBlockedExtension : defaultExtensionList) {
            Assertions.assertFalse(defaultBlockedExtension.getIsChecked());
            if(defaultBlockedExtension.getExtension().equals("EXE")){
                fail("소문자로 변환되지 않음.");
            }
        }
    }

    @Test
    public void addDefaultExtensionExceptionTest(){
        assertThrows(ExtensionException.class,()-> extensionService.addDefaultExtension("123456789012345678901"));
        assertThrows(ExtensionException.class,()-> extensionService.addDefaultExtension("cmd"));
        assertThrows(ExtensionException.class,()-> extensionService.addDefaultExtension("123한글"));
        assertThrows(ExtensionException.class,()-> extensionService.addDefaultExtension("123**"));

    }

    @Test
    public void deleteDefaultExtensionTest(){
        extensionService.deleteDefaultExtension("cmd");
        List<DefaultBlockedExtension> defaultExtensionList = extensionService.getDefaultExtensionList();
        assertEquals(defaultExtensionList.size(),1);
        extensionService.deleteDefaultExtension("bat");
        defaultExtensionList = extensionService.getDefaultExtensionList();
        assertEquals(defaultExtensionList.size(),0);
        assertThrows(ExtensionException.class,()-> extensionService.deleteDefaultExtension("bat"));
    }

    @Test
    public void addCustomExtensionTest() {
        extensionService.addCustomExtension("a");
        extensionService.addCustomExtension("B");
        List<BlockedExtension> extensionList = extensionService.getExtensionList();
        assertEquals(extensionList.size(), 4);

        for (BlockedExtension blockedExtension : extensionList) {
            if (blockedExtension.getExtension().equals("B")) {
                fail("소문자로 변환되지 않음.");

            }
            if (blockedExtension.getExtension().equals("b") || blockedExtension.getExtension().equals("a")
            ||blockedExtension.getExtension().equals("abc") || blockedExtension.getExtension().equals("def")) {
                continue;
            } else {
                fail("잘못된 파일확장자 저장됨 : " + blockedExtension.getExtension());
            }
        }
    }

    @Test
    public void addCustomExtensionMaxSizeTest(){
        for(int i = 0;i<198;i++){
            extensionService.addCustomExtension("a"+i);
        }
        assertThrows(ExtensionException.class,()-> extensionService.addCustomExtension("z"));
    }

    @Test
    public void addCustomExtensionDuplicateTest(){
        extensionService.addCustomExtension("a");
        assertThrows(ExtensionException.class,()-> extensionService.addCustomExtension("a"));
    }

    @Test
    public void addDefaultExtensionToCustomExtensionDuplicateTest(){
        extensionService.addCustomExtension("bat");
        List<BlockedExtension> extensionList = extensionService.getExtensionList();
        List<DefaultBlockedExtension> defaultExtensionList = extensionService.getDefaultExtensionList();
        assertEquals(extensionList.size(),2);
        assertEquals(defaultExtensionList.size(),2);
        for (DefaultBlockedExtension defaultBlockedExtension : defaultExtensionList) {
            if(defaultBlockedExtension.getExtension().equals("bat")){
                assertTrue(defaultBlockedExtension.getIsChecked());
            }
        }
        extensionService.addCustomExtension("bat");
        extensionList = extensionService.getExtensionList();
        defaultExtensionList = extensionService.getDefaultExtensionList();
        assertEquals(extensionList.size(),2);
        assertEquals(defaultExtensionList.size(),2);
        for (DefaultBlockedExtension defaultBlockedExtension : defaultExtensionList) {
            if(defaultBlockedExtension.getExtension().equals("bat")){
                assertFalse(defaultBlockedExtension.getIsChecked());
            }
        }
    }

    @Test
    public void deleteCustomExtensionTest(){
        extensionService.deleteCustomExtension("abc");
        List<BlockedExtension> extensionList = extensionService.getExtensionList();
        assertEquals(extensionList.size(),1);
        extensionService.deleteCustomExtension("def");
        extensionList = extensionService.getExtensionList();
        assertEquals(extensionList.size(),0);
    }

}
