package com.example.simpleformstopdf.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file, String dir);

    Stream<Path> loadAll();

    Path load(String filename, String dir);

    Resource loadAsResource(String filename, String dir);

    void delete(String filename, String dir);

    void deleteAll();
}
